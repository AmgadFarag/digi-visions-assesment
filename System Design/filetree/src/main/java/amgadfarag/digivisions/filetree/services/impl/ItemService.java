package amgadfarag.digivisions.filetree.services.impl;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import amgadfarag.digivisions.filetree.entities.FileBinary;
import amgadfarag.digivisions.filetree.entities.Item;
import amgadfarag.digivisions.filetree.entities.PermissionGroup;
import amgadfarag.digivisions.filetree.enums.ItemType;
import amgadfarag.digivisions.filetree.enums.PermissionGroupEnum;
import amgadfarag.digivisions.filetree.repositories.ItemRepository;
import amgadfarag.digivisions.filetree.services.interfaces.IItemService;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionGroupService;

@Service
@Component
public class ItemService implements IItemService {
    private Logger log = Logger.getLogger(ItemService.class.getName());

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private IPermissionGroupService permissionGroupService;
    
    @Override
    public Item createSpace(String spaceName) {
        log.info("check if spcae exists");
        Optional<Item> optionalItem = itemRepository.findByNameAndType(spaceName, ItemType.SPACE.toString().toLowerCase());
        Item space = optionalItem.isPresent()? optionalItem.get() : new Item();

        log.info("create permission group");
        PermissionGroup permissionGroup = permissionGroupService.create(PermissionGroupEnum.ADMIN.toString().toLowerCase());

        log.info("Fill Item");
        space.setName(spaceName);
        space.setPermissionGroup(permissionGroup);
        space.setType(ItemType.SPACE.toString().toLowerCase());

        log.info("save Item");
        itemRepository.save(space);
        return space;
    }

    @Override
    public Item createFolder(String spaceName, String folderName) {
        log.info("check if folder exists");
        Optional<Item> optionalFolder = itemRepository.findByNameAndType(folderName, ItemType.FOLDER.toString().toLowerCase());
        Item folder = optionalFolder.isPresent()? optionalFolder.get() : new Item();

        log.info("check if spcae exists");
        Optional<Item> optionalSpace = itemRepository.findByNameAndType(spaceName, ItemType.SPACE.toString().toLowerCase());
        Item space = optionalSpace.isPresent()? optionalSpace.get() : new Item();

        log.info("create permission group");
        PermissionGroup permissionGroup = permissionGroupService.create(PermissionGroupEnum.EDIT_ONLY.toString().toLowerCase());

        log.info("Fill Item");
        folder.setName(folderName);
        folder.setPermissionGroup(permissionGroup);
        folder.setType(ItemType.FOLDER.toString().toLowerCase());
        folder.setParent(space.getName()+"/");

        log.info("save Item");
        itemRepository.save(folder);
        return folder;
    }

    @Override
    public Item createFile(String folderName, String fileName, MultipartFile multipartFile) {
        log.info("check if file exists");
        Optional<Item> optionalFile = itemRepository.findByNameAndType(fileName, ItemType.FILE.toString().toLowerCase());
        Item file = optionalFile.isPresent()? optionalFile.get() : new Item();

        log.info("check if folder exists");
        Optional<Item> optionalFolder = itemRepository.findByNameAndType(folderName, ItemType.FOLDER.toString().toLowerCase());
        Item folder = optionalFolder.isPresent()? optionalFolder.get() : new Item();

        FileBinary fileBinary = new FileBinary();

        log.info("create permission group");
        PermissionGroup permissionGroup = permissionGroupService.create(PermissionGroupEnum.EDIT_ONLY.toString().toLowerCase());

        log.info("Fill Item");
        file.setName(fileName);
        file.setPermissionGroup(permissionGroup);
        file.setType(ItemType.FILE.toString().toLowerCase());
        file.setParent(folder.getParent()+"/"+folder.getName()+"/");

        fileBinary.setItem(file);
        try {
            fileBinary.setBinary((multipartFile != null)? multipartFile.getBytes() : null);
        } catch (Exception e) {
            log.log(Level.SEVERE ,"Error while saving "+fileName+". Error while getting file bytes. ", e);
            e.printStackTrace();
        }

        log.info("save Item");
        itemRepository.save(file);
        return file;
    }

    @Override
    public Item getByNameAndType(String name, ItemType type) {
        log.info("check if item exists");
        Optional<Item> optionalItem = itemRepository.findByNameAndType(name, type.toString().toLowerCase());
        if (optionalItem.isPresent()) {
            log.info(name + " found");
            return optionalItem.get();
        }else{
            log.info(name + " not found");
            return null;
        }
    }


    @Override
    public void deleteSpace(String spaceName) {
        Optional<Item> optionalItem = itemRepository.findByNameAndType(spaceName, ItemType.SPACE.toString().toLowerCase());
        if (optionalItem.isPresent() && optionalItem.get().getId() != null)
            itemRepository.deleteById(optionalItem.get().getId());
    }

    @Override
    public void deleteFolder(String folderName) {
        Optional<Item> optionalItem = itemRepository.findByNameAndType(folderName, ItemType.FOLDER.toString().toLowerCase());
        if (optionalItem.isPresent() && optionalItem.get().getId() != null)
            itemRepository.deleteById(optionalItem.get().getId());
    }

    @Override
    public void deleteFile(String fileName) {
        Optional<Item> optionalItem = itemRepository.findByNameAndType(fileName, ItemType.FILE.toString().toLowerCase());
        if (optionalItem.isPresent() && optionalItem.get().getId() != null)
            itemRepository.deleteById(optionalItem.get().getId());
    }
}

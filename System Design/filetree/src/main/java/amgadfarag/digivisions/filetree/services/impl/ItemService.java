package amgadfarag.digivisions.filetree.services.impl;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amgadfarag.digivisions.filetree.entities.File;
import amgadfarag.digivisions.filetree.entities.Item;
import amgadfarag.digivisions.filetree.entities.PermissionGroup;
import amgadfarag.digivisions.filetree.enums.ItemType;
import amgadfarag.digivisions.filetree.enums.PermissionGroupEnum;
import amgadfarag.digivisions.filetree.repositories.ItemRepository;
import amgadfarag.digivisions.filetree.services.interfaces.IItemService;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionGroupService;

@Service
public class ItemService implements IItemService {
    private Logger log = Logger.getLogger(ItemService.class.getName());

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private IPermissionGroupService permissionGroupService;
    
    @Override
    public Item createSpace(String spaceName){
        // check if folder exists
        Optional<Item> optionalItem = itemRepository.findByNameAndType(spaceName, ItemType.SPACE.toString().toLowerCase());
        Item space = optionalItem.isPresent()? optionalItem.get() : new Item();

        // create permission group
        PermissionGroup permissionGroup = permissionGroupService.create(PermissionGroupEnum.ADMIN.toString().toLowerCase());

        // Fill Item
        space.setName(spaceName);
        space.setPermissionGroup(permissionGroup);
        space.setType(ItemType.SPACE.toString().toLowerCase());

        // Save Item
        itemRepository.save(space);
        return space;
    }

    @Override
    public Item createFolder(String spaceName, String folderName) {
        // check if folder exists
        Optional<Item> optionalItem = itemRepository.findByNameAndType(folderName, ItemType.FOLDER.toString().toLowerCase());
        Item folder = optionalItem.isPresent()? optionalItem.get() : new Item();

        // create permission group
        PermissionGroup permissionGroup = permissionGroupService.create(PermissionGroupEnum.EDIT_ONLY.toString().toLowerCase());

        // Fill Item
        folder.setName(folderName);
        folder.setPermissionGroup(permissionGroup);
        folder.setType(ItemType.FOLDER.toString().toLowerCase());

        // Save Item
        itemRepository.save(folder);
        return folder;
    }

    @Override
    public Item createFile(String folderName, String fileName) {
        // check if file exists
        Optional<Item> optionalItem = itemRepository.findByNameAndType(fileName, ItemType.FILE.toString().toLowerCase());
        Item file = optionalItem.isPresent()? optionalItem.get() : new Item();

        File fileBinary = new File();

        // create permission group
        PermissionGroup permissionGroup = permissionGroupService.create(PermissionGroupEnum.EDIT_ONLY.toString().toLowerCase());

        // Fill Item
        file.setName(fileName);
        file.setPermissionGroup(permissionGroup);
        file.setType(ItemType.FILE.toString().toLowerCase());

        fileBinary.setItem(file);
        fileBinary.setBinary(null);

        // Save Item
        itemRepository.save(file);
        return file;
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

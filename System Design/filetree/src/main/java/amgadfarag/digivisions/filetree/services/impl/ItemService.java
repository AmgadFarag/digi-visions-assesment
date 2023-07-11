package amgadfarag.digivisions.filetree.services.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amgadfarag.digivisions.filetree.entities.File;
import amgadfarag.digivisions.filetree.entities.Item;
import amgadfarag.digivisions.filetree.entities.PermissionGroup;
import amgadfarag.digivisions.filetree.enums.ItemType;
import amgadfarag.digivisions.filetree.enums.PermissionGroupEnum;
import amgadfarag.digivisions.filetree.services.interfaces.IItemService;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionGroupService;

@Service
public class ItemService implements IItemService {
    private Logger log = Logger.getLogger(ItemService.class.getName());

    @Autowired
    private IPermissionGroupService permissionGroupService;
    
    @Override
    public Item createSpace(String spaceName){
        // create item
        Item space = new Item();

        // create permission group
        PermissionGroup permissionGroup = permissionGroupService.create(PermissionGroupEnum.ADMIN.toString().toLowerCase());

        // Fill Item
        space.setName(spaceName);
        space.setPermissionGroup(permissionGroup);
        space.setType(ItemType.SPACE.toString().toLowerCase());

        //TODO Save Item

        return space;
    }

    @Override
    public Item createFolder(String spaceName, String folderName) {
        //TODO check if folder exists

        // create item
        Item folder = new Item();

        // create permission group
        PermissionGroup permissionGroup = permissionGroupService.create(PermissionGroupEnum.EDIT_ONLY.toString().toLowerCase());

        // Fill Item
        folder.setName(folderName);
        folder.setPermissionGroup(permissionGroup);
        folder.setType(ItemType.FOLDER.toString().toLowerCase());

        //TODO Save Item

        return folder;
    }

    @Override
    public Item createFile(String folderName, String fileName) {
        //TODO check if file exists

        // create item
        Item file = new Item();
        File fileBinary = new File();

        // create permission group
        PermissionGroup permissionGroup = permissionGroupService.create(PermissionGroupEnum.EDIT_ONLY.toString().toLowerCase());

        // Fill Item
        file.setName(fileName);
        file.setPermissionGroup(permissionGroup);
        file.setType(ItemType.FILE.toString().toLowerCase());

        fileBinary.setItem(file);
        fileBinary.setBinary(null);

        //TODO Save Item

        return file;
    }


    @Override
    public void deleteSpace(String spaceName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSpace'");
    }

    @Override
    public void deleteFolder(String folderName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFolder'");
    }

    @Override
    public void deleteFile(String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFile'");
    }
}

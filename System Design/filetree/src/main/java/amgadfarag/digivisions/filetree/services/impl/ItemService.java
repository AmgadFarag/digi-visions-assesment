package amgadfarag.digivisions.filetree.services.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amgadfarag.digivisions.filetree.entities.Item;
import amgadfarag.digivisions.filetree.entities.Permission;
import amgadfarag.digivisions.filetree.entities.PermissionGroup;
import amgadfarag.digivisions.filetree.enums.PermissionEnum;
import amgadfarag.digivisions.filetree.services.interfaces.IItemService;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionGroupService;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionService;

@Service
public class ItemService implements IItemService {
    private Logger log = Logger.getLogger(ItemService.class.getName());

    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IPermissionGroupService permissionGroupService;
    
    public Item createSpace(String spaceName){
        // create item
        Item space = new Item();

        // create permission group
        PermissionGroup permissionGroup = permissionGroupService.create("admin");

        //TODO Fill Item

        return space;
    }
}

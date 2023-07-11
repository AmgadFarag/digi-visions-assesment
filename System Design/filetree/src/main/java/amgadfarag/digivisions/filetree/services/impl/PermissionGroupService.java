package amgadfarag.digivisions.filetree.services.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amgadfarag.digivisions.filetree.entities.Permission;
import amgadfarag.digivisions.filetree.entities.PermissionGroup;
import amgadfarag.digivisions.filetree.enums.PermissionEnum;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionGroupService;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionService;

@Service
public class PermissionGroupService implements IPermissionGroupService {
    private Logger log = Logger.getLogger(PermissionGroupService.class.getName());
    
    @Autowired
    private IPermissionService permissionService;

    public PermissionGroup create(String name) {
        //TODO check if group exists

        // create Permission Group
        PermissionGroup permissionGroup = new PermissionGroup();

        //TODO check if permissions exists

        // create permissions
        Permission viewPermission = permissionService.create(PermissionEnum.VIEW);
        Permission editPermission = permissionService.create(PermissionEnum.EDIT);

        //TODO Fill & Save PermissionGroup
        permissionGroup.setGroupName(name);


        //TODO Fill & Save Permissions
        viewPermission.setUserEmail("viewingUser@dummy.email");
        editPermission.setUserEmail("editingUser@dummy.email");
        switch(name) {
            case "admin": 
            viewPermission.setPermissionGroup(permissionGroup);
            viewPermission.setPermissionLevel("0");
            editPermission.setPermissionGroup(permissionGroup);
            editPermission.setPermissionLevel("0");
            break;
            case "edit-only": 
            editPermission.setPermissionGroup(permissionGroup);
            editPermission.setPermissionLevel("1");
            break;
            case "view-only": 
            viewPermission.setPermissionGroup(permissionGroup);
            viewPermission.setPermissionLevel("1");
            break;
            default:break;
        }
        

        return permissionGroup;
    }
}

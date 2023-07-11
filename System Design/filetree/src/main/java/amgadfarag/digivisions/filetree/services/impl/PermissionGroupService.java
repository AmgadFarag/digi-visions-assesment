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
        // create Permission Group
        PermissionGroup permissionGroup = new PermissionGroup();

        // create permissions
        Permission viewPermission = permissionService.create(PermissionEnum.VIEW);
        Permission editPermission = permissionService.create(PermissionEnum.EDIT);

        //TODO Fill PermissionGroup

        return permissionGroup;
    }
}

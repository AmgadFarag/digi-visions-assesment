package amgadfarag.digivisions.filetree.services.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import amgadfarag.digivisions.filetree.entities.Permission;
import amgadfarag.digivisions.filetree.enums.PermissionEnum;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionService;

@Service
public class PermissionService implements IPermissionService {
    private Logger log = Logger.getLogger(PermissionService.class.getName());

    @Override
    public Permission create(PermissionEnum permissionType) {
        //TODO check if permission eists

        // create permission
        Permission permission = new Permission();

        //TODO Fill Permission
        
        return permission;
    }
    
}

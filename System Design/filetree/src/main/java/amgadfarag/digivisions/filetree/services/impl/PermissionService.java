package amgadfarag.digivisions.filetree.services.impl;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amgadfarag.digivisions.filetree.entities.Permission;
import amgadfarag.digivisions.filetree.enums.PermissionEnum;
import amgadfarag.digivisions.filetree.repositories.PermissionRepository;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionService;

@Service
public class PermissionService implements IPermissionService {
    private Logger log = Logger.getLogger(PermissionService.class.getName());

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission create(PermissionEnum permissionType) {
        // check if permission eists
        Optional<Permission> optionalPermission = permissionRepository.findByPermissionLevel(permissionType.toString().toLowerCase());
        Permission permission = optionalPermission.isPresent()? optionalPermission.get() : new Permission();

        // Fill Permission
        if (permissionType.equals(PermissionEnum.VIEW)) {
            permission.setUserEmail("viewingUser@dummy.email");
            permission.setPermissionLevel("view");
        }

        if (permissionType.equals(PermissionEnum.EDIT)) {
            permission.setUserEmail("editingUser@dummy.email");
            permission.setPermissionLevel("edit");
        }
        
        // Save Permission
        permissionRepository.save(permission);

        return permission;
    }

    @Override
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }
    
}

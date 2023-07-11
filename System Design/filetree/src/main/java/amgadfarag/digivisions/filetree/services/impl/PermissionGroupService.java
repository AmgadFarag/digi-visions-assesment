package amgadfarag.digivisions.filetree.services.impl;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amgadfarag.digivisions.filetree.entities.Permission;
import amgadfarag.digivisions.filetree.entities.PermissionGroup;
import amgadfarag.digivisions.filetree.enums.PermissionEnum;
import amgadfarag.digivisions.filetree.repositories.PermissionGroupRepository;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionGroupService;
import amgadfarag.digivisions.filetree.services.interfaces.IPermissionService;

@Service
public class PermissionGroupService implements IPermissionGroupService {
    private Logger log = Logger.getLogger(PermissionGroupService.class.getName());
    
    @Autowired
    private PermissionGroupRepository groupRepository;
    @Autowired
    private IPermissionService permissionService;

    public PermissionGroup create(String name) {
        // check if group exists
        Optional<PermissionGroup> optionalItem = groupRepository.findByGroupName(name);
        PermissionGroup permissionGroup = optionalItem.isPresent()? optionalItem.get() : new PermissionGroup();

        // create permissions
        Permission viewPermission = permissionService.create(PermissionEnum.VIEW);
        Permission editPermission = permissionService.create(PermissionEnum.EDIT);

        // Fill & Save PermissionGroup
        permissionGroup.setGroupName(name);
        groupRepository.save(permissionGroup);

        // Fill & Save Permissions
        switch(name) {
            case "admin": 
            viewPermission.setPermissionGroup(permissionGroup);
            editPermission.setPermissionGroup(permissionGroup);
            break;
            case "edit-only": 
            editPermission.setPermissionGroup(permissionGroup);
            break;
            case "view-only": 
            viewPermission.setPermissionGroup(permissionGroup);
            break;
            default:break;
        }

        permissionService.save(viewPermission);
        permissionService.save(editPermission);
        return permissionGroup;
    }
}

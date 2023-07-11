package amgadfarag.digivisions.filetree.services.interfaces;

import amgadfarag.digivisions.filetree.entities.Permission;
import amgadfarag.digivisions.filetree.enums.PermissionEnum;

public interface IPermissionService {
    
    public Permission create(PermissionEnum permissionType);
    
}

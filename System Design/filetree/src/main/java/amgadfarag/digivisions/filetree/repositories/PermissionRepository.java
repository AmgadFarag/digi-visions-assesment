package amgadfarag.digivisions.filetree.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amgadfarag.digivisions.filetree.entities.Permission;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
    Optional<Permission> findByPermissionLevel(String permissionLevel);
    
}

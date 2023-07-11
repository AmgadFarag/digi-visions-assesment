package amgadfarag.digivisions.filetree.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amgadfarag.digivisions.filetree.entities.PermissionGroup;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long>{
    Optional<PermissionGroup> findByGroupName(String groupName);
}

package amgadfarag.digivisions.filetree.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amgadfarag.digivisions.filetree.entities.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    
}

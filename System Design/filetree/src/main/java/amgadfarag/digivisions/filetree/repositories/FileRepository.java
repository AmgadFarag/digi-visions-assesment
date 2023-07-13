package amgadfarag.digivisions.filetree.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amgadfarag.digivisions.filetree.entities.File;
import amgadfarag.digivisions.filetree.entities.Item;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByItem(Item item);
    
}

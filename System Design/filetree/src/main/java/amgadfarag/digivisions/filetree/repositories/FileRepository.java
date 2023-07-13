package amgadfarag.digivisions.filetree.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amgadfarag.digivisions.filetree.entities.FileBinary;
import amgadfarag.digivisions.filetree.entities.Item;

@Repository
public interface FileRepository extends JpaRepository<FileBinary, Long> {
    Optional<FileBinary> findByItem(Item item);
    
}

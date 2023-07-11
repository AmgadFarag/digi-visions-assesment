package amgadfarag.digivisions.filetree.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amgadfarag.digivisions.filetree.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    Optional<Item> findByNameAndType(String name, String type);
    
}

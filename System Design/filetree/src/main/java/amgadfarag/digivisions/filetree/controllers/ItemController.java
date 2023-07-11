package amgadfarag.digivisions.filetree.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import amgadfarag.digivisions.filetree.services.interfaces.IItemService;

@RestController("/api/item")
public class ItemController {
    private Logger log = Logger.getLogger(ItemController.class.getName());

    @Autowired
    private IItemService spaceService;

    @GetMapping("/create-space")
    private ResponseEntity createSpace() {
        spaceService.createSpace("stc-assessments");

        return ResponseEntity.ok().build();
    }
    
}

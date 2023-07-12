package amgadfarag.digivisions.filetree.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import amgadfarag.digivisions.filetree.services.interfaces.IItemService;

@RestController("/api/item")
public class ItemController {
    private Logger log = Logger.getLogger(ItemController.class.getName());

    @Autowired
    private IItemService itemService;

    @GetMapping("/create-space")
    private ResponseEntity createSpace() {
        try{
            itemService.createSpace("stc-assessments");
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    

    @GetMapping("/create-folder")
    private ResponseEntity createFolder() {
        try{
            itemService.createFolder("stc-assessments", "backend");
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @PostMapping("/create-file")
    private ResponseEntity createFile(@RequestBody MultipartFile file) {
        try{
            itemService.createFile("backend", "assessment.pdf", file);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    
}

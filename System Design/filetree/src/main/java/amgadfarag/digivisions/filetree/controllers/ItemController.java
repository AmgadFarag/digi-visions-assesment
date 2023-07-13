package amgadfarag.digivisions.filetree.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.http.multipart.UploadedMultipartFile;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import amgadfarag.digivisions.filetree.entities.Item;
import amgadfarag.digivisions.filetree.enums.ItemType;
import amgadfarag.digivisions.filetree.services.interfaces.IItemService;

@RestController("/api/item")
public class ItemController {
    private Logger log = Logger.getLogger(ItemController.class.getName());

    @Autowired
    private IItemService itemService;

    @GetMapping("/create-space")
    private ResponseEntity createSpace(String spaceName) {
        if (spaceName == null || spaceName.equalsIgnoreCase("")) {
            spaceName = "stc-assessments";
        }
        try{
            itemService.createSpace(spaceName);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    

    @GetMapping("/create-folder")
    private ResponseEntity createFolder(String spaceName, String folderName) {
        if (folderName == null || folderName.equalsIgnoreCase("")) {
            folderName = "backend";
        }
        if (spaceName == null || spaceName.equalsIgnoreCase("")) {
            spaceName = "stc-assessments";
        }
        try{
            itemService.createFolder(spaceName, folderName);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @PostMapping("/create-file")
    private ResponseEntity createFile(String folderName, String fileName, @RequestBody MultipartFile file) {
        if (folderName == null || folderName.equalsIgnoreCase("")) {
            folderName = "backend";
        }
        if (fileName == null || fileName.equalsIgnoreCase("")) {
            fileName = "assessment.pdf";
        }
        if (file == null){
            file = loadTempAssessmentPath(fileName);
        }
        try{
            itemService.createFile(folderName, fileName, file);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @GetMapping("/view")
    public ResponseEntity<Item> viewFile(String fileName) {
        if (fileName == null || fileName.equalsIgnoreCase("")) {
            fileName = "assessment.pdf";
        }
        Item file = itemService.getByNameAndType(fileName, ItemType.FILE);
        log.info("Returning " + file!=null?file.getName():"null");
        return ResponseEntity.ok().body(file);
    }

    private MultipartFile loadTempAssessmentPath(String fileName) {
        try {
            byte[] bytes = new ClassPathResource(fileName).getContentAsByteArray();
            return new UploadedMultipartFile(bytes, "application/pdf", fileName, "/"+fileName);
        } catch (Exception e){
            log.log(Level.SEVERE, "Error reading "+fileName, e);
            return null;
        }
    }
    
}

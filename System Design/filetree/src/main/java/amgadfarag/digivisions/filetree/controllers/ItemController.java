package amgadfarag.digivisions.filetree.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.http.multipart.UploadedMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import amgadfarag.digivisions.filetree.entities.Item;
import amgadfarag.digivisions.filetree.enums.ItemType;
import amgadfarag.digivisions.filetree.services.interfaces.IItemService;

@RestController
@RequestMapping("/api/item")
@Component
public class ItemController {
    private Logger log = Logger.getLogger(ItemController.class.getName());

    @Autowired
    private IItemService itemService;

    @GetMapping("/create-space")
    private ResponseEntity createSpace(@RequestParam(name="spaceName", required=false) String spaceName) {
        log.info("Request recieved");
        if (spaceName == null || spaceName.equalsIgnoreCase("")) {
            spaceName = "stc-assessments";
        }
        try{
            itemService.createSpace(spaceName);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    

    @GetMapping("/create-folder")
    private ResponseEntity createFolder(@RequestParam(name="spaceName", required=false) String spaceName,
                                        @RequestParam(name="folderName", required=false) String folderName) {
        log.info("Request recieved");
        if (folderName == null || folderName.equalsIgnoreCase("")) {
            folderName = "backend";
        }
        if (spaceName == null || spaceName.equalsIgnoreCase("")) {
            spaceName = "stc-assessments";
        }
        try{
            itemService.createFolder(spaceName, folderName);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @PostMapping("/create-file")
    private ResponseEntity createFile(@RequestParam(name="folderName", required=false) String folderName,
                                    @RequestParam(name="fileName", required=false) String fileName, @RequestBody MultipartFile file) {
        log.info("Request recieved");
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
            return ResponseEntity.ok().body("Success");
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @GetMapping("/view")
    public ResponseEntity<Item> viewFile(@RequestParam(name="fileName", required=false) String fileName) {
        log.info("Request recieved");
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

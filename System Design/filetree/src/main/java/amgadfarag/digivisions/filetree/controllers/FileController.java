package amgadfarag.digivisions.filetree.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import amgadfarag.digivisions.filetree.services.interfaces.IFileService;

@RestController("/api/file")
public class FileController {
    private Logger log = Logger.getLogger(FileController.class.getName());

    @Autowired
    private IFileService fileService;

    @GetMapping("/download")
    public ResponseEntity<MultipartFile> download(String fileName) {
        if (fileName == null || fileName.equalsIgnoreCase("")) {
            fileName = "assessment.pdf";
        }
        MultipartFile file = fileService.downloadFile(fileName);
        log.info("Returning " + file!=null?file.getName():"null");
        return ResponseEntity.ok().body(file);
    }
    
}

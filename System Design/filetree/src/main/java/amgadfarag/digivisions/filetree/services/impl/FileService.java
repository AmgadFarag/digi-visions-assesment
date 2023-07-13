package amgadfarag.digivisions.filetree.services.impl;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Optional;
import java.util.logging.Logger;

// import org.springframework.mock.web.MockMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.http.multipart.UploadedMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import amgadfarag.digivisions.filetree.entities.FileBinary;
import amgadfarag.digivisions.filetree.entities.Item;
import amgadfarag.digivisions.filetree.enums.ItemType;
import amgadfarag.digivisions.filetree.repositories.FileRepository;
import amgadfarag.digivisions.filetree.services.interfaces.IFileService;
import amgadfarag.digivisions.filetree.services.interfaces.IItemService;

@Service
@Component
public class FileService implements IFileService{
    private Logger log = Logger.getLogger(FileService.class.getName());

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private IItemService itemService;

    @Override
    public MultipartFile downloadFile(String fileName) {
        MultipartFile file = null;

        log.info("checking if "+fileName+" exists");
        Item item = itemService.getByNameAndType(fileName, ItemType.FILE);
        if (item != null) {
            Optional<FileBinary> binaryFile = fileRepository.findByItem(item);
            if (binaryFile.isPresent()) {
                FileNameMap fileNameMap = URLConnection.getFileNameMap();
                String mimeType = fileNameMap.getContentTypeFor(fileName);
                file = new UploadedMultipartFile(binaryFile.get().getBinary(), mimeType,fileName, item.getParent()+fileName);
                // file = new MockMultipartFile(fileName, item.getParent()+fileName, binaryFile.get().getBinary());
            }
        }
        return file;
    }


    
}

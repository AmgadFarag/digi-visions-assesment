package amgadfarag.digivisions.filetree.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    public MultipartFile downloadFile(String fileName);
}

package amgadfarag.digivisions.filetree.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import amgadfarag.digivisions.filetree.entities.Item;

public interface IItemService {
    
    public Item createSpace(String spaceName);
    public Item createFolder(String spaceName, String folderName);
    public Item createFile(String folderName, String fileName, MultipartFile file);


    public void deleteSpace(String spaceName);
    public void deleteFolder(String folderName);
    public void deleteFile(String fileName);

}

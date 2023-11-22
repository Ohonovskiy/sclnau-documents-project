package sclnau.documents.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sclnau.documents.entity.Document;

import java.io.File;

@Service
public class DocumentsFileManager {
    @Value("${upload.path}")
    String filepath;
    public String saveFileAndGetFilePath(MultipartFile multipartFile, String fileName) throws Exception {

        multipartFile.transferTo(new File(filepath+fileName));

        return filepath+fileName;
    }

    public void deleteDocument(Document document){
        File file = new File(document.getPath());
        file.delete();
    }
}

package sclnau.documents.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

@Service
public class FileNameGenerator {

    public String generateHashedFileName(MultipartFile file, Long docId) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        try (InputStream is = new FileInputStream(String.valueOf(file))) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        byte[] digest = md.digest();
        StringBuilder result = new StringBuilder();
        for (byte b : digest) {
            result.append(String.format("%02x", b));
        }

        return result.append(docId).append('.').append(FilenameUtils.getExtension(file.getOriginalFilename())).toString();
    }
}

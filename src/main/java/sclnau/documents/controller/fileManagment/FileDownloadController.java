package sclnau.documents.controller.fileManagment;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileDownloadController {
    @GetMapping("/user/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam("path") String filePath) {
        Path file = Paths.get(filePath);

        Resource resource;
        try {
            resource = new UrlResource(file.toUri());
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }

        String contentType = "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

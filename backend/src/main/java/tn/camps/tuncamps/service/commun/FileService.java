package tn.camps.tuncamps.service.commun;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

   public Resource downloadFile(String fileId, String path) throws IOException;

    String uploadFile(MultipartFile multipartFile, String path) throws IOException;
}

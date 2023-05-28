package tn.camps.tuncamps.service.commun.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.camps.tuncamps.service.commun.FileService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private static final String UPLOAD_FOLDER = "/path/to/upload/folder/";


    @Autowired
    public String uploadFile(MultipartFile multipartFile, String path) {
        // Generate a unique file id
        String fileId = UUID.randomUUID().toString();

        // Create the folder if it doesn't exist
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Save the file to the folder with the generated file ID
        File file = new File(folder, fileId);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
        // Return the generated file ID
        return fileId;
    }

    @Autowired
    public Resource downloadFile(String fileId, String path) throws IOException {
        // Construct the file path based on the file ID
        File file = new File(path + "/" + fileId);

        // Check if the file exists
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + fileId);
        }

        // Create a Resource object from the file
        Resource resource = new UrlResource(file.toURI());

        // Check if the resource exists and is readable
        if (!resource.exists() || !resource.isReadable()) {
            throw new IOException("Unable to read the file: " + fileId);
        }

        return resource;
    }



    private String generateUniqueFileName(String fileId, String originalFileName) {
        // Generate a unique file name using the file ID and original file name
        return fileId + "_" + originalFileName;
    }

    private File generateFilePath(String fileId) {
        // Generate the file path based on the file ID
        return new File(UPLOAD_FOLDER, fileId);
    }

    private String createFolder(String folderName) {
        String folderPath = "/path/to/folder/" + folderName; // Specify the desired folder path
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folderPath;
    }


    private void saveFile(File sourceFile, String folderPath) throws IOException {
        String fileName = sourceFile.getName();
        String destinationPath = folderPath + "/" + fileName;

        Path sourcePath = sourceFile.toPath();
        Path destination = Paths.get(destinationPath);

        Files.copy(sourcePath, destination, StandardCopyOption.REPLACE_EXISTING);
    }
}


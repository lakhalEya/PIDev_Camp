package tn.camps.tuncamps.controller.ecomerce;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class Uploadcontrollor {

	private String Examens_directory = System.getProperty("user.dir") + "/assets/images";

	@PostMapping("/imageupload")
	public ResponseEntity<String> upload(@RequestParam("files") MultipartFile Files) throws IOException {

		String filename = StringUtils.cleanPath(Files.getOriginalFilename());
		Path fileStorage = get(Examens_directory, filename).toAbsolutePath().normalize();
		copy(Files.getInputStream(), fileStorage, REPLACE_EXISTING);
		this.Examens_directory = "";
		return ResponseEntity.ok().body(filename);
	}

}
package tn.camps.tuncamps.controller.forum;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.camps.tuncamps.persistence.entity.enumeration.CommunityCategory;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.service.forum.ICommunitySpace;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/community-spaces")
public class CommunitySpaceController {

        @Autowired
        private ICommunitySpace iCommunitySpace;

        @PostMapping("/add")
        @ResponseBody
        public CommunitySpace createCommunitySpace(@RequestBody CommunitySpace communitySpace) {
                        return iCommunitySpace.createCommunitySpace(communitySpace);
        }

        @PostMapping("/upload")
        @ResponseBody
        public CommunitySpace createCommunitySpace(@RequestParam("file") MultipartFile file) throws  IOException {
               CommunitySpace communitySpace = new CommunitySpace();
                try {
                        communitySpace.setFileData(file.getBytes());
                        communitySpace.setFileName(file.getOriginalFilename());
                        communitySpace.setFileType(file.getContentType());
                        return iCommunitySpace.uploadCommunitySpace(file);
                } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                }
        }
        @PostMapping("/merge")
        @ResponseBody
        public ResponseEntity<CommunitySpace> uploadAndCreateCommunitySpace(
                @RequestParam("file") MultipartFile file,
                @RequestParam("title") String title,
                @RequestParam("description") String description,
                @RequestParam("category") CommunityCategory category
        ) {
                try {
                        byte[] fileData = file.getBytes();
                        String fileName = file.getOriginalFilename();
                        String fileType = file.getContentType();

                        CommunitySpace communitySpace = new CommunitySpace();
                        communitySpace.setTitle(title);
                        communitySpace.setDescription(description);
                        communitySpace.setCategory(category);
                        communitySpace.setFileData(fileData);
                        communitySpace.setFileName(fileName);
                        communitySpace.setFileType(fileType);

                        CommunitySpace savedCommunitySpace = iCommunitySpace.createCommunitySpace(communitySpace);
                        return ResponseEntity.status(HttpStatus.CREATED).body(savedCommunitySpace);
                } catch (IOException e) {
                        e.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }


          @PutMapping("/update")
        public ResponseEntity<CommunitySpace> updateDocument(@PathVariable int id, @RequestBody CommunitySpace communitySpace) {
                CommunitySpace newCommunitySpace = iCommunitySpace.updateCommunitySpace(id, communitySpace);
                return new ResponseEntity<>( HttpStatus.OK);
        }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteCommunitySpace(@PathVariable("id") int id) {
                iCommunitySpace.deleteCommunitySpace(id);
                return new ResponseEntity<>(HttpStatus.OK);
        }

        @GetMapping("/show")
        public List<CommunitySpace> listCommunitySpace()
        {
                return iCommunitySpace.retrieveAllCommunitySpace();
        }
        @GetMapping("/show/{id}")
        public CommunitySpace retreiveCommunitySpaceById(@PathVariable("id") int id){
                return  iCommunitySpace.retrieveCommunitySpace(id);
        }

        @GetMapping("/search")
        public List<CommunitySpace> searchCommunitySpaces(@RequestParam("keyword") String keyword) {
                return iCommunitySpace.searchCommunitySpaces(keyword);
        }

}

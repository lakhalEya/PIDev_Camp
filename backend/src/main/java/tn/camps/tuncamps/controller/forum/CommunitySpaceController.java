package tn.camps.tuncamps.controller.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.persistence.entity.forum.Post;
import tn.camps.tuncamps.service.forum.ICommunitySpace;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/community-spaces")
public class CommunitySpaceController {

        @Autowired
        private ICommunitySpace iCommunitySpace;

        @PostMapping("/add")
        @ResponseBody
        public CommunitySpace createCommunitySpace(@RequestBody CommunitySpace communitySpace) {
                        return iCommunitySpace.createCommunitySpace(communitySpace);
        }
//        @GetMapping("/{id}")
//        public ResponseEntity<CommunitySpace> getCommunitySpace(@PathVariable int id) {
//            return iCommunitySpace.ok(communitySpace);
//        }
//
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


//
//        @PutMapping("/{id}")
//        public ResponseEntity<CommunitySpace> updateCommunitySpace(@PathVariable int id, @RequestBody CommunitySpace communitySpace) {
//            CommunitySpace updatedCommunitySpace = communitySpaceService.updateCommunitySpace(id, communitySpace);
//            return ResponseEntity.ok(updatedCommunitySpace);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<Void> deleteCommunitySpace(@PathVariable int id) {
//            communitySpaceService.deleteCommunitySpace(id);
//            return ResponseEntity.noContent().build();
//        }
}

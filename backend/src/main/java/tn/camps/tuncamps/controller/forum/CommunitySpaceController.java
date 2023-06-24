package tn.camps.tuncamps.controller.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.service.forum.ICommunitySpace;

import java.util.List;

@RestController
@RequestMapping("/api/community-spaces")
public class CommunitySpaceController {

        @Autowired
        private ICommunitySpace iCommunitySpace;


//        @GetMapping("/{id}")
//        public ResponseEntity<CommunitySpace> getCommunitySpace(@PathVariable int id) {
//            return iCommunitySpace.ok(communitySpace);
//        }
//
        @PostMapping("/add")
        @ResponseBody
        public CommunitySpace createCommunitySpace(@RequestBody CommunitySpace communitySpace) {
           return iCommunitySpace.createCommunitySpace(communitySpace);
        }

          @PutMapping("/update")
        public ResponseEntity<CommunitySpace> updateDocument(@RequestBody CommunitySpace communitySpace) {
                CommunitySpace newCommunitySpace = iCommunitySpace.updateCommunitySpace(communitySpace);
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

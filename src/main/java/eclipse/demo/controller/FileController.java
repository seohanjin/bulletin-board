package eclipse.demo.controller;

import eclipse.demo.domain.Files;
import eclipse.demo.domain.Member;
import eclipse.demo.service.FilesService;
import eclipse.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

    @Autowired
    FilesService filesService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private MemberService memberService;


    @PostMapping("/board/api/new")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file){
        try{
            Files uploadFile = filesService.store(file);

            return ResponseEntity.ok().body("/image/" + uploadFile.getId());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/image/{fileId}")
    public ResponseEntity<?> saveFile(@AuthenticationPrincipal Member member, @PathVariable Long fileId){
        try{
            Files uploadFile = filesService.load(fileId);
            Resource resource = resourceLoader.getResource("file:" + uploadFile.getFilePath());
            return ResponseEntity.ok().body(resource);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/profile/{profileId}")
    public ResponseEntity<?> findFile(@AuthenticationPrincipal Member member){
        try{
            Long memberId = member.getId();
            Member findMember = memberService.findOne(memberId);

            Resource resource = resourceLoader.getResource("file:" + findMember.getFiles().getFilePath());
            return ResponseEntity.ok().body(resource);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }



    private final Path rootLocation;

    public FileController(String uploadPath) {
        this.rootLocation = Paths.get(uploadPath);
    }


}

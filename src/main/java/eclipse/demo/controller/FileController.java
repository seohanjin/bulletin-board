package eclipse.demo.controller;

import eclipse.demo.domain.Files;
import eclipse.demo.domain.Member;
import eclipse.demo.dto.MemberDto;
import eclipse.demo.service.FilesService;
import eclipse.demo.service.MemberService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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



    @PostMapping("/upload")
    public String uploadFile(@RequestPart MultipartFile files) throws IOException {


        Files file = new Files();

        String sourceFileName = files.getOriginalFilename();
        System.out.println("sourFileName>>" + sourceFileName);

        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        System.out.println("sourceFileNameExtension>>" + sourceFileNameExtension);

        FilenameUtils.removeExtension(sourceFileName);


        File destinationFile;
        String destinationFileName;
        String fileUrl = "D:\\Spring\\demo\\src\\main\\resources\\static\\img\\";

        do {
            // 막 알수없는 파일이름으로 변환
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
        }while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        file.setFilename(destinationFileName);
        file.setFileOriName(sourceFileName);
        file.setFileUrl(fileUrl);
        filesService.save(file);

        return "redirect:/upload";
    }

    @GetMapping("/upload")
    public String downFile(Model model){
        Files file = filesService.findFile(1L);
        System.out.println("filename>>" + file.getFilename());
        model.addAttribute("file", file);

        return "board/getFile";
    }

    @PostMapping("/board/api/new")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file){
        try{
            Files uploadFile = filesService.store(file);
            System.out.println("PostMapping완료>>");
            return ResponseEntity.ok().body("/image/" + uploadFile.getId());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/image/{fileId}")
    public ResponseEntity<?> saveFile(@AuthenticationPrincipal Member member, @PathVariable Long fileId){
        System.out.println("GetMapping>>>");
        try{


            Files uploadFile = filesService.load(fileId);
            Resource resource = resourceLoader.getResource("file:" + uploadFile.getFilePath());
            System.out.println("resource>>" + resource);
            return ResponseEntity.ok().body(resource);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/file/profile")
    public String profile_image(@AuthenticationPrincipal Member member, @RequestParam("file") MultipartFile file, Model model){

        try{
            Long memberId = member.getId();
            Member findMember = memberService.findOne(memberId);
//            String username = SecurityContextHolder.getContext().getAuthentication().getName();
//            Member findMember = memberService.findByName(username);
            filesService.upload_image(file, findMember);
//
//            System.out.println("PostMapping완료>>");
//            Files findFile = filesService.load(uploadFile.getId());
//            Resource resource = resourceLoader.getResource("file:" + findFile.getFilePath());
//            System.out.println("file:>>" + resource);
//            System.out.println("filePath:>>:" + member.getFiles().getFilePath());

            model.addAttribute("form", new MemberDto());
            model.addAttribute("memberForm", findMember);

            return "members/profile";
//            return "redirect:/image_update";
        }catch (Exception e){
            e.printStackTrace();
            return "members/profile";
        }
    }

    @GetMapping("/image_update")
    public String download_file(@AuthenticationPrincipal Member member, Model model){
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Member findMember = memberService.findByName(username);
        Long memberId = member.getId();
        Member findMember = memberService.findOne(memberId);
        String filePath = findMember.getFiles().getFilePath();
        System.out.println(findMember.getFiles().getFilePath());
        model.addAttribute("form", new MemberDto());
        model.addAttribute("memberForm", findMember);
        model.addAttribute("imagePath", findMember.getFiles().getFilePath());

        return "members/profile";
    }



    private final Path rootLocation;

    public FileController(String uploadPath) {
        this.rootLocation = Paths.get(uploadPath);
    }


}

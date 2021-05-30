package eclipse.demo.api;

import eclipse.demo.domain.Member;
import eclipse.demo.dto.MemberDto;
import eclipse.demo.service.FilesService;
import eclipse.demo.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileApiController {
    private final MemberService memberService;
    private final FilesService filesService;
    private final Path rootLocation;

    public  FileApiController(MemberService memberService, FilesService filesService, String uploadPath) {
        this.memberService = memberService;
        this.filesService = filesService;
        this.rootLocation = Paths.get(uploadPath);
    }

    // 개인 프로필 사진 변경 시
    @PostMapping("/profile/upload")
    public String profile_image(@AuthenticationPrincipal Member member, @RequestParam("file") MultipartFile file){

        Long memberId = member.getId();
        Member findMember = memberService.findOne(memberId);

        // 현재 존재하는 사진이 존재한다면 디렉토리에서 지운다.
        try{
            if (findMember.getFiles() != null){
                File delete_file = new File(rootLocation+"\\profile\\"+findMember.getFiles().getFileOriName());
                delete_file.delete();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            filesService.upload_image(file, findMember);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}

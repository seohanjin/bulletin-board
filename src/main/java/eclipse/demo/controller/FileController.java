package eclipse.demo.controller;

import eclipse.demo.domain.Files;
import eclipse.demo.service.FilesService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    FilesService filesService;

    @PostMapping("/upload")
    public String uploadFile(@RequestPart MultipartFile files) throws IOException {
        Files file = new Files();

        String sourceFileName = files.getOriginalFilename();
        System.out.println(">>originalFilename" + sourceFileName);

        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        System.out.println(">>s>>" + sourceFileNameExtension);

        String removeFilExtension = FilenameUtils.removeExtension(sourceFileName);
        System.out.println(removeFilExtension);


        File destinationFile;
        String destinationFileName;
        String fileUrl = "D:\\Spring\\demo\\src\\main\\resources\\static\\images\\";

        do {
            // 막 알수없는 파일이름으로 변환
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
            System.out.println(">>destinationFile>>" + destinationFile);
        }while (destinationFile.exists());

        boolean mkdir = destinationFile.getParentFile().mkdirs();
        System.out.println(">>getParent>>" + mkdir);
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

}

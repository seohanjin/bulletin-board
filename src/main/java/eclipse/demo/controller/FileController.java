package eclipse.demo.controller;

import eclipse.demo.domain.Files;
import eclipse.demo.service.FilesService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.FilenameFilter;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    FilesService filesService;

    @PostMapping("/upload")
    public String uploadFile(@RequestPart MultipartFile file) throws IOException {
        filesService.uploadFile(file);
        return "redirect:/";
    }

}

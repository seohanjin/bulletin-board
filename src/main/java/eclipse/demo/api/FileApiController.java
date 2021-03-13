package eclipse.demo.api;

import eclipse.demo.domain.Files;
import eclipse.demo.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileApiController {

    @Autowired
    private FilesService filesService;

    @Autowired
    ResourceLoader resourceLoader;


}

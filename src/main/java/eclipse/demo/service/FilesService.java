package eclipse.demo.service;

import eclipse.demo.domain.Files;
import eclipse.demo.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FilesService {

    @Autowired
    FilesRepository filesRepository;

    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File("D:\\"+file.getOriginalFilename()));
    }
}

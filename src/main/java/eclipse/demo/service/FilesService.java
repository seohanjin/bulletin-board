package eclipse.demo.service;

import eclipse.demo.domain.Files;
import eclipse.demo.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class FilesService {

    @Autowired
    FilesRepository filesRepository;

    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File("D:\\"+file.getOriginalFilename()));
    }

    @Transactional
    public void save(Files files){
        Files f = new Files();
        f.setFilename(files.getFilename());
        f.setFileOriName(files.getFileOriName());
        f.setFileUrl(files.getFileUrl());

        filesRepository.save(f);
    }

    public Files findFile(Long id){
        Files files = filesRepository.findById(id).orElse(null);
        return files;
    }
}

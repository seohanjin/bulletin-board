package eclipse.demo.service;

import eclipse.demo.domain.Files;
import eclipse.demo.repository.FilesRepository;
import org.apache.commons.fileupload.UploadContext;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FilesService {

    @Autowired
    FilesRepository filesRepository;

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

//    public UploadContext store(MultipartFile file) throws IOException{
//        try {
//            if (file.isEmpty()){
//                throw new Exception("Failed to store empty file" + file.getOriginalFilename());
//            }
//
//            FileUtils.f
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

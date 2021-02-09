package eclipse.demo.service;

import eclipse.demo.domain.Files;
import eclipse.demo.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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

    private final Path rootLocation;

    public FilesService(String uploadPath){
        this.rootLocation =  Paths.get(uploadPath);
        System.out.println("rootLocation>>" + rootLocation.toString());
    }

    @Transactional
    public Files store(MultipartFile file) throws Exception{

        try{
            if (file.isEmpty()){
                throw new Exception("Failed to store empty file" + file.getOriginalFilename());
            }

            String saveFileName = fileSave(rootLocation.toString(), file);
            Files saveFile = new Files();
            saveFile.setFilename(file.getOriginalFilename());
            saveFile.setFileOriName(saveFileName);
            saveFile.setContentType(file.getContentType());
            saveFile.setSize(file.getResource().contentLength());
            saveFile.setFilePath(rootLocation.toString().replace(File.separatorChar, '/') + '/' + saveFileName);
            filesRepository.save(saveFile);
            return saveFile;

        }catch (IOException e){
            throw new Exception("Failed to store file" + file.getOriginalFilename(), e);
        }
    }

    public Files load(Long fileId){
        return filesRepository.findById(fileId).get();
    }

    @Transactional
    public String fileSave(String rootLocation, MultipartFile file) throws IOException{
        File uploadDir = new File(rootLocation);

        if (!uploadDir.exists()){
            uploadDir.mkdirs();
        }

        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid.toString() + file.getOriginalFilename();
        File saveFile = new File(rootLocation, saveFileName);
        FileCopyUtils.copy(file.getBytes(), saveFile);

        return saveFileName;
    }


}

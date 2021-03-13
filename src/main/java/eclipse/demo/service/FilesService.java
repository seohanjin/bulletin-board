package eclipse.demo.service;

import eclipse.demo.domain.Files;
import eclipse.demo.domain.Member;
import eclipse.demo.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    @Autowired
    private MemberService memberService;

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
        this.rootLocation = Paths.get(uploadPath);
        System.out.println("rootLocation>>" + rootLocation.toString());
    }

    @Transactional
    public Files store(MultipartFile file) throws Exception{

        System.out.println("rootLocation>>" + rootLocation);
        try{
            if (file.isEmpty()){
                throw new Exception("Failed to store empty file" + file.getOriginalFilename());
            }

            // 1. 절대 경로 2. file 을 넘겨준다.(fileName을 바꾸기 위해)
            String saveFileName = fileSave(rootLocation.toString(), file);
            System.out.println("location.toString>>" + rootLocation.toString());
            System.out.println("saveFileName>>" + saveFileName);

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
        System.out.println("uploadDir>>" + uploadDir);

        if (!uploadDir.exists()){
            uploadDir.mkdirs();
        }

        UUID uuid = UUID.randomUUID();
        System.out.println("uuid>>"+ uuid);
        System.out.println("uuid.toStirng()>>" + uuid.toString());

        String saveFileName = uuid.toString() + file.getOriginalFilename();
        System.out.println("file.getOriginalFilename>>" + file.getOriginalFilename());
        System.out.println("uuid + file.getOriginameName>>" + saveFileName);

        File saveFile = new File(rootLocation, saveFileName);
        System.out.println("saveFile>>" + saveFile);

        FileCopyUtils.copy(file.getBytes(), saveFile);

        return saveFileName;
    }


    @Transactional
    public Files upload_image(MultipartFile file, Member member) throws Exception {
        System.out.println("rootLocation>>" + rootLocation);
        try{
            if (file.isEmpty()){
                throw new Exception("Failed to store empty file" + file.getOriginalFilename());
            }

            // 1. 절대 경로 2. file 을 넘겨준다.(fileName을 바꾸기 위해)
            String saveFileName = fileSave(rootLocation.toString(), file);
            System.out.println("location.toString>>" + rootLocation.toString());
            System.out.println("saveFileName>>" + saveFileName);

            Files saveFile = new Files();
            saveFile.setFilename(file.getOriginalFilename());
            saveFile.setFileOriName(saveFileName);
            saveFile.setContentType(file.getContentType());
            saveFile.setSize(file.getResource().contentLength());
            saveFile.setFilePath(rootLocation.toString().replace(File.separatorChar, '/') + '/' + saveFileName);
            filesRepository.save(saveFile);
            member.setFiles(saveFile);
            return saveFile;

        }catch (IOException e){
            throw new Exception("Failed to store file" + file.getOriginalFilename(), e);
        }
    }
}

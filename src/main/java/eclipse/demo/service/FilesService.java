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

    private final Path rootLocation;

    public FilesService(String uploadPath){
        this.rootLocation = Paths.get(uploadPath);
    }

    @Transactional
    public void save(Files files){
        Files f = new Files();
        f.setFilename(files.getFilename());
        f.setFileOriName(files.getFileOriName());
        f.setFileUrl(files.getFileUrl());

        filesRepository.save(f);
    }

    @Transactional
    public void delete_files(Files files){
        filesRepository.delete(files);
    }

    public Files findFile(Long id){
        Files files = filesRepository.findById(id).orElse(null);
        return files;
    }


    @Transactional
    public Files store(MultipartFile file) throws Exception{

        try{
            if (file.isEmpty()){
                throw new Exception("Failed to store empty file" + file.getOriginalFilename());
            }

            // 1. 절대 경로 2. file 을 넘겨준다.(fileName을 바꾸기 위해)
            String saveFileName = fileSave(rootLocation.toString(), file);

            Files saveFile = new Files();
            saveFile.setFilename(saveFileName);
            saveFile.setFileOriName(file.getOriginalFilename());
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

//      원하는 디렉토리에 파일이 존재하는지 확인하기 위하여 File 객체 생성
        File uploadDir = new File(rootLocation);

        if (!uploadDir.exists()){
            uploadDir.mkdirs();
        }

        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid.toString() + file.getOriginalFilename();

//      rootLocation 폴더 경로의 saveFileName 이라는 파일에 대한 File 객체를 생성한다.
        File saveFile = new File(rootLocation, saveFileName);

//      실제 경로에 파일을 복사(저장)한다.
        FileCopyUtils.copy(file.getBytes(), saveFile);

        return saveFileName;
    }


    @Transactional
    public void upload_image(MultipartFile file, Member member) throws Exception {
        try{
            if (file.isEmpty()){
                throw new Exception("Failed to store empty file" + file.getOriginalFilename());
            }
            String dir = rootLocation.toString()+"/profile";

            File uploadDir = new File(dir);
            if (!uploadDir.exists()){
                uploadDir.mkdirs();
            }

            UUID uuid = UUID.randomUUID();
            String saveFileName = uuid.toString()+".jpg";

            File saveFile = new File(dir, saveFileName);

            FileCopyUtils.copy(file.getBytes(), saveFile);

            member.setUserProfile(saveFileName);


        }catch (IOException e){
            throw new Exception("Failed to store file" + file.getOriginalFilename(), e);
        }
    }
}

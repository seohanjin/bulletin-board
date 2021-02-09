package eclipse.demo.api;

import eclipse.demo.domain.Board;
import eclipse.demo.service.BoardService;
import lombok.Data;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.nio.file.Files;

@RestController
public class BoardApiController {

    @Autowired
    BoardService boardService;


    @Data
    static class RequestBoardDto{
        private String title;
        private String content;
    }

    @Data
    static class ResponseBoarDto{
        private Long id;

        public ResponseBoarDto(Long id){
            this.id = id;
        }
    }
}

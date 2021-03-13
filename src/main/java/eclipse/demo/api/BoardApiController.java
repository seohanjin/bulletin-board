package eclipse.demo.api;

import eclipse.demo.service.BoardService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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

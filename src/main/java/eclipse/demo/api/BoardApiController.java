package eclipse.demo.api;

import eclipse.demo.domain.Board;
import eclipse.demo.service.BoardService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

    @Autowired
    BoardService boardService;

    @PostMapping("/board/api/new")
    public ResponseBoarDto save(@RequestBody Board board){

        Long boardId = boardService.saveBoard(board);

        return new ResponseBoarDto(boardId);
    }

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

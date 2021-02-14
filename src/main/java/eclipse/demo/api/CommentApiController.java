package eclipse.demo.api;

import eclipse.demo.domain.Comment;
import eclipse.demo.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentApiController {

    @Autowired
    CommentService commentService;

    @GetMapping("/alarm")
    public List<CommentDto> getComment(){
        List<Comment> allComment = commentService.findAllComment();

        List<CommentDto> collect = allComment.stream().map(m -> new CommentDto( m.getId(), m.getLevel(),m.getContent())).collect(Collectors.toList());

        return collect;
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class CommentDto{
        private Long id;
        private int level;
        private String content;
    }
}

package eclipse.demo.api;

import eclipse.demo.domain.Comment;
import eclipse.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    CommentService commentService;

    @GetMapping("/alarm")
    public List<Comment> getComment(){

        List<Comment> findComment = commentService.findAllComment();

        return findComment;
    }
}

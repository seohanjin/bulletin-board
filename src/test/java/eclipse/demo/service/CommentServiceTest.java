package eclipse.demo.service;

import eclipse.demo.domain.Comment;
import eclipse.demo.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class CommentServiceTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;

    @Test
    @Transactional
    public void saveComment(){


//        commentService.ssave("seo");
//        commentService.ssave("park");
//
//        Comment one = commentService.findOne(1L);
//        Comment two = commentService.findOne(2L);
//
//
//        System.out.println(">>" + one.getContent());
//        System.out.println(">>" + one.getCommentGroup());
//        System.out.println(">>" + one.getLevel());
//
//        System.out.println("2" + two.getContent());
//        System.out.println("2" + two.getCommentGroup());

    }

}
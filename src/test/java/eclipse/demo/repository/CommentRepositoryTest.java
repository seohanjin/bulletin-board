package eclipse.demo.repository;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired private CommentRepository commentRepository;
    @Autowired private BoardService boardService;
    @Autowired private CommentService commentService;


    @Test
    public void 댓글(){
//        Board board = new Board("제목1", "내용1");
//        boardService.saveBoard(board);
//        Board board1 = new Board("제목2", "내용2");
//        boardService.saveBoard(board1);

//        Comment comment = new Comment(board, "댓글1");
//        comment.setCommentGroup(1);
//        commentRepository.save(comment);
        Comment comment = new Comment();
        comment.setContent("댓글11");
        comment.setCommentGroup(1);
        commentRepository.save(comment);

        Comment comment1 = commentRepository.findById(1L).orElse(null);
        System.out.println("aaa>>" + comment1.getContent());
        System.out.println("aaa>>" + comment1.getCommentGroup());
//        Comment commentGroup = commentRepository.findCommentGroup();
//        System.out.println(commentGroup);
    }

    @Test
    public void findBoardGroup(){
        Comment comment = new Comment();
        comment.setContent("내용1");
        comment.setCommentGroup(1);


        Optional<Integer> commentGroup = commentRepository.findCommentGroup();


        commentRepository.save(comment);

        Comment findComment = commentRepository.findById(1L).orElse(null);

        System.out.println("commentGrouping>>" + commentGroup);
        System.out.println("content>>" + findComment.getContent());
        System.out.println("commentGroup>>" + findComment.getCommentGroup());
    }



}
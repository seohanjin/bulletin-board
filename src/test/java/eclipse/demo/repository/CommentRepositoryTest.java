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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired private CommentRepository commentRepository;
    @Autowired private BoardService boardService;
    @Autowired private CommentService commentService;


    @Test
    public void 댓글(){
        Board board = new Board("제목1", "내용1");
        boardService.saveBoard(board);
        Board board1 = new Board("제목2", "내용2");
        boardService.saveBoard(board1);

        Comment comment = new Comment(board, "댓글1");
        Comment comment1 = new Comment(board, "댓글2");
        Comment comment2 = new Comment(board1, "댓글3");
        commentRepository.save(comment);
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        List<Comment> comments = commentRepository.findComment(board.getId());

        for (Comment comment3 : comments) {
            System.out.println("Board의 제목>>" + comment3.getBoard().getTitle());
            System.out.println("Board의 내용>>" + comment3.getBoard().getContent());
            System.out.println("Comment내용>>" + comment3.getContent());
        }


    }

}
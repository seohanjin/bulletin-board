package eclipse.demo;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.repository.CommentRepository;
import eclipse.demo.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
public class CommentTest {

    @PersistenceContext
    EntityManager em;

    @Autowired private CommentRepository commentRepository;
    @Autowired private BoardService boardService;


    @Test
    public void testComment(){
        Board board = new Board("제목1", "내용1");
        em.persist(board);

        Board board1 = new Board("제목2", "내용2");
        em.persist(board1);

        Comment comment = new Comment(board, "댓글1");
        em.persist(comment);
        Comment comment1 = new Comment(board, "댓글2");
        em.persist(comment1);


        List<Comment> comments = commentRepository.findCommentAll(board.getId());

        for (Comment comment2 : comments) {
            comment2.getBoard().getTitle();
        }


    }


}

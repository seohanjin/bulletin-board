package eclipse.demo.service;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired private BoardService boardService;
    @Autowired private CommentService commentService;

    @Test
    void unreadMessage() {
        Board board = new Board("타이틀", "내용");
        boardService.saveBoard(board);
        commentService.save(board, "댓글");

        int i = notificationService.unreadMessage();
        System.out.println(">>" + i);
    }
}
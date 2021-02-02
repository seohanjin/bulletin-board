package eclipse.demo.repository;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.BoardLike;
import eclipse.demo.service.BoardLikeService;
import eclipse.demo.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardLikeRepositoryTest {

    @Autowired
    private BoardLikeService boardLikeService;
    @Autowired
    private BoardService boardService;

    @Test
    public void 게시글아이디로좋아요찾기(){
        Long boardId1 = boardService.saveBoard(new Board("게시글1", "내용1"));
        Long boardId2 = boardService.saveBoard(new Board("게시글1", "내용1"));


        Board board1 = boardService.findOne(boardId1);

        boardLikeService.save(new BoardLike(1, board1));
        boardLikeService.save(new BoardLike(1, board1));
        boardLikeService.save(new BoardLike(1, board1));
        boardLikeService.save(new BoardLike(1, board1));
        boardLikeService.save(new BoardLike(1, board1));


        BoardLike boardLikeServiceAllById = boardLikeService.findAllById(boardId2);
        System.out.println(">>>>" + boardLikeServiceAllById);

    }

}
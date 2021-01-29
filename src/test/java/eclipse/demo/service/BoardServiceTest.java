package eclipse.demo.service;

import eclipse.demo.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

//    @Test
//    public void findBoardByIdDesc(){
//        List<Board> all = boardService.findAllByIdDesc();
//        for (Board board : all) {
//            System.out.println("board-->" + board.getId());
//        }
//    }

}
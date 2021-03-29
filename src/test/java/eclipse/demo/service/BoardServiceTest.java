package eclipse.demo.service;

import eclipse.demo.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    @Transactional
    public void 페이징처리(){

        for (int i = 0; i < 5; i++){
            Board board = new Board("제목"+i, "내용"+i);
            boardService.saveBoard(board);
        }

//        Page<Board> boards = boardService.boardPage();
//        assertThat(boards.getSize()).isEqualTo(3);



    }
}
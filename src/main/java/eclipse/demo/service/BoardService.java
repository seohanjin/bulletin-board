package eclipse.demo.service;

import eclipse.demo.domain.Board;
import eclipse.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 게시글 저장
    @Transactional
    public Long saveBoard(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    // 게시글 수정
    @Transactional
    public void updateBoard(Long boardId, String title, String content) {
        Board board = boardRepository.findById(boardId).orElse(null);
        board.changeBoard(title, content);
        boardRepository.save(board);
    }

    public Board findOne(Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        return board.orElse(null);
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(Board board) {
        boardRepository.delete(board);
    }

    //전체 게시글
    public List<Board> findAll() {
        return boardRepository.findAll();
    }


    // 조회수 up
    @Transactional
    public void upView(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        board.upViewCnt(board);
    }



    public Page<Board> getBoardList(Pageable pageable) {

        return boardRepository.findAllByOrderByIdDesc(pageable);
    }

}

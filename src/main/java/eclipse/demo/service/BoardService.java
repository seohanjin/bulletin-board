package eclipse.demo.service;

import eclipse.demo.domain.Board;
import eclipse.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

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

    public Page<Board> getBoardList(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0  : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "id"));
//
//        System.out.println("page번호 >>" + page);
//        System.out.println("현 페이지======>>" + pageable.getPageNumber());
//        String page1 = (pageable.getPageNumber() == 0) ? "안녕하세요" : "0이아닙니다." ;
//        System.out.println("page111>>>>> " + page1);
//        System.out.println("페이지넘김=====================");
        return boardRepository.findAll(pageable);
    }

}

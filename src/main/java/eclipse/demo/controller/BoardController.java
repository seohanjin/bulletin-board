package eclipse.demo.controller;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.BoardLike;
import eclipse.demo.domain.Comment;
import eclipse.demo.domain.ReComment;
import eclipse.demo.dto.BoardDto;
import eclipse.demo.dto.CommentDto;
import eclipse.demo.service.BoardLikeService;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final BoardLikeService boardLikeService;

    @GetMapping("/board/new")
    public String createForm(Model model) {

        model.addAttribute("boardForm", new BoardDto());
        return "board/boardForm";
    }

    @PostMapping("/board/new")
    public String createBoard(BoardDto boardDto) {
        Board board = new Board(boardDto.getTitle(), boardDto.getContent());
        boardService.saveBoard(board);
        return "redirect:/";
    }

    @GetMapping("/board/list")
    public String boardList(@PageableDefault Pageable pageable, Model model) {
        Page<Board> list = boardService.getBoardList(pageable);
        model.addAttribute("list", list);

        return "board/boardList";
    }

    @GetMapping("/board/{boardId}/detail")
    public String boardDetail(@PathVariable("boardId") Long boardId, Model model) {
        Board board = boardService.findOne(boardId);
        // 댓글
        List<Comment> comments = commentService.findJoinComment(boardId);
        //대댓글
        List<ReComment> reComments = commentService.ReFindAll();

        BoardLike boardLike = boardLikeService.findAllById(board.getId());
        if (boardLike == null || boardLike.getStatus() == 0) {
            System.out.println("boardliek..." + boardLike);
            model.addAttribute("boardLike", 0);
        } else {
            model.addAttribute("boardLike", 1);
        }

        // 게시물 클릭시 조회수Up
        boardService.upView(board.getId());

        BoardDto boardDto = new BoardDto(board.getId(), board.getTitle(), board.getContent(), board.getViewCnt());
        model.addAttribute("reAll", reComments);
        model.addAttribute("comments", comments);
        model.addAttribute("form", new CommentDto());
        model.addAttribute("boardDto", boardDto);
        return "board/boardDetail";
    }

    @GetMapping("/board/{editId}/edit")
    public String boardEdit(@PathVariable("editId") Long editId, Model model) {
        Board board = boardService.findOne(editId);
        BoardDto editForm = new BoardDto(board.getId(), board.getTitle(), board.getContent(), board.getViewCnt());

        model.addAttribute("form", editForm);
        return "board/boardUpdateForm";
    }

    @PostMapping("/board/{editId}/edit")
    public String boardUpdate(@PathVariable Long editId, @ModelAttribute("form") BoardDto form) {

        boardService.updateBoard(editId, form.getTitle(), form.getContent());
        return "redirect:/";
    }

    @GetMapping("/board/{deleteId}/delete")
    public String boardDelete(@PathVariable Long deleteId) {
        Board board = boardService.findOne(deleteId);
        boardService.deleteBoard(board);

        return "redirect:/";
    }

}

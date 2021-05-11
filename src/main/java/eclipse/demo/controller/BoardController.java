package eclipse.demo.controller;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.BoardLike;
import eclipse.demo.domain.Comment;
import eclipse.demo.domain.Member;
import eclipse.demo.dto.BoardDto;
import eclipse.demo.dto.CommentDto;
import eclipse.demo.repository.CommentRepository;
import eclipse.demo.repository.MemberRepository;
import eclipse.demo.service.BoardLikeService;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
import eclipse.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/board/new")
    public String createForm(Model model) {

        model.addAttribute("boardForm", new BoardDto());
        return "board/boardForm";
    }

    @PostMapping("/board/new")
    public String createBoard(@AuthenticationPrincipal Member member, BoardDto boardDto){

        String username = member.getEmail();
        Member findMember = memberRepository.findByEmail(username);
        Board board1 = new Board(findMember, boardDto.getTitle(), boardDto.getContent());
        boardService.saveBoard(board1);

        return "redirect:/";
    }

    @GetMapping("/board/list")
    public String boardList(Pageable pageable, Model model) {
        Page<Board> boards = boardService.getBoardList(pageable);

        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getPageable().getPageNumber() + 4, boards.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("list", boards);

        return "board/boardList";
    }

    @GetMapping("/board/{boardId}/detail")
    public String boardDetail(@AuthenticationPrincipal Member member, @PathVariable("boardId") Long boardId, Model model) {
        Member findMember = memberService.findOne(member.getId());
        Board board = boardService.findOne(boardId);

        List<Comment> commentAll = commentRepository.findCommentAll(board.getId());

        BoardLike boardLike = boardLikeService.findLike(findMember.getId(), board.getId());
        Long likeCount = boardLikeService.boardCount(boardId);

        if (boardLike == null || boardLike.getStatus() == 0) {
            System.out.println("boardliek..." + boardLike);
            model.addAttribute("boardLike", 0);
        } else {
            model.addAttribute("boardLike", 1);
        }

        // 게시물 클릭시 조회수Up
        boardService.upView(board.getId());

        BoardDto boardDto = new BoardDto(board.getId(), board.getTitle(), board.getContent(), board.getViewCnt());

        model.addAttribute("likeCount", likeCount);
        model.addAttribute("sortComment", commentAll);
        model.addAttribute("form", new CommentDto());
        model.addAttribute("boardDto", boardDto);
        return "board/boardDetail";
    }

    @GetMapping("/board/{editId}/edit")
    public String boardEdit(@PathVariable("editId") Long editId, Model model) {
        Board board = boardService.findOne(editId);
        BoardDto editForm = new BoardDto(board.getId(), board.getTitle(), board.getContent(), board.getViewCnt());

        model.addAttribute("board", board);
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

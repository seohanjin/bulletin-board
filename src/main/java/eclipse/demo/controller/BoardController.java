package eclipse.demo.controller;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.Member;
import eclipse.demo.dto.BoardDto;
import eclipse.demo.dto.MemberDto;
import eclipse.demo.repository.CoCommentRepository;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CoCommentRepository coCommentRepository;
    private final MemberService memberService;

    @GetMapping("/board/new")
    public String createForm(Model model){

        model.addAttribute("boardForm", new BoardDto());
        return "board/boardForm";
    }

    @PostMapping("/board/new")
    public String createBoard(BoardDto boardDto, Authentication authentication){

        String username = authentication.getName();
        List<Member> byName = memberService.findByName(username);

        Board board = new Board(byName.get(0), boardDto.getTitle(), boardDto.getContent());


        boardService.saveBoard(board);

        return "redirect:/";
    }
    
    @GetMapping("/board/list")
    public String boardList(@PageableDefault Pageable pageable, Model model){

        Page<Board> list = boardService.getBoardList(pageable);


        model.addAttribute("list", list);


        return "board/boardList";
    }

    @GetMapping("/board/{boardId}/detail")
    public String boardDetail(@PathVariable("boardId") Long boardId, Model model){
        Board board = boardService.findOne(boardId);
        Member member = memberService.findOne(board.getMember().getId());

        // 여기서는 조회수를 올려주고
        boardService.upView(board.getId());

        BoardDto boardDto = new BoardDto(board.getId(), board.getTitle(), board.getContent(), board.getViewCnt());
        MemberDto memberDto = new MemberDto();
        memberDto.setNickname(member.getNickname());

        List<Board> commentList  = coCommentRepository.findAllWithCocomment();
        Iterator<Board> it = commentList.iterator();

        model.addAttribute("memberDto", memberDto);
        model.addAttribute("commentList", commentList);
        model.addAttribute("boardDto", boardDto);
        return "board/boardDetail";
    }

    @GetMapping("/board/{editId}/edit")
    public String boardEdit(@PathVariable("editId") Long editId, Model model){
        Board board = boardService.findOne(editId);

//        BoardDto editForm = BoardDto.boardDto(board.getId(), board.getTitle(), board.getContent(), board.getViewCnt());
        BoardDto editForm = new BoardDto(board.getId(), board.getTitle(), board.getContent(), board.getViewCnt());

        model.addAttribute("form", editForm);
        return "board/boardUpdateForm";
    }

    @PostMapping("/board/{editId}/edit")
    public String boardUpdate(@PathVariable Long editId, @ModelAttribute("form") BoardDto form){

        boardService.updateBoard(editId, form.getTitle(), form.getContent());
        return "redirect:/";
    }

    @GetMapping("/board/{deleteId}/delete")
    public String boardDelete(@PathVariable Long deleteId){
        Board board = boardService.findOne(deleteId);
        boardService.deleteBoard(board);

        return "redirect:/";
    }

}

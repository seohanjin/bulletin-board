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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
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

        String username = member.getUsername();

        //썸네일 이미지 추출
        String src = "";
        Element img = Jsoup.parse(boardDto.getContent()).select("img").first();
        if (img == null){
            src = "no image";
        }else{
            src = img.attr("src");
        }

        Member findMember = memberRepository.findByUsername(username);
        Board board = new Board(findMember, boardDto.getTitle(), boardDto.getContent(), src);
        boardService.saveBoard(board);

        return "redirect:/";
    }

    @GetMapping("/board/list")
    public String boardList(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Page<Board> boards = boardService.getBoardList(pageable);

        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getPageable().getPageNumber() + 4, boards.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("list", boards);

        return "board/boardList";
    }

    @GetMapping("/board/{boardId}/detail")
    public String boardDetail(@PathVariable("boardId") Long boardId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = ((UserDetails) authentication).getUsername();
        System.out.println("username::::" + username);

        Member findMember = memberService.findByName(username);

//        Member findMember = memberService.findOne(member.getId());
        Board board = boardService.findOne(boardId);
        BoardLike boardLike = boardLikeService.findLike(findMember.getId(), board.getId());
        List<Comment> commentAll = commentRepository.findCommentAll(board.getId());

        // 접속한 유저와 게시글의 좋아요를 누른 여부에 따라 빈하트와 꽉찬하트로 구분한다.
        if (boardLike == null) {
            model.addAttribute("boardLike", 0);
        } else {
            model.addAttribute("boardLike", 1);
        }

        // 작성자와 접속한 유저가 같다면 수정, 삭제 버튼을 보여주게 한다.
        if (board.getMember().getUsername() == findMember.getUsername()) {
            model.addAttribute("authorize", 1);
        }else {
            model.addAttribute("authorize", 0);
        }

        model.addAttribute("commentList", commentAll);
        model.addAttribute("comment", new CommentDto());
        model.addAttribute("board", board);
        return "board/boardDetail";
    }

    @GetMapping("/board/{editId}/edit")
    public String boardEdit(@PathVariable("editId") Long editId, Model model) {
        Board board = boardService.findOne(editId);
        BoardDto editForm = new BoardDto(board.getId(), board.getTitle(), board.getContent());

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

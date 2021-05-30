package eclipse.demo.controller;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.domain.Member;
import eclipse.demo.dto.BoardDto;
import eclipse.demo.dto.CommentDto;
import eclipse.demo.repository.CommentRepository;
import eclipse.demo.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;
    private final BoardLikeService boardLikeService;
    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final NotificationService notificationService;

    @GetMapping("/board/{boardId}/{commentId}")
    public String reComment(@PathVariable("boardId") Long boardId,
                            @PathVariable("commentId") Long commentId,
                            Model model) {

        model.addAttribute("reCommentForm", new CommentDto());
        return "board/comment";
    }

    // 댓글
    @PostMapping("/board/{boardId}/detail")
    public String createComment(@PathVariable Long boardId,
                                @ModelAttribute("comment") CommentDto commentDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/members";
        }else {
            Member member = (Member) authentication.getPrincipal();
            Member findMember = memberService.findOne(member.getId());
            Board findBoard = boardService.findOne(boardId);

            notificationService.save(findMember, findBoard, commentDto.getContent());

            commentService.save(findMember, findBoard, commentDto.getContent());
            return "redirect:/board/" + boardId + "/detail";
        }


    }



}

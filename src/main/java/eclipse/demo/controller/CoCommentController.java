package eclipse.demo.controller;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.CoComment;
import eclipse.demo.domain.Comment;
import eclipse.demo.dto.CoCommentDto;
import eclipse.demo.dto.CommentDto;
import eclipse.demo.repository.CoCommentRepository;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CoCommentService;
import eclipse.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class CoCommentController {

    private final CoCommentService coCommentService;
    private final CoCommentRepository coCommentRepository;
    private final CommentService commentService;
    private final BoardService boardService;


    @GetMapping("/comment/{id}")
    public String commentPopup(@PathVariable("id") Long commentId, Model model){
//        Comment comment = commentService.findOne(commentId);
//
//        model.addAttribute("comment", comment);
        model.addAttribute("coCommentForm", new CommentDto());
        return "/board/comment";
    }

    @PostMapping("/comment/{commentId}")
    public String createCoComment(@PathVariable Long commentId, @ModelAttribute("coCommentDto") CoCommentDto coCommentDto){

        Comment comment = commentService.findOne(commentId);
        Board board = boardService.findOne(comment.getBoard().getId());
//        coCommentService.saveCoComment(commentId, coCommentDto.getContent());
        CoComment coComment = CoComment.createCoComment(comment, board, coCommentDto.getContent());
//        System.out.println("commentId>>>>" + commentId);
//        System.out.println("coComment>>>>" + commentForm.getCommentContent());
        coCommentService.saveCoComment(coComment);
        return "redirect:/";
    }
}

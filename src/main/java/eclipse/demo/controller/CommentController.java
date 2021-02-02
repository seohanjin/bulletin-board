package eclipse.demo.controller;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.dto.BoardDto;
import eclipse.demo.dto.CommentDto;
import eclipse.demo.service.BoardLikeService;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/board/{boardId}/detail")
    public String createComment(@PathVariable Long boardId,
                                @ModelAttribute("form") CommentDto form) {

        Board board = boardService.findOne(boardId);

        commentService.save(board, form.getContent());
        return "redirect:/board/" + boardId + "/detail";
    }

    @GetMapping("/board/{boardId}/{commentId}")
    public String reComment(@PathVariable("boardId") Long boardId,
                            @PathVariable("commentId") Long commentId,
                            Model model) {

        model.addAttribute("reCommentForm", new CommentDto());
        return "board/comment";
    }

    @PostMapping("/board/{boardId}/{commentId}")
    public String saveReComment(@PathVariable("boardId") Long boardId,
                                @PathVariable("commentId") Long commentId,
                                BoardDto boardDto) {

        Board board = boardService.findOne(boardId);
        Comment comment = commentService.findOne(commentId);
        commentService.saveReComment(board, comment, boardDto.getContent());

        return "redirect:/board/" + boardId + "/detail";
    }


}

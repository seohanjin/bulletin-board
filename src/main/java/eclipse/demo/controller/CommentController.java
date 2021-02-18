package eclipse.demo.controller;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.dto.BoardDto;
import eclipse.demo.dto.CommentDto;
import eclipse.demo.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

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
                                @ModelAttribute("form") CommentDto commentDto) {

        Board findBoard = boardService.findOne(boardId);

        commentService.save(findBoard, commentDto.getContent());
        return "redirect:/board/" + boardId + "/detail";
    }

    // 대댓글 이상일때
    @PostMapping("/board/{boardId}/{commentId}")
    public String createReComment(@PathVariable("boardId") Long boardId,
                                @PathVariable("commentId") Long commentId,
                                BoardDto boardDto) {

        Board board = boardService.findOne(boardId);
        Comment comment = commentService.findOne(commentId);


        commentRepository.bulkUpdate(comment.getCommentGroup(), comment.getCommentSequence());

        commentService.reSave(board, boardDto.getContent(), comment);
        return "redirect:/board/" + boardId + "/detail";
    }


}

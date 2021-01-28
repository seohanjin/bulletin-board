package eclipse.demo.controller;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.dto.BoardDto;
import eclipse.demo.dto.CommentDto;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/board/{boardId}/detail")
    public String createComment(@PathVariable Long boardId,
                                @ModelAttribute("form") CommentDto form) {

        Board board = boardService.findOne(boardId);

        commentService.save(board, form.getContent());
        return "redirect:/board/" + boardId + "/detail";
    }

    @GetMapping("/board/{boardId}/{commentId}")
    public String reComment(@PathVariable("boardId")Long boardId,
                            @PathVariable("commentId")Long commentId,
                            Model model){

        model.addAttribute("reCommentForm", new CommentDto());
        return "board/comment";
    }

    @PostMapping("/board/{boardId}/{commentId}")
    public String saveReComment(@PathVariable("boardId")Long boardId,
                                @PathVariable("commentId")Long commentId,
                                BoardDto boardDto){

        Board board = boardService.findOne(boardId);
        Comment comment = commentService.findOne(commentId);
        commentService.saveReComment(board, comment, boardDto.getContent());

        return "redirect:/board/" + boardId + "/detail";
    }

    @GetMapping("/index")
    public String index(Model model){
        List<Board> boards = boardService.findAll();

        model.addAttribute("board", boards);

        return "index";
    }







}

package eclipse.demo.controller;


import eclipse.demo.domain.Board;
import eclipse.demo.dto.CommentDto;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/board/{boardId}/detail")
    public String createComment(@PathVariable Long boardId, @ModelAttribute("form") CommentDto form){

        Board board = boardService.findOne(boardId);

        commentService.save(board, form.getContent());

        return "redirect:/board/"+ boardId +"/detail";
    }


}

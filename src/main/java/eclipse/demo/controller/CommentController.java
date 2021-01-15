package eclipse.demo.controller;


import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

//    @PostMapping("/board/{boardId}/detail")
//    public String createComment(@PathVariable Long boardId, @ModelAttribute("form") CommentDto form){
//
//        System.out.println("내용>>>>>" + form.getCommentContent());
//        commentService.saveComment(boardId, form.getCommentContent());
//
//        return "redirect:/board/"+ boardId +"/detail";
//    }


}

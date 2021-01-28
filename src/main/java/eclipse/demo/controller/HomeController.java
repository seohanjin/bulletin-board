package eclipse.demo.controller;

import eclipse.demo.domain.Board;
import eclipse.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;

    @RequestMapping("/")
    public String home(Model model){
        log.info("home controller");
        List<Board> boards = boardService.findAll();
        model.addAttribute("board", boards);
        return "home";
    }
}

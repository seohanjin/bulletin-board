package eclipse.demo.controller;

import eclipse.demo.domain.Alarm;
import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.repository.AlarmRepository;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.http11.upgrade.UpgradeProcessorInternal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final AlarmRepository alarmRepository;

    @RequestMapping("/")
    public String home(Model model){
        log.info("home controller");
//        List<Board> boards = boardService.findAllByIdDesc();
        List<Comment> comments = commentService.findAllByIdDesc();
//        model.addAttribute("board", boards);
        List<Alarm> alarms = alarmRepository.findAll();
        model.addAttribute("alarms", alarms);
        model.addAttribute("comments", comments);
        return "home";
    }
}

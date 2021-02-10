package eclipse.demo.controller;

import eclipse.demo.domain.Alarm;
import eclipse.demo.repository.AlarmRepository;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
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

    private final AlarmRepository alarmRepository;

    @RequestMapping("/")
    public String home(Model model){
        log.info("home controller");

        List<Alarm> alarms = alarmRepository.findAll();
        model.addAttribute("alarms", alarms);
        return "home";
    }
}

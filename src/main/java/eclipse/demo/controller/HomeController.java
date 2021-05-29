package eclipse.demo.controller;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.Notification;
import eclipse.demo.repository.NotificationRepository;
import eclipse.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final NotificationRepository notificationRepository;
    private final BoardService boardService;

    @RequestMapping("/")
    public String home(@PageableDefault(size = 5) Pageable pageable, Model model){

        Page<Board> boards = boardService.getBoardList(pageable);

        model.addAttribute("list", boards);

        List<Notification> notifications = notificationRepository.findAll();
        model.addAttribute("notifications", notifications);


        return "home";
    }

}

package eclipse.demo.controller;

import eclipse.demo.domain.Notification;
import eclipse.demo.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final NotificationRepository notificationRepository;

    @RequestMapping("/")
    public String home(Model model){


        log.info("home controller");

        List<Notification> notifications = notificationRepository.findAll();
        model.addAttribute("notifications", notifications);

//


        return "home";
    }
}

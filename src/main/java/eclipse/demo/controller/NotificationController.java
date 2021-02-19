package eclipse.demo.controller;

import eclipse.demo.domain.Notification;
import eclipse.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification")
    public String notification(Model model){

        List<Notification> notifications = notificationService.findNoty();

        model.addAttribute("notice", notifications);
        return "/notification";
    }
}

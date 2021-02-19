package eclipse.demo.controller;

import eclipse.demo.domain.Notification;
import eclipse.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification")
    public String notification(Model model){

        List<Notification> notifications = notificationService.findNoty();
        int unreadCnt = notificationService.unreadMessage();

        model.addAttribute("notice", notifications);
        model.addAttribute("unreadCnt", unreadCnt);
        return "/notification";
    }

    @GetMapping("/notification/{id}")
    public String notificationDetail(@PathVariable Long id){

        Notification findOne = notificationService.findOne(id);
        notificationService.changeConfirmStatus(findOne);
        return "redirect:"+ findOne.getLink();

    }
}

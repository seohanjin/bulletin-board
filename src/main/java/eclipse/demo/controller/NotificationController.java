package eclipse.demo.controller;

import eclipse.demo.domain.Member;
import eclipse.demo.domain.Notification;
import eclipse.demo.service.MemberService;
import eclipse.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/notification")
    public String notification(@AuthenticationPrincipal Member member, Model model){

        Member findMember = memberService.findOne(member.getId());

        List<Notification> notifications = notificationService.findNotification(findMember.getId());
        Long aLong = notificationService.unreadMessage(findMember.getId());
//        System.out.println(">>countQuery>>" + unreadCnt.size());


        model.addAttribute("notice", notifications);
        model.addAttribute("unreadCnt", aLong);
        return "/notification";
    }

    @GetMapping("/notification/{id}")
    public String notificationDetail(@PathVariable Long id){

        Notification findOne = notificationService.findOne(id);
        notificationService.changeConfirmStatus(findOne);
        return "redirect:"+ findOne.getLink();

    }
}

package eclipse.demo.api;

import eclipse.demo.domain.Member;
import eclipse.demo.domain.Notification;
import eclipse.demo.service.MemberService;
import eclipse.demo.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentApiController {
    @Autowired
    NotificationService notificationService;

//    @Autowired
//    MemberService memberService;
//    @GetMapping("/alarm")
//    public List<AlarmDto> getComment({

//        Member findMember = memberService.findOne(member.getId());

//        List<Notification> notification = notificationService.findNotification(findMember);
//        List<AlarmDto> collect = notification.stream().map(alarm -> new AlarmDto(alarm)).collect(Collectors.toList());

//        return collect;/
//    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

//    @Getter
//    static class CommentDto{
//        private Long id;
//        private int level;
//        private String content;
//        private String board;
//
//        public CommentDto(Comment comment){
//            id = comment.getId();
//            level = comment.getLevel();
//            content = comment.getContent();
//            board = comment.getBoard().getTitle();
//        }
//    }

    @Getter
    static class AlarmDto{
        private Long AlarmId;
        private Long BoardId;
        private String BoardTitle;
        private String CommentContent;
        private LocalDateTime alarmData;
        private String href;


        public AlarmDto(Notification notification) {
            this.AlarmId = notification.getId();
            this.BoardId = notification.getBoard().getId();
            this.BoardTitle = notification.getBoard().getTitle();
            this.CommentContent = notification.getComment().getContent();
            this.alarmData = notification.getCreatedAt();
            this.href = "/board/"+ BoardId + "/detail";
        }
    }



}

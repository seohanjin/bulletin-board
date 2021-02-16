package eclipse.demo.api;

import eclipse.demo.domain.Alarm;
import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.repository.AlarmRepository;
import eclipse.demo.service.AlarmService;
import eclipse.demo.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentApiController {


    @Autowired
    AlarmService alarmService;

    @GetMapping("/alarm")
    public List<AlarmDto> getComment(){
//        List<Comment> allComment = commentService.findAllComment();
//
//        List<CommentDto> collect = allComment.stream().map(c -> new CommentDto(c)).collect(Collectors.toList());

        List<Alarm> allAlarm = alarmService.findAllDesc();
        List<AlarmDto> collect = allAlarm.stream().map(alarm -> new AlarmDto(alarm)).collect(Collectors.toList());

        return collect;
    }


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


        public AlarmDto(Alarm alarm) {
            this.AlarmId = alarm.getId();
            this.BoardId = alarm.getBoard().getId();
            this.BoardTitle = alarm.getBoard().getTitle();
            this.CommentContent = alarm.getComment().getContent();
            this.alarmData = alarm.getCreatedAt();
            this.href = "/board/"+ BoardId + "/detail";
        }
    }



}

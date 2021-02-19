package eclipse.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTime{

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String oriLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Enumerated(EnumType.STRING)
    private ReadNotification confirmation;

    @Enumerated(EnumType.STRING)
    private NotificationType status;


    // 댓글 알림
    public Notification(Board board, Comment comment) {
        this.board = board;
        this.comment = comment;
        this.setCreatedAt(LocalDateTime.now());
        this.status = NotificationType.COMMENT;
        this.oriLink = "/board/" + board.getId()+ "/detail";
    }


    public void changeBoardTitle(String title) {
        if (title.length() > 15){
            this.content = '"' + title.substring(0,15) + "...\"";
        }else {
            this.content = title;
        }
    }
}

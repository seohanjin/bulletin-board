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

    private String link;

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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Long receive_member;



    // 댓글 알림
    public Notification(Member member,Board board, Comment comment) {
        this.member = member;
        this.board = board;
        this.comment = comment;
        this.setCreatedAt(LocalDateTime.now());
        this.status = NotificationType.COMMENT;
        this.confirmation = ReadNotification.YET;
        this.link = "/board/" + board.getId()+ "/detail";
    }

    // 댓글 알림
    public Notification(Member member,Board board, String content) {
        this.member = member;
        this.board = board;
        this.content = content;
        this.setCreatedAt(LocalDateTime.now());
        this.status = NotificationType.COMMENT;
        this.confirmation = ReadNotification.YET;
        this.link = "/board/" + board.getId()+ "/detail";
    }

    // 댓글 알림
    public Notification(Board board, String content, Long receive_member) {
        this.board = board;
        this.content = content;
        this.setCreatedAt(LocalDateTime.now());
        this.status = NotificationType.COMMENT;
        this.confirmation = ReadNotification.YET;
        this.link = "/board/" + board.getId()+ "/detail";
        this.receive_member = receive_member;
    }


    public void changeBoardTitle(String title) {
        if (title.length() > 15){
            this.content = '"' + title.substring(0,15) + "...\"";
        }else {
            this.content = title;
        }
    }

    public void changeConfirm() {
        this.confirmation = ReadNotification.READ;
    }
}

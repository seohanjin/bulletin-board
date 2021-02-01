package eclipse.demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends BaseTime{

    @Id
    @Column(name = "alarm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "reComment_id")
    private ReComment reComment;

    public Alarm(Board board, Comment comment) {
        this.board = board;
        this.comment = comment;
        this.setCreatedAt(LocalDateTime.now());
    }

    public Alarm(Board board,ReComment reComment){
        this.board = board;
        this.reComment = reComment;
        this.setCreatedAt(LocalDateTime.now());
    }






}

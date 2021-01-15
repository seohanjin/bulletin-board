package eclipse.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class CoComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coComment_id")
    private Long id;

    private String content;

    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    // == 연관관계 메서드 == //
    public void setComment(Comment comment) {
        this.comment = comment;
        comment.getCoComments().add(this);
    }

    public void setBoard(Board board){
        this.board = board;
        board.getCoComments().add(this);
    }

    // == 생성자 메서드 == //
    public static CoComment createCoComment(Comment comment, Board board,String content){
        CoComment coComment = new CoComment();
        coComment.setBoard(board);
        coComment.setContent(content);
        coComment.setLocalDateTime(LocalDateTime.now());
        coComment.setComment(comment);

        return coComment;
    }


}

package eclipse.demo.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String commentContent;

    private int commentCnt;

    private LocalDateTime localDateTime;

    // group --> 첫번째 댓글 1, 두번째 댓글 2 ... id 따라감
    private Long ref;

    // 들여쓰기 수준(level)
    private int step;

    // 같은 그룹내에서의 순서(asc)
    private int refOrder;

    // 자식 글의 개수
    private int answerNum;

    // 부모 글의 기본키
    private int parentNum;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;




    public void setBoard(Board board){
        this.board = board;
        board.getComments().add(this);
    }

    public Comment(Comment comment, Board board){
        this.ref = board.getId();
        this.step = comment.getStep();
        this.refOrder = comment.getRefOrder();
        this.answerNum = comment.getAnswerNum();
        this.parentNum = comment.getParentNum();

    }
}

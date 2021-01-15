package eclipse.demo.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private int ref;

    // 들여쓰기 수준(level)
//    private int step;

//     같은 그룹내에서의 순서(asc)
    private int refOrder;

    // 자식 글의 개수
//    private int AnswerNum;

    // 부모 글의 기본키
//    private int parentNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "comment")
    private List<CoComment> coComments = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private Comment parent;
//
//    @OneToMany(mappedBy = "parent")
//    private List<Comment> child = new ArrayList<>();
//


    // == 연관관계 메서드 == //
//    public void addChildCategory(Comment child){
//        this.child.add(child);
//        child.setParent(this);
//
//    }

    public void setBoard(Board board){
        this.board = board;
        board.getComments().add(this);
    }

    // == 생성자 메서드 == //
    public static Comment createComment(Board board, String com){
        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setCommentContent(com);
        comment.setLocalDateTime(LocalDateTime.now());

        return comment;
    }
}

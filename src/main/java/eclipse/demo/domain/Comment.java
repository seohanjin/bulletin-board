package eclipse.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private int commentCnt;

    private int commentGroup;

    @ColumnDefault("0")
    private int commentSequence;

    @ColumnDefault("0")
    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "comment")
    private List<Notification> notifications = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;


    // 댓글
    public Comment(Board board, String content) {
         this.board = board;
        this.content = content;
        this.setCreatedAt(LocalDateTime.now());

    }

    // 댓글 commentGroup 증가
    public void changeGroup(int commentGroup){
        this.commentGroup = commentGroup + 1;
    }

    public Comment(Board board, Comment parent, String content){
        this.board = board;
        this.commentGroup = parent.getCommentGroup();
        this.commentSequence = parent.getCommentSequence()+1;
        this.level = parent.getLevel()+1;
        this.content = content;

    }

    public Comment(Member member, Board board, String content){
        this.member = member;
        this.board = board;
        this.content = content;
        this.setCreatedAt(LocalDateTime.now());
    }


}

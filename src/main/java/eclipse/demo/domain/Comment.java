package eclipse.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private int commentCnt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "comment")
    private List<ReComment> reComments = new ArrayList<>();

    public Comment(Board board, String content) {
        this.board = board;
        this.content = content;
    }


}

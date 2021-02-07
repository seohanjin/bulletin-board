package eclipse.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private Board board;


    @OneToMany(mappedBy = "comment")
    private List<ReComment> reComments = new ArrayList<>();

    @OneToMany(mappedBy = "comment")
    private List<Alarm> alarms = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @ColumnDefault("0")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    public Comment(Board board, String content) {
        this.board = board;
        this.content = content;
        this.setCreatedAt(LocalDateTime.now());

    }

    public Comment(Member member, Board board, String content){
        this.member = member;
        this.board = board;
        this.content = content;
        this.setCreatedAt(LocalDateTime.now());
    }




}

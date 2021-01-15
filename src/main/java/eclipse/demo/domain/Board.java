package eclipse.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Column(length = 10000000)
    private String content;

    private int viewCnt;

    @OneToMany(mappedBy = "board")
    private List<BoardLike> likes = new ArrayList<>();


    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();


    @OneToMany(mappedBy = "board")
    private Set<CoComment> coComments = new HashSet<>();

    

    // == 연관관계 메서드 == //
    public void setMember(Member member){
        this.member = member;
        member.getBoards().add(this);
    }

    public Board(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.setCreatedAt(LocalDateTime.now());
    }

    // 생성자 메서드
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
        this.setCreatedAt(LocalDateTime.now());
    }

    public void changeBoard(String title, String content){
        this.title = title;
        this.content = content;
        this.setUpdatedAt(LocalDateTime.now());
    }

    public void upViewCnt(Board board){
        this.viewCnt = board.getViewCnt()+1;
    }


    // == 생성 메서드 == //
//    public static Board createBoard(String title, String content){
//        Board board = new Board();
//        board.setTitle(title);
//        board.setContent(content);
//        board.setDateTime(LocalDateTime.now());
//
//        return board;
//    }
}

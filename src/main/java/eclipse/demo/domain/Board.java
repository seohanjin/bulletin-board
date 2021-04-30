package eclipse.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Column(length = 10000000)
    private String content;

    private int viewCnt;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<BoardLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();




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

    public void changeBoard(String title, String content) {
        this.title = title;
        this.content = content;
        this.setUpdatedAt(LocalDateTime.now());
    }

    public void upViewCnt(Board board) {
        this.viewCnt = board.getViewCnt() + 1;
    }


}

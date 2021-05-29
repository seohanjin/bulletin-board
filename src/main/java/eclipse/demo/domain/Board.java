package eclipse.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
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

    private String thumbnail;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<BoardLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(Member member, String title, String content, String thumbnail) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.setCreatedAt(LocalDateTime.now());
        this.thumbnail = thumbnail;
        member.getBoards().add(this);
    }


    public void changeBoard(String title, String content) {
        this.title = title;
        this.content = content;
        this.setUpdatedAt(LocalDateTime.now());
    }



}

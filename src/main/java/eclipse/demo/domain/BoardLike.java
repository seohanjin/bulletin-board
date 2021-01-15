package eclipse.demo.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardLike_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public void changeMember(Member member){
        this.member = member;
        member.getLikes().add(this);
    }

    public void changeBoard(Board board){
        this.board = board;
        board.getLikes().add(this);
    }

}

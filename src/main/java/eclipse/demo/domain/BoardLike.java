package eclipse.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardLike_id")
    private Long id;

    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

//    public BoardLike(Board board){
//        this.board = board;
//        this.stats = 1;
//    }

    public BoardLike(Member member, Board board,  int status){
        this.member = member;
        this.status = status;
        this.board = board;
    }

    public void changeStatus(int status){
        this.status = status;
    }

    
}

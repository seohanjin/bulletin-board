package eclipse.demo.domain;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public ReComment(Board board, Comment comment, String content){
        this.board = board;
        this.comment = comment;
        this.content = content;
    }
}

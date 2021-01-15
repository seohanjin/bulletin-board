package eclipse.demo.repository;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.CoComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CoCommentRepository {

    private final EntityManager em;

    public void save(CoComment coComment){
        System.out.println("rePository에서의 값>>>>>대댓글" + coComment.getContent());
        System.out.println("rePository에서의 값>>>>>댓글" + coComment.getComment().getCommentContent());
        System.out.println("rePository에서의 값>>>>>아이디" + coComment.getId());
        System.out.println("rePository에서의 값>>>>>로컬데이트" + coComment.getLocalDateTime());

        em.persist(coComment);
    }

    public CoComment findOne(Long coCommentId){
        return em.find(CoComment.class, coCommentId);
    }

    public List<CoComment> findAll(){
        List<CoComment> list = em.createQuery("select c from CoComment c", CoComment.class).getResultList();

        return list;
    }

    public List<Board> findAllWithCocomment(){
        return em.createQuery(
                "select b from Board b"+
                        " join fetch b.coComments m" +
                        " join fetch b.comments c", Board.class)
                .getResultList();

    }


}

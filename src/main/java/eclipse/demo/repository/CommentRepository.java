package eclipse.demo.repository;

import eclipse.demo.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment){

        em.persist(comment);
    }


    public Comment findOne(Long id){

        return em.find(Comment.class, id);
    }

    public List<Comment> findAll(){
        List<Comment> result = em.createQuery("select c from Comment c", Comment.class).getResultList();

        return result;
    }

    public void deleteComment(Comment comment){
        em.remove(comment);
    }

}

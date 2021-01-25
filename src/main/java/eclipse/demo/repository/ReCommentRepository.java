package eclipse.demo.repository;

import eclipse.demo.domain.ReComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReCommentRepository extends JpaRepository<ReComment, Long> {

    @Query("select r from ReComment r inner join Board b ON r.id = b.id where b.id = :boardId")
    List<ReComment> findReComment(@Param("boardId") Long reCommentId);
}

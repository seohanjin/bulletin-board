package eclipse.demo.repository;

import eclipse.demo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c inner join c.board b where b.id = :id")
    List<Comment> findComment(@Param("id") Long boardId);
}

package eclipse.demo.repository;

import eclipse.demo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select max(c.commentGroup) from Comment c")
    Optional<Integer> findCommentGroup();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Comment c set c.commentSequence = c.commentSequence + 1 " +
            "where c.commentGroup = :commentGroup AND c.commentSequence > :commentSequence")
    int bulkUpdate(@Param("commentGroup") int commentGroup, @Param("commentSequence") int commentSequence);

    @Query("select c from Comment c join fetch c.board b where b.id = :id order by c.commentGroup desc, c.commentSequence asc ")
    List<Comment> findCommentAll(@Param("id") Long id);



}


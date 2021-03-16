package eclipse.demo.repository;

import eclipse.demo.domain.Comment;
import eclipse.demo.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByOrderByIdDesc();
    List<Notification> findAllByMemberIdOrderByIdDesc(Long memberId);


    @Query("select c from Comment c join fetch c.board b where b.id = :id order by c.commentGroup desc, c.commentSequence asc ")
    List<Comment> findCommentAll(@Param("id") Long id);

    @Query("select n from Notification n join fetch n.member m where n.confirmation = 'YET' and m.id = :memberId")
    List<Notification> unreadMessage(@Param("memberId") Long memberId);
}

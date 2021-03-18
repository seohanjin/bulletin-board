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

    @Query("select n from Notification n where n.receive_member = :id order by n.id desc")
    List<Notification> findNotification(@Param("id") Long receive_member);


    @Query("select c from Comment c join fetch c.board b where b.id = :id order by c.commentGroup desc, c.commentSequence asc ")
    List<Comment> findCommentAll(@Param("id") Long id);

    @Query("select count(n) from Notification n where n.receive_member = :id and n.confirmation = 'YET'")
    Long unreadMessage(@Param("id") Long receive_member);
}

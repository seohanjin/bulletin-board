package eclipse.demo.repository;

import eclipse.demo.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByOrderByIdDesc();
    List<Notification> findAllByMemberId();

    @Query("select count(*) from Notification n where n.confirmation = 'YET'")
    int unreadMessage();
}

package eclipse.demo.service;

import eclipse.demo.domain.Member;
import eclipse.demo.domain.Notification;
import eclipse.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public List<Notification> findNotification(Long memberId){
        return notificationRepository.findAllByMemberIdOrderByIdDesc(memberId);
    }

    public Notification findOne(Long id){
        return notificationRepository.findById(id).orElse(null);
    }

    public List<Notification> unreadMessage(Long memberId){
        return notificationRepository.unreadMessage(memberId);
    }

    // Confirm
    @Transactional
    public void changeConfirmStatus(Notification notification){
        notification.changeConfirm();
    }

}

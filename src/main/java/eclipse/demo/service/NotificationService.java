package eclipse.demo.service;

import eclipse.demo.domain.Board;
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

    @Transactional
    public void save(Member member, Board board, String comment){

        if (member != board.getMember()){
            Long memberId = board.getMember().getId();
            Notification notification = new Notification(board, comment, memberId);
            notification.changeBoardTitle(board.getTitle());
            notificationRepository.save(notification);
        }

    }

    public List<Notification> findNotification(Long receive_member){
        return notificationRepository.findNotification(receive_member);
    }

    public Notification findOne(Long id){
        return notificationRepository.findById(id).orElse(null);
    }

    public Long unreadMessage(Long memberId){
        return notificationRepository.unreadMessage(memberId);
    }

    // Confirm
    @Transactional
    public void changeConfirmStatus(Notification notification){
        notification.changeConfirm();
    }

}

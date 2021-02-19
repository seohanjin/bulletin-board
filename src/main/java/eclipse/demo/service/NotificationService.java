package eclipse.demo.service;

import eclipse.demo.domain.Notification;
import eclipse.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public List<Notification> findNoty(){
        return notificationRepository.findAllByOrderByIdDesc();
    }

}

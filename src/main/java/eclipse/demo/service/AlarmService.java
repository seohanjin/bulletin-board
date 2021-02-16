package eclipse.demo.service;

import eclipse.demo.domain.Alarm;
import eclipse.demo.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmService {

    @Autowired
    AlarmRepository alarmRepository;

    public List<Alarm> findAllDesc(){
        return alarmRepository.findAllByOrderByIdDesc();

    }
}

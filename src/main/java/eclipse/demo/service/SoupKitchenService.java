package eclipse.demo.service;

import eclipse.demo.domain.SoupKitchen;
import eclipse.demo.repository.SoupKitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SoupKitchenService {

    @Autowired
    private SoupKitchenRepository soupKitchenRepository;

    public List<SoupKitchen> findSoupKitchen(String SiDo, String GunGu){
        return soupKitchenRepository.findBySiDoContainingAndGunGuContaining(SiDo, GunGu);

    }
}

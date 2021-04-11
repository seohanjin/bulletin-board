package eclipse.demo.service;

import eclipse.demo.domain.SoupKitchen;
import eclipse.demo.repository.SoupKitchenRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SoupKitchenServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SoupKitchenRepository soupKitchenRepository;

    @Test
    @Transactional
    public void 무료급식소찾기(){
        SoupKitchen soupKitchen = new SoupKitchen();
        soupKitchen.setSiDo("서울특별시");
        soupKitchen.setGunGu("강남구");

        em.persist(soupKitchen);

        List<SoupKitchen> findSoupKitchen = soupKitchenRepository.findBySiDoContainingAndGunGuContaining("서울특별시", "강남구");


        Assertions.assertThat(soupKitchen).isEqualTo(findSoupKitchen.get(0));
    }

}
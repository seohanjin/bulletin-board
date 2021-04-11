package eclipse.demo.repository;

import eclipse.demo.domain.SoupKitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SoupKitchenRepository extends JpaRepository<SoupKitchen, Long> {

    List<SoupKitchen> findBySiDoContainingAndGunGuContaining(String SiDo, String GunGu);
}

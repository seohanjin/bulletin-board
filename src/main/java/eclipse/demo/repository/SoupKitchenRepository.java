package eclipse.demo.repository;

import eclipse.demo.domain.SoupKitchen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoupKitchenRepository extends JpaRepository<SoupKitchen, Long> {
}

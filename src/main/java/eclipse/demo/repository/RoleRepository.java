package eclipse.demo.repository;

import eclipse.demo.domain.Authority.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

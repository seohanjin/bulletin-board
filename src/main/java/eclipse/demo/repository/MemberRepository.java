package eclipse.demo.repository;

import eclipse.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);
    List<Member> findAllByUsername(String username);
}

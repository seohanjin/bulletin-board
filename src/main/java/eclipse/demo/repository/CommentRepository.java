package eclipse.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Comment extends JpaRepository<Comment, Long> {
}

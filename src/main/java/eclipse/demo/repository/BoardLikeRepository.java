package eclipse.demo.repository;

import eclipse.demo.domain.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    Optional<BoardLike> findAllByBoardId(Long id);
}

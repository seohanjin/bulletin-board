package eclipse.demo.repository;

import eclipse.demo.domain.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    BoardLike findLikeByBoardIdAndMemberId(Long boardId, Long memberId);

    Long countByBoardId(Long boardId);


}

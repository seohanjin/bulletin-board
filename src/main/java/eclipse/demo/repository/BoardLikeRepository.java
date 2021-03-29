package eclipse.demo.repository;

import eclipse.demo.domain.BoardLike;
import eclipse.demo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {


    BoardLike findLikeByBoardIdAndMemberId(Long boardId, Long memberId);

    @Query("select count(*) from BoardLike l inner join l.board b where b.id = :id and l.status = 1")
    Long countByBoardId(@Param("id") Long boardId);
}

package eclipse.demo.repository;

import eclipse.demo.domain.BoardLike;
import eclipse.demo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {


    BoardLike findLikeByBoardIdAndMemberId(Long boardId, Long memberId);




}

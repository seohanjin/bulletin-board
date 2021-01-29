package eclipse.demo.repository;

import eclipse.demo.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    public List<Board> findAllByOrderByIdDesc();
}

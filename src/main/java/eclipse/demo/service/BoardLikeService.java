package eclipse.demo.service;

import eclipse.demo.domain.BoardLike;
import eclipse.demo.repository.BoardLikeRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardLikeService {
    private final BoardLikeRepository boardLikeRepository;

    public void save(BoardLike boardLike){
        boardLikeRepository.save(boardLike);
    }
}

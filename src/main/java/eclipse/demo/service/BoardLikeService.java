package eclipse.demo.service;

import eclipse.demo.domain.BoardLike;
import eclipse.demo.repository.BoardLikeRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardLikeService {
    private final BoardLikeRepository boardLikeRepository;

    @Transactional
    public Long save(BoardLike boardLike){

        BoardLike save = boardLikeRepository.save(boardLike);
        return save.getId();
    }

    public BoardLike findAllById(Long id){
        BoardLike boardLike = boardLikeRepository.findAllByBoardId(id).orElse(null);
        return boardLike;
    }


}

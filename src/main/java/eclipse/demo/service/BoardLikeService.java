package eclipse.demo.service;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.BoardLike;
import eclipse.demo.domain.Member;
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
    public Long save(Member member, Board board, int status){

        BoardLike saveBoardLike = new BoardLike(member, board, status);
        BoardLike save = boardLikeRepository.save(saveBoardLike);
        return save.getId();
    }

    public BoardLike findLike(Long memberId, Long boardId){
        BoardLike likeByBoardIdAndMemberId = boardLikeRepository.findLikeByBoardIdAndMemberId(boardId, memberId);
        return likeByBoardIdAndMemberId;
    }

    @Transactional
    public Long update(BoardLike boardLike){
        BoardLike save = boardLikeRepository.save(boardLike);

        return save.getId();
    }



    @Transactional
    public void delete(Long boardLikeId){
        boardLikeRepository.deleteById(boardLikeId);
    }


}

package eclipse.demo.service;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.BoardLike;
import eclipse.demo.domain.Member;
import eclipse.demo.repository.BoardLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardLikeService {
    private final BoardLikeRepository boardLikeRepository;

    @Transactional
    public void saveBoardLike(Member member, Board board){

        BoardLike saveBoardLike = BoardLike.builder()
                .member(member)
                .board(board)
                .build();

        boardLikeRepository.save(saveBoardLike);
    }

    public BoardLike findLike(Long memberId, Long boardId){
        BoardLike likeByBoardIdAndMemberId = boardLikeRepository.findLikeByBoardIdAndMemberId(boardId, memberId);
        return likeByBoardIdAndMemberId;
    }



    @Transactional
    public void delete(Long boardLikeId){
        boardLikeRepository.deleteById(boardLikeId);
    }


}

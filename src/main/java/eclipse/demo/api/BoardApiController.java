package eclipse.demo.api;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.BoardLike;
import eclipse.demo.domain.Member;
import eclipse.demo.service.BoardLikeService;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final BoardLikeService boardLikeService;

    @PostMapping("/boardLike")
    public String saveLike(@RequestBody BoardLikeRequest boardLikeRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new ClassCastException("로그인을 해주세요");
        } else {
            Member member = (Member) authentication.getPrincipal();
            Board findBoard = boardService.findOne(boardLikeRequest.getBoardId());
            Member findMember = memberService.findOne(member.getId());
            BoardLike findBoardLike = boardLikeService.findLike(findMember.getId(), findBoard.getId());

            // 빈하트일 경우
            if (boardLikeRequest.getStatus().equals("empty")) {
                boardLikeService.saveBoardLike(findMember, findBoard);
            }
            // 꽉찬하트일 경우
            else {
                boardLikeService.delete(findBoardLike.getId());
            }
            return "ok";
        }

    }


    @Data
    static class BoardLikeRequest {
        private String status;
        private Long boardId;
    }
}

package eclipse.demo.api;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.domain.Member;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
import eclipse.demo.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;
    private final BoardService boardService;
    private final MemberService memberService;

    // 대댓글 이상일때
    @PostMapping("/board/reComment")
    public String createReComment(@RequestBody ReCommentRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            throw new ClassCastException("로그인을 해주세요");
        }else {
            Member member = (Member) authentication.getPrincipal();
            Member findMember = memberService.findOne(member.getId());
            Board board = boardService.findOne(request.getBoardId());
            Comment comment = commentService.findOne(request.getCommentId());

            commentService.sequenceUpdate(comment.getCommentGroup(), comment.getCommentSequence());

            commentService.reSave(findMember, board, request.getContent(), comment);
            return "ok";
        }
    }

    @Data
    static class ReCommentRequest{
        private Long commentId;
        private Long boardId;
        private String content;
    }
}

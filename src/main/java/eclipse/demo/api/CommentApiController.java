package eclipse.demo.api;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.domain.Member;
import eclipse.demo.repository.CommentRepository;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.CommentService;
import eclipse.demo.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String createReComment(@AuthenticationPrincipal Member member, @RequestBody ReCommentRequest request) {

        Member findMember = memberService.findOne(member.getId());
        Board board = boardService.findOne(request.getBoardId());
        Comment comment = commentService.findOne(request.getCommentId());

        commentService.sequenceUpdate(comment.getCommentGroup(), comment.getCommentSequence());

        commentService.reSave(findMember, board, request.getContent(), comment);
        return "ok";
    }

    @Data
    static class ReCommentRequest{
        private Long commentId;
        private Long boardId;
        private String content;
    }
}

package eclipse.demo.service;


import eclipse.demo.domain.Member;
import eclipse.demo.domain.Notification;
import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.repository.NotificationRepository;
import eclipse.demo.repository.BoardRepository;
import eclipse.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final NotificationRepository notificationRepository;
    private final MemberService memberService;


    // 첫번째 댓글
    @Transactional
    public void save(Member member, Board board, String content) {

        Member findMember = memberService.findOne(member.getId());

        Integer commentGroup = commentRepository.findCommentGroup().orElse(0);

        Comment comment = new Comment(findMember,board, content);
        comment.changeGroup(commentGroup);


        Notification notification = new Notification(member,board, comment);
        notification.changeBoardTitle(board.getTitle());


        commentRepository.save(comment);
        notificationRepository.save(notification);

    }

    // 대댓글 이상
    @Transactional
    public void reSave(Member member, Board board, String content, Comment parent) {

        Comment comment = new Comment(member, board, parent, content);

        Notification notification = new Notification(member, board, comment);
        notification.changeBoardTitle(board.getTitle());

        commentRepository.save(comment);
        notificationRepository.save(notification);
    }


    public Comment findOne(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        return comment;
    }

}

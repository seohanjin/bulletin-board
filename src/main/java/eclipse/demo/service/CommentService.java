package eclipse.demo.service;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.domain.Member;
import eclipse.demo.domain.Notification;
import eclipse.demo.repository.BoardRepository;
import eclipse.demo.repository.CommentRepository;
import eclipse.demo.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
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

        Integer commentGroup = commentRepository.findCommentGroup().orElse(0);
        Comment comment = Comment.builder()
                .member(member)
                .board(board)
                .content(content)
                .build();
        comment.changeGroup(commentGroup);
        commentRepository.save(comment);


    }

    // 대댓글 이상
    @Transactional
    public void reSave(Member member, Board board, String content, Comment parent) {

        Comment re_comment = Comment.builder()
                .member(member)
                .board(board)
                .commentGroup(parent.getCommentGroup())
                .commentSequence(parent.getCommentSequence() + 1)
                .level(parent.getLevel() + 1)
                .content(content)
                .build();

        Notification notification = new Notification(member, board, re_comment);
        notification.changeBoardTitle(board.getTitle());
        commentRepository.save(re_comment);
        notificationRepository.save(notification);
    }


    public Comment findOne(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        return comment;
    }

}

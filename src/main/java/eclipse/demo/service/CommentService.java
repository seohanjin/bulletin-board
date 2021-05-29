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

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final NotificationRepository notificationRepository;


    // 첫번째 댓글
    @Transactional
    public void save(Member member, Board board, String content) {

        // commentGroup 의 최댓값을 구해서 첫번째 댓글들의 순서를 정한다.
        Integer commentGroup = commentRepository.findCommentGroup().orElse(0);
        Comment comment = Comment.builder()
                .member(member)
                .board(board)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
        comment.changeGroup(commentGroup);
        board.getComments().add(comment);

        commentRepository.save(comment);

    }


    // 대댓글 이상
    @Transactional
    public void reSave(Member member, Board board, String content, Comment parent) {

        Comment re_comment = Comment.builder()
                .content(content)
                .commentGroup(parent.getCommentGroup())
                .commentSequence(parent.getCommentSequence() + 1)
                .level(parent.getLevel() + 1)
                .board(board)
                .member(member)
                .createdAt(LocalDateTime.now())
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

    @Transactional
    public void sequenceUpdate(int commentGroup, int commentSequence){
        commentRepository.bulkUpdate(commentGroup, commentSequence);
    }

}

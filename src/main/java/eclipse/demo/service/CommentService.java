package eclipse.demo.service;


import eclipse.demo.domain.Alarm;
import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.repository.AlarmRepository;
import eclipse.demo.repository.BoardRepository;
import eclipse.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final AlarmRepository alarmRepository;


    // 첫번째 댓글
    @Transactional
    public void save(Board board, String content){
        Integer commentGroup = commentRepository.findCommentGroup().orElse(0);

        Comment comment = new Comment(board, content);
        comment.changeGroup(commentGroup);

        Alarm alarm = new Alarm(board, comment);

        commentRepository.save(comment);
        alarmRepository.save(alarm);

    }

    // 대댓글 이상
    @Transactional
    public void reSave(Board board, String content, Comment parent){

         Comment comment = new Comment(board, parent, content);
         commentRepository.save(comment);
    }


    public Comment findOne(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElse(null);
        return comment;
    }

}

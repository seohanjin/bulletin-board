package eclipse.demo.service;


import eclipse.demo.domain.CoComment;
import eclipse.demo.repository.CoCommentRepository;
import eclipse.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CoCommentService {

    private final CoCommentRepository coCommentRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void saveCoComment(CoComment coComment){
//        Comment comment = commentRepository.findOne(commentId);
//        System.out.println("comment정보>>>>Service단계>>>" + comment.getCommentContent());
//        CoComment coComment = CoComment.createCoComment(comment, content);
        coCommentRepository.save(coComment);

    }
}

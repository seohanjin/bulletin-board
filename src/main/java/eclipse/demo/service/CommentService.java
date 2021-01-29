package eclipse.demo.service;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.domain.ReComment;
import eclipse.demo.dto.CommentDto;
import eclipse.demo.repository.BoardRepository;
import eclipse.demo.repository.CommentRepository;
import eclipse.demo.repository.ReCommentRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final ReCommentRepository reCommentRepository;

    @Transactional
    public void save(Board board, String content){
        Board findBoard = boardRepository.findById(board.getId()).orElse(null);
        Comment comment = new Comment(findBoard, content);

        commentRepository.save(comment);
    }

    public List<Comment> findJoinComment(Long boardId){
        List<Comment> findComments = commentRepository.findComment(boardId);

        return findComments;
    }

    public Comment findOne(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElse(null);
        return comment;
    }

    public List<Comment> findAllByIdDesc(){
        List<Comment> findAll = commentRepository.findAllByOrderByIdDesc();
        return findAll;
    }
//<----------------- 대댓글 --------------------------->
    @Transactional
    public void saveReComment(Board board, Comment comment, String content){
        ReComment reComment = new ReComment(board, comment, content);

        reCommentRepository.save(reComment);
    }

    public List<ReComment> findReComment(Long boardId){

        List<ReComment> reComment = reCommentRepository.findReComment(boardId);
        return reComment;

    }

    public List<ReComment> ReFindAll(){
        List<ReComment> reComments = reCommentRepository.findAll();

        return reComments;
    }





}

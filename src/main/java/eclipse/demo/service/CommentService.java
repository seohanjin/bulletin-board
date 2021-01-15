package eclipse.demo.service;


import eclipse.demo.domain.Comment;
import eclipse.demo.repository.BoardRepository;
import eclipse.demo.repository.CommentRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
//
//    @Transactional
//    public Long saveComment(Long boardId, String com){
//        Board board = boardRepository.findOne(boardId);
//
//        Comment comment = Comment.createComment(board, com);
//        commentRepository.save(comment);
//
//        return comment.getId();
//    }

//    @Transactional
//    public Long saveCoComment(){
//        boardRepository.findOne()
//    }

    public Comment findOne(Long id){
        return commentRepository.findOne(id);
    }

//    @Transactional
//    public Long comment(Long boardId){
//        Board board = boardRepository.findOne(boardId);
//
////        Comment comment = Comment.createComment(board);
//
////        return comment.getId();
//    }
}

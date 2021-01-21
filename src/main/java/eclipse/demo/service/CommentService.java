package eclipse.demo.service;


import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.repository.BoardRepository;
import eclipse.demo.repository.CommentRepository;
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




}

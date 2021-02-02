package eclipse.demo.initDB;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.Comment;
import eclipse.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


@Component
@RequiredArgsConstructor
class initDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit1(){

            Member member1 = new Member("서한진", "1234", "나무늘보");
            Member member2 = new Member("박수진", "1234", "뿡뿡이");
            Member member3 = new Member("용시훈", "1234", "머리에 개기름");
            Member member4 = new Member("김지환", "1234", "호주토박이");
            Member member5 = new Member("배이안", "1234", "경찰");


//            Board board1 = new Board(member1, "게시글1", "내용1");
//            Board board2 = new Board(member1,"게시글2", "내용2");
//            Board board3 = new Board(member2,"게시글3", "내용3");
//            Board board4 = new Board(member3,"게시글4", "내용4");
//            Board board5 = new Board(member4,"게시글5", "내용5");
//            Board board6 = new Board(member5,"게시글6", "내용6");
//            Board board7 = new Board(member4,"게시글7", "내용7");
//            Board board8 = new Board(member3,"게시글8", "내용8");
//            Board board9 = new Board(member1,"게시글9", "내용9");
//            Board board10 = new Board(member5,"게시글10", "내용10");

//            Comment comment1 = new Comment(member1, board1, "게시글1 댓글1");
//            Comment comment2 = new Comment(member2, board1, "게시글1 댓글2");
//            Comment comment3 = new Comment(member1, board2, "게시글2 댓글1");
//            Comment comment4 = new Comment(member3, board2, "게시글2 댓글2");
//            Comment comment5 = new Comment(member4, board3, "게시글3 댓글1");
//            Comment comment6 = new Comment(member3, board4, "게시글4 댓글1");
//            Comment comment7 = new Comment(member5, board5, "게시글5 댓글1");



//            em.persist(board1);
//            em.persist(board2);
//            em.persist(board3);
//            em.persist(board4);
//            em.persist(board5);
//            em.persist(board6);
//            em.persist(board7);
//            em.persist(board8);
//            em.persist(board9);
//            em.persist(board10);

//            em.persist(comment1);
//            em.persist(comment2);
//            em.persist(comment3);
//            em.persist(comment4);
//            em.persist(comment5);
//            em.persist(comment6);
//            em.persist(comment7);



        }



    }

}



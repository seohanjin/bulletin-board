package eclipse.demo.initDB;

import eclipse.demo.domain.Board;
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

            Board board1 = new Board("게시글1", "내용1");
            Board board2 = new Board("게시글2", "내용2");
            Board board3 = new Board("게시글3", "내용3");
            Board board4 = new Board("게시글4", "내용4");
            Board board5 = new Board("게시글5", "내용5");
            Board board6 = new Board("게시글6", "내용6");
            Board board7 = new Board("게시글7", "내용7");
            Board board8 = new Board("게시글8", "내용8");
            Board board9 = new Board("게시글9", "내용9");
            Board board10 = new Board("게시글10", "내용10");
            Board board11 = new Board("게시글11", "내용11");
            Board board12 = new Board("게시글12", "내용12");
            Board board13 = new Board("게시글13", "내용13");
            Board board14 = new Board("게시글14", "내용14");
            Board board15 = new Board("게시글15", "내용15");
            Board board16 = new Board("게시글16", "내용16");



            em.persist(board1);
            em.persist(board2);
            em.persist(board3);
            em.persist(board4);
            em.persist(board5);
            em.persist(board6);
            em.persist(board7);
            em.persist(board8);
            em.persist(board9);
            em.persist(board10);
            em.persist(board11);
            em.persist(board12);
            em.persist(board13);
            em.persist(board14);
            em.persist(board15);
            em.persist(board16);



        }



    }

}



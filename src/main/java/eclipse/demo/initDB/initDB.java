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


            em.persist(board1);
            em.persist(board2);
            em.persist(board3);
            em.persist(board4);


        }



    }

}



package eclipse.demo.repository;

import eclipse.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return members;

    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", name)
                .getResultList();

    }

    public List<Member> findMember(String userName, String password){
        List<Member> existMember = em.createQuery("select m from Member m where m.username = :username and m.password = :password", Member.class)
                .setParameter("username", userName)
                .setParameter("password", password)
                .getResultList();

        return existMember;
    }



}

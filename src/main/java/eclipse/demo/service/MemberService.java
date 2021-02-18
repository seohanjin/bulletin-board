package eclipse.demo.service;


import eclipse.demo.domain.Authority.Role;
import eclipse.demo.domain.Authority.UserRole;
import eclipse.demo.domain.Member;
import eclipse.demo.repository.MemberRepository;
import eclipse.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoleRepository roleRepository;

    // 회원가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복회원 검사
    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getUsername());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findByName(String name){
        List<Member> byName = memberRepository.findByName(name);

        return byName;
    }

    // 회원 한명 조회
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

    // 회원수정
    @Transactional
    public void update(Long id, String username, String password, String nickname) {
        Member member = memberRepository.findOne(id);
        member.changeMember(username, password, nickname);
    }

    public List<Member> findAll(){
        List<Member> findMembers = memberRepository.findAll();
        return findMembers;
    }

}

package eclipse.demo.service;


import eclipse.demo.domain.Member;
import eclipse.demo.repository.MemberRepository;
import eclipse.demo.repository.UserRepository;
import eclipse.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
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
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        userRepository.save(member);
        return member.getId();
    }

    // 중복회원 검사
    private void validateDuplicateMember(Member member){
        List<Member> findMembers = userRepository.findByName(member.getUsername());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findByName(String name){
        List<Member> byName = userRepository.findByName(name);

        return byName;
    }

    // 회원 한명 조회
    public Member findOne(Long id){
        return userRepository.findOne(id);
    }

    // 회원수정
    @Transactional
    public void update(Long id, String username, String nickname) {
        Member member = userRepository.findOne(id);
        member.changeMember(username, nickname);
    }

    public List<Member> findAll(){
        List<Member> findMembers = userRepository.findAll();
        return findMembers;
    }

    @Transactional
    public void edit_profile(Member member) {
        memberRepository.save(member);
    }
}

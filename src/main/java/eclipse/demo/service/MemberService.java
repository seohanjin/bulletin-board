package eclipse.demo.service;


import eclipse.demo.domain.Member;
import eclipse.demo.repository.MemberRepository;
import eclipse.demo.repository.RoleRepository;
import eclipse.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public Long join(Member member) {

        userRepository.save(member);

        return member.getId();
    }

    public List<Member> findAllByUsername(String username){
        return memberRepository.findAllByUsername(username);
    }

    public List<Member> findAllByNickname(String nickname){
        return memberRepository.findAllByNickname(nickname);
    }


    public Member findByName(String name){
        Member byUsername = memberRepository.findByUsername(name);
        return byUsername;
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

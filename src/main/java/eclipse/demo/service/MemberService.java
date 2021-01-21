package eclipse.demo.service;


import eclipse.demo.domain.Authority.Role;
import eclipse.demo.domain.Authority.UserRole;
import eclipse.demo.domain.Member;
import eclipse.demo.repository.MemberRepository;
import eclipse.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    // 회원가입
    @Transactional
    public Long join(Member member){

        // member : username, password, nickname, enabled 를 포함한 member

//        String encodedPassword = passwordEncoder.encode(member.getPassword());
        validateDuplicateMember(member);


        UserRole userRole = new UserRole(member, new Role());

//        Member member1 = new Member(member.getUserName(), member.getPassword(), member.getNickName(), true, (List<UserRole>) userRole);
//        Member member1 = new Member(member.getUsername(), encodedPassword, member.getNickname(), true, userRole);
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


    //로그인


    // 회원 한명 조회
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

//    @Transactional
//    public void update(Long id, String password, String nickName){
//        Member member = memberRepository.findOne(id);
//        member.setPassword(password);
//        member.setNickName(nickName);
//
//    }

}

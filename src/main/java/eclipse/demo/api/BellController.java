package eclipse.demo.api;

import eclipse.demo.domain.Member;
import eclipse.demo.service.MemberService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BellController {

    private final MemberService memberService;

    @PostMapping()
    public CreateMemberResponse bellList(@RequestBody Member member){
        Member member1 = new Member(member.getUsername(), member.getPassword(), member.getNickname());
        Long id = memberService.join(member1);

        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}

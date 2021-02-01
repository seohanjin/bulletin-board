package eclipse.demo.api;

import eclipse.demo.controller.CommentController;
import eclipse.demo.domain.Board;
import eclipse.demo.domain.BoardLike;
import eclipse.demo.domain.Member;
import eclipse.demo.dto.MemberDto;
import eclipse.demo.repository.BoardLikeRepository;
import eclipse.demo.repository.MemberRepository;
import eclipse.demo.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final BoardLikeRepository boardLikeRepository;

    @GetMapping("/api/v1/members")
    public List<Member> members(){
        return memberRepository.findAll();
    }

    @PostMapping("/register")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member(request.getUsername(), request.getPassword(), request.getNickname());

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @PostMapping("/like")
    public void saveLIke(@RequestBody CommentController requestLike){
        BoardLike boardLike = new BoardLike(requestLike.status);
        boardLikeRepository.save(boardLike);

    }

    @Data
    static class CreateMemberRequest{
        private String username;
        private String password;
        private String nickname;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CommentController{
        private int status;
    }

}

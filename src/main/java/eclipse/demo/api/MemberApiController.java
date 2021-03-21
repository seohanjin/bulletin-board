package eclipse.demo.api;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.BoardLike;
import eclipse.demo.domain.Member;
import eclipse.demo.repository.UserRepository;
import eclipse.demo.service.BoardLikeService;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.MemberService;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MemberApiController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardLikeService boardLikeService;
    @Autowired
    private BoardService boardService;


    //        List<MemberDto> memberDtos = new ArrayList<>();
//
//        for (Member findMember : findMembers) {
//            memberDtos.add(new MemberDto(findMember));
//        }
//        return new Result(memberDtos.size(), memberDtos);


    @GetMapping("/api/members")
    public Result<List<MemberDto>> members() {
        List<Member> findMembers = memberService.findAll();
        List<MemberDto> collect = findMembers.stream().map(member -> new MemberDto(member)).collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Data
    static class Result<T>{
        private int count;
        private T data;


        public Result(int count, T data) {
            this.count = count;
            this.data = data;
        }
    }

    @Getter
    static class MemberDto{
        private Long id;
        private String username;
        private String password;
        private String nickname;

        public MemberDto(Member member) {
            this.id = member.getId();
            this.username = member.getUsername();
            this.password = member.getPassword();
            this.nickname = member.getNickname();
        }
    }


    @PostMapping("/api/members")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member(request.getUsername(), request.getPassword(), request.getNickname());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/members/{id}")
    public UpdateMemberResponse updateMember(
            @PathVariable Long id,
            @RequestBody @Valid UpdateMemberRequest request){

//        memberService.update(id, request.getUsername(), request.getPassword(), request.getNickname());
        Member findMember = memberService.findOne(id);

//        return new UpdateMemberResponse(findMember.getId(), findMember.getUsername(), findMember.getPassword(), findMember.getNickname());
        return new UpdateMemberResponse(findMember);
    }

    @Data
    static class UpdateMemberRequest{
        private String username;
        private String password;
        private String nickname;
    }

    @Data
    static class UpdateMemberResponse{
        private Long id;
        private String username;
        private String password;
        private String nickname;


        public UpdateMemberResponse(Member member) {
            this.id = member.getId();
            this.username = member.getUsername();
            this.password = member.getPassword();
            this.nickname = member.getNickname();
        }
    }

    @Data
    static class CreateMemberRequest {
        private String username;
        private String password;
        private String nickname;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

//-------------------------------------------------------------------------------------
    @PostMapping("/like")
    public String saveLIke(@AuthenticationPrincipal Member member, @RequestBody RequestLike requestLike) {
        Board board = boardService.findOne(requestLike.getBoardId());
        Member findMember = memberService.findOne(member.getId());
        BoardLike boardLike = boardLikeService.findLike(findMember.getId(), board.getId());

        // null --> 처음 하트를 누른 것이다.
        if (boardLike == null || boardLike.getStatus() == 0) {
            Long save = boardLikeService.save(findMember, board, 1);
            return "full";
        }
        // 하트 on -> off
        else {
            Long save = boardLikeService.save(findMember, board, 0);
            return "empty";
        }

    }

    @PutMapping("/like")
    public UpdateLikeResponse updateBoardLike(@AuthenticationPrincipal Member member, @RequestBody RequestLike requestLike) {
        Member findMember = memberService.findOne(member.getId());
        Board board = boardService.findOne(requestLike.getBoardId());
        BoardLike boardLike = boardLikeService.findLike(findMember.getId(), board.getId());
        boardLike.setStatus(0);
        boardLikeService.update(boardLike);

        return new UpdateLikeResponse("ok");
    }


    @Data
    static class UpdateLikeResponse {
        private String a;

        public UpdateLikeResponse(String a) {
            this.a = a;
        }
    }


    @Data
    static class CreateLikeResponse {
        private Long id;

        public CreateLikeResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class RequestLike {
        private int status;
        private Long boardId;
    }


    @Data
    private class ResponseLike {
        String status;

        public ResponseLike(String status){
            this.status = status;
        }
    }
}

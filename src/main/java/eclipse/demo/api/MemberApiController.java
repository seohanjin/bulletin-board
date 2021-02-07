package eclipse.demo.api;

import eclipse.demo.domain.Board;
import eclipse.demo.domain.BoardLike;
import eclipse.demo.domain.Member;
import eclipse.demo.repository.MemberRepository;
import eclipse.demo.service.BoardLikeService;
import eclipse.demo.service.BoardService;
import eclipse.demo.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.UploadContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final BoardLikeService boardLikeService;
    private final BoardService boardService;


    @GetMapping("/api/v1/members")
    public List<Member> members() {
        return memberRepository.findAll();
    }

    @PostMapping("/register")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member(request.getUsername(), request.getPassword(), request.getNickname());

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }


    @PostMapping("/like")
    public CreateMemberResponse saveLIke(@RequestBody RequestLike requestLike) {
        Board board = boardService.findOne(requestLike.getBoardId());
        BoardLike boardLike = boardLikeService.findAllById(board.getId());
        // null 이다. --> 처음 하트를 누른 것이다.
        if (boardLike == null) {
            BoardLike saveBoardLike = new BoardLike(requestLike.getStatus(), board);
            Long save = boardLikeService.save(saveBoardLike);
            return new CreateMemberResponse(save);
        }
        // null이 아니다. --> 하트를 다시 누른 것으로 update실행
        else {
            boardLike.setStatus(1);
            Long save = boardLikeService.save(boardLike);
            return new CreateMemberResponse(save);
        }

    }

    @PutMapping("/like")
    public UpdateLikeResponse updateBoardLike(@RequestBody RequestLike requestLike) {
        Board board = boardService.findOne(requestLike.getBoardId());
        BoardLike boardLike = boardLikeService.findAllById(board.getId());
        boardLike.setStatus(0);
        boardLikeService.save(boardLike);

        return new UpdateLikeResponse("ok");
    }

    @Data
    static class CreateMemberRequest {
        private String username;
        private String password;
        private String nickname;
    }

    @Data
    static class UpdateLikeResponse {
        private String a;

        public UpdateLikeResponse(String a) {
            this.a = a;
        }
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
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

//    @PostMapping("/image")
//    public ResponseEntity<?> handleFileUpload(@RequestParam("file")MultipartFile file){
//        try{
//            UploadContext uploadContext = imageService.store(file);
//        }
//    }

}

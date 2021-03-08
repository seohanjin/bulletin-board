package eclipse.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Getter @Setter
public class MemberDto {


    private Long id;

//    @NotEmpty(message = "아이디는 필수입니다!")
    private String username;

//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}",
//    message = "비밀번호는 영문 대소문자 구분없이 숫자 포함 최소 6~20글자입니다!")
    private String password;

//    @NotEmpty(message = "닉네임은 필수입니다!")
    private String nickname;



//    private String email;
//
//    private String birthday;
//
//    private String sex;
//
//    private String phoneNumber;

}

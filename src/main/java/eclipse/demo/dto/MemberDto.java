package eclipse.demo.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class MemberDto {

//    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식을 맞춰주세요.")
    private String username;

    @Pattern(regexp = "[a-zA-Z1-9]{6,12}",
    message = "비밀번호는 영문 대소문자 구분없이 숫자 포함 최소 6~12자리 이내로 입력해주세요.")
    @NotBlank(message = "패스워드를 입력해주세요")
    private String password;

    @Pattern(regexp = "[a-zA-Z1-9]{6,12}",
            message = "비밀번호는 영문 대소문자 구분없이 숫자 포함 최소 6~12자리 이내로 입력해주세요.")
    @NotBlank(message = "패스워드를 입력해주세요")
    private String password_confirm;

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;

}

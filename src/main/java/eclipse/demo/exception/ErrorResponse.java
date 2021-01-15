package eclipse.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorResponse {

    // enum을 이용하여 에러코드를 정의한 후 내려줄 것
    private String code;

    // 어떠한 에러인지 간단하게 넣어줌
    private String description;

    // 에러의 세부내용을 넣어줌
    private String detail;


}

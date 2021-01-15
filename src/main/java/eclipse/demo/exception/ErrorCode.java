package eclipse.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {

    NOT_NULL("ERROR_CODE_0001", "필수값 누락");

    @Getter
    private String code;

    @Getter
    private String description;
}

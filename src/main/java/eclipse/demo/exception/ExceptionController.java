package eclipse.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionController {
    // @Controller, @RestController에서 발생한 Exception에 대해서 모두 이것에서 처리할 수 있다.

    // @Valid의 유효성을 통과하지 못하면, MethodArgumentValidException이 발생한다.
    // 그러면 @ExceptionHandler를 통해서 캐치 후 Error를 가공

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("MethodArgumentNotValidException 발생!! rul:{}, trace:{}", request.getRequestURI(), e.getStackTrace());

        ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse makeErrorResponse(BindingResult bindResult){
        String code = "";
        String description="";
        String detail="";

        if(bindResult.hasErrors()){

            detail = bindResult.getFieldError().getDefaultMessage();

            String bindResultCode = bindResult.getFieldError().getCode();

            code = ErrorCode.NOT_NULL.getCode();
            description = ErrorCode.NOT_NULL.getDescription();
        }

        return new ErrorResponse(code, description, detail);
    }

}

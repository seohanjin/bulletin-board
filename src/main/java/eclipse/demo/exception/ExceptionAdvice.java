package eclipse.demo.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ClassCastException.class)
    public ErrorMessage authenticationError(ClassCastException e){

//        ModelAndView mav = new ModelAndView("jsonView");
//        mav.addObject("error", e.getMessage());
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }

    @Data
    static class ErrorMessage{
        private String message;
    }
    @Bean
    public MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }

}

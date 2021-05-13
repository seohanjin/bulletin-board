package eclipse.demo.exception;

import lombok.Data;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Data
public class CustomExceptionHandler extends IllegalStateException {

    private String message;

    public CustomExceptionHandler(String message) {
        this.message = message;
    }

    public String stateException(Model model){

        model.addAttribute("message", message);

        return "members/signUpMember";
    }

    //    public String
}

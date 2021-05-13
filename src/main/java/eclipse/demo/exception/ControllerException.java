package eclipse.demo.exception;

import lombok.Data;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Data
public class ControllerException extends Throwable {

    private String message;

    public ControllerException(String message) {
        this.message = message;
    }

    public String exceptionHandler(Model model){

        model.addAttribute("message", message);

        return "members/signUpMember";
    }
}

package eclipse.demo.exception;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Data
@ControllerAdvice
@Controller
public class CustomExceptionHandler{


    private String message;

    public CustomExceptionHandler(String message) {
        this.message = message;
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public String stateException(HttpServletRequest request, IllegalStateException e, Model model){
        String requestURI = request.getRequestURI();
        System.out.println("---" + requestURI);

        ModelAndView mav = new ModelAndView();
//        mav.set
        model.addAttribute("message", message);
        model.addAttribute("uri", requestURI);

        return "members/new";
    }

    //    public String
}

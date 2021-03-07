package eclipse.demo.controller;

import eclipse.demo.domain.Member;
import eclipse.demo.dto.MemberDto;
import eclipse.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.validation.Valid;


@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberDto());
        return "members/createMemberForm";
    }


    @PostMapping("/members/new")
    public String create(@Valid MemberDto form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
            memberService.join(new Member(form.getUsername(), passwordEncoder.encode(form.getPassword()), form.getNickname(), true));

        return "redirect:/";
    }

    @GetMapping("/members")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new MemberDto());
        return "members/login";
    }

    @GetMapping("/members/profile")
    public String proFile(){
        return "members/profile";
    }

    @GetMapping("/members/edit_password")
    public String edit_password(){
        return "members/edit_password";
    }

}

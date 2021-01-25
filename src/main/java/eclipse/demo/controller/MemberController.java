package eclipse.demo.controller;

import eclipse.demo.domain.Member;
import eclipse.demo.dto.MemberDto;
import eclipse.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
        memberService.join(new Member(form.getUsername(), form.getPassword(), form.getNickname(), true));

        return "redirect:/";
    }

    @GetMapping("/members")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new MemberDto());
        return "members/login";
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/";
    }

//    @PostMapping("/members/login")
//    public String loginTry(MemberDto loginForm, BindingResult result){
//        Member member = new Member();
//        member.setUserName(loginForm.getUserName());
//        member.setPassword(loginForm.getPassword());
//
//        memberService.login(member);
//
//        return "redirect:/";
//
//    }

}

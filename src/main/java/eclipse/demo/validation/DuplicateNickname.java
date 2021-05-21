package eclipse.demo.validation;

import eclipse.demo.domain.Member;
import eclipse.demo.exception.NicknameException;
import eclipse.demo.service.MemberService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class DuplicateNickname implements ConstraintValidator<NicknameException, String> {

    private final MemberService memberService;

    @Override
    public void initialize(NicknameException constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        List<Member> findMembers = memberService.findAllByNickname(value);
        if (!findMembers.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}

package eclipse.demo.validation;

import eclipse.demo.domain.Member;
import eclipse.demo.service.MemberService;
import eclipse.demo.exception.AccountException;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class DuplicateAccount implements ConstraintValidator<AccountException, String> {

    private final MemberService memberService;

    @Override
    public void initialize(AccountException constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        List<Member> findMembers = memberService.findAllByUsername(value);
        if (!findMembers.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}

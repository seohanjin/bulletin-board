package eclipse.demo.exception;

import eclipse.demo.domain.Member;
import eclipse.demo.service.MemberService;
import eclipse.demo.validation.ContractNumberConstraint;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class ContactNumberValidator implements ConstraintValidator<ContractNumberConstraint, String> {

    private final MemberService memberService;

    @Override
    public void initialize(ContractNumberConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext context) {
        
        List<Member> findMembers = memberService.findAllByUsername(contactField);
        if (!findMembers.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}

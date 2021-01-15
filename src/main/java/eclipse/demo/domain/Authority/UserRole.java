package eclipse.demo.domain.Authority;

import eclipse.demo.domain.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userRole_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole(Member member, Role role) {
        this.member = member;
        this.role = role;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

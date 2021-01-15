package eclipse.demo.domain.Authority;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles = new ArrayList<>();


    public Role() {
        this.authority = Authority.ROLE_USER;
    }
}

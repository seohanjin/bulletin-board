package eclipse.demo.domain;

import eclipse.demo.domain.Authority.UserRole;
import lombok.Getter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

//    @OneToMany(mappedBy = "member")
//    private List<Board> boards = new ArrayList<>();

    // 실제이름
    private String username;

    // 패스워드
    private String password;

    // 닉네임
    private String nickname;

    private String userProfile;

//    @OneToMany(mappedBy = "member")
//    private List<BoardLike> likes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private LoginStatus loginStatus;

    private boolean enabled;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "comment")
    private List<ReComment> reComments = new ArrayList<>();


    //    private String birthDay;
//
//    private String email;
//
//    private String sex;
//
//    private String phoneNumber;

    protected Member(){

    }

    public Member(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.enabled = enabled;
    }

    public void addUserRole(UserRole userRole){
        userRole.setMember(this);
        userRoles.add(userRole);

    }

    public Member(String username, String password, String nickname, boolean enabled, UserRole... userRoles) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.enabled = enabled;
        for (UserRole userRole : userRoles) {
            addUserRole(userRole);
        }
    }
}

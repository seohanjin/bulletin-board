package eclipse.demo.domain;

import eclipse.demo.domain.Authority.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    // 실제이름
    private String username;

    // 패스워드
    private String password;

    // 닉네임
    private String nickname;

    private String userProfile;

    private String role;


    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Member(String email, String password, String nickname) {
        this.username = email;
        this.password = password;
        this.nickname = nickname;
        this.role = "USER_ROLE";
    }

    public void changeMember(String email, String nickname){
        this.username = email;
        this.nickname = nickname;
    }

    @OneToMany(mappedBy = "member")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<BoardLike> likes = new ArrayList<>();

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<UserRole> userRoles = new ArrayList<>();


}

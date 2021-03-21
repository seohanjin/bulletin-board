package eclipse.demo.domain;

import eclipse.demo.domain.Authority.UserRole;
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

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    // 실제이름
    private String username;

    // 패스워드
    private String password;

    // 닉네임
    private String nickname;

    private String userProfile;

    private String role;

    @OneToMany(mappedBy = "member")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<BoardLike> likes = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "file_id")
//    private Files files;

    private boolean enabled;

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }
//    public void setFiles(Files files) {
//        this.files = files;
//    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<UserRole> userRoles = new ArrayList<>();


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


    public Member(String username, String password, String nickname, boolean enabled) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.enabled = enabled;
        this.role = "USER_ROLE";
    }

    public void changeMember(String username, String nickname){
        this.username = username;
        this.nickname = nickname;
    }

}

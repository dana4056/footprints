package footprints.footprints.domain.member;
import footprints.footprints.domain.member.DTO.MemberDTO;
import footprints.footprints.domain.post.Post;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member implements UserDetails {


//    @GeneratedValue
//    private Long id;
    @Id
    private String nick;    //id -> nick 으로 pk 변경
    private String email;
    private String pw;
    private String area;
    @ElementCollection(fetch = FetchType.EAGER)  // 이거 해결해야함
    private List<String> roles;

    public void InfoUpdate(MemberDTO memberDTO){
        this.nick = memberDTO.getNick();
        this.email = memberDTO.getEmail();
        this.pw = memberDTO.getPw();
        this.area = memberDTO.getArea();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    public void Update(MemberDTO memberDTO){
        this.email = memberDTO.getEmail();
        this.pw = memberDTO.getPw();
    }

    public void setRoles() {
        this.roles = Collections.singletonList("ROLE_USER");
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return nick;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

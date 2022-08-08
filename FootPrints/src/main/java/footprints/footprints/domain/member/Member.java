package footprints.footprints.domain.member;
import footprints.footprints.domain.post.PostDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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

    public void Update(MemberDTO memberDTO){
        this.email = memberDTO.getEmail();
        this.pw = memberDTO.getPw();
    }

    public void InfoUpdate(MemberDTO memberDTO){
        this.nick = memberDTO.getNick();
        this.email = memberDTO.getEmail();
        this.pw = memberDTO.getPw();
        this.area = memberDTO.getArea();
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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

package footprints.footprints.service.security;

import footprints.footprints.domain.member.Member;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    public String makeJwtToken(Member member);

}

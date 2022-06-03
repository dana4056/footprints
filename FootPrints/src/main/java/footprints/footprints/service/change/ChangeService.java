package footprints.footprints.service.change;

import footprints.footprints.domain.member.MemberChangeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface ChangeService {

    String findID(String email);
    String findPwd(String email);
    boolean changePwd(MemberChangeDTO memberChangeDTO);
}

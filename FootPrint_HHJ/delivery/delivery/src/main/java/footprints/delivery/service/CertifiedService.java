package footprints.delivery.service;


import footprints.delivery.exception.AuthenticationNumberMismatchException;
import footprints.delivery.repository.SmsCertification;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.apache.http.auth.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class CertifiedService {

    private final SmsCertification smsCertification;

    public void sendmessage(String phoneNumber) throws CoolsmsException {

        String api_key = "NCSGOQZYXQQ9T7JQ";
        String api_secret = "SFDWI2VAPY9HHSL1UQDL6CCUTANKYAWS";
        Message coolsms = new Message(api_key, api_secret);

        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);
        params.put("from", "01096629052");
        params.put("type", "sms");
        params.put("text", "인증번호는 [" + numStr + "] 입니다.");

        coolsms.send(params); // 메시지 전송
        smsCertification.createSmsCertification(phoneNumber, numStr);

    }

    public void verifySms(String phoneNumber, String numStr){
        if(isVerify(phoneNumber,numStr)){
            throw new AuthenticationNumberMismatchException("인증번호가 일치하지 않습니다.");
        }
        smsCertification.removeSmsCertification(phoneNumber);
    }

    public boolean isVerify(String phoneNumber, String numStr){
        return !(smsCertification.hasKey(phoneNumber) &&
                smsCertification.getSmsCertification(phoneNumber)
                        .equals(numStr));
    }
}

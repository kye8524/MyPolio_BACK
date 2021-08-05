package mypolio.mypolio.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class SaltUtil {
    public String encodePassword(String salt, String pwd){
        return BCrypt.hashpw(pwd,salt);
    }

    public String genSalt(){
        return BCrypt.gensalt();
    }
}

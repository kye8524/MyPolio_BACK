package mypolio.mypolio.service;

import mypolio.mypolio.entity.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService {
    void signUpUser(Member member);

//    Member loginUser(String email, String pwd) throws Exception;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

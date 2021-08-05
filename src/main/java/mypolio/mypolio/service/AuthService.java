package mypolio.mypolio.service;

import mypolio.mypolio.entity.Member;

public interface AuthService {
    void signUpUser(Member member);

    Member loginUser(String email, String pwd) throws Exception;
}

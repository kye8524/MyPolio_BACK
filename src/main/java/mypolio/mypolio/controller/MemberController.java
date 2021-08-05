package mypolio.mypolio.controller;

import lombok.extern.slf4j.Slf4j;
import mypolio.mypolio.entity.Member;
import mypolio.mypolio.entity.Response;
import mypolio.mypolio.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class MemberController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public Response signUpUser(@RequestBody Member member) {
        try {
            authService.signUpUser(member);
            return new Response("success", "회원가입을 성공적으로 완료했습니다.", null);
        } catch (Exception e) {
            return new Response("error", "회원가입을 하는 도중 오류가 발생했습니다.", null);
        }
    }
}

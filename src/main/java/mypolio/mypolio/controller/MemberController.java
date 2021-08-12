package mypolio.mypolio.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mypolio.mypolio.config.security.JwtTokenProvider;
import mypolio.mypolio.entity.Member;
import mypolio.mypolio.entity.Response;
import mypolio.mypolio.repository.MemberRepository;
import mypolio.mypolio.service.AuthService;
import mypolio.mypolio.service.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final SaltUtil saltUtil;

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


    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public Response login(@RequestBody Map<String, String> user, HttpServletRequest req, HttpServletResponse res) {
        try {
            Member member = memberRepository.findByEmail(user.get("email"))
                    .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
            String userPwd = saltUtil.encodePassword(member.getSalt().getSalt(), user.get("pwd"));
            String memberPwd = member.getPwd();
            if (!userPwd.equals(memberPwd)) {
                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
            }
            String token = jwtTokenProvider.createToken(member.getEmail(), member.getRole());
            member.setToken(token);
            memberRepository.save(member);
            res.addHeader("token", token);
            return new Response("success", "로그인에 성공했습니다.", token);
        } catch (Exception e) {
            return new Response("error", "로그인에 실패했습니다.", e.getMessage());
        }
    }
}
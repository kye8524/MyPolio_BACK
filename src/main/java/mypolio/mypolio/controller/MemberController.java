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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {
    private final PasswordEncoder passwordEncoder;
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

//    // 회원가입
//    @RequestMapping(method = RequestMethod.POST, path = "/join")
//    public Long join(@RequestBody Map<String, String> user) {
//        return memberRepository.save(User.builder()
//                .email(user.get("email"))
//                .password(passwordEncoder.encode(user.get("pwd")))
//                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
//                .build()).getId();
//    }

    // 로그인
    @RequestMapping(method = RequestMethod.POST, path = "/signin")
    public String login(@RequestBody Map<String, String> user) {
        Member member = memberRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("pwd"), member.getPwd())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getEmail(), member.getRole());
    }
//
//    @RequestMapping(method = RequestMethod.POST, path = "signin1")
//    public void asd(){
//        String a = !passwordEncoder
//    }
}


//!passwordEncoder.matches(user.get("pwd"), saltUtil.encodePassword(member.getSalt().getSalt(), member.getPwd()))

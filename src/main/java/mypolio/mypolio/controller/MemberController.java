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
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;


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
            return new Response("success", "회원가입을 성공적으로 완료했습니다.", member);
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

    @RequestMapping(method = RequestMethod.GET, path = "/{userSeq}")
    public Response getUser(@PathVariable int userSeq){
        Optional<Member> user = memberRepository.findBySeq(userSeq);
        if(user.isPresent()){
            Member member = user.get();
            if(member.getUserType() == 0){
                member.setDesignPortfolios( new ArrayList<>());
            }else if(member.getUserType()==1){
                member.setDesignPortfolios(member.getDesignPortfolios());
            }else{
                return new Response("error", "user type 에러가 발생함", null);
            }
            return new Response("success", "user 정보 보기", user);
        }

        return new Response("error", "user가 없음", null);
    }
    
    @RequestMapping(method = RequestMethod.PATCH, path = "/update")
    public Response updateUser(@RequestBody Member userDetail){
        if(authService.userUpdate(userDetail)){
            return new Response("success", "유저 정보 업데이트", userDetail);
        }else {
            return new Response("error", "해당 유저를 찾을 수 없음", null);
        }

    }
}

package mypolio.mypolio.controller;

import lombok.AllArgsConstructor;
import mypolio.mypolio.dto.UserDto;
import mypolio.mypolio.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

//    index 페이지 보기
    @GetMapping("/")
    public String index() {
        System.out.println("get / index");
        return "/index";
    }

    //회원가입페이지  없어도됨
    @GetMapping("/user/signup")
    public String dispSignup(){
        System.out.println("get /user/signup");
        return "/signup";
    }

    //회원가입
    @PostMapping("/user/signup")
    public String execSignup(UserDto userDto){
        System.out.println("post /user/signup");

        int userSeq = userService.joinUser(userDto);
        System.out.println(userSeq);

        return "redirect:/user/login";
    }

    //로그인 페이지 보디
    @GetMapping("/user/login")
    public String dispLogin(){
        System.out.println("get /user/login ");
        return "/login";
    }

    //로그인 결과
    @GetMapping("/user/login/result")
    public String dispLoginResult(){
        System.out.println("get /user/login/result");
        return "/loginSecces";
    }

    //로그아웃 결과
    @GetMapping("/user/logout/result")
    public String dispLogout(){
        System.out.println("get /user/logout/result");
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        System.out.println("get /user/denied");
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        System.out.println("get /user/info");
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        System.out.println("get /admin");
        return "/admin";
    }
}

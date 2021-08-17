package mypolio.mypolio.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mypolio.mypolio.entity.DevPortfolio;
import mypolio.mypolio.entity.Member;
import mypolio.mypolio.entity.Response;
import mypolio.mypolio.entity.TechStack;
import mypolio.mypolio.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/DesignPortfolioController")
public class DesignPortfolioController {

    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(method = RequestMethod.POST, path = "")
    public Response createDevPortfolio(@RequestBody DevPortfolio devPortfolio){
        try {
            Optional<Member> member_seq = memberRepository.findBySeq(devPortfolio.getMember().getSeq());
            Member member1 = member_seq.get();
            devPortfolio.setMember(member1);
            List<TechStack> stacks = devPortfolio.getStacks();
            for(TechStack stack : stacks){
                stack.setDevPortfolio(devPortfolio);
            }
//            devPortfolioRepository.save(devPortfolio);
            return new Response("success", "포트폴리오를 생성했습니다", null);
        }catch (Exception e){
            return new Response("error", "포트폴리오를 생성하는 도중 에러가 발생했습니다.", e.getMessage());
        }
    }
}

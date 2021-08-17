package mypolio.mypolio.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mypolio.mypolio.entity.DesignPortfolio;
import mypolio.mypolio.entity.Member;
import mypolio.mypolio.entity.Response;
import mypolio.mypolio.repository.DesignPortfolioRepository;
import mypolio.mypolio.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/designPortfolio")
public class DesignPortfolioController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DesignPortfolioRepository designPortfolioRepository;


    @RequestMapping(method = RequestMethod.POST, path = "")
    public Response createDesignPortfolio(@RequestBody DesignPortfolio designPortfolio){
        try {
            Optional<Member> member = memberRepository.findBySeq(designPortfolio.getMember().getSeq());
            Member user = member.get();
            designPortfolio.setMember(user);
            designPortfolioRepository.save(designPortfolio);
            return new Response("success", "디자이너 포트폴리오를 생성했습니다.", null);
        }catch (Exception e){
            return new Response("error", "디자이너 포트폴리오를 생성하는 도중 에러가 발생했습니다.", e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{designPortfolio_seq}")
    public Response getDesignPortfolio(@PathVariable int designPortfolio_seq){
        try {
            Optional<DesignPortfolio> ExDesignPortfolio = designPortfolioRepository.findById(designPortfolio_seq);
            DesignPortfolio designPortfolio = ExDesignPortfolio.get();
            designPortfolio.setMember(new Member());
            return new Response("success", "포트폴리오를 불러왔습니다", designPortfolio);
        }catch (Exception e)
        {
            return new Response("error", "포트폴리오를 불러오는 과정에서 에러가 발생했습니다.", null);
        }
    }
}

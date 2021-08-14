package mypolio.mypolio.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mypolio.mypolio.entity.DevPortfolio;
import mypolio.mypolio.entity.Member;
import mypolio.mypolio.entity.Response;
import mypolio.mypolio.repository.DevPortfolioRepository;
import mypolio.mypolio.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/devPortfolio")
public class DevPortfolioController {
    @Autowired
    DevPortfolioRepository devPortfolioRepository;

    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(method = RequestMethod.POST, path = "")
    public Response createDevPortfolio(@RequestBody DevPortfolio devPortfolio){
        try {
            Optional<Member> member_seq = memberRepository.findBySeq(devPortfolio.getMember().getSeq());
            Member member1 = member_seq.get();
            devPortfolio.setMember(member1);
            devPortfolioRepository.save(devPortfolio);
            return new Response("success", "포트폴리오를 생성했습니다", devPortfolio);
        }catch (Exception e){
            return new Response("error", "포트폴리오를 생성하는 도중 에러가 발생했습니다.", e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{devPortfolio_seq}")
    public Response getDevPortfolio(@PathVariable int devPortfolio_seq){
        try{
            Optional<DevPortfolio> devPortfolio = devPortfolioRepository.findBySeq(devPortfolio_seq);
            return new Response("success", "포트폴리오를 불러왔습니다.", devPortfolio);
        }catch (Exception e){
            return new Response("error", "포트폴리오를 찾아올 수 없습니다", e.getMessage());
        }
    }


}

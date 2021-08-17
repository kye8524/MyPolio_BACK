package mypolio.mypolio.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mypolio.mypolio.entity.*;
import mypolio.mypolio.repository.DevPortfolioRepository;
import mypolio.mypolio.repository.DevProjectRepository;
import mypolio.mypolio.repository.MemberRepository;
import mypolio.mypolio.repository.TechStackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    TechStackRepository techStackRepository;

    @Autowired
    DevProjectRepository devProjectRepository;

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
            devPortfolioRepository.save(devPortfolio);
            return new Response("success", "포트폴리오를 생성했습니다", null);
        }catch (Exception e){
            return new Response("error", "포트폴리오를 생성하는 도중 에러가 발생했습니다.", e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{devPortfolio_seq}")
    public Response getDevPortfolio(@PathVariable int devPortfolio_seq){
        try{
            Optional<DevPortfolio> ExDevPortfolio = devPortfolioRepository.findBySeq(devPortfolio_seq);
            DevPortfolio devPortfolio = ExDevPortfolio.get();
            List<TechStack> stacks= techStackRepository.findAllByDevPortfolio(devPortfolio);
            for(TechStack stack : stacks){
                stack.setDevPortfolio(new DevPortfolio());
            }
            devPortfolio.setStacks(stacks);
            List<DevProject> projects = devProjectRepository.findAllByDevPortfolio(devPortfolio);
            for(DevProject project : projects){
                project.setDevPortfolio(new DevPortfolio());
            }
            devPortfolio.setDevProjects(projects);
            return new Response("success", "포트폴리오를 불러왔습니다.", devPortfolio);
        }catch (Exception e){
            return new Response("error", "포트폴리오를 찾아올 수 없습니다", e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/project/{devPortfolio_seq}")
    public Response createProject(@PathVariable int devPortfolio_seq, @RequestBody DevProject devProject){
        try {
            System.out.println("post project : "+ devPortfolio_seq);
            Optional<DevPortfolio> ExDevPortfolio = devPortfolioRepository.findBySeq(devPortfolio_seq);
            DevPortfolio devPortfolio = ExDevPortfolio.get();
            devProject.setDevPortfolio(devPortfolio);
            devProjectRepository.save(devProject);
            return new Response("success", "프로젝트를 생성했습니다.", null);
        }catch (Exception e){
            return new Response("error", "프로젝트를 생성하는 도중 에러가 발생했습니다.", e.getMessage());
        }
    }


}
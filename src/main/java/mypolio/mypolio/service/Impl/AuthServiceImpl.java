package mypolio.mypolio.service.Impl;

import mypolio.mypolio.entity.Member;
import mypolio.mypolio.entity.Salt;
import mypolio.mypolio.repository.MemberRepository;
import mypolio.mypolio.service.AuthService;
import mypolio.mypolio.service.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SaltUtil saltUtil;

    @Override
    public void signUpUser(Member member) {
        String pwd = member.getPwd();
        String salt = saltUtil.genSalt();
        member.setSalt(new Salt(salt));
        member.setPwd(saltUtil.encodePassword(salt, pwd));
        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    @Override
    public boolean userUpdate(Member userDetail){
        Optional<Member> member = memberRepository.findBySeq(userDetail.getSeq());
        if(member.isPresent()){
            Member user = member.get();
            user.setName(userDetail.getName());
            user.setUserType(userDetail.getUserType());
            memberRepository.save(user);
            return true;
        }else {
            return false;
        }
    }
}

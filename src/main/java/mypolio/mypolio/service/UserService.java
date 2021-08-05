package mypolio.mypolio.service;

import lombok.AllArgsConstructor;
import mypolio.mypolio.domain.Role;
import mypolio.mypolio.domain.entity.UserEntity;
import mypolio.mypolio.domain.repository.UserRepository;
import mypolio.mypolio.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public Integer joinUser(UserDto userDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPwd(passwordEncoder.encode(userDto.getPwd()));;

        return userRepository.save(userDto.toEntity()).getUser_seq();
    }


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findByEmail(userEmail);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if( ("admin").equals(userEmail)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPwd(), authorities);
    }
}

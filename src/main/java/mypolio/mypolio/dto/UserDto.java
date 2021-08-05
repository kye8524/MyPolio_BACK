package mypolio.mypolio.dto;

import lombok.*;
import mypolio.mypolio.domain.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDto {
    private Integer user_seq;
    private String name;
    private String email;
    private String pwd;
//    private String accessToken;
    private String profileImage;
//    private String signTime;
//    private String salt;
//    private String userType;
    private String info;

    private LocalDateTime modifiedDate;


    @Builder
    public UserDto(Integer user_seq, String name, String email, String pwd, String profileImage, String info) {
        this.user_seq = user_seq;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.profileImage = profileImage;
        this.info = info;
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
                .user_seq(user_seq)
                .name(name)
                .email(email)
                .pwd(pwd)
                .profileImage(profileImage)
                .info(info)
                .build();
    }
}

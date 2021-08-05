package mypolio.mypolio.domain.entity;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_seq;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    private String pwd;

//    @Column(length = 35, nullable = false)
//    private String accessToken;
//
//    @Column(length = 45, nullable = false)
//    private String signTime;

    @Column(length = 45, nullable = false)
    private String profileImage;

//    @Column(length = 45, nullable = false)
//    private String salt;
//
//    @Column(length = 10, nullable = false)
//    private String userType;

    @Column(length = 200, nullable = false)
    private String info;

    @Builder
    public UserEntity(Integer user_seq, String name, String email, String pwd, String profileImage, String info) {
        this.user_seq = user_seq;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.profileImage = profileImage;
        this.info = info;
    }
}

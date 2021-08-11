package mypolio.mypolio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mypolio.mypolio.config.UserRole;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int user_seq;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String pwd;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_ADMIN;


    @JoinColumn(name = "salt")
    @OneToOne(cascade = CascadeType.ALL)
    private Salt salt;


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date signTime;

    //@Override
   //public String toString() {
   //    return "User{" +
   //            "seq=" + user_seq +
   //            ", email='" + email + '\'' +
   //            ", pwd='" + pwd + '\'' +
   //            ", name='" + name + '\'' +
   //            ", signtime=" + signTime +
   //            '}';
   //}
}

package mypolio.mypolio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mypolio.mypolio.config.UserRole;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @GeneratedValue
    private int user_seq;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String pwd;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_ADMIN;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salt")
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

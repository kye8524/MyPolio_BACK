package mypolio.mypolio.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements UserDetails{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int user_seq;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 300, nullable = false)
    private String pwd;



    @Column(length = 30, nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> role = new ArrayList<>();

//    @Enumerated(EnumType.STRING)
//    private UserRole role = UserRole.ROLE_ADMIN;


//    @JoinColumn(name = "salt")
    @OneToOne(cascade = CascadeType.ALL)
    private Salt salt;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date signTime;


    //restart
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { return true; }


}

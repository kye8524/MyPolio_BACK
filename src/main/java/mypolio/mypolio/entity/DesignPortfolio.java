
package mypolio.mypolio.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DesignPortfolio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int seq;

    @Column
    private String template;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private Member member;

    @Column(nullable = true)
    private String info;


    @Column(nullable = true)
    private String education;

    @OneToMany(mappedBy = "designPortfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @NonNull
    private List<DesignExperience> experience = new ArrayList<>();

    @OneToMany(mappedBy = "designPortfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @NonNull
    private List<DesignTechStack> stacks = new ArrayList<>();

    @Column(length = 100, nullable = false)
    private String contactEmail;

    @Column(length = 20, nullable = false)
    private String contactPhone;

    @OneToMany(mappedBy = "designPortfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @NonNull
    private List<ProjectImg> projectImgs = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date regTime;
}

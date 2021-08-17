package mypolio.mypolio.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DevPortfolio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int seq;

    @Column(length = 10, nullable = false)
    private String template;

    @ManyToOne
    private Member member;

    @Column(length = 100, nullable = true)
    private String contactEmail;

    @Column(length = 20, nullable = true)
    private String contactPhone;

    @Column(length = 100, nullable = true)
    private String contactGit;

    @OneToMany(mappedBy = "devPortfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @NonNull
    private List<TechStack> stacks = new ArrayList<>();

    @OneToMany(mappedBy = "devPortfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @NonNull
    private List<DevProject> devProjects = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date regTime;
}

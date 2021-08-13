package mypolio.mypolio.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DevPortfolio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int seq;

    @Column(length = 10, nullable = false)
    private String template;

    @Column(length = 100, nullable = false)
    private String projectName;

    @ManyToOne
    private Member member;

    @Column(length = 100, nullable = false)
    private String projectUrl;

    @Column(length = 100, nullable = false)
    private String projectInfo;

    @Column(length = 100, nullable = true)
    private String projectImage;

    @Column(length = 100, nullable = true)
    private String contactEmail;

    @Column(length = 20, nullable = true)
    private String contactPhone;

    @Column(length = 100, nullable = true)
    private String contactGit;

    @Column(length = 100, nullable = true)
    private String git;

    @Column(length = 100, nullable = true)
    private String react;

    @Column(length = 100, nullable = true)
    private String spring;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date regTime;
}

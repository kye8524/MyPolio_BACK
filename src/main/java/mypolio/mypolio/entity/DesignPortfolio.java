
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
public class DesignPortfolio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int seq;

    @Column
    private String template;

    @Column(length = 50, nullable = false)
    private String projectName;

    @Column(length = 100, nullable = false)
    private String projectUrl;

    @Column(length = 100, nullable = false)
    private String projectInfo;

    @Column(length = 100, nullable = false)
    private String projectImage;

    @Column(length = 100, nullable = false)
    private String contactEmail;

    @Column(length = 20, nullable = false)
    private String contactPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private Member member;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date regTime;
}

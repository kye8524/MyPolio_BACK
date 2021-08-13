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

    @Column
    private String template;

    @Column
    private String projectName;

    @Column
    private int userSeq;

    @Column
    private String projectUrl;
    @Column
    private String projectInfo;
    @Column
    private String projectImage;
    @Column
    private String contactEmail;
    @Column
    private String contactPhone;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date regTime;
}

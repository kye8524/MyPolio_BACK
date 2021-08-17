package mypolio.mypolio.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DesignExperience {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int seq;

    @Column(length = 100, nullable = false)
    private String company;

    @Column(length = 100, nullable = false)
    private String period;

    @Column(nullable = true)
    private String info;


    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    @JoinColumn(name = "designPortfolio_id")
    private DesignPortfolio designPortfolio;
}

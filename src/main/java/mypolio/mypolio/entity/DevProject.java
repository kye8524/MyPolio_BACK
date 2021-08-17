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
public class DevProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = true)
    private String info;

    @Column(length = 100, nullable = true)
    private String image;

    @Column(length = 100, nullable = true)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    @JoinColumn(name = "devportfolio_id")
    private DevPortfolio devPortfolio;
}

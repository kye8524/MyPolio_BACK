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
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(length = 20, nullable = false)
    private String tech;

    @Column
    private int prof;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    @JoinColumn(name = "devportfolio_id")
    private DevPortfolio devPortfolio;
}

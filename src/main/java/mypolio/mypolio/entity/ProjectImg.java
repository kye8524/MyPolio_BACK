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
public class ProjectImg {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int seq;

    @ManyToOne
    @JoinColumn(name = "designPortfolio_id")
    private DesignPortfolio designPortfolio;

    @Column(nullable = false)
    private String origFileName;

    @Column(nullable = false)
    private String filePath;

    private Long fileSize;

/*
    @Builder
    public Photo(String origFileName, String filePath, Long fileSize){
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    // Board 정보 저장
    public void setBoard(Board board){
        this.board = board;

        // 게시글에 현재 파일이 존재하지 않는다면
        if(!board.getPhoto().contains(this))
            // 파일 추가
            board.getPhoto().add(this);
    }
*/
}

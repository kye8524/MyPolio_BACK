package mypolio.mypolio.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Salt {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int email;

    @Column(length = 200, nullable = false)
    private String salt;

    public Salt(){

    }
    public Salt(String salt){
        this.salt=salt;
    }

}

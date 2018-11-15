package faeteam3.Notlage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class Bezugsperson {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long bezugspersonId;

    public Bezugsperson()
    {

    }
}

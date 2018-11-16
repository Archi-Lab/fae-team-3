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

    public Bezugsperson(Long bezugspersonId)
    {
        this.bezugspersonId = bezugspersonId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Bezugsperson)
        {
            return ((Bezugsperson)obj).bezugspersonId == this.bezugspersonId;
        }
        return false;
    }
}

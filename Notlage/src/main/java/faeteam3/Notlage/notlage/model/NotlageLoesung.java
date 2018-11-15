package faeteam3.Notlage.notlage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import faeteam3.Notlage.Bezugsperson;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class NotlageLoesung {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long notlageBestaetigungid;


    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Bezugsperson bezugsperson;

    public NotlageLoesung()
    {
    }

    public NotlageLoesung(Bezugsperson bezugsperson)
    {
        this.bezugsperson = bezugsperson;
    }
}

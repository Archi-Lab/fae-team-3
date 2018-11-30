package faeteam3.Notlage.notlage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class NotlageBestaetigung {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long notlageBestaetigungid;


//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.ALL)
//    private Bezugsperson bezugsperson;

    public NotlageBestaetigung()
    {
    }

//    public NotlageBestaetigung(Bezugsperson bezugsperson)
//    {
//        this.bezugsperson = bezugsperson;
//    }
//
//    public Bezugsperson getBezugsperson() {
//        return bezugsperson;
//    }
}

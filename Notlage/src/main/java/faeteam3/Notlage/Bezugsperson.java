package faeteam3.Notlage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import faeteam3.Notlage.notlage.controller.NotlageController;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Getter
public class Bezugsperson {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotlageController.class);

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
            LOGGER.info("bp1:" + bezugspersonId + "bp2:" + ((Bezugsperson)obj).bezugspersonId);
            return ((Bezugsperson)obj).bezugspersonId == this.bezugspersonId;
        }
        return false;
    }
}

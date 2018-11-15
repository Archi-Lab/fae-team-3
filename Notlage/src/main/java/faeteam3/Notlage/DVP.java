package faeteam3.Notlage;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;

@Embeddable
public class DVP {

    public DVP(){}

    public DVP(Long id){
        this.id = id;
    }

	@JsonIgnore
	private Long id;
}

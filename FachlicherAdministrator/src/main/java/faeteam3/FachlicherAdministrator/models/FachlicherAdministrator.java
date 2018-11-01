package faeteam3.FachlicherAdministrator.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="fachlicheradministrator")
public class FachlicherAdministrator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "FachlicherAdministrator{" +
                "id=" + id +
                "}";
    }
}

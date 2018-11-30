package faeteam3.Notlage.notlage.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import faeteam3.Notlage.DVP;
import faeteam3.Notlage.Nachricht;

import javax.persistence.*;
import java.util.Optional;

@Entity(name = "notlage")
public class Notlage extends AbstractNotlage {

    public Notlage(DVP dvp, Nachricht nachricht)
    {
        super(dvp, nachricht);
    }

    public Notlage()
    {
        super();
    }

    /**
     * returns {@code true} if {@link Notlage} is in state {@link Status#BESTÄTIGT}, otherwise {@code false}
     * @return {@code true} if {@link Notlage} is in state {@link Status#BESTÄTIGT}, otherwise {@code false}
     */
    @JsonIgnore
    public boolean isBestaetigt() {
	    return getBestaetigung().isPresent();
    }

    /**
     * returns {@code true} if {@link Notlage} is in state {@link Status#GELOEST}, otherwise {@code false}
     * @return {@code true} if {@link Notlage} is in state {@link Status#GELOEST}, otherwise {@code false}
     */
    @JsonIgnore
    public boolean isGeloest() {
        return getLoesung().isPresent();
    }


//    /**
//     * Marks the {@link Notlage} as confirmed
//     * @param bezugsperson which confirmed the {@link Notlage}
//     */
//    public void markBestaetigt(Bezugsperson bezugsperson) {
//        if(status == Status.GELOEST)
//        {
//            throw new IllegalStateException("Notlage was already solved!");
//        }
//        else if(status == Status.BESTÄTIGT)
//        {
//            throw new IllegalStateException("Notlage was already confirmed!");
//        }
//        this.bestaetigung = new NotlageBestaetigung(bezugsperson);
//        status = Status.BESTÄTIGT;
//    }

    /**
     * Marks the {@link Notlage} as confirmed
     */
    public void markBestaetigt() {
        if(status == Status.GELOEST)
        {
            throw new IllegalStateException("Notlage was already solved!");
        }
        else if(status == Status.BESTÄTIGT)
        {
            throw new IllegalStateException("Notlage was already confirmed!");
        }
        this.bestaetigung = new NotlageBestaetigung();
        status = Status.BESTÄTIGT;
    }

//    /**
//     * Marks the {@link Notlage} as solved
//     * @param bezugsperson which solved the {@link Notlage}
//     */
//    public void markGeloest(Bezugsperson bezugsperson) {
//        if(status == Status.IN_BEARBEITUNG)
//        {
//            throw new IllegalStateException("Notlage wasnt confirmed!");
//        }
//        else if(status == Status.GELOEST)
//        {
//            throw new IllegalStateException("Notlage was already solved!");
//        }
//        Bezugsperson bezugspersonBestaetigung =getBestaetigung().get().getBezugsperson();
//        if(!bezugsperson.equals(bezugspersonBestaetigung))
//        {
//            throw new BezugspersonsArentEqual();
//        }
//        this.loesung = new NotlageLoesung(bezugsperson);
//        status = Status.GELOEST;
//    }

    /**
     * Marks the {@link Notlage} as solved
     */
    public void markGeloest() {
        if(status == Status.IN_BEARBEITUNG)
        {
            throw new IllegalStateException("Notlage wasnt confirmed!");
        }
        else if(status == Status.GELOEST)
        {
            throw new IllegalStateException("Notlage was already solved!");
        }
        this.loesung = new NotlageLoesung();
        status = Status.GELOEST;
    }

    /**
     * returns {@code true} if {@link Notlage} is in state {@link Status#IN_BEARBEITUNG}, otherwise {@code false}
     * @return {@code true} if {@link Notlage} is in state {@link Status#IN_BEARBEITUNG}, otherwise {@code false}
     */
    @JsonIgnore
    public boolean isOffen() {
	    return status == Status.IN_BEARBEITUNG;
    }

    public Optional<NotlageBestaetigung> getBestaetigung() {
        return Optional.ofNullable(bestaetigung);
    }

    public Optional<NotlageLoesung> getLoesung() {
        return Optional.ofNullable(loesung);
    }




}

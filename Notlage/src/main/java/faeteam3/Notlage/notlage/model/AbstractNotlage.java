package faeteam3.Notlage.notlage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import faeteam3.Notlage.DVP;
import faeteam3.Notlage.Nachricht;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractNotlage implements InterfaceNotlage {

    /**
     * Enumeration for all the statuses a {@link Notlage} can be in.
     *
     * @author Florian Bornes
     */
    public enum Status {


        /**
         * {@link Notlage} wurde erstellt und befindet sich in Bearbeitung und kann nicht geändert werden!
         */
        IN_BEARBEITUNG,

        /**
         * Die {@link Notlage} wurde von Bezugsperson zur Lösung angenommen.
         */
        BESTÄTIGT,

        /**
         * Die {@link Notlage} wurde von {@link faeteam3.Notlage.Bezugsperson} gelöst. Befindet sich in finalen Zustand und kann nicht mehr bearbeitet werden.
         */
        GELOEST

    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Long notlageId;

    @JsonIgnore
    @Embedded
    private final DVP dvp;

    @JsonIgnore
    @Embedded
    private final Nachricht nachricht;

    protected Notlage.Status status;

    @JsonIgnore
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    protected NotlageBestaetigung bestaetigung;

    @JsonIgnore
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    protected NotlageLoesung loesung;

    public AbstractNotlage(){
        this(new DVP(), new Nachricht());
    }

    public AbstractNotlage(DVP dvp, Nachricht nachricht)
    {
        this.dvp = dvp;
        this.nachricht = nachricht;
        this.status = Notlage.Status.IN_BEARBEITUNG;
    }

    public Long getNotlageId() {
        return notlageId;
    }

    public DVP getDvp() {
        return dvp;
    }

    public Nachricht getNachricht() {
        return nachricht;
    }

    public Notlage.Status getStatus() {
        return status;
    }
}

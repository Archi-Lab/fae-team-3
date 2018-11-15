package faeteam3.Notlage.notlage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import faeteam3.Notlage.DVP;
import faeteam3.Notlage.Nachricht;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractNotlage implements InterfaceNotlage {

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

    protected Status status;

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
        this.status = Status.IN_BEARBEITUNG;
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

    public Status getStatus() {
        return status;
    }
}

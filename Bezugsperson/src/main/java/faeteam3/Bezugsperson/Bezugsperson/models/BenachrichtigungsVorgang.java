package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="BenachrichtigungsVorgang")
public class BenachrichtigungsVorgang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String notlageURI;

    @Embedded
    private List<Benachrichtigung> benachrichtigungList;


    public BenachrichtigungsVorgang(String notlageURI) {
        this.notlageURI = notlageURI;
        benachrichtigungList = new ArrayList<Benachrichtigung>();
    }

    public String getNotlageURI() {
        return notlageURI;
    }

    /*
        TODO
        - was übergeben?
        - wo den Text speichern?
     */
    public void sendeNeueBenachrichtigung(Long bp_id) {
        Benachrichtigung benachrichtigung = new Benachrichtigung(bp_id);
        this.benachrichtigungList.add(benachrichtigung);
        benachrichtigung.versendeBenachrichtigung();
    }
}

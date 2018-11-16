package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.*;
import java.util.Calendar;

@Entity(name="Benachrichtigung")
public class Benachrichtigung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ben_id;

    private Long bp_id;

    private Calendar gesendet_am;

    private Calendar zugestellt;

    private Calendar gelesen;



    public Benachrichtigung(Long bp_id) {
        this.bp_id = bp_id;
    }

    public void versendeBenachrichtigung() {
        this.gesendet_am = Calendar.getInstance();
    }

    public void setzeAlsZugestellt() {
        this.zugestellt = Calendar.getInstance();
    }

    public void setzeAlsGelesen() {
        this.gelesen = Calendar.getInstance();
    }

    public Calendar getGesendet_am() {
        return gesendet_am;
    }

    public Calendar getZugestellt() {
        return zugestellt;
    }

    public Calendar getGelesen() {
        return gelesen;
    }
}

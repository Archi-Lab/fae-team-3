package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Embeddable
public class Anwesenheit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long anw_id;

    public Anwesenheit() {
    }

    public Anwesenheit(Calendar startDate, Calendar endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Calendar startDate;
    private Calendar endDate;

    public Long getAnw_id() {
        return anw_id;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

}

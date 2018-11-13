package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Embeddable;
import java.util.Calendar;

@Embeddable
public class Anwesenheit {

    public Anwesenheit(Calendar startDate, Calendar endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Calendar startDate;
    private Calendar endDate;


    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

}

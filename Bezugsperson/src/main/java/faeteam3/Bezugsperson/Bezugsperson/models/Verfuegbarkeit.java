package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Embeddable;
import java.util.Calendar;

@Embeddable
public class Verfuegbarkeit {

    public Verfuegbarkeit(Calendar startDate, Calendar endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Calendar startDate;
    private Calendar endDate;


    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
}

package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Calendar;

@Entity(name = "Anwesenheit")
public class Anwesenheit {
    
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	private String anw_id;

    public Anwesenheit() {
    }

    public Anwesenheit(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    
    private LocalDateTime startDate;
    
    
    private LocalDateTime endDate;

    public String getAnw_id() {
        return anw_id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

}

package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Embeddable;

@Embeddable
public class Prioritaet {

    public Prioritaet(int prioritaet) {
        setPrioritaet(prioritaet);
    }

    private int prioritaet;

    public int getPrioritaet() {
        return prioritaet;
    }

    private void setPrioritaet(int prioritaet) {
        if (prioritaet > 0) {
            this.prioritaet = prioritaet;
        }

    }
}

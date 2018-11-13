package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(name="DVP")
public class DVP {

    public DVP(String dvp_id_ext) {
        this.dvp_id_ext = dvp_id_ext;
        this.bps = new ArrayList<BP_prio>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dvp_id;

    private final String dvp_id_ext;

    @Embeddable
    private class BP_prio implements Comparable<BP_prio> {

        public BP_prio(Long bp_id, Prioritaet prioritaet) {
            this.bp_id = bp_id;
            this.prioritaet = prioritaet;
        }

        public Long bp_id;
        public Prioritaet prioritaet;

        @Override
        public int compareTo(BP_prio o) {
            return this.prioritaet.getPrioritaet() - o.prioritaet.getPrioritaet();
        }
    }

    @Embedded
    private List<BP_prio> bps;

    public Long getBPByOrder(int order) {
        return bps.get(order).bp_id;
    }

    public void add_or_update_bp(Long bp_id, Prioritaet prioritaet) {
        for (BP_prio bp_prio: bps) {
            if (bp_prio.bp_id == bp_id) {
                /* update */
                bp_prio.prioritaet = prioritaet;
                Collections.sort(bps);

                return;
            }
        }

        bps.add(new BP_prio(bp_id, prioritaet));
        Collections.sort(bps);
    }

    public Long delete_bp(Long bp_id) {
        for (BP_prio bp_prio: bps) {
            if (bp_prio.bp_id == bp_id) {
                bps.remove(bp_prio);

                return bp_id;
            }
        }
        return null;
    }

    public Long getDvp_id() {
        return dvp_id;
    }

    public String getDvp_id_ext() {
        return dvp_id_ext;
    }

}

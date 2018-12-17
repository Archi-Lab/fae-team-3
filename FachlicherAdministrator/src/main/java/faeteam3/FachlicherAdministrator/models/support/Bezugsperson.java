package faeteam3.FachlicherAdministrator.models.support;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class Bezugsperson {


    private Long bp_id;

    private String bp_id_ext;

    private List<Long> anwesenheitList = new ArrayList<>();

    public Bezugsperson() {
    }

    public Bezugsperson(String bp_id_ext) {
        this.bp_id_ext = bp_id_ext;
    }


    public Long getBp_id() {
        return bp_id;
    }

    public String getBp_id_ext() {
        return bp_id_ext;
    }

    public List<Long> getAnwesenheitList() {
        return anwesenheitList;
    }


}

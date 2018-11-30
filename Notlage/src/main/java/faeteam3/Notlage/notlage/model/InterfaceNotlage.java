package faeteam3.Notlage.notlage.model;


import java.util.Optional;

public interface InterfaceNotlage {
    /**
     * returns {@code true} if {@link Notlage} is in state {@link Status#BESTÄTIGT}, otherwise {@code false}
     * @return {@code true} if {@link Notlage} is in state {@link Status#BESTÄTIGT}, otherwise {@code false}
     */
    boolean isBestaetigt();

    /**
     * returns {@code true} if {@link Notlage} is in state {@link Status#GELOEST}, otherwise {@code false}
     * @return {@code true} if {@link Notlage} is in state {@link Status#GELOEST}, otherwise {@code false}
     */
    boolean isGeloest();


//    /**
//     * Marks the {@link Notlage} as confirmed
//     * @param bezugsperson which confirmed the {@link Notlage}
//     */
//    void markBestaetigt(Bezugsperson bezugsperson);
//
//    /**
//     * Marks the {@link Notlage} as solved
//     * @param bezugsperson which solved the {@link Notlage}
//     */
//    void markGeloest(Bezugsperson bezugsperson);

    /**
     * Marks the {@link Notlage} as confirmed
     */
    void markBestaetigt();

    /**
     * Marks the {@link Notlage} as solved
     */
    void markGeloest();

    /**
     * returns {@code true} if {@link Notlage} is in state {@link Status#IN_BEARBEITUNG}, otherwise {@code false}
     * @return {@code true} if {@link Notlage} is in state {@link Status#IN_BEARBEITUNG}, otherwise {@code false}
     */
    boolean isOffen();

    Optional<NotlageBestaetigung> getBestaetigung();

    Optional<NotlageLoesung> getLoesung();

    Status getStatus();
}

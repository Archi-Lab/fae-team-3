package faeteam3.Notlage.model;

/**
 * Enumeration for all the statuses a {@link Notlage} can be in.
 *
 * @author Florian Bornes
 */
public enum Status {


    /**
     * {@link Notlage} wurde erstellt und befindet sich in Bearbeitung und kann nicht geändert werden!
     */
    IN_BEARBEITUNG,

    /**
     * Die {@link Notlage} wurde von Bezugsperson zur Lösung angenommen.
     */
    BESTÄTIGT,

    /**
     * Die {@link Notlage} wurde von {@link faeteam3.Notlage.Bezugsperson} gelöst. Befindet sich in finalen Zustand und kann nicht mehr bearbeitet werden.
     */
    GELOEST

}

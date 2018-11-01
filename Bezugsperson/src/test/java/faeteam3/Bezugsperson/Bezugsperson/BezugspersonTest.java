package faeteam3.Bezugsperson.Bezugsperson;

import static org.junit.Assert.*;

import faeteam3.Bezugsperson.Bezugsperson.models.*;
import faeteam3.Bezugsperson.Bezugsperson.repositories.BezugspersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest

public class BezugspersonTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BezugspersonTest.class);

    @Autowired
    private BezugspersonRepository bezugspersonRepository;

    @Test
    public void createBezugspersonExpectCreated() {
        final Bezugsperson bezugsperson = new Bezugsperson();

        bezugsperson.setPrioritaet(10);
        bezugsperson.setAnfragen_insgesamt(50);
        bezugsperson.setAnfragen_beantwortet(45);

        final Kontaktdaten kontaktdaten = new Kontaktdaten("Max", "Mustermann", "Musterstrasse 50", "01578123456");
        bezugsperson.setKontaktdaten(kontaktdaten);

        final List<Verfuegbarkeit> verfuegbarkeitList = new ArrayList<Verfuegbarkeit>();
        verfuegbarkeitList.add(new Verfuegbarkeit(Calendar.getInstance(), Calendar.getInstance()));
        bezugsperson.setVerfuegbarkeitList(verfuegbarkeitList);

        final List<Kommunikationskanal> kommunikationskanalList = new ArrayList<Kommunikationskanal>();
        kommunikationskanalList.add(new Kommunikationskanal("Meine Email", Kommunikationsart.EMAIL, "MeineMail@meinedomain.de"));
        bezugsperson.setKommunikationskanalList(kommunikationskanalList);

        LOGGER.info("Bezugsperson to save:");
        LOGGER.info(bezugsperson.toString());

        final Bezugsperson savedBezugsperson = this.bezugspersonRepository.save(bezugsperson);

        assertNotNull(savedBezugsperson);
        assertNotNull(savedBezugsperson.getBez_id());
        assertEquals(bezugsperson.getPrioritaet(), savedBezugsperson.getPrioritaet());
        assertEquals(bezugsperson.getAnfragen_insgesamt(), savedBezugsperson.getAnfragen_insgesamt());
        assertEquals(bezugsperson.getAnfragen_beantwortet(), savedBezugsperson.getAnfragen_beantwortet());

        assertEquals(bezugsperson.getKontaktdaten().getVorname(), savedBezugsperson.getKontaktdaten().getVorname());
        assertEquals(bezugsperson.getKontaktdaten().getNachname(), savedBezugsperson.getKontaktdaten().getNachname());
        assertEquals(bezugsperson.getKontaktdaten().getAdresse(), savedBezugsperson.getKontaktdaten().getAdresse());
        assertEquals(bezugsperson.getKontaktdaten().getTelefonnummer(), savedBezugsperson.getKontaktdaten().getTelefonnummer());

        assertEquals(bezugsperson.getVerfuegbarkeitList().size(), savedBezugsperson.getVerfuegbarkeitList().size());
        for (int i = 0; i < bezugsperson.getVerfuegbarkeitList().size(); i++) {
            assertEquals(bezugsperson.getVerfuegbarkeitList().get(i).getStartDate(), savedBezugsperson.getVerfuegbarkeitList().get(i).getStartDate());
            assertEquals(bezugsperson.getVerfuegbarkeitList().get(i).getEndDate(), savedBezugsperson.getVerfuegbarkeitList().get(i).getEndDate());
        }

        assertEquals(bezugsperson.getKommunikationskanalList().size(), savedBezugsperson.getKommunikationskanalList().size());
        for (int i = 0; i < bezugsperson.getKommunikationskanalList().size(); i++) {
            assertEquals(bezugsperson.getKommunikationskanalList().get(i).getKanalBezeichnung(), savedBezugsperson.getKommunikationskanalList().get(i).getKanalBezeichnung());
            assertEquals(bezugsperson.getKommunikationskanalList().get(i).getKommunikationsart(), savedBezugsperson.getKommunikationskanalList().get(i).getKommunikationsart());
            assertEquals(bezugsperson.getKommunikationskanalList().get(i).getTechAdresse(), savedBezugsperson.getKommunikationskanalList().get(i).getTechAdresse());
        }

        LOGGER.info("Bezugsperson was saved:");
        LOGGER.info(savedBezugsperson.toString());

    }
}

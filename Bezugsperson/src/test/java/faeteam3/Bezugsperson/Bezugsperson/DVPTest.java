package faeteam3.Bezugsperson.Bezugsperson;

import faeteam3.Bezugsperson.Bezugsperson.models.*;
import faeteam3.Bezugsperson.Bezugsperson.repositories.DVPRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest

public class DVPTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DVPTest.class);

    @Autowired
    private DVPRepository DVPRepository;

    @Test
    public void createDVPExpectCreated() {
        final DVP DVP = new DVP("12345");

        DVP.add_or_update_bp(9L, new Prioritaet(3));
        DVP.add_or_update_bp(5L, new Prioritaet(1));
        DVP.add_or_update_bp(2L, new Prioritaet(2));

        LOGGER.info("DVP to save:");
        LOGGER.info(DVP.toString());

        final DVP savedDVP = this.DVPRepository.save(DVP);

        assertNotNull(savedDVP);
        assertNotNull(savedDVP.getDvp_id());

        assertEquals(DVP.getDvp_id_ext(), savedDVP.getDvp_id_ext());

        for (int i = 0; i < 3; i++) {
            assertEquals(DVP.getBPByOrder(i), DVP.getBPByOrder(i));
        }

        LOGGER.info("DVP was saved:");
        LOGGER.info(savedDVP.toString());

        LOGGER.info("Check for correct Order of BPs");
        assertEquals(DVP.getBPByOrder(0), (Long) 5L);
        assertEquals(DVP.getBPByOrder(1), (Long) 2L);
        assertEquals(DVP.getBPByOrder(2), (Long) 9L);

        LOGGER.info("richtig sortiert!");
        /*
        DVP.setPrioritaet(10);
        DVP.setAnfragen_insgesamt(50);
        DVP.setAnfragen_beantwortet(45);

        final Kontaktdaten kontaktdaten = new Kontaktdaten("Max", "Mustermann", "Musterstrasse 50", "01578123456");
        DVP.setKontaktdaten(kontaktdaten);

        final List<Anwesenheit> anwesenheitList = new ArrayList<Anwesenheit>();
        anwesenheitList.add(new Anwesenheit(Calendar.getInstance(), Calendar.getInstance()));
        DVP.setAnwesenheitList(anwesenheitList);

        final List<Kommunikationskanal> kommunikationskanalList = new ArrayList<Kommunikationskanal>();
        kommunikationskanalList.add(new Kommunikationskanal("Meine Email", Kommunikationsart.EMAIL, "MeineMail@meinedomain.de"));
        DVP.setKommunikationskanalList(kommunikationskanalList);

        LOGGER.info("DVP to save:");
        LOGGER.info(DVP.toString());

        final DVP savedDVP = this.DVPRepository.save(DVP);

        assertNotNull(savedDVP);
        assertNotNull(savedDVP.getBez_id());
        assertEquals(DVP.getPrioritaet(), savedDVP.getPrioritaet());
        assertEquals(DVP.getAnfragen_insgesamt(), savedDVP.getAnfragen_insgesamt());
        assertEquals(DVP.getAnfragen_beantwortet(), savedDVP.getAnfragen_beantwortet());

        assertEquals(DVP.getKontaktdaten().getVorname(), savedDVP.getKontaktdaten().getVorname());
        assertEquals(DVP.getKontaktdaten().getNachname(), savedDVP.getKontaktdaten().getNachname());
        assertEquals(DVP.getKontaktdaten().getAdresse(), savedDVP.getKontaktdaten().getAdresse());
        assertEquals(DVP.getKontaktdaten().getTelefonnummer(), savedDVP.getKontaktdaten().getTelefonnummer());

        assertEquals(DVP.getAnwesenheitList().size(), savedDVP.getAnwesenheitList().size());
        for (int i = 0; i < DVP.getAnwesenheitList().size(); i++) {
            assertEquals(DVP.getAnwesenheitList().get(i).getStartDate(), savedDVP.getAnwesenheitList().get(i).getStartDate());
            assertEquals(DVP.getAnwesenheitList().get(i).getEndDate(), savedDVP.getAnwesenheitList().get(i).getEndDate());
        }

        assertEquals(DVP.getKommunikationskanalList().size(), savedDVP.getKommunikationskanalList().size());
        for (int i = 0; i < DVP.getKommunikationskanalList().size(); i++) {
            assertEquals(DVP.getKommunikationskanalList().get(i).getKanalBezeichnung(), savedDVP.getKommunikationskanalList().get(i).getKanalBezeichnung());
            assertEquals(DVP.getKommunikationskanalList().get(i).getKommunikationsart(), savedDVP.getKommunikationskanalList().get(i).getKommunikationsart());
            assertEquals(DVP.getKommunikationskanalList().get(i).getTechAdresse(), savedDVP.getKommunikationskanalList().get(i).getTechAdresse());
        }

        LOGGER.info("DVP was saved:");
        LOGGER.info(savedDVP.toString());
        */
    }
}

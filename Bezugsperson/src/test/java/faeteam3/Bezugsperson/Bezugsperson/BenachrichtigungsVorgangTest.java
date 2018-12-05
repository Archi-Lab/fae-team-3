package faeteam3.Bezugsperson.Bezugsperson;

import static org.junit.Assert.*;

import faeteam3.Bezugsperson.Bezugsperson.models.*;
import faeteam3.Bezugsperson.Bezugsperson.repositories.BezugspersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest

public class BenachrichtigungsVorgangTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BezugspersonTest.class);

    @Autowired
    private BezugspersonRepository bezugspersonRepository;

    @Test
    public void createBezugspersonExpectCreated() {
    	/*
        final Bezugsperson bezugsperson = new Bezugsperson("12345");

        bezugsperson.addAnwesenheit(new Anwesenheit(Calendar.getInstance(), Calendar.getInstance()));
        bezugsperson.addKommunikationskanal(new Kommunikationskanal("Meine Email", Kommunikationsart.EMAIL, new EMailAdresse("example@invalid.com")));


        LOGGER.info("Bezugsperson to save:");
        LOGGER.info(bezugsperson.toString());

        final Bezugsperson savedBezugsperson = this.bezugspersonRepository.save(bezugsperson);

        assertNotNull(savedBezugsperson);
        assertNotNull(savedBezugsperson.getBp_id());

        assertEquals(bezugsperson.getAnwesenheitList().size(), savedBezugsperson.getAnwesenheitList().size());
        for (int i = 0; i < bezugsperson.getAnwesenheitList().size(); i++) {
            assertEquals(bezugsperson.getAnwesenheitList().get(i).getStartDate(), savedBezugsperson.getAnwesenheitList().get(i).getStartDate());
            assertEquals(bezugsperson.getAnwesenheitList().get(i).getEndDate(), savedBezugsperson.getAnwesenheitList().get(i).getEndDate());
        }

        assertEquals(bezugsperson.getKommunikationskanalList().size(), savedBezugsperson.getKommunikationskanalList().size());
        for (int i = 0; i < bezugsperson.getKommunikationskanalList().size(); i++) {
            assertEquals(bezugsperson.getKommunikationskanalList().get(i).getKanalBezeichnung(), savedBezugsperson.getKommunikationskanalList().get(i).getKanalBezeichnung());
            assertEquals(bezugsperson.getKommunikationskanalList().get(i).getKommunikationsart(), savedBezugsperson.getKommunikationskanalList().get(i).getKommunikationsart());
            assertEquals(bezugsperson.getKommunikationskanalList().get(i).getTechAdresse(), savedBezugsperson.getKommunikationskanalList().get(i).getTechAdresse());
        }

        LOGGER.info("Bezugsperson was saved:");
        LOGGER.info(savedBezugsperson.toString());
        */
    }
    
}

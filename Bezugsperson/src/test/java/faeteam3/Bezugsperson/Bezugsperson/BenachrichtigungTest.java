package faeteam3.Bezugsperson.Bezugsperson;

import faeteam3.Bezugsperson.Bezugsperson.models.*;
import faeteam3.Bezugsperson.Bezugsperson.repositories.BenachrichtigungRepository;
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

public class BenachrichtigungTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BenachrichtigungTest.class);

    @Autowired
    private BenachrichtigungRepository BenachrichtigungRepository;

    @Test
    public void createBenachrichtigungExpectCreated() {
        final Benachrichtigung Benachrichtigung = new Benachrichtigung(12345L);

        Benachrichtigung.versendeBenachrichtigung();
        Benachrichtigung.setzeAlsZugestellt();
        Benachrichtigung.setzeAlsGelesen();

        LOGGER.info("Benachrichtigung to save:");
        LOGGER.info(Benachrichtigung.toString());

        final Benachrichtigung savedBenachrichtigung = this.BenachrichtigungRepository.save(Benachrichtigung);

        assertNotNull(savedBenachrichtigung);

        assertEquals(Benachrichtigung.getGesendet_am(), savedBenachrichtigung.getGesendet_am());
        assertEquals(Benachrichtigung.getZugestellt(), savedBenachrichtigung.getZugestellt());
        assertEquals(Benachrichtigung.getGelesen(), savedBenachrichtigung.getGelesen());

        LOGGER.info("Benachrichtigung was saved:");
        LOGGER.info(savedBenachrichtigung.toString());

    }
}

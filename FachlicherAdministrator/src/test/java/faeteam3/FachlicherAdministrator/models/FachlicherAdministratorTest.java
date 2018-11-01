package faeteam3.FachlicherAdministrator.models;

import faeteam3.FachlicherAdministrator.repositories.FachlicherAdministratorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FachlicherAdministratorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FachlicherAdministrator.class);

    @Autowired
    private FachlicherAdministratorRepository fachlicherAdministratorRepository;

    @Test
    public void createFachlicherAdministratorExpectedCreated(){
        final FachlicherAdministrator fachlicherAdministrator = new FachlicherAdministrator();

       LOGGER.info("FachlicherAdministrator to save:");
       LOGGER.info(fachlicherAdministrator.toString());

       final FachlicherAdministrator savedFachlicherAdministrator = this.fachlicherAdministratorRepository.save(fachlicherAdministrator);

       assertNotNull(savedFachlicherAdministrator);
       assertNotNull(savedFachlicherAdministrator.getId());

       LOGGER.info("FachlicherAdministrator was saved:");
       LOGGER.info(savedFachlicherAdministrator.toString());
    }
}

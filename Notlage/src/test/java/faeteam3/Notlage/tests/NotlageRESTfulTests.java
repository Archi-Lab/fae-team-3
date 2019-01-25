package faeteam3.Notlage.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import faeteam3.Notlage.controller.NotlageController;
import faeteam3.Notlage.model.Nachricht;
import faeteam3.Notlage.model.Notlage;
import faeteam3.Notlage.model.Status;
import faeteam3.Notlage.repository.NotlageRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class NotlageRESTfulTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotlageController.class);
	

	@Autowired
    private NotlageRepository notlageRepository;

    public NotlageRESTfulTests() 
    {
    	
    }
    
	@Test
    public void intern_Basis()
    {
		Long bp_id=43L;
		Nachricht nachricht = new Nachricht("17","ungeRou",3L,"payload");
		Notlage notlage = new Notlage();
		notlage.setExtraDatat(nachricht.getPayload());
		notlage.setDvp(nachricht.getDvpid());
		
		notlage.bestaetigeNotlage(bp_id);
		Assert.assertEquals(true,notlage.isBestaetigt());
		Assert.assertEquals(bp_id,notlage.getNotlageBestaetigung().getNotlageBestaetigungid());
		Assert.assertEquals(Status.BESTÄTIGT,notlage.getStatus());
		
		notlage.loeseNotlage(bp_id);
		Assert.assertEquals(true,notlage.isGeloest());
		Assert.assertEquals(bp_id,notlage.getNotlageLoesung().getNotlageLoesungid());
		Assert.assertEquals(Status.GELOEST,notlage.getStatus());
    }
	
	@Test
    public void intern_Repository()
    {
		Long bp_id=43L;
		Long dvp_id=12L;
		Nachricht nachricht = new Nachricht("33","ungeVer",dvp_id,"payload");
		Notlage not = new Notlage();
		not.setExtraDatat(nachricht.getPayload());
		not.setDvp(nachricht.getDvpid());
		notlageRepository.save(not);
		
		List<Notlage> list_nots = notlageRepository.findByDvpInternDvpID(dvp_id);
		Notlage notlage = list_nots.get(0);
		Assert.assertEquals(notlage.getDvp().getDvpID(),dvp_id);
		
		notlage.bestaetigeNotlage(bp_id);
		Assert.assertEquals(true,notlage.isBestaetigt());
		Assert.assertEquals(bp_id,notlage.getNotlageBestaetigung().getNotlageBestaetigungid());
		Assert.assertEquals(Status.BESTÄTIGT,notlage.getStatus());
		
		notlage.loeseNotlage(bp_id);
		Assert.assertEquals(true,notlage.isGeloest());
		Assert.assertEquals(bp_id,notlage.getNotlageLoesung().getNotlageLoesungid());
		Assert.assertEquals(Status.GELOEST,notlage.getStatus());
    }

}

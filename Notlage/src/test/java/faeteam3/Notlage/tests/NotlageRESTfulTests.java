package faeteam3.Notlage.tests;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import faeteam3.Notlage.controller.NotlageController;
import faeteam3.Notlage.kafka.SendeEinheit;
import faeteam3.Notlage.model.Nachricht;
import faeteam3.Notlage.model.Notlage;
import faeteam3.Notlage.model.Status;
import faeteam3.Notlage.model.support.UngeRou;
import faeteam3.Notlage.model.support.UngeVer;
import faeteam3.Notlage.repository.NotlageRepository;
import java.util.UUID;



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
		UUID bp_id=UUID.randomUUID();
		Nachricht nachricht = new Nachricht(UUID.randomUUID(),"ungewöhnliche Route",UUID.randomUUID(),"payload");
		Notlage notlage = new Notlage();
		notlage.setExtraDatat(nachricht.getPayload());
		notlage.setDvp(nachricht.getDvpid());
		
		notlage.bestaetigeNotlage(bp_id);
		Assert.assertEquals(true,notlage.isBestaetigt());
		Assert.assertEquals(bp_id,notlage.getNotlageBestaetigung().getBestaetigerBpID());
		Assert.assertEquals(Status.BESTÄTIGT,notlage.getStatus());
		
		notlage.loeseNotlage(bp_id);
		Assert.assertEquals(true,notlage.isGeloest());
		Assert.assertEquals(bp_id,notlage.getNotlageLoesung().getLoeserBpID());
		Assert.assertEquals(Status.GELOEST,notlage.getStatus());
    }
	
	@Test
    public void intern_Repository()
    {
		UUID bp_id=UUID.randomUUID();
		UUID dvp_id=UUID.randomUUID();
		Nachricht nachricht = new Nachricht(dvp_id,"ungewöhnliches Verhalten",UUID.randomUUID(),"payload");
		Notlage not = new Notlage();
		not.setExtraDatat(nachricht.getPayload());
		not.setDvp(nachricht.getDvpid());
		not.setIdOrigin(nachricht.getOrigin_id());
		not.setOrigin(nachricht.getOrigin());
		Notlage nnn = notlageRepository.save(not);
		System.out.println(nnn.getDvp().getDvpID());
		System.out.println(dvp_id);

		System.out.println(not.getDvp().getDvpID());
		
		List<Notlage> list_nots = notlageRepository.findByDvpInternDvpID(dvp_id);
		LOGGER.info(String.valueOf(list_nots.size()));
		System.out.println(list_nots);
		//Notlage notlage = list_nots.get(0);
		Notlage notlage = nnn;
		Assert.assertEquals(notlage.getDvp().getDvpID(),dvp_id);
		
		notlage.bestaetigeNotlage(bp_id);
		Assert.assertEquals(true,notlage.isBestaetigt());
		Assert.assertEquals(bp_id,notlage.getNotlageBestaetigung().getBestaetigerBpID());
		Assert.assertEquals(Status.BESTÄTIGT,notlage.getStatus());
		
		notlage.loeseNotlage(bp_id);
		Assert.assertEquals(true,notlage.isGeloest());
		Assert.assertEquals(bp_id,notlage.getNotlageLoesung().getLoeserBpID());
		Assert.assertEquals(Status.GELOEST,notlage.getStatus());
    }
	
	

}

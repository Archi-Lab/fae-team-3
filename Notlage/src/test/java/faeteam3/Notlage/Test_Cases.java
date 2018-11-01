package faeteam3.Notlage;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=NotlageApplicationTests.class)
@SpringBootTest
@ComponentScan(basePackages = {"faeteam3.Notlage"} )
public class Test_Cases 
{

  private static final Logger LOGGER = LoggerFactory.getLogger(Test_Cases.class);

  @Autowired
  private DVPRepository dvp_repository;
  
  @Autowired
  private NotlageRepository notlage_repository;
  
  @Autowired
  @Qualifier("derService")
  private MyServer service;
  
  
  
  @Test
  public void test_neue_Notlage()
  {
	  
	  final DVP person1 = new DVP(122l);
	  final DVP savedPerson1 = this.dvp_repository.save(person1);

	  Benachrichtigung neue_benach = new Benachrichtigung(savedPerson1.getRegisterId(),"infos");
	  final Notlage not1 = new Notlage("1,9",person1,neue_benach);
	  Nachricht nach = new Nachricht("  ", "  ", savedPerson1.getRegisterId());
			  
	  service.nachricht_eingang(nach);
	  List<Notlage> list1 = notlage_repository.findBybetroffener(savedPerson1);
	  for (Notlage not : list1) 
	  {
		  assertEquals(not.get_DVP().getRegisterId(), person1.getRegisterId());
	  }
	  
	  service.bestätigung_eingang(savedPerson1.getRegisterId());
	  List<Notlage> list2 = notlage_repository.findBybetroffener(savedPerson1);
	  for (Notlage not : list2) 
	  {
		  assertEquals(not.get_benachrichtigung().isWurde_bestätigt(), true);
	  }
	  
	  service.bestätigung_notlage_behoben_eingang(savedPerson1.getRegisterId());
	  List<Notlage> list3 = notlage_repository.findBybetroffener(savedPerson1);
	  for (Notlage not : list3) 
	  {
		  assertEquals(not.ist_behoben(), true);
	  }
	  
	    
  }
  
  @Test
  public void test_neue_DVP()
  {
	  
	  

	  LOGGER.info("sssssssssss ---------------------");

	  LOGGER.info("sssssssssss ---------------------");

	  LOGGER.info("sssssssssss ---------------------");
	  Iterable<Notlage> sa = notlage_repository.findAll();
	   
	  for (Notlage dvp : sa) 
	  {
		  LOGGER.info("staaaaa "+dvp.toString());
		
	  }
	  LOGGER.info("ENDE  ---------------------");

	  
	  
	  final DVP person1 = new DVP(1l);
	  final DVP person2 = new DVP(2l);
	  final DVP person3 = new DVP(3l);
	  
	  LOGGER.info("sssssssssss ---------------------");
	  LOGGER.info(String.valueOf(person1.get_id()));
	    
	  service.neue_Person(person1);
	  service.neue_Person(person2);
	  service.neue_Person(person3);
	  Optional<DVP> list1 = dvp_repository.findByRegisterId(person1.getRegisterId());
	  Optional<DVP> list2 = dvp_repository.findByRegisterId(person2.getRegisterId());
	  Optional<DVP> list3 = dvp_repository.findByRegisterId(person3.getRegisterId());
	  if (list1.isPresent())
	  {
		  assertEquals(list1.get().getRegisterId(), person1.getRegisterId());
	  }
	  else
	  {
		  assertEquals(true,false);
	  }
	  
	  if (list1.isPresent())
	  {
		  assertEquals(list2.get().getRegisterId(), person2.getRegisterId());
	  }
	  else
	  {
		  assertEquals(true,false);
	  }
	  
	  if (list1.isPresent())
	  {
		  assertEquals(list3.get().getRegisterId(), person3.getRegisterId());
	  }
	  else
	  {
		  assertEquals(true,false);
	  }

  }

  @Test
  public void createPersonExpectCreated(){
	  
	  LOGGER.info("spam ---------------------");
	  LOGGER.info("spam ---------------------");
	  LOGGER.info("spam ---------------------");
	  LOGGER.info("spam ---------------------");
	  LOGGER.info("spam ---------------------");
	  LOGGER.info("spam ---------------------");
	  LOGGER.info("spam ---------------------");
	  LOGGER.info("spam ---------------------");
    final DVP person1 = new DVP(1l);
    final DVP person2 = new DVP(2l);
    final DVP person3 = new DVP(3l);
    final DVP person4 = new DVP(4l);
    
    LOGGER.info(String.valueOf("as 7"+person1.get_id()));
    
    final DVP savedPerson1 = this.dvp_repository.save(person1);
    final DVP savedPerson2 = this.dvp_repository.save(person2);
    final DVP savedPerson3 = this.dvp_repository.save(person3);
    final DVP savedPerson4 = this.dvp_repository.save(person4);
    
    LOGGER.info(String.valueOf("as 8"+person1.get_id()));
    
    

//    person.setAge(23);
//    person.setGender(Gender.MALE);
//    person.setForename("Jann");
//    person.setSurname("Deterling");

    LOGGER.info("Person to save:");
    assertNotNull(savedPerson1);
    assertNotNull(savedPerson1.get_id());
    
    assertNotNull(savedPerson2);
    assertNotNull(savedPerson2.get_id());
    
    assertNotNull(savedPerson3);
    assertNotNull(savedPerson3.get_id());
    
    assertNotNull(savedPerson4);
    assertNotNull(savedPerson4.get_id());

    assertEquals(person1.get_id(), savedPerson1.get_id());
    assertEquals(person2.get_id(), savedPerson2.get_id());
    assertEquals(person3.get_id(), savedPerson3.get_id());
    assertEquals(person4.get_id(), savedPerson4.get_id());
    

    LOGGER.info(String.valueOf("as1 "+person1.get_id()));

	LOGGER.info(String.valueOf("as2 "+savedPerson1.get_id()));



	Benachrichtigung neue_benach = new Benachrichtigung(savedPerson2.getRegisterId(),"infos");
    final Notlage not1 = new Notlage("1,1",person2,neue_benach);

    final Notlage saved_not1 = this.notlage_repository.save(not1);
    
    assertNotNull(saved_not1);
    assertNotNull(saved_not1.get_id());

    
    assertEquals(person2.get_id(), saved_not1.get_betroffener().get_id());
    LOGGER.info(person2.get_id()+"  "+saved_not1.get_betroffener().get_id());
  
    
    LOGGER.info("ENDE");
    

  }

}

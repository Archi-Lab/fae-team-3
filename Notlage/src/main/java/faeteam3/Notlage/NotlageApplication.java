package faeteam3.Notlage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotlageApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotlageApplication.class, args);
	}
	
	
	
	
	
	
//	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	
	// coomm
//	@Bean
//	public CommandLineRunner demo(NotlageRepository repository) {
//		return (args) -> {
//			// save a couple of customers
//			repository.save(new Notlage("Jack", "Bauer"));
//			repository.save(new Notlage("Chloe", "O'Brian"));
//			repository.save(new Notlage("Kim", "Bauer"));
//			repository.save(new Notlage("David", "Palmer"));
//			repository.save(new Notlage("Michelle", "Dessler"));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Notlage customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			repository.findById(1L)
//				.ifPresent(customer -> {
//					log.info("Customer found with findById(1L):");
//					log.info("--------------------------------");
//					log.info(customer.toString());
//					log.info("");
//				});
//
//			// fetch customers by last name
//			log.info("Customer found with findBy xx LastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByOrt("Bauer").forEach(bauer -> {
//				log.info(bauer.toString());
//			});
//			// for (Customer bauer : repository.findByLastName("Bauer")) {
//			// 	log.info(bauer.toString());
//			// }
//			log.info("");
//		};
//	}
}

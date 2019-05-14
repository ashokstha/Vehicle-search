package io.ashok;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VehicleApplication {

	private final static Logger logger = Logger.getLogger(VehicleApplication.class);
	
	public static void main(String[] args) {
		logger.debug("--------- Spring application started--------");
		SpringApplication.run(VehicleApplication.class, args);
		
	}

}

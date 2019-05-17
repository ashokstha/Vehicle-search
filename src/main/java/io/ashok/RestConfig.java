package io.ashok;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import io.ashok.vehicle.VehicleController;


@Configuration
public class RestConfig extends ResourceConfig {

	public RestConfig() {
		this.register(VehicleController.class);
		this.packages("io.ashok.vehicle");
	}

}

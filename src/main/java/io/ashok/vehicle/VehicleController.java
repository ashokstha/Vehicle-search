package io.ashok.vehicle;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ashok.util.RandomString;

@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VehicleController {

	// private final static Logger logger =
	// Logger.getLogger(VehicleController.class);

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping("/vehicles")
	public Response getAllVehicles() {
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		VehicleSearchResult vehiclesResult = new VehicleSearchResult(vehicles);
		return Response.ok().entity(vehiclesResult).build();
	}

	@RequestMapping("/vehicles/{vin}")
	public Response findVehicleByVin(@PathVariable String vin) {
		Optional<Vehicle> vehicle = vehicleService.getVehicle(vin);
		return Response.ok().entity(vehicle).build();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/vehicles")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response addVehicle(@RequestBody Vehicle vehicle) {
		vehicle.setVin(RandomString.getALphaNum(17));
		vehicleService.addVehicle(vehicle);
		return Response.noContent().build();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}")
	public Response modifyVehicle(@RequestBody Vehicle vehicle, @PathVariable String vin) {
		vehicleService.updateVehicle(vehicle, vin);
		return Response.noContent().build();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}/{statusValue}")
	public Response changeVehicleStatus(@PathVariable String vin, @PathVariable String statusValue) {
		vehicleService.updateStatus(vin, statusValue);
		return Response.noContent().build();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/{vin}")
	public Response deleteVehicle(@RequestBody Vehicle vehicle, @PathVariable String vin) {
		vehicleService.deleteVehicle(vin);
		return Response.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET, value="/vehicles/make/{makeValue}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response searchVehicleByMake(@PathVariable String makeValue) {
		List<Vehicle> vehicles = vehicleService.searchByMake(makeValue);
		VehicleSearchResult vehiclesResult = new VehicleSearchResult(vehicles);
		return Response.ok().entity(vehiclesResult).build();
	}

	/*
	 * Test this model
	 */
	@RequestMapping(method=RequestMethod.GET, value="/vehicles/model/{model}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response searchVehicleByModel(@PathVariable String model) {
		List<Vehicle> vehicles = vehicleService.searchByModel(model);
		VehicleSearchResult vehiclesResult = new VehicleSearchResult(vehicles);
		return Response.ok().entity(vehiclesResult).build();
	}

	/*
	 * Test this model
	 */
	@RequestMapping(method=RequestMethod.GET, value="/vehicles/year/{year}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response searchVehicleByYear(@PathVariable String year) {
		List<Vehicle> vehicles = vehicleService.searchByYear(year);
		VehicleSearchResult vehiclesResult = new VehicleSearchResult(vehicles);
		return Response.ok().entity(vehiclesResult).build();
	}

	/*
	 * Test this model
	 */
	@RequestMapping(method=RequestMethod.GET, value="/vehicles/price/{price}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response searchVehicleByPrice(@PathVariable String price) {
		List<Vehicle> vehicles = vehicleService.searchByPrice(price);
		VehicleSearchResult vehiclesResult = new VehicleSearchResult(vehicles);
		return Response.ok().entity(vehiclesResult).build();
	}

}

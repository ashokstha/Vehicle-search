package io.ashok.vehicle;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.ashok.util.RandomString;

@Controller
//@Path("/vehicles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VehicleController {

	// private final static Logger logger =
	// Logger.getLogger(VehicleController.class);

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping("/vehicles")
	//@GET
	public Response getAllVehicles() {
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		VehicleSearchResult vehiclesResult = new VehicleSearchResult(vehicles);
		return Response.ok().entity(vehiclesResult).build();
	}

	// @RequestMapping("/vehicles/{vin}")
	@GET
	@Path("/{vin}")
	public Response findVehicleByVin(@PathParam("vin") String vin) {
		Optional<Vehicle> vehicle = vehicleService.getVehicle(vin);
		return Response.ok().entity(vehicle).build();
	}

	// @RequestMapping(method = RequestMethod.POST, value = "/vehicles")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response addVehicle(Vehicle vehicle) {
		vehicle.setVin(RandomString.getALphaNum(17));
		vehicleService.addVehicle(vehicle);
		return Response.noContent().build();
	}

	// @RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}")
	@PUT
	@Path("/{vin}")
	public Response modifyVehicle(Vehicle vehicle, @PathParam("vin") String vin) {
		vehicleService.updateVehicle(vehicle, vin);
		return Response.noContent().build();
	}

	// @RequestMapping(method = RequestMethod.PUT, value =
	// "/vehicles/{vin}/status/{statusValue}")
	@PUT
	@Path("/{vin}/status/{status}")
	public Response changeVehicleStatus(@PathParam("vin") String vin, @PathParam("status") String status) {
		vehicleService.updateStatus(vin, status);
		return Response.noContent().build();
	}

	// @RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/{vin}")
	@DELETE
	@Path("/{vin}")
	public Response deleteVehicle(@PathParam("vin") String vin) {
		vehicleService.deleteVehicle(vin);
		return Response.noContent().build();
	}

	 @RequestMapping(method=RequestMethod.GET, value="/vehicles/make/{makeValue}")
//	@GET
//	@Path("/make/{makeValue}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response searchVehicleByMake(@PathParam("makeValue") String makeValue) {
		List<Vehicle> vehicles = vehicleService.searchByMake(makeValue);
		VehicleSearchResult vehiclesResult = new VehicleSearchResult(vehicles);
		return Response.ok().entity(vehiclesResult).build();
	}

	/*
	 * Test this model
	 */
	 @RequestMapping(method=RequestMethod.GET, value="/vehicles/model/{model}")
//	@GET
//	@Path("/model/{model}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response searchVehicleByModel(@PathParam("model") String model) {
		List<Vehicle> vehicles = vehicleService.searchByModel(model);
		VehicleSearchResult vehiclesResult = new VehicleSearchResult(vehicles);
		return Response.ok().entity(vehiclesResult).build();
	}

	/*
	 * Test this model
	 */
	 @RequestMapping(method=RequestMethod.GET, value="/vehicles/year/{year}")
//	@GET
//	@Path("/year/{year}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response searchVehicleByYear(@PathParam("year") String year) {
		List<Vehicle> vehicles = vehicleService.searchByYear(year);
		VehicleSearchResult vehiclesResult = new VehicleSearchResult(vehicles);
		return Response.ok().entity(vehiclesResult).build();
	}

	/*
	 * Test this model
	 */
	 @RequestMapping(method=RequestMethod.GET, value="/vehicles/price/{price}")
//	@GET
//	@Path("/price/{price}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response searchVehicleByPrice(@PathParam("price") String price) {
		List<Vehicle> vehicles = vehicleService.searchByPrice(price);
		VehicleSearchResult vehiclesResult = new VehicleSearchResult(vehicles);
		return Response.ok().entity(vehiclesResult).build();
	}

}

package io.ashok.vehicle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ashok.util.RandomString;

@RestController
public class VehicleController {

	//private final static Logger logger = Logger.getLogger(VehicleController.class);

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping("/vehicles")
	public List<Vehicle> getAllVehicles() {
		return vehicleService.getAllVehicles();
	}

	@RequestMapping("/vehicles/{vin}")
	public Optional<Vehicle> findVehicleByVin(@PathVariable String vin) {
		return vehicleService.getVehicle(vin);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/vehicles")
	public void addVehicle(@RequestBody Vehicle vehicle) {
		vehicle.setVin(RandomString.getALphaNum(17));
		vehicleService.addVehicle(vehicle);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}")
	public void modifyVehicle(@RequestBody Vehicle vehicle, @PathVariable String vin) {		
			vehicleService.updateVehicle(vehicle, vin);		
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}/{statusValue}")
	public void changeVehicleStatus(@PathVariable String vin, @PathVariable String statusValue) {
		vehicleService.updateStatus(vin, statusValue);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/{vin}")
	public void deleteVehicle(@RequestBody Vehicle vehicle, @PathVariable String vin) {
		vehicleService.deleteVehicle(vin);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/vehicles/make/{makeValue}")
	public List<Vehicle> searchVehicleByMake(@PathVariable String makeValue){
		return vehicleService.searchByMake(makeValue);
	}
	
	/*
	 * Test this model
	 */
	@RequestMapping(method=RequestMethod.GET, value="/vehicles/model/{model}")
	public List<Vehicle> searchVehicleByModel(@PathVariable String model){		
		return vehicleService.searchByModel(model);
	}
	
	/*
	 * Test this model
	 */
	@RequestMapping(method=RequestMethod.GET, value="/vehicles/year/{year}")
	public List<Vehicle> searchVehicleByYear(@PathVariable String year){		
		return vehicleService.searchByYear(year);
	}
	
	/*
	 * Test this model
	 */
	@RequestMapping(method=RequestMethod.GET, value="/vehicles/price/{price}")
	public List<Vehicle> searchVehicleByPrice(@PathVariable String price){		
		return vehicleService.searchByPrice(price);
	}

}

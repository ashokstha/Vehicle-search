package io.ashok.vehicle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping("/vehicles")
	public List<Vehicle> getAllVehicles() {
		return vehicleService.getAllVehicles();
	}

	@RequestMapping("/vehicles/{vin}")
	public Optional<Vehicle> getVehicle(@PathVariable String vin) {
		return vehicleService.getVehicle(vin);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/vehicles")
	public void addVehicle(@RequestBody Vehicle vehicle) {
		vehicleService.addVehicle(vehicle);
	}	

	@RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}")
	public void updateVehicle(@RequestBody Vehicle vehicle, @PathVariable String vin) {
		if (this.getVehicle(vin) != null) {
			vehicle.setVin(vin);
			vehicleService.updateVehicle(vehicle);
		}
	}
	
	/*
	 * TODO fix issue 
	 * if vin exist get vehicle entity and update status only
	 * else display error message
	 */

	@RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}/{status}")
	public void updateVehicleStatus(@PathVariable String vin,
			@PathVariable Status status) {
		Vehicle vehicle = new Vehicle();//(Vehicle) this.getVehicle(vin) ;
		if (vehicle != null) {
			vehicle.setStatus(status);
			vehicleService.updateVehicle(vehicle);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/{vin}")
	public void deleteVehicle(@RequestBody Vehicle vehicle, @PathVariable String vin) {
		vehicleService.deleteVehicle(vin);
	}

}

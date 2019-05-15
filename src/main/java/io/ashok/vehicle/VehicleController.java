package io.ashok.vehicle;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ashok.util.RandomString;
import io.ashok.util.StringToEnum;

@RestController
public class VehicleController {
	
	private final static Logger logger = Logger.getLogger(VehicleController.class);

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
		vehicle.setVin(RandomString.getALphaNum(17));
		vehicleService.addVehicle(vehicle);
	}	

	@RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}")
	public void updateVehicle(@RequestBody Vehicle vehicle, @PathVariable String vin) {
		Optional<Vehicle> veh = this.getVehicle(vin);
		
		if (veh.isPresent()) {
			vehicle.setVin(vin);
			vehicleService.updateVehicle(vehicle);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}/{statusValue}")
	public void updateVehicleStatus(@PathVariable String vin,
			@PathVariable String statusValue) {
		
		Status status = StringToEnum.convertStatus(statusValue);
		if (status==null) {
			logger.debug("updateVehicleStatus() => Invalid Status.");
			return;
		}
		
		Optional<Vehicle> vehicle = this.getVehicle(vin);
		if (vehicle.isPresent()) {
			Vehicle veh = vehicle.get();
			veh.setStatus(status);
			vehicleService.updateVehicle(veh);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/{vin}")
	public void deleteVehicle(@RequestBody Vehicle vehicle, @PathVariable String vin) {
		vehicleService.deleteVehicle(vin);
	}

}

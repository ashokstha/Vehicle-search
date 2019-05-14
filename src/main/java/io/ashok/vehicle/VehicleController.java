package io.ashok.vehicle;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {
	
	private final static Logger logger = Logger.getLogger(VehicleController.class);

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping("/vehicles")
	public List<Vehicle> getAllVehicles() {
		logger.debug("inside getvehicles()");
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
		logger.debug("calling VehicleController.updateVehicle() method."); 
		Optional<Vehicle> veh = this.getVehicle(vin);
		logger.debug("veh: "+veh);
		
		if (veh.isPresent()) {
			vehicle.setVin(vin);
			vehicleService.updateVehicle(vehicle);
		}
	}
	
	/*
	 * TODO fix issue 
	 * if vin exist get vehicle entity and update status only
	 * else display error message
	 */

	@RequestMapping(method = RequestMethod.PUT, value = "/vehicles/{vin}/{statusValue}")
	public void updateVehicleStatus(@PathVariable String vin,
			@PathVariable String statusValue) {
		
		Status status = StringToEnum.convert(statusValue);
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

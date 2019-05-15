package io.ashok.vehicle;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ashok.util.StringToEnum;

@RestController
public class VehicleSearchController {
		
	private final static Logger logger = Logger.getLogger(VehicleController.class);
	
	@Autowired
	private VehicleService vehicleService;

	@RequestMapping(method=RequestMethod.GET, value="/vehicles/make/{makeValue}")
	public List<Vehicle> searchVehicleByMake(@PathVariable String makeValue){
		
		Make make = StringToEnum.convertMake(makeValue);
		if (make==null) {
			logger.debug("updateVehicleMake() => Invalid Make.");
			return null;
		}
		
		return vehicleService.searchByMake(make);
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

package io.ashok.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	
	public List<Vehicle> getAllVehicles(){
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepository.findAll().forEach(vehicles::add);
		return vehicles;
	}
	
	public Optional<Vehicle> getVehicle(String vin){
		return vehicleRepository.findById(vin);
	}
	
	public void addVehicle(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}
	
	public void updateVehicle(String vin, Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}
	
	public void deleteVehicle(String vin) {
		vehicleRepository.deleteById(vin);
	}
	
}

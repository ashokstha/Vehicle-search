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

	public List<Vehicle> getAllVehicles() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepository.findAll().forEach(vehicles::add);
		return vehicles;
	}

	public Optional<Vehicle> getVehicle(String vin) {
		return vehicleRepository.findById(vin);
	}

	public void addVehicle(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

	public void updateVehicle(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

	public void deleteVehicle(String vin) {
		vehicleRepository.deleteById(vin);
	}

	public List<Vehicle> searchByMake(Make make) {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepository.findAll().forEach(v -> {
			if (v.getMake() == make) {
				vehicles.add(v);
			}
		});

		return vehicles;
	}

	public List<Vehicle> searchByModel(String model) {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepository.findAll().forEach(v -> {
			if (v.getModel().equals(model)) {
				vehicles.add(v);
			}
		});

		return vehicles;
	}

	public List<Vehicle> searchByYear(String year) {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepository.findAll().forEach(v -> {
			if (v.getModelYear().equals(year)) {
				vehicles.add(v);
			}
		});

		return vehicles;
	}

	public List<Vehicle> searchByPrice(String price) {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepository.findAll().forEach(v -> {
			if (v.getPrice() == Float.parseFloat(price)) {
				vehicles.add(v);
			}
		});

		return vehicles;
	}

}

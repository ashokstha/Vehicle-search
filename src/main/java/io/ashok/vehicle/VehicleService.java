package io.ashok.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ashok.VehicleApplication;
import io.ashok.exception.ValidationException;
import io.ashok.exception.VehicleNotFoundException;
import io.ashok.util.StringToEnum;

@Service
public class VehicleService {

	private final static Logger logger = Logger.getLogger(VehicleApplication.class);

	@Autowired
	private VehicleRepository vehicleRepository;

	private void validateVehicle(final Vehicle vehicle) {
		if (vehicle == null) {
			throw new ValidationException("Invalid Vehicle info for persistence.");
		}
	}

	public List<Vehicle> getAllVehicles() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepository.findAll().forEach(vehicles::add);
		return vehicles;
	}

	public Optional<Vehicle> getVehicle(String vin) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(vin);
		if (!vehicle.isPresent()) {
			throw new VehicleNotFoundException("Error! Vehicle not found in the system.");
		}
		
		return vehicle;
	}

	public void addVehicle(Vehicle vehicle) {
		validateVehicle(vehicle);
		vehicleRepository.save(vehicle);
	}

	public void updateVehicle(Vehicle vehicle, String vin) {
		validateVehicle(vehicle);
		
		Optional<Vehicle> veh = this.getVehicle(vin);

		if (veh.isPresent() && veh.get().getStatus() == Status.PENDING) {
			vehicle.setVin(vin);
			vehicleRepository.save(vehicle);
		}
	}

	public void deleteVehicle(String vin) {
		Optional<Vehicle> vehicle = this.getVehicle(vin);
		if (vehicle.isPresent() && vehicle.get().getStatus() == Status.PENDING) {
			vehicleRepository.deleteById(vin);
		} else {
			logger.debug("Vehicle not found");
		}

	}

	public List<Status> getNextStatus(Status status) {
		List<Status> lst = new ArrayList<Status>();
		switch (status) {
		case PENDING:
			lst.add(Status.ACTIVE);
			break;
		case ACTIVE:
			lst.add(Status.CANCELLED);
			lst.add(Status.PENDING);
			lst.add(Status.SOLD);
			break;
		case SOLD:
			break;
		case CANCELLED:
			break;
		}
		return lst;
	}

	public boolean isValidNextStatus(Status oldStatus, Status newStatus) {
		List<Status> statuses = this.getNextStatus(oldStatus);
		for (Status status : statuses) {
			if (status == newStatus) {
				return true;
			}
		}

		return false;
	}

	/*
	 * update status pending -> active active -> pending, sold, canceled sold -> no
	 * change canceled -> no change
	 */
	public void updateStatus(String vin, String statusValue) {
		Status status = StringToEnum.convertStatus(statusValue);
		if (status == null) {
			logger.debug("updateVehicleStatus() => Invalid Status.");
			return;
		}

		Optional<Vehicle> vehicle = this.getVehicle(vin);
		if (vehicle.isPresent()) {
			Vehicle veh = vehicle.get();
			if (this.isValidNextStatus(veh.getStatus(), status)) {
				veh.setStatus(status);
				vehicleRepository.save(veh);
			}
		}
	}

	public List<Vehicle> searchByMake(String makeValue) {
		Make make = StringToEnum.convertMake(makeValue);
		if (make == null) {
			logger.debug("updateVehicleMake() => Invalid Make.");
			return null;
		}

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

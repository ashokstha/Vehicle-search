package io.ashok.vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleSearchResult {
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public VehicleSearchResult() {

	}

	public VehicleSearchResult(List<Vehicle> vehicles) {
		super();
		this.vehicles = vehicles;
	}
}

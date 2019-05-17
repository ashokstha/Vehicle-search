package io.ashok.vehicle;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehicleSearchResult {
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public VehicleSearchResult(List<Vehicle> vehicles) {
		super();
		this.vehicles = vehicles;
	}
}

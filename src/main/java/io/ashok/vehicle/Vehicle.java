package io.ashok.vehicle;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {

	@Id
	String vin;
	Make make;
	String model;
	String modelYear;
	String description;
	Status status;
	String soldDate;
	String createdDate;
	float price;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(String soldDate) {
		this.soldDate = soldDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Vehicle() {

	}

	public Vehicle(String vin, Make make, String model, String modelYear, String description, Status status,
			String soldDate, String createdDate, float price) {
		super();
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.modelYear = modelYear;
		this.description = description;
		this.status = status;
		this.soldDate = soldDate;
		this.createdDate = createdDate;
		this.price = price;
	}

}

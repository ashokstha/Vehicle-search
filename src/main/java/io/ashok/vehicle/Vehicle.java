package io.ashok.vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.jboss.logging.Logger;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	String vin;

	Make make;
	String model;
	String modelYear;
	String description;
	Status status;
	Date soldDate;
	Date createdDate;
	float price;
	
	private final static Logger logger = Logger.getLogger(VehicleController.class);

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

	public Date getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(String soldDate) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		try {
			Date date = simpleDateFormat.parse(soldDate);
			this.soldDate = date;
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		try {
			Date date = simpleDateFormat.parse(createdDate);
			this.createdDate = date;
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
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
		this.price = price;
		
		this.setCreatedDate(createdDate);
		this.setSoldDate(soldDate);
	}

}

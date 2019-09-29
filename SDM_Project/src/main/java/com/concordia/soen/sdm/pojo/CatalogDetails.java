package com.concordia.soen.sdm.pojo;
import java.util.List;
public class CatalogDetails {
	private int  vehicleId;	
	private String  type;
	private int  make;
	private int  model;
	private int  year;
	private String  color;
	private String  licensePlate;
	private int  availability;
	private int cost;
	public void setvehicleid(int vehicleId) {
		
		this.vehicleId=vehicleId;
	}
	public int  getvehicleid() {
		return vehicleId;
	}
	public void settype(String type) {
		// TODO Auto-generated method stub
		this.type=type;
	}
	public String gettype() {
		return type;
	}
	public void setmake(int make) {
		// TODO Auto-generated method stub
		this.make=make;
	}
	public int getmake() {
		return make;
	}
	
	public void setyear(int year) {
		// TODO Auto-generated method stub
		this.year=year;
	}
	public int getyear() {
		return year;
	}
	public void setmodel(int model) {
		// TODO Auto-generated method stub
		this.model=model;
	}
	public int getmodel() {
		return model;
	}
	public void setcolor(String color) {
		// TODO Auto-generated method stub
		this.color=color;
	}
	public String getcolor() {
		return color;
	}
	public void setlicenseplate(String licensePlate) {
		// TODO Auto-generated method stub
		this.licensePlate=licensePlate;
	}
	public String getlicenseplate() {
		return licensePlate;
	}
	public void setavailability(int availability) {
		// TODO Auto-generated method stub
		this.availability=availability;
	}
	public int getavailability() {
		return availability;
	}
	public void setcost(int cost) {
		// TODO Auto-generated method stub
		this.cost=cost;
	}
	public int getcost() {
		return cost;
	}

}

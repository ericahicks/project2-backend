package com.skillstorm.hotel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="roomtype")
public class RoomTypes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="roomtypeid")
	private int roomtypeid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private int price;
	
	

	public RoomTypes() {
		//super();
	}



	public RoomTypes(int roomtypeid) {
		//super();
		this.roomtypeid = roomtypeid;
	}



	public RoomTypes(int roomtypeid, String name) {
		//super();
		this.roomtypeid = roomtypeid;
		this.name = name;
	}



	public RoomTypes(int roomtypeid, String name, int price) {
		//super();
		this.roomtypeid = roomtypeid;
		this.name = name;
		this.price = price;
	}



	public int getRoomtypeid() {
		return roomtypeid;
	}



	public void setRoomtypeid(int roomtypeid) {
		this.roomtypeid = roomtypeid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	@Override
	public String toString() {
		return "RoomTypes [roomtypeid=" + roomtypeid + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
	
	

}

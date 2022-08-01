package com.skillstorm.hotel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="room")
public class Rooms {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="roomnumber")
	private int roomnumber;
	
	@Column(name="roomtypeid")
	private int roomtypeid;
	
	

	public Rooms() {
		//super();
	}



	public Rooms(int roomnumber) {
		//super();
		this.roomnumber = roomnumber;
	}



	public Rooms(int roomnumber, int roomtypeid) {
		//super();
		this.roomnumber = roomnumber;
		this.roomtypeid = roomtypeid;
	}



	public int getRoomnumber() {
		return roomnumber;
	}



	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}



	public int getRoomtypeid() {
		return roomtypeid;
	}



	public void setRoomtypeid(int roomtypeid) {
		this.roomtypeid = roomtypeid;
	}



	@Override
	public String toString() {
		return "Rooms [roomnumber=" + roomnumber + ", roomtypeid=" + roomtypeid + "]";
	}
	
	
	
	
	
}

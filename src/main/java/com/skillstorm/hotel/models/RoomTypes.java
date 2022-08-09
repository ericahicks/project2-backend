package com.skillstorm.hotel.models;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "roomtype")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "roomtypeid")
public class RoomTypes {

	/////////////////////////////////////////////////////////////////
	////////////////////// Instance Variables ///////////////////////
	/////////////////////////////////////////////////////////////////

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roomtypeid")
	private int roomtypeid;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private BigInteger price;
	
	@OneToMany(mappedBy = "roomType")
	private List<Rooms> rooms;

	/////////////////////////////////////////////////////////////////
	////////////////////////// Constructors /////////////////////////
	/////////////////////////////////////////////////////////////////

	public RoomTypes() {
		// super();
	}

	public RoomTypes(int roomtypeid, String name, BigInteger price) {
		// super();
		this.roomtypeid = roomtypeid;
		this.name = name;
		this.price = price;
	}

	/////////////////////////////////////////////////////////////////
	////////////////////// Getters / Setters ////////////////////////
	/////////////////////////////////////////////////////////////////

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

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}


	/////////////////////////////////////////////////////////////////
	////////////////////////// Other Methods ////////////////////////
	/////////////////////////////////////////////////////////////////

	@Override
	public String toString() {
		return "RoomTypes [roomtypeid=" + roomtypeid + ", name=" + name + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomtypeid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomTypes other = (RoomTypes) obj;
		if (roomtypeid != other.roomtypeid)
			return false;
		return true;
	}
	
}

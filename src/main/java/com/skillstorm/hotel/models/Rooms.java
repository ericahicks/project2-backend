package com.skillstorm.hotel.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "room")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "roomnumber")
public class Rooms {

	/////////////////////////////////////////////////////////////////
	////////////////////// Instance Variables ///////////////////////
	/////////////////////////////////////////////////////////////////

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roomnumber")
	private int roomnumber;

	@ManyToOne
	@JoinColumn(name = "roomtypeid")
	@JsonIdentityReference(alwaysAsId = true)
	private RoomTypes roomType;
	
	@OneToMany(mappedBy = "room")
	private List<Reservations> reservations;

	/////////////////////////////////////////////////////////////////
	////////////////////////// Constructors /////////////////////////
	/////////////////////////////////////////////////////////////////

	public Rooms() {
		// super();
	}

	public Rooms(int roomnumber, RoomTypes roomType, List<Reservations> reservations) {
		super();
		this.roomnumber = roomnumber;
		this.roomType = roomType;
		this.reservations = reservations;
	}

	/////////////////////////////////////////////////////////////////
	////////////////////// Getters / Setters ////////////////////////
	/////////////////////////////////////////////////////////////////

	public int getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}

	public RoomTypes getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomTypes roomType) {
		this.roomType = roomType;
	}

	/////////////////////////////////////////////////////////////////
	////////////////////////// Other Methods ////////////////////////
	/////////////////////////////////////////////////////////////////

	@Override
	public String toString() {
		return "Rooms [roomnumber=" + roomnumber + ", roomType=" + roomType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomnumber;
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
		Rooms other = (Rooms) obj;
		if (roomnumber != other.roomnumber)
			return false;
		return true;
	}

}

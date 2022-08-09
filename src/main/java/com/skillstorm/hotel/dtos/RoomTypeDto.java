package com.skillstorm.hotel.dtos;

import java.math.BigInteger;

public class RoomTypeDto {
	
	private int roomtypeid;

	private String name;

	private BigInteger price;
	
	public RoomTypeDto() { }

	public RoomTypeDto(int roomtypeid, String name, BigInteger price) {
		super();
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

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "RoomTypeDto [roomtypeid=" + roomtypeid + ", name=" + name + ", price=" + price + "]";
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
		RoomTypeDto other = (RoomTypeDto) obj;
		if (roomtypeid != other.roomtypeid)
			return false;
		return true;
	}
	
	

}

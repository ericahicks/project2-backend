package com.skillstorm.hotel.dtos;

public class RoomDto {
	
	private int roomnumber;
	
	private int roomtype;

	public RoomDto() {
		super();
	}

	public RoomDto(int roomnumber, int roomtype) {
		super();
		this.roomnumber = roomnumber;
		this.roomtype = roomtype;
	}

	public int getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}

	public int getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(int roomtype) {
		this.roomtype = roomtype;
	}

	@Override
	public String toString() {
		return "RoomDto [roomnumber=" + roomnumber + ", roomtype=" + roomtype + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomnumber;
		result = prime * result + roomtype;
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
		RoomDto other = (RoomDto) obj;
		if (roomnumber != other.roomnumber)
			return false;
		if (roomtype != other.roomtype)
			return false;
		return true;
	}
	
}

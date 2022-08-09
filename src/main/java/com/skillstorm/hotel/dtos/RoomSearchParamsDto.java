package com.skillstorm.hotel.dtos;

import java.time.LocalDate;

public class RoomSearchParamsDto {
	
	private int roomTypeId;
	private LocalDate checkin;
	private LocalDate checkout;
	
	public RoomSearchParamsDto() { }

	public RoomSearchParamsDto(int roomTypeId, LocalDate checkin, LocalDate checkout) {
		super();
		this.roomTypeId = roomTypeId;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public int getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "RoomSearchParamsDto [roomTypeId=" + roomTypeId + ", checkin=" + checkin + ", checkout=" + checkout
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkin == null) ? 0 : checkin.hashCode());
		result = prime * result + ((checkout == null) ? 0 : checkout.hashCode());
		result = prime * result + roomTypeId;
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
		RoomSearchParamsDto other = (RoomSearchParamsDto) obj;
		if (checkin == null) {
			if (other.checkin != null)
				return false;
		} else if (!checkin.equals(other.checkin))
			return false;
		if (checkout == null) {
			if (other.checkout != null)
				return false;
		} else if (!checkout.equals(other.checkout))
			return false;
		if (roomTypeId != other.roomTypeId)
			return false;
		return true;
	}
	
}

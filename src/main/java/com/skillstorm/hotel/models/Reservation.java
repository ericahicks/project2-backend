package com.skillstorm.hotel.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Entity
@Table(name="reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservationid")
	private int id;

	@Min(101)
	@Max(520)
	@Column(name = "roomnumber")
	private int roomnumber;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonIdentityReference(alwaysAsId = true)
//	@JsonBackReference
	private User user;
	
	@FutureOrPresent
	@Column(name = "checkin")
	private Date checkin;
	
	@Future
	@Column(name = "checkout")
	private Date checkout;
	
	public Reservation() { }

	public Reservation(int id, int roomnumber, User user, Date checkin, Date checkout) {
		super();
		this.id = id;
		this.roomnumber = roomnumber;
		this.user = user;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Reservation(int roomnumber, User user, Date checkin, Date checkout) {
		super();
		this.roomnumber = roomnumber;
		this.user = user;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Reservation(int roomnumber, Date checkin, Date checkout) {
		super();
		this.roomnumber = roomnumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", roomnumber=" + roomnumber + ", user=" + user + ", checkin=" + checkin
				+ ", checkout=" + checkout + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Reservation other = (Reservation) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}

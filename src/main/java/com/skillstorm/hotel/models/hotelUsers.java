package com.skillstorm.hotel.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class HotelUsers {


	/////////////////////////////////////////////////////////////////
	//////////////////////// Instance Variables /////////////////////
	/////////////////////////////////////////////////////////////////
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private int id;
	
	@Column(name="firstname")
	@NotBlank
	private String firstName;
	
	@Column(name="lastname")
	@NotBlank
	private String lastName;
	
	@Column(name="phonenumber")
	@NotBlank
	private String number;
	
	@Column(name="email")
	@NotBlank
	@Email
	private String email;
	
	@OneToMany(mappedBy = "users")
	private List<Reservations> reservations;


	/////////////////////////////////////////////////////////////////
	//////////////////////// Constructors ///////////////////////////
	/////////////////////////////////////////////////////////////////
	
	// No Arg Constructor
	public HotelUsers() {
		//super();
	}

	// Contructor using all fields except reservation list
	public HotelUsers(int id, String firstName, String lastName, String number, String email) {
		//super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.email = email;
	}
	
	// Constructor using all fields except id and reservation list
	public HotelUsers(String firstName, String lastName, String number, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.email = email;
	}
	
	// Constructor using all fields except id
	public HotelUsers(String firstName, String lastName, String number, String email, List<Reservations> reservations) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.email = email;
		this.reservations = reservations;
	}
	
	// Constructor using all fields
	public HotelUsers(int id, String firstName, String lastName, String number, String email, List<Reservations> reservations) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.email = email;
		this.reservations = reservations;
	}
	

	/////////////////////////////////////////////////////////////////
	////////////////////// Getters / Setters ////////////////////////
	/////////////////////////////////////////////////////////////////
	
	// All fields

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	/////////////////////////////////////////////////////////////////
	////////////////////////// Other Methods ////////////////////////
	/////////////////////////////////////////////////////////////////

	@Override
	public String toString() {
		return "HotelUsers [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", number=" + number
				+ ", email=" + email + "]";
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
		HotelUsers other = (HotelUsers) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

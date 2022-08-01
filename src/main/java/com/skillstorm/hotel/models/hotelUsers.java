package com.skillstorm.hotel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;




@Entity
@Table(name="user")
public class hotelUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private int id;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="phonenumber")
	private String number;
	
	@Column(name="email")
	private String email;
	

	public hotelUsers() {
		//super();
	}


	public hotelUsers(int id) {
		//super();
		this.id = id;
	}


	public hotelUsers(int id, String firstName) {
		//super();
		this.id = id;
		this.firstName = firstName;
	}


	public hotelUsers(int id, String firstName, String lastName) {
		//super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public hotelUsers(int id, String firstName, String lastName, String number) {
		//super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
	}


	public hotelUsers(int id, String firstName, String lastName, String number, String email) {
		//super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.email = email;
	}


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


	@Override
	public String toString() {
		return "hotelUsers [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", number=" + number
				+ ", email=" + email + "]";
	}
	
	
	
	
	
//	public hotelUsers() {
//		//super();
	/*
	 * // }
	 * 
	 * 
	 * public hotelUsers(int id) { //super(); this.id = id; }
	 * 
	 * 
	 * public hotelUsers(int id, String firstName) { //super(); this.id = id;
	 * this.firstName = firstName; }
	 * 
	 * 
	 * public hotelUsers(int id, String firstName, String lastName) { //super();
	 * this.id = id; this.firstName = firstName; this.lastName = lastName; }
	 * 
	 * 
	 * public hotelUsers(int id, String firstName, String lastName, int number) {
	 * //super(); this.id = id; this.firstName = firstName; this.lastName =
	 * lastName; this.number = number; }
	 * 
	 * 
	 * public hotelUsers(int id, String firstName, String lastName, int number,
	 * String email) { //super(); this.id = id; this.firstName = firstName;
	 * this.lastName = lastName; this.number = number; this.email = email; }
	 * 
	 * 
	 * 
	 * public hotelUsers(String firstName, String lastName, int number, String
	 * email) { //super(); this.firstName = firstName; this.lastName = lastName;
	 * this.number = number; this.email = email; }
	 * 
	 * 
	 * public int getId() { return id; }
	 * 
	 * 
	 * public void setId(int id) { this.id = id; }
	 * 
	 * 
	 * public String getFirstName() { return firstName; }
	 * 
	 * 
	 * public void setFirstName(String firstName) { this.firstName = firstName; }
	 * 
	 * 
	 * public String getLastName() { return lastName; }
	 * 
	 * 
	 * public void setLastName(String lastName) { this.lastName = lastName; }
	 * 
	 * 
	 * public int getNumber() { return number; }
	 * 
	 * 
	 * public void setNumber(int number) { this.number = number; }
	 * 
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * 
	 * @Override public String toString() { return "hotelUsers [id=" + id +
	 * ", firstName=" + firstName + ", lastName=" + lastName + ", number=" + number
	 * + ", email=" + email + "]"; }
	 */
	
	
	
	
	
	
	
}

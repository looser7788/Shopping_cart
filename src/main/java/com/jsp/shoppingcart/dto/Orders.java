package com.jsp.shoppingcart.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String address;
private long mobilenumber;
private double totalprice;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public long getMobilenumber() {
	return mobilenumber;
}
public void setMobilenumber(long mobilenumber) {
	this.mobilenumber = mobilenumber;
}
public double getTotalprice() {
	return totalprice;
}
public void setTotalprice(double totalprice) {
	this.totalprice = totalprice;
}
public List<Iteam> getIteams() {
	return iteams;
}
public void setIteams(List<Iteam> iteams) {
	this.iteams = iteams;
}
@OneToMany
private List<Iteam> iteams;
}

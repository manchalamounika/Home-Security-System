package scu.coen275.sosafe;

import java.util.Date;

public class Customer {
	private String name;
	private Address address;
	private String emgContact_1;
	private String emgContact_2;
	private String serviceStart;
	private String serviceEnd;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		System.out.println(address.toString());
		return address.toString();
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmgContact_1() {
		return emgContact_1;
	}
	public void setEmgContact_1(String emgContact_1) {
		this.emgContact_1 = emgContact_1;
	}
	public String getEmgContact_2() {
		return emgContact_2;
	}
	public void setEmgContact_2(String emgContact_2) {
		this.emgContact_2 = emgContact_2;
	}
	public String getServiceStart() {
		return serviceStart;
	}
	public void setServiceStart(String date) {
		this.serviceStart = date;
	}
	public String getServiceEnd() {
		return serviceEnd;
	}
	public void setServiceEnd(String serviceEnd) {
		this.serviceEnd = serviceEnd;
	}
	
}

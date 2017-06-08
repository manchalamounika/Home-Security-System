package scu.coen275.sosafe;

public class Address {
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String zipcode;
	public Address(String line1,String line2,String city,String state,String zip){
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.zipcode = zip;
		
	}
	@Override
	public String toString() {
		return "line1=" + line1 + ", line2=" + line2 + ", city=" + city + ", state=" + state + ", zipcode="
				+ zipcode ;
	}
	
}

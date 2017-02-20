package travelAgency;


public class Address {
	//fields that are particular to each Address
	
	private String street;
	private String city;
	private USState state;
	private String zipcode;
	
	
	public Address(String street, String city, String state, String zipcode) throws InvalidDataException
	{
		if (street == null || city == null || state == null)
			throw new InvalidDataException();
		
		this.street = street;
		this.city = city;
		//remove whitespace
		String newState = state.replaceAll("\\s", "");
		this.state = USState.valueOf(newState.toUpperCase());
		this.zipcode = zipcode;
	}
	
	//copy constructor. will copy all fields of Address to a new address object from the one given
	public Address(Address add)throws InvalidDataException{
		
		this(add.street, add.city, add.state.name(), add.zipcode);
	
	}
	
	public String getStreet(){
		
		return this.street;
	}
	
	public void setStreet(String street){
		
		this.street = street;		
	}
	
	public String getCity(){
		
		return this.city;
	}
	
	public void setCity(String city){
		
		this.city = city;
	}
	
	public USState getState(){
		
		return this.state;
	}
	
	public void setState(String state){
		
		//remove whitespace
		String newState = state.replaceAll("\\s", "");
		this.state = USState.valueOf(newState);
	}
	
	public String getZipcode(){
		
		return this.zipcode;
	}
	
	public void setZipcode(String zipcode){
		
		this.zipcode = zipcode;
	}
	
	
	public String toString(){
		
		StringBuilder info  = new StringBuilder();
		
		info.append("\nAddress:\n");
		info.append(this.street);
		info.append("\n");
		info.append(this.city);
		info.append(", ");
		info.append(this.state.getSymbol());
		info.append(" ");
		info.append(zipcode);
		
		return info.toString();
	}
	
}
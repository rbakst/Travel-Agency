package travelAgency;

public class Person {
	
	private Long ssNum;
	private Address address;
	private String firstName;
	private String lastName;
	private char gender;
	private String phoneNumber;
	

	public Person (Long ssNum, String firstName, String lastName, Address address, char gender, 
			String phoneNumber)throws InvalidDataException{
		
		if (ssNum == null)
			throw new InvalidDataException();
		
		this.ssNum = ssNum;
		this.firstName = firstName;
		this.lastName = lastName;
	
		//make a deep copy of the address using copy constructor of address
		this.address = new Address(address);
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		
	}
	
	public Person(Long ssNum, String firstName, String lastName, char gender, String street, String city, 
			String state, String zipcode, String phoneNumber)throws InvalidDataException{
		
		//make the address components into an address object and call the other person constructor
		this(ssNum, firstName, lastName, new Address(street, city, state, zipcode), gender, phoneNumber);
	}

	public Address getAddress() throws InvalidDataException{
		//deep copy
		return new Address(address);
	}

	public void setAddress (String street, String city, String state, String zipcode) throws InvalidDataException{
		
		this.address = new Address(street, city, state, zipcode);
	}

	public void setStreet(String street, String zipcode)
	{
		//?should we use these setters of the address class?
		this.address.setStreet(street);
		this.address.setZipcode(zipcode);
	}
	
	public Long getssNum(){
		return this.ssNum;
	}
		
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public char getGender(){
		return this.gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Integer compareTo(Person other){
		
		return this.ssNum.compareTo(other.ssNum);
	}
	
	public boolean equals(Object obj){
		
		if (this == obj)
			return true;
		
		if(obj == null)
			return false;
		
		if (!(obj instanceof Person))
			return false;
		
		Person other = (Person) obj;
		
		return this.ssNum.equals(other.ssNum);
	}
	public String toString(){
		
		StringBuilder info = new StringBuilder();
		
		info.append("\nName: ");
		info.append(firstName + " " + lastName);
		info.append(address);
		info.append("\nPhone Number: ");
		info.append(phoneNumber);
		
		return info.toString();
	}
	
}

	

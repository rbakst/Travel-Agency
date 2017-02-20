package travelAgency;

public class Customer extends Person{

	private Integer customerID;
	private CreditCard creditCard;
	
	
	public Customer(Integer customerID, Long ssNum, String firstName, String lastName,  Address address,
			char gender, String phoneNumber, CreditCard creditCard, Integer pin) throws InvalidDataException{
		
		super(ssNum, firstName, lastName, address, gender, phoneNumber);
		
		//identifying info cant be null
		if(customerID == null)
			throw new InvalidDataException();
			
		this.customerID = customerID;
		this.creditCard = creditCard;		
	}
	
	public Customer (Integer customerID, Long ssNum, String firstName, String lastName, String street, 
			String city, String state, String zipcode, char gender, String phoneNumber, CreditCard creditCard, 
			Integer pin)throws InvalidDataException{
		
		this(customerID, ssNum, firstName, lastName, new Address(street, city, state, zipcode), gender, phoneNumber, creditCard, pin);
	}
	
	//to be used when instantiating from main, so that a credit card object can be instantiated as well
	public Customer (Integer customerID, Long ssNum, String firstName, String lastName, String street, 
			String city, String state, String zipcode, char gender, String phoneNumber, Long creditCardID,
			String expDate, Integer pin)throws InvalidDataException{
		
		this(customerID, ssNum, firstName, lastName, new Address(street, city, state, zipcode), gender, phoneNumber, 
				new CreditCard(creditCardID, expDate, pin), pin);
	}
	
	public Customer(Customer other)throws InvalidDataException{
		
		//copy constructor uses methods of the person class to access all info to pass abt the customer to be copied.
		this(other.customerID, other.getssNum(), other.getFirstName(), other.getLastName(), other.getAddress(), 
				other.getGender(), other.getPhoneNumber(), other.creditCard, other.creditCard.getPin());
	}
	
	public Integer getCustomerID(){
		return customerID;
	}
	
	public void chargeCard(Double amount)
			throws InsufficientFundsException, InvalidPinException, CardExpiredException {
		
		this.creditCard.addCharge(amount, this.creditCard.getPin());
		
	}
	
	public int compareTo(Customer other){
		
		return this.customerID.compareTo(other.customerID);
		
	}
	
	public boolean equals(Object obj){
		
		//if in same memory location
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		else if (this.getClass() != obj.getClass())
			return false;
		
		else{
			Customer other = (Customer) obj;
			
			return this.customerID.equals(other.customerID);
		}
		
	}
	
	public String toString(){
		StringBuilder info = new StringBuilder();
		
		info.append("\nCustomer ID: ");
		info.append(customerID);
	
		info.append(super.toString());
	
		info.append("\nCredit Card: ");
		info.append(creditCard);
		
		return info.toString();
		
	}
	
	//test 
	public static void main (String [] args)throws InvalidDataException{
	//	Customer c = new Customer (1234, 123456789, "Rachel", "Bakst",  new Address("136-10 72nd ave", "flushing", "new york", "11367"), 'f', "17182630987", new CreditCard(4321L, "1-22-2018", 9876), 9876);
	//	System.out.println(c);
	}
}

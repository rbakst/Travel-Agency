package travelAgency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreditCard {

	private Double balance;
	private Long creditCardID;
	private Double creditLimit;
	private LocalDate expirationDate;
	private Integer pin;
	
	public CreditCard(Long ID, String expDate, int pin)throws InvalidDataException{
		
		//these are defining fields that cannot be left out
		if(ID == null || expDate == null)
			throw new InvalidDataException();
		
		//initialize the fields for which they give the information
		this.creditCardID = ID;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		this.expirationDate = LocalDate.parse(expDate, formatter);
		this.pin = pin;
		
		//initialize the other fields
		this.balance = 0.0;
		this.creditLimit = 5000.00; 
	}
	
	public void addCharge(Double amount, int pin)
			throws InsufficientFundsException, InvalidPinException, CardExpiredException{
		
		//if pin number is invalid
		if(pin != this.pin)
			throw new InvalidPinException();
				
		//if his credit limit is more than the amount to pay plus what he owes
		else if (amount + this.balance > this.creditLimit)
			throw new InsufficientFundsException();
		 
		 //if his card expired -check if this is how to do it
		 else if (this.expirationDate.compareTo(LocalDate.now()) < 0)
			 throw new CardExpiredException();
		
		//if everything is valid
		else
		{
			balance += amount;
		}
	}
	
	public Integer compareTo(CreditCard other){
		
		return this.creditCardID.compareTo(other.creditCardID);
	}
	
	public boolean equals(Object obj){
		
		if (this == obj)//if its referencing the same object, avoid all the following code and return true
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())	//check that they are the same class
			return false;
		
		//now that they're the same class, make a new CC object that is obj
		CreditCard other = (CreditCard) obj;
		
		if (!this.creditCardID.equals(other.creditCardID))
			return false;

		if (!this.expirationDate.equals(other.expirationDate))
			return false;
		
		//if it got past all the previous returns, they must be equal
		return true;
		
	}
	public String toString()
	{
		StringBuilder info = new StringBuilder();
		
		info.append("\nCredit Card Number: ");
		info.append(this.creditCardID);
		info.append("\nCurrent Balance: $");
		info.append(this.balance);
		info.append("\nCreditLimit: $");
		info.append(creditLimit);
		info.append("\nExpiration Date: (mm/dd/yyyy)");
		info.append(expirationDate);
	
		return info.toString();
	}
	
	public Integer getPin(){
		return this.pin;
	}


//test 
//public static void main(String [] args)throws InsufficientFundsException, InvalidPinException, CardExpiredException{
	//CreditCard c;
	//try {
	//	c = new CreditCard((long) 1235, "02/03/2017", 1234);
	//	System.out.println(c);
		
	//	CreditCard d = new CreditCard((long) 1234, "02/03/2017", 1234);
		
//		System.out.println(c.compareTo(d));
		
	//	d.addCharge(500.00, 1234);
		
	//	System.out.println(d);
		
//	} catch (InvalidDataException e) {
		// TODO Auto-generated catch block
	//	e.printStackTrace();
	//}
	//catch (DateTimeParseException e1){
		//System.out.println("Please enter date in format (mm/dd/yyyy)");
//	}
	
	
//}
}

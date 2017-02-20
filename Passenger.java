package travelAgency;
import java.time.LocalDate;

public class Passenger extends Person{
	
	private Passport passport;
	private Integer passengerID;
	
	
	public Passenger(Integer passengerID, Long ssNum, String firstName, String lastName, char gender, String street, String city, 
			String state, String zipcode, String phoneNumber, Passport passport) throws InvalidDataException{
	
		super(ssNum, firstName, lastName, gender, street, city, state, zipcode, phoneNumber);
		
		//identifying info cant be null
		if (passengerID == null)
			throw new InvalidDataException();
		
		this.passport = passport;
		this.passengerID = passengerID;		
	}
	
	//to be used when called from main, passes passport info to instantiate a passport object
	public Passenger(Integer passengerID, Long ssNum, String firstName, String lastName, char gender, String street, String city, 
			String state, String zipcode, String phoneNumber, String passportID, String birthDate, String birthPlace,
			String issueDate, String expDate, String nationality) throws InvalidDataException{
	
		this(passengerID, ssNum, firstName, lastName, gender, street, city, state, zipcode, phoneNumber, 
				new Passport(passportID, firstName, lastName, birthDate, birthPlace,issueDate, expDate, nationality));
	}
	
	public Passenger (Integer passengerID, Long ssNum, String firstName, String lastName, char gender, String street, String city,
			String state, String zipcode, String phoneNumber)throws InvalidDataException{
		
		//has no passport-pass null
		this(passengerID, ssNum, firstName, lastName, gender, street, city, state, zipcode, phoneNumber, null);

	}
	
	public Passenger (Passenger other)throws InvalidDataException{
		
		//since all the fields are ones of the person class, use getters to access them. components of address use getters of address class
		
		this(other.passengerID, other.getssNum(), other.getFirstName(), other.getLastName(), other.getGender(),
				other.getAddress().getStreet(), other.getAddress().getCity(), other.getAddress().getState().name(),
				other.getAddress().getZipcode(), other.getPhoneNumber(), other.passport);
	}
	
	public boolean hasPassport(){
		
		//if he doesnt have a passport
		if (this.passport == null)
			return false;
		
		//if his passport is expired
		if (this.passport.getExpirationDate().compareTo(LocalDate.now())> 0)
			return false;
		
		return true;
	}
	
	public void setPassport (Passport p){
		
		this.passport = p;
	}
	
	public Passport getPassport(){
		//return a new passport object that was sent to the copy constructor of passport
		return new Passport(this.passport);
	}
	
	public Integer getPassengerID(){
		
		return this.passengerID;
	}
	
	public Integer compareTo(Passenger other){
			
		return (this.passengerID.compareTo(other.passengerID));
	}
		
	public boolean equals(Object obj){
		
		if (this == obj)
			return true;	//if its the exact same object
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		
		//must be the same class now so instantiate a passenger object as obj to make their fields comparable
		Passenger other = (Passenger) obj;
		
		return this.passengerID.equals(other.passengerID);
	}
	
	public String toString(){
		
		StringBuilder info = new StringBuilder();
		
		info.append("\nPassenger ID: ");
		info.append(this.passengerID);
		info.append("\n" + super.toString());
		info.append("\nPassport: \n" );
		info.append(this.passport);
		
		return info.toString();		
	}
}

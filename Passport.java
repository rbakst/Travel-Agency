package travelAgency;

import java.time.LocalDate;
import java.lang.String;
import java.time.format.DateTimeFormatter;

public class Passport {
	
	private Long passportID;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String birthPlace;
	private LocalDate issueDate;
	private LocalDate expirationDate;
	private String nationality;
	
	//constructor
	public Passport(String passportID, String firstName, String lastName, String birthDate, 
			String birthPlace, String issueDate, String expirationDate, String nationality)
			throws InvalidDataException{
		
		if (passportID == null)
			throw new InvalidDataException();
		
		if (nationality == null)
			throw new InvalidDataException();
		
		this.passportID = Long.parseLong(passportID);
		this.firstName = firstName;
		this.lastName = lastName;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		this.birthDate = LocalDate.parse(birthDate, formatter);
		
		this. birthPlace = birthPlace;
		this.issueDate = LocalDate.parse(issueDate, formatter);
		this.expirationDate = LocalDate.parse(expirationDate, formatter);		
		
		//issue date can't be after expiration
		if (this.issueDate.compareTo(this.expirationDate) >= 0)
			throw new InvalidDataException();
		
		//birthdate can't be after issueDate
		if (this.birthDate.compareTo(this.issueDate) >= 0)
			throw new InvalidDataException();
		
		this.nationality = nationality;
		
	}
	//copy constructor
	public Passport(Passport passport){
		
		this.passportID = passport.passportID;
		this.firstName = passport.firstName;
		this.lastName = passport.lastName;
		this.birthDate = passport.birthDate;
		this.birthPlace = passport.birthPlace;
		this.issueDate = passport.issueDate;
		this.expirationDate = passport.expirationDate;
		this.nationality = passport.nationality;
	}
	
	public int compareTo(Passport other){
		
		//first ensure that nationalities are the same, then compare passport ids
		if (this.nationality.equals(other.nationality)){
			return this.passportID.compareTo(other.passportID);
		}
		
		//if nationalities are not the same, compare nationalities (?)
		return this.nationality.compareTo(other.nationality);
	}
	
	@ Override
	public boolean equals(Object other){
		
		//compare the passport objects
		if (this == other)
			return true;
		if (other == null)
			return false;
		//check that they are the same type of object (why can we use the == operator and not have to use .equals()?)
		if (this.getClass() != other.getClass())
			return false;
		//create a passport object that's the same as the "other" object
		Passport other2 = (Passport) other;
		
		//compare the passport IDs
		if(!this.passportID.equals(other2.passportID))
			return false;
		
		//compare the nationalities
		if(!this.nationality.equals(other2.nationality))
			return false;
		
		return true;
	}
	
	public LocalDate getExpirationDate()
	{
		return this.expirationDate;
	}
	
	public Long getID()
	{
		return this.passportID;
		
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	
	public String getName()
	{
		return this.firstName + " " + this.lastName;
	}
	
	public String getNationality()
	{
		return this.nationality;
	}
	
	public String toString()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		StringBuilder info = new StringBuilder();
		
		info.append( "Name: ");
		info.append(firstName + " " + lastName);
		info.append( "\nPassport ID: ");
		info.append(passportID);
		info.append( "\nBirth Date: ");
		info.append(formatter.format(birthDate));
		info.append("\nBirth Place: ");
		info.append(birthPlace);
		info.append("\nIssue Date: ");
		info.append(formatter.format(issueDate));
		info.append("\nExpiration Date: ");
		info.append(formatter.format(expirationDate));
		info.append("\nNationality: ");
		info.append(nationality);
		
		return info.toString();
	}

}
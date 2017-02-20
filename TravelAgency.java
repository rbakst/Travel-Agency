package travelAgency;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TravelAgency {
	
	private ArrayList <ScheduledFlight> flights;
	private ArrayList<Person> persons;
	
	private static Integer lastCustomerID = 0;
	private static Integer lastEmployeeID = 0;
	private static Integer lastPassengerID = 0;
	
	public TravelAgency(){

		flights = new ArrayList<ScheduledFlight>();
		persons = new ArrayList<Person>();
		
	}
	
	public Integer addCustomer(Long ssNum, String firstName, String lastName, char gender, String street, String city,
			String state, String zipcode, String phoneNumber,  Long creditCardID, String expDate, Integer pin)
					throws DuplicateDataException, InvalidDataException{
		
		for (int i = 0; i < persons.size(); i++){
			
			//if lastname, firstName, and phone number are the same, and he's a customer throw ex

			if (persons.get(i) instanceof Customer){
				if (persons.get(i).getLastName().equalsIgnoreCase(lastName)){
					if (persons.get(i).getFirstName().equalsIgnoreCase(firstName)){
						if (persons.get(i).getPhoneNumber().equals(phoneNumber)){
							throw new DuplicateDataException();
						}
					}		
				}				
			}
		}
		
		//it didn't throw the duplicate data exception, instantiate a customer
			Customer aCustomer = new Customer(++lastCustomerID, ssNum, firstName, lastName, street, city, state, 
					zipcode, gender, phoneNumber, creditCardID, expDate, pin);
	
			//add him to the persons arraylist	
			persons.add(aCustomer);
		
			return lastCustomerID;
	}
	
	public Integer addAgent(Long ssNumber, String firstName, String lastName, char gender, String street,
			String city, String state, String zipcode, String phoneNumber)throws DuplicateDataException, InvalidDataException{
		
		for (int i = 0; i < persons.size(); i++){
				
				//if lastname, firstName, and phone number are the same, and he's a travel agent throw ex
			if (persons.get(i) instanceof TravelAgent){
				if (persons.get(i).getLastName().equalsIgnoreCase(lastName)){
					if (persons.get(i).getFirstName().equalsIgnoreCase(firstName)){
						if (persons.get(i).getPhoneNumber().equals(phoneNumber)){
							throw new DuplicateDataException();
						}
					}		
				}				
			}
		}
		
		//it didn't throw the duplicate data exception, instantiate a travelagent
		TravelAgent aTravelAgent = new TravelAgent(++lastEmployeeID, ssNumber, firstName, lastName, gender, 
				street, city, state, zipcode, phoneNumber);
		
		//add him to the persons arraylist	
		persons.add(aTravelAgent);
		
		return lastEmployeeID;
	}
	
	//for a passeneger without a passport, sent to that constructor
	public Integer addPassenger(Long ssNum, String firstName, String lastName, char gender, String street, String city,
			String state, String zipcode, String phoneNumber) throws DuplicateDataException, InvalidDataException{
			
		for (int i = 0; i < persons.size(); i++){
				
			//if lastname, firstName, and phone number are the same, and he's a passenger throw ex
		if (persons.get(i) instanceof Passenger){
			if (persons.get(i).getLastName().equalsIgnoreCase(lastName)){
				if (persons.get(i).getFirstName().equalsIgnoreCase(firstName)){
					if (persons.get(i).getPhoneNumber().equals(phoneNumber)){
						throw new DuplicateDataException();
					}
				}		
			}				
		}
	}
			
		//it didn't throw the duplicate data exception, instantiate a passenger
		Passenger aPassenger = new Passenger(++lastPassengerID, ssNum, firstName, lastName, gender, 
				street, city, state, zipcode, phoneNumber);
			
		//add him to the persons arraylist	
		persons.add(aPassenger);
			
		return lastPassengerID;
	}
		
	//for a passenger with a passport, sent to that constructor
	public Integer addPassenger(Long ssNum, String firstName, String lastName, char gender, String street, String city,
			String state, String zipcode, String phoneNumber, String passportID, String birthDate, String birthPlace,
			String issueDate, String expDate, String nationality) throws DuplicateDataException, InvalidDataException{
		
		for (int i = 0; i < persons.size(); i++){
			
			//if lastname, firstName, and phone number are the same, and he's a passenger throw ex
		if (persons.get(i) instanceof Passenger){
			if (persons.get(i).getLastName().equalsIgnoreCase(lastName)){
				if (persons.get(i).getFirstName().equalsIgnoreCase(firstName)){
					if (persons.get(i).getPhoneNumber().equals(phoneNumber)){
						throw new DuplicateDataException();
					}
				}		
			}				
		}
	}
		
		//it didn't throw the duplicate data exception, instantiate a passenger
		Passenger aPassenger = new Passenger(++lastPassengerID, ssNum, firstName, lastName, gender, 
				street, city, state, zipcode, phoneNumber, passportID, birthDate, birthPlace,
			 issueDate, expDate, nationality);
		
		//add him to the persons arraylist	
		persons.add(aPassenger);
		
		return lastPassengerID;
	}
	
	
	public Passenger findPassengerInfo(Integer ID)throws InvalidDataException, NotFoundException{
		
		for (int i = 0; i < persons.size(); i++){
			
			if(persons.get(i) instanceof Passenger){
				//typecast the person to be a passenger to access passenger methods 
				Passenger temp = (Passenger)persons.get(i);
			
				if (temp.getPassengerID().equals(ID)){
					//return a deep copy
					return new Passenger(temp);
				}
			}
		}
		
		//a passenger with corresponding ID was not found
		throw new NotFoundException();
	}
	
	public Customer findCustomerInfo(Integer ID) throws InvalidDataException, NotFoundException{
		
		for (int i = 0; i < persons.size(); i++){
			
			if(persons.get(i) instanceof Customer){
				//typecast the person to be a customer to access customer methods 
				Customer temp = (Customer)persons.get(i);
				
				if (temp.getCustomerID().equals(ID)){
					//return a deep copy
					return new Customer(temp);
				}
			}
		}
		
		//a customer with corresponding ID was not found
		throw new NotFoundException();	
	}
	
	public ArrayList <Passenger> getPassengers(){
		//instantiate a new arraylist with only passnegers and return
		
		//Array list of passengers to return
		ArrayList<Passenger> passengers = new ArrayList<>();
		
		//loop through persons
		for (Person aPerson: this.persons){		
			if(aPerson instanceof Passenger){
				//typecast aPerson to be a Passenger (recognized by the loop) and add to arraylist
				passengers.add((Passenger) aPerson);
			}
		}
		
		return passengers;
	}
	
	public void addScheduledFlight(String departDate, Integer flightNum)throws InvalidDataException{
		
		//make the string a localdate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate departure = LocalDate.parse(departDate, formatter);
		
		
		//loop through the flights to check for duplicates
		for (ScheduledFlight flight: this.flights){
			
			if (flight.getFlightID().equals(flightNum)){
				throw new DuplicateDataException();
			}
		}
		
		//no duplicates found, instantiate a flight
		ScheduledFlight aFlight = new ScheduledFlight(departure, flightNum);
		//add to arraylist
		flights.add(aFlight);
		
	}
	
	public void bookFlight(Integer flightNum, Integer passengerID, Integer seatNum)throws NotFoundException,
		InvalidDataException, DuplicateDataException, SeatNotAvailableException, FullyBookedException{//from scheduled flight class bookseat method
		
		
		
		//use this class's method to get a list of only passengers, easy access to passenger methods
		ArrayList <Passenger> passengers = getPassengers();	
		
		Passenger thePassenger = null;	//the passenger to be found
		
		for (Passenger aPassenger: passengers ){
			//if passenger ID when loopoing is same as one provided, set thepassenger variable and break
			if (aPassenger.getPassengerID().equals(passengerID)){
				
				thePassenger = aPassenger;//passing a reference of the same object to new variable
				break;
			}
		}
		//if thepassenegr was still not initialized, the id wasn't found
		if (thePassenger == null)
			throw new NotFoundException();
	
		
		ScheduledFlight theFlight = null;	//the flight to be found
		
		for (ScheduledFlight aFlight: flights){
			//if scheduled flight when looping is same as scheduled flight num they provided, set found and break
			if (aFlight.getFlightID().equals(flightNum)){
			
				theFlight = aFlight;	//passing a reference of same object to new variable
				break;
			}
		}
		//if theflight is still null, flightnum wasnt found
		if (theFlight == null)
			throw new NotFoundException();

		//now that flight and passenegr were found, book a seat on this specific flight by passing thepasssenegr and seatnum to its book flight method
		theFlight.bookSeat(thePassenger, seatNum);
		
	
	}
	
	public void cancelFlight(Integer flightNum, Integer passengerID, Integer seatNum)throws NotFoundException, InvalidDataException{
		
		//use this class's method to get a list of only passengers, easy access to passenger methods
		ArrayList <Passenger> passengers = getPassengers();	
				
		Passenger thePassenger = null;	//the passenger to be found
				
		for (Passenger aPassenger: passengers ){
			//if passenger ID when loopoing is same as one provided, set thepassenger variable and break
			if (aPassenger.getPassengerID().equals(passengerID)){
				
				thePassenger = aPassenger;//passing a reference of the same object to new variable
				break;
			}
		}
		//if thepassenegr was still not initialized, the id wasn't found
		if (thePassenger == null)
			throw new NotFoundException();
			
				
		ScheduledFlight theFlight = null;	//the flight to be found
				
		for (ScheduledFlight aFlight: flights){
			//if scheduled flight when looping is same as scheduled flight num they provided, set found and break
			if (aFlight.getFlightID().equals(flightNum)){
					
				theFlight = aFlight;	//passing a reference of same object to new variable
				break;
			}
		}
		//if theflight is still null, flightnum wasnt found
		if (theFlight == null)
			throw new NotFoundException();
				
		//now that passenger and flight have been found, use cancelreservation method for this specific flight from scheduled flightclass 
		theFlight.cancelReservation(thePassenger, seatNum);
	} 
	
	public String viewPlaneLayout(){
	
		StringBuilder info = new StringBuilder();
		
		info.append("Flights: \n");
		for (ScheduledFlight aFlight: flights){
			info.append(aFlight + "\n");
		}
		
		return info.toString();
		
	}

}



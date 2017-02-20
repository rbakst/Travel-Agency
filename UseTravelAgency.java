package travelAgency;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class UseTravelAgency {

	public static void main(String[] args){
		
		//instantiate the travel agency
		TravelAgency lanlan = new TravelAgency();
		
		int choice;
		
		Scanner keyboard = new Scanner(System.in);
		Integer customerID;
		Integer employeeID;
		Integer passengerID;
		Integer lastAssignedFlightID = 0;	//will increment as each flight is scheduled (regardless of a new day starting)
	
		do
		{
			choice = menu();
			switch (choice)
			{
			//add a customer
			case 1:
				
				try{
					customerID = addCustomer(lanlan);
					System.out.println("Customer ID: " + customerID);
				}
				catch(DuplicateDataException e1)
				{
					System.out.println("This customer is already part of this agency.");
				}
				catch (DateTimeParseException e3)//must come before illegal argument exception, otherwise will already be caught by it
				{
					System.out.println("Please enter date in format (mm/dd/yyyy)");
				}	
				catch(InvalidDataException e2)
				{
					System.out.println("Some essential data (CC ID, expdate, Cust ID, ssNum) not entered.");
				}
				catch (IllegalArgumentException e4)
				{
					System.out.println("Some data entered incorrectly.");
				}
				break;
			//add an agent
			case 2:
				
				try{
					employeeID = addAgent(lanlan);
					System.out.println("Employee ID: " + employeeID);
				}catch(DuplicateDataException e1)
				{
					System.out.println("This travel agent is already part of this agency");
				}
				catch (DateTimeParseException e5)
				{
					System.out.println("Please enter date in format (mm/dd/yyyy)");
				}	
				catch(InvalidDataException e2)
				{
					System.out.println("Some esential data (SSNum or Employee ID) not entered");
				}
				catch (IllegalArgumentException e4)
				{
					System.out.println("Some data entered incorrectly.");
				}
				
				break;
			//add a passenegr
			case 3:
				
				try{
					passengerID = addPassenger(lanlan);
					System.out.println("Passenger ID: " + passengerID);
					
				}catch(DuplicateDataException e1)
				{
					System.out.println("This Passneger has already been a passnger in this agency");
				}
				catch (DateTimeParseException e4)
				{
					System.out.println("Please enter date in format (mm/dd/yyyy)");
				}	
				catch(InvalidDataException e2)
				{
					System.out.println("Some essential data (SSNum, Passneger ID, passport ID, nationality) not entered");
				}
				catch (IllegalArgumentException e5)
				{
					System.out.println("Some data entered incorrectly.");
				}
				
				break;
			//find passenegr info
			case 4:
				
				//get ID
				System.out.println("Passenger ID: ");
				passengerID = keyboard.nextInt();
				
				try{
					//look for passenger
					Passenger p = lanlan.findPassengerInfo(passengerID);
					
					//print
					System.out.println(p);
				
				}catch(InvalidDataException e1)
				{
					System.out.println(e1.getMessage());
				}
				catch(NotFoundException e2)
				{
					System.out.println(e2.getMessage());
				}
				break;
			//find customer info
			case 5:
	
				//get ID
				System.out.println("Customer ID: ");
				customerID = keyboard.nextInt();
				
				try{
					//look for passenger
					Customer c = lanlan.findCustomerInfo(customerID);
					
					//print
					System.out.println(c);
			
				}catch(InvalidDataException e1)
				{
					System.out.println(e1.getMessage());
				}
				catch(NotFoundException e2)
				{
					System.out.println(e2.getMessage());
				}
				
				break;
			//get all passnegers of agency
			case 6:
				
				ArrayList <Passenger> passengers = lanlan.getPassengers();
				
				//uses arraylist.toString which uses passenegr.tostring
				System.out.println(passengers);
				
				break;
			//schedule a flight
			case 7:
				
				System.out.println("Depature Date: (mm/dd/yyyy)");
				String departDate = keyboard.next();
				
				try{
					lanlan.addScheduledFlight(departDate, ++lastAssignedFlightID);
					System.out.println("Flight Scheduled. Flight ID: " + lastAssignedFlightID);
				
				}catch(InvalidDataException e1)
				{
					System.out.println(e1.getMessage());
				}
				catch (DateTimeParseException e3)
				{
					System.out.println("Please enter date in format (mm/dd/yyyy)");
				}	
				catch(DuplicateDataException e4)
				{
					System.out.println("Error in assigning flight ID. Duplicate ID.");
				}
				
				break;
			case 8:
				
				try{
					bookFlight(lanlan);
					System.out.println("Flight Booked. Thank you for choosing lanlan.");
				
				}catch(NotFoundException e1)
				{
					System.out.println("Passenger or Flight Number not found");
				}
				catch(InvalidDataException e2)
				{
					System.out.println(e2.getMessage());
				}
				catch(DuplicateDataException e3)
				{
					System.out.println("Passenger already has a seat on this plane.");
				}
				catch(SeatNotAvailableException e4)
				{
					System.out.println("Requested Seat not available.");
				}
				catch(FullyBookedException e5)
				{
					System.out.println("Flight fully booked.");
				}
				
				break;
			case 9:
				try{
					cancelFlight(lanlan);
					System.out.println("Reservation Canceled.");
				
				}catch(NotFoundException e1)
				{
					System.out.println("Passenger or Flight Number not found");
				}
				catch(InvalidDataException e2)
				{
					System.out.println(e2.getMessage());
				}
				break;
				
			case 10:
				
				System.out.println(lanlan.viewPlaneLayout());
				
				break;
			
			case 11:
				System.exit(0);
				break;
			
			default:
				System.out.println("Invalid choice.");
					
			}
		}while (choice != 11);
	}
	
	public static int menu(){
		
		StringBuilder options = new StringBuilder();
		
		options.append("\nPlease enter 1-11");
		options.append("\n\n1. Add Customer");
		options.append("\n2. Add Travel Agent");
		options.append("\n3. Add Passenger");
		options.append("\n4. Find Passenger Info");
		options.append("\n5. Find Customer Info");
		options.append("\n6. View Passngers of Travel Agency");
		options.append("\n7. Schedule a Flight");
		options.append("\n8. Book a Flight");
		options.append("\n9. Cancel a Reservation");
		options.append("\n10. View Plane Layouts");
		options.append("\n11. Exit");
		
		
		System.out.println(options.toString());
		
		Scanner keyboard = new Scanner(System.in);
		
		int choice = keyboard.nextInt();
		
		return choice;
	}
	
	public static String[] getBasicInfo()
	{
		Scanner keyboard = new Scanner(System.in);
		
		//array to hold the answers 
		String [] answers = new String[9];
		
		System.out.println("Social Security Number: ");
		answers[0] = keyboard.next();
		System.out.println("First Name: ");
		answers[1] = keyboard.next();
		System.out.println("Last Name: ");
		answers[2] = keyboard.next();
		System.out.println("gender (f/m): ");
		answers[3] = keyboard.next();
		keyboard.nextLine();	//buffer
		System.out.println("Street: ");
		answers[4] = keyboard.nextLine();
		System.out.println("City: ");
		answers[5] = keyboard.nextLine();
		System.out.println("State: ");
		answers[6] = keyboard.nextLine();
		System.out.println("Zipcode:");
		answers[7] = keyboard.next();
		keyboard.nextLine();
		System.out.println("Phone Number: ");
		answers[8] = keyboard.nextLine();
		
		return answers;
		
	}
	
	//choice 1
	public static Integer addCustomer(TravelAgency lanlan) throws DuplicateDataException,InvalidDataException, DateTimeParseException 
	{
		Scanner keyboard = new Scanner(System.in);
		
		//call getBasicinfo method to get an array with the answers
		String [] answers = getBasicInfo();
		
		Long ssNum;
		String firstName;
		String lastName;
		char gender;
		String street;
		String city;
		String state;
		String zipcode;
		String phoneNumber;
		Long creditCardID;
		String expDate;
		Integer pin;		
		
		//read in values from array to their variables
			//the only variables that are parsed in main (and not in the deeper classes are those due to the fact
			//that it was an array and not individual keyboard entries being read in.
		ssNum = Long.parseLong(answers[0]);
		firstName = answers[1];
		lastName = answers[2];
		gender = answers[3].charAt(0);
		street = answers[4];
		city = answers[5];
		state = answers[6];
		zipcode = answers[7];
		phoneNumber = answers[8];
		
		//get the rest of the info
		System.out.println("Credit Card ID: ");
		creditCardID = keyboard.nextLong();
		System.out.println("Expiration Date (mm/dd/yyyy)");
		expDate = keyboard.next();
		System.out.println("Pin: ");
		pin = keyboard.nextInt();		
	
		//add a customer to the travelagency, get his ID
		Integer customerID = lanlan.addCustomer(ssNum, firstName, lastName, gender, street, city, state, zipcode, phoneNumber,
				creditCardID, expDate, pin);
		
		return customerID;
	}
	
	//choice 2
	public static Integer addAgent(TravelAgency lanlan) throws DuplicateDataException, InvalidDataException
	{
		
		Scanner keyboard = new Scanner(System.in);
		
		//call getBasicinfo method to get an array with the answers
		String[] answers = new String[9];
		answers = getBasicInfo();
		
		Long ssNum;
		String firstName;
		String lastName;
		char gender;
		String street;
		String city;
		String state;
		String zipcode;
		String phoneNumber;
		
		//read in values from array to their variables
			//the only variables that are parsed in main (and not in the deeper classes are those due to the fact
			//that it was an array and not individual keyboard entries being read in.
		ssNum = Long.parseLong(answers[0]);
		firstName = answers[1];
		lastName = answers[2];
		gender = answers[3].charAt(0);
		street = answers[4];
		city = answers[5];
		state = answers[6];
		zipcode = answers[7];
		phoneNumber = answers[8];
		
		//add an agent to the travel agency, get is ID num
		Integer employeeID = lanlan.addAgent(ssNum, firstName, lastName, gender, street, city, state, zipcode, phoneNumber);
		
		return employeeID;
	}
	
	//choice 3
	public static Integer addPassenger(TravelAgency lanlan)throws DuplicateDataException, InvalidDataException
	{
		//public void addPassenger(Long ssNum, String firstName, String lastName, char gender, String street, String city,
		//String state, String zipcode, String phoneNumber, Passport p) throws DuplicateDataException, InvalidDataException{
		Scanner keyboard = new Scanner(System.in);
		
		//call getBasicinfo method to get an array with the answers
		String[] answers = new String[9];
		answers = getBasicInfo();
		
		Long ssNum;
		String firstName;
		String lastName;
		char gender;
		String street;
		String city;
		String state;
		String zipcode;
		String phoneNumber;
		String passportID;
		String birthDate;
		String birthPlace;
		String issueDate;
		String expDate;
		String nationality;
		
		//read in values from array to their variables
			//the only variables that are parsed in main (and not in the deeper classes are those due to the fact
			//that it was an array and not individual keyboard entries being read in.
		ssNum = Long.parseLong(answers[0]);
		firstName = answers[1];
		lastName = answers[2];
		gender = answers[3].charAt(0);
		street = answers[4];
		city = answers[5];
		state = answers[6];
		zipcode = answers[7];
		phoneNumber = answers[8];
		
		//Check if he as a passport
		System.out.println("Do you have a passport? (yes/no)");
		String ans = keyboard.next();
		
		//input validation
		while ((!ans.equalsIgnoreCase("yes")) && (!ans.equalsIgnoreCase("no")))
		{
			System.out.println("Enter 'yes' or 'no':");
			ans = keyboard.next();
		}
		
		Integer passengerID;
		//if doesn't have passport, send to that method of travel agency
		if (ans.equalsIgnoreCase("no"))
		{
			passengerID = lanlan.addPassenger(ssNum, firstName, lastName, gender, street, city, state, zipcode, phoneNumber);
		}
		
		//if he does have a passport, get the info
		else
		{
			//get the rest of the info
			System.out.println("Passport ID: ");
			passportID = keyboard.next();
			System.out.println("Birth Date (mm/dd/yyyy)");
			birthDate = keyboard.next();
			keyboard.nextLine();
			System.out.println("Birth Place: ");
			birthPlace = keyboard.nextLine();		
			System.out.println("Issue Date (mm/dd/yyyy): ");
			issueDate = keyboard.next();
			System.out.println("Expiration Date (mm/dd/yyyy): ");
			expDate = keyboard.next();
			keyboard.nextLine();
			System.out.println("Nationality: ");
			nationality = keyboard.nextLine();
		
			//add a paasenger to the travel agency
			passengerID = lanlan.addPassenger(ssNum, firstName, lastName, gender, street, city, state, zipcode, phoneNumber,
					passportID, birthDate, birthPlace, issueDate, expDate, nationality);
		}
		
		return passengerID;
			
	}
	
	//choice 8
	public static void bookFlight(TravelAgency lanlan)throws NotFoundException, InvalidDataException, DuplicateDataException, SeatNotAvailableException, FullyBookedException
	{
		
		Scanner keyboard = new Scanner(System.in);
		Integer flightNum;
		Integer passengerID;
		Integer seatNum;
		
		System.out.println("Flight Number: ");
		flightNum = keyboard.nextInt();
		System.out.println("Passenger ID: ");
		passengerID = keyboard.nextInt();
		System.out.println("Seat Number: ");
		seatNum = keyboard.nextInt();
		
		lanlan.bookFlight(flightNum, passengerID, seatNum-1);
		
	}
	//choice 9
	public static void cancelFlight(TravelAgency lanlan)throws NotFoundException, InvalidDataException
	{
			
			Scanner keyboard = new Scanner(System.in);
			Integer flightNum;
			Integer passengerID;
			Integer seatNum;
			
			System.out.println("Flight Number: ");
			flightNum = keyboard.nextInt();
			System.out.println("Passenger ID: ");
			passengerID = keyboard.nextInt();
			System.out.println("Seat Number: ");
			seatNum = keyboard.nextInt();
			
			lanlan.cancelFlight(flightNum, passengerID, seatNum-1);
	}
	
}

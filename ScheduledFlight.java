package travelAgency;

import java.util.ArrayList;
import java.time.LocalDate;

public class ScheduledFlight {
	
	private ArrayList <Passenger> passengers;
	private Seat [] seats = new Seat[180];
	private LocalDate departureDate;
	private Integer flightID;
	private int availFirstClassSeats = 0;
	private int availEconomySeats = 0;
	
	
	public ScheduledFlight(LocalDate departureDate, Integer flightID)throws InvalidDataException{
	
		if (flightID == null)
			throw new InvalidDataException();
		
		passengers = new ArrayList<Passenger>();
		//the seat types are assigned. passenger arraylist is automatically 10 values assigned to null
		
		//first class seats are instantiated with seattype firstclass (passenger null, available true)
		for (int i = 0; i < 16; i++){

			seats[i] = new Seat(SeatType.valueOf("FIRSTCLASS"));
			passengers.add(null);	//although this is unnecessary since the first ten seats are automatically there and assigned to null, it's there in case code is changed and seats are added
			availFirstClassSeats += 1;	//will come out with the total number of firstClassSeats
		}
		
		//economy seats are instantiated with seattype economy
		for (int i = 15; i < 180; i++){
			seats[i] = new Seat(SeatType.valueOf("ECONOMY"));
			
//can I use .set()? or does it only work b/c its size is ten and I should really use .add()?
			passengers.add(null);
			availEconomySeats += 1;	//will come out with the total number of economy seats
		}
		
		this.departureDate = departureDate;
		this.flightID = flightID;
	}
	
	
	//following 2 getters-are they supposed to be here?
	public LocalDate getDepartureDate(){
		return this.departureDate;
	}
	
	public Integer getFlightID(){
		return this.flightID;
	}
	public void bookSeat(Passenger p, Integer seatNumber)
			throws FullyBookedException, InvalidDataException, DuplicateDataException, SeatNotAvailableException{
		
		//if plane is fully booked
		if (availFirstClassSeats == 0 || availEconomySeats == 0){
			
			throw new FullyBookedException();
		}

		//if this passenger already has a seat on the plane
		if (passengers.contains(p)){
				
			throw new DuplicateDataException();
		}
	
		//if invalid seat number was entered (seat number that's sent here should be changed to one less than what user entered in main
		if (seatNumber < 0 || seatNumber > 9){
				
				throw new InvalidDataException();
		}
		
		//if the seat they want to book is not empty, seatnotavailable ex will be thrown from the bookSeat method of the Seat class
			//double check is here so that we don't end up with a passenegr tat both booked a flight and got an excpetion
		if (!seats[seatNumber].isAvailable())
			throw new SeatNotAvailableException();			
		
		//once this point was reached, can book a seat
		//set the value in position of the seat number requested minus one to a reference to the passenger
		passengers.set(seatNumber, p);
		//send the seat at the seatNumber requested to the bookseat method of the seat class.
			//available will be false, its passenger will be p
		seats[seatNumber].bookSeat(p);
			
		//use the getSeatType method of the seat class, send it the instance of this seat and the seatnumber itself
		if (seats[seatNumber].getSeatType(seatNumber).name().equals("FIRSTCLASS")){
				
			availFirstClassSeats -= 1;
		}	
			
		else{ //it's an economy seat
					
			availEconomySeats -=1;	
		}
	}
	
	public void cancelReservation(Passenger p, int seatNumber) throws InvalidDataException{

		//if invalid seat number was entered (seat number that's sent here should be changed to one less than what user entered in main
		if (seatNumber < 0 || seatNumber > 9){
								
			throw new InvalidDataException();
		}
		
		//verify that the passenger is sitting in this seat	
		if ((seats[seatNumber].getPassenger() == null) || (!seats[seatNumber].getPassenger().equals(p)))
			throw new InvalidDataException();
		
		//remove him from passenegrs arraylist
		passengers.remove(p);
		
		//remove from seats array
		seats[seatNumber].cancelReservation(p);	
		
		if (seats[seatNumber].getSeatType(seatNumber).name().equals("FIRSTCLASS")){
			
			availFirstClassSeats += 1;
		}
		
		else{ // it was an economy seat
			
			availEconomySeats += 1;
		}
	}
//test this method	
	public String toString(){
		
		StringBuilder info = new StringBuilder();
		
		info.append("\nFlight ID: ");
		info.append(flightID);
		info.append("\nDeparture Date: ");
		info.append(departureDate);
		info.append("\nAvailable First Class Seats: ");
		info.append(availFirstClassSeats);
		info.append("\nAvailable Economy Seats: ");
		info.append(availEconomySeats + "\n");
		
		//no seats can be null , all were instantiated with constructor
		for (int i = 0; i < seats.length; i++){
			
			info.append("\nSeat " + (i + 1) + ":\n");
			info.append(seats[i]);

		}
		
		return info.toString();
	}
	
	
	

}

package travelAgency;

public class Seat {
	
	private boolean available;
	private Passenger passenger;
	private SeatType type;
	

	public Seat(SeatType type){
		
		this.available = true;
		this.passenger = null;
		this.type = type;
		
	}
	
	//this method applies only to a plane with 10 seats
	public SeatType getSeatType(Integer seatNumber)throws InvalidDataException{
		
		//the seat number they gave should be from 1-10 
		if ((seatNumber < 0) || (seatNumber > 9)){
				
			throw new InvalidDataException();
		}
				
		//will be between 1 and 5 inclusive
		else if (seatNumber <= 4){
					
			return SeatType.valueOf("FIRSTCLASS");
		}
				
		//will be between 6 and 10 inclusive
		else{
					
			return SeatType.valueOf("ECONOMY");
		}		
	}

	public boolean isAvailable(){
		if (this.available == true)
			return true;
		else
			return false;
	}
	
	public Passenger getPassenger(){
		return this.passenger;
	}
	
	public void bookSeat(Passenger p)throws SeatNotAvailableException{
		
		//if seat is booked
		if (this.available == false)
			throw new SeatNotAvailableException();
		
		//if seat is not booked
		this.available = false;
		this.passenger = p;
	}
	
	public void cancelReservation(Passenger p) throws InvalidDataException{

		//if seat wasn't booked yet
		if (available == true)
			throw new InvalidDataException();
		
		//if seat was in fact booked
		this.available = true;
		this.passenger = null;
	}

	
	public String toString(){
		
		StringBuilder info = new StringBuilder();
		
		
		//only display passenger if s/o's in it already (its not avail)
		if (!available){
			info.append("Seat Booked");
			info.append(this.passenger);
			info.append("\n");
		}
		
		else{
			info.append("\nSeat Available ");
			info.append("\nSeat Type: \n");
			info.append(type);
			info.append("\n");
		}
		
		return info.toString();

	}
		
}



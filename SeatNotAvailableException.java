package travelAgency;

public class SeatNotAvailableException extends Exception {
	public SeatNotAvailableException(){
		super("Seat not available.");
	}

}

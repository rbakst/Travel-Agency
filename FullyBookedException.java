package travelAgency;

public class FullyBookedException extends Exception {
	public FullyBookedException(){
		super("Fully Booked.");
	}

}

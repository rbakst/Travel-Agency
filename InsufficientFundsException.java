package travelAgency;

public class InsufficientFundsException extends RuntimeException{

	public InsufficientFundsException(){
		
		super("insufficient funds");
	}
}

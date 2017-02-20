package travelAgency;

public class TravelAgent extends Person {
	
	private Integer employeeID;
	private Double earnings;
	private Long socialSecurityNum;
	private static Double commissionRate;
	
	
	//notice that this constructor receives street, city, etc separately, but customer receives an address
	public TravelAgent (Integer employeeID, Long ssNum, String firstName, String lastName, char gender, 
			String street, String city, String state, String zipcode, String phoneNumber)throws InvalidDataException{
		
		//instantiate a Person object since travelagent "is a" person
		super(ssNum, firstName, lastName, gender, street, city, state, zipcode, phoneNumber);
		
		if (employeeID == null)
			throw new InvalidDataException();			
		
		this.employeeID = employeeID;
		this.socialSecurityNum = ssNum;
		
	}
	
	public void bookTicket(Double amount){
	
		this.earnings += amount * commissionRate;
	}
	
	public Integer getEmployeeID(){
		return this.employeeID;
	}
	
	public Double getEarnings(){
		return this.earnings;
	}
	
	public Long getSSNum(){
		return this.socialSecurityNum;
	}
	
	public Double getCommissionRate(){
		return commissionRate;
	}
	
	public void setCommissionRate(Double rate){
		commissionRate = rate;
	}
	public String toString(){
		
		StringBuilder info = new StringBuilder();
		
		info.append("\nEmployee ID: ");
		info.append(employeeID);
		info.append("\n" + super.toString());
		info.append("\nEarnings: ");
		info.append(earnings);
		info.append("\nSocial Security Number: ");
		info.append(socialSecurityNum);
		info.append("\nCommission Rate: ");
		info.append(commissionRate);
		
		return info.toString();
	}
	
	public int compareTo(TravelAgent other){
		
		return this.employeeID.compareTo(other.employeeID);
	}
	
	public boolean equals(Object obj){
		
		if (this == obj)
			return true;
		else if (obj == null)
			return false;
		else if (this.getClass()!=obj.getClass())
			return false;
		
		TravelAgent other = (TravelAgent) obj;
		
		return this.employeeID.equals(other.employeeID);
	}
}

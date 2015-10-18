package exceptions;

public class exHand extends Exception 
{
	private String errormessage;
	   
	public exHand()
	   {
		   errormessage = "The top two hands are tied!";
	   }

	/**
	 * @return the errormessage
	 */
	public String getErrormessage() {
		return errormessage;
	}

	/**
	 * @param errormessage the errormessage to set
	 */
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	   
	
	
	   
}

import java.util.*;

/**
 * This class tests the validity of user-generated SSN's.
 * @author Sebastian Ashcallay
 *
 */
public class SocSecProcessor {
	
	/**
	 * Main Method:
	 * Takes names and corresponding SSN's, checking the validity of the SSN's. 
	 * - If an SSN is invalid, the method prints the cause of error alongside the name and SSN entered.
	 * - If an SSN is valid, the method prints a message acknowledging its validity.
	 * The user can enter multiple names and SSN's (program termination depends on them).
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		boolean toContinue = true;
		
		while (toContinue) {
			try {
				
				System.out.print("Name? ");
				String userName = in.nextLine();
				
				System.out.print("SSN? ");
				String socSecNum = in.nextLine();
				
				if (isValid(socSecNum))
					System.out.println(userName + " " + socSecNum + " is valid");
				
				System.out.print("\nContinue (Y/N)? ");
				String YNanswer = in.nextLine();
				
				// Input Validation
				if (YNanswer.toLowerCase().equals("y")) {
					toContinue = true;
					System.out.println();
					
				} else if (YNanswer.toLowerCase().equals("n")) {
					toContinue = false;
					System.out.println();
					
				} else {
					while (!YNanswer.toLowerCase().equals("y") && !YNanswer.toLowerCase().equals("n")) {
						System.out.print("* ERROR!! Please select <Y> or <N> \n--> ");
						YNanswer = in.nextLine();
					}
					
					toContinue = (YNanswer.toLowerCase().equals("y"))? true: false;
					System.out.println();
				}
			
			} catch (SocSecException e) {
				System.out.println(e.getMessage());
				
				System.out.print("\nContinue (Y/N)? ");
				String YNanswer = in.nextLine();
				
				// Input Validation
				if (YNanswer.toLowerCase().equals("y")) {
					toContinue = true;
					System.out.println();
					
				} else if (YNanswer.toLowerCase().equals("n")) {
					toContinue = false;
					System.out.println();
					
				} else {
					while (!YNanswer.toLowerCase().equals("y") && !YNanswer.toLowerCase().equals("n")) {
						System.out.print("* ERROR!! Please select <Y> or <N> \n--> ");
						YNanswer = in.nextLine();
					}
					
					toContinue = (YNanswer.toLowerCase().equals("y"))? true: false;
					System.out.println();
				}
				
			} 
		}
		
		
		in.close();
		
	}
	
	/**
	 * isValid() Method:
	 * This method checks the validity of a SSN.
	 * @param ssn: Social Security Number (String) to be checked
	 * @return true if SSN is valid
	 * @throws SocSecException: Thrown if SSN is invalid, with an specific error highlighted
	 */
	public static boolean isValid(String ssn) throws SocSecException {
		
		if (ssn.length() != 11)
			throw new SocSecException("wrong number of characters");
		
		for (int i = 0; i < ssn.length(); i++) {
			if (ssn.charAt(i) == '-')
				if ((i != 3) && (i != 6))
					throw new SocSecException("dashes at wrong positions");
			
			if ((i != 3) && (i != 6))
				if (!Character.isDigit(ssn.charAt(i)))
					throw new SocSecException("contains a character that is not a digit");
		}
		
		return true;
		
	}

}

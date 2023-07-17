import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CourseDBManager implements CourseDBManagerInterface {

	private final int DEFAULT_CAPACITY = 10;
	private CourseDBStructure courses = new CourseDBStructure(DEFAULT_CAPACITY);
	
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		courses.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}

	@Override
	public CourseDBElement get(int crn) throws IOException {
		return courses.get(crn);
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner read = new Scanner(input);
		while (read.hasNext()) {
			
			// Read line
			String line = read.nextLine();
			
			// Continue onto next line if: line is a comment (#) or line is blank
			if ((line == null) || (line.isBlank()) || (line.charAt(0) == '#'))
				continue;
			
			// Detect if the formatting is correct; if it is, extract information and add to courses
			if (correctFormat(line)) {
				String id = extractID(line);
				int crn = extractCRN(line);
				int credits = extractCredits(line);
				String roomNum = extractRoom(line);
				String instructor = extractInstructor(line);
				courses.add(new CourseDBElement(id, crn, credits, roomNum, instructor)); // Add new course
			}
		}
		
		read.close();

	}
	
	private String extractID(String line) {
		String[] elements = line.split("\\s+");
		return elements[0];
	}
	
	private int extractCRN(String line) {
		String[] elements = line.split("\\s+");
		return Integer.parseInt(elements[1]);
	}
	
	private int extractCredits(String line) {
		String[] elements = line.split("\\s+");
		return Integer.parseInt(elements[2]);
	}
	
	private String extractRoom(String line) {
		String[] elements = line.split("\\s+");
		return elements[3];
	}
	
	private String extractInstructor(String line) {
		String[] elements = line.split("\\s+");
		String instructorName = ""; // Empty string
		
		for (int i = 4; i < elements.length; i++)
			instructorName += (i == elements.length - 1)? (elements[i]):(elements[i] + " ");  
		
		return instructorName;
	}
	
	
	
	private boolean correctFormat(String line) {
		String[] elements = line.split("\\s+");
		
		// If number of elements is less than 5, there will not be enough fields for a CourseDBElement
		if (elements.length < 5)
			return false;
		
		
		/* Check courseID:
		 * 1. Size: 7 characters
		 * 2. First 4 characters are letters, and last 3 are numbers
		 */
		if (elements[0].length() != 7)
			return false;
		
		for (int i = 0; i < elements[0].length(); i++) {
			if ((i < 4) && !Character.isLetter(elements[0].charAt(i)))
				return false;
			
			if ((i >= 4) && !Character.isDigit(elements[0].charAt(i)))
				return false;
		}
		
		/* Check CRN:
		 * 1. Size: 5 characters
		 * 2. All characters are digits
		 */
		if (elements[1].length() != 5)
			return false;
		
		for (int i = 0; i < elements[1].length(); i++)
			if (!Character.isDigit(elements[1].charAt(i)))
				return false;
						
		/* Check Credits:
		 * 1. All characters are digits (assuming that the number of credits is less than 4 digits long)
		 */
		if (elements[2].length() > 3)
			return false;
		
		for (int i = 0; i < elements[2].length(); i++)
			if (!Character.isDigit(elements[2].charAt(i)))
				return false;
		
		/* Check Room Number:
		 * 1. Assume that this string is a representation of the room number (or format).
		 * 	  Not given enough information about formats other than distance learning and the AA000-formatted Room Numbers.
		 *    However, we will assume that the format includes the string "distance" or the format AA000
		 */
		boolean isDistance = false;
		if (elements[3].toLowerCase().contains("distance"))
			isDistance = true;
		
		if (!isDistance) {
			
			// Check size of AA000 format
			if (elements[3].length() != 5)
				return false;
			
			// Check pattern
			for (int i = 0; i < elements[3].length(); i++) {
				if ((i < 2) && !Character.isLetter(elements[3].charAt(i)))
					return false;
				
				if ((i >= 2) && !Character.isDigit(elements[3].charAt(i)))
					return false;
			}
		}
			
		
		
		/* Check Instructor Name:
		 * 1. Check that the remaining strings do not contain numbers.
		 *    Remember, a full name is not composed of only letters; 
		 *    it can also include hyphens, periods, accents, and whitespace in between names
		 */
		for (int i = 4; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length(); j++)
				if (Character.isDigit(elements[i].charAt(j)))
					return false;
		}
		
		return true;
	}

	@Override
	public ArrayList<String> showAll() {
		return courses.showAll();
	}

}

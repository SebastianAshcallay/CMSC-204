import java.util.*;
public class CourseDBElement implements Comparable<CourseDBElement> {

	private String courseID;
	private int CRN;
	private int numOfCredits;
	private String roomNum;
	private String instructorName;
	
	public CourseDBElement() {
		this.courseID = "N/A";
		this.CRN = 0;
		this.numOfCredits = 0;
		this.roomNum = "N/A";
		this.instructorName = "N/A";
	}
	
	public CourseDBElement(String courseID, int CRN, int credits, String room, String instructor) {
		this.courseID = courseID;
		this.CRN = CRN;
		this.numOfCredits = credits;
		this.roomNum = room;
		this.instructorName = instructor;
	}
	
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}
	
	public void setCredits(int credits) {
		this.numOfCredits = credits;
	}
	
	public void setID(String courseID) {
		this.courseID = courseID;
	}
	
	public void setRoomNum(String room) {
		this.roomNum = room;
	}
	
	public void setInstructor(String instructor) {
		this.instructorName = instructor;
	}
	
	
	public int getCRN() {
		return CRN;
	}
	
	public int getCredits() {
		return numOfCredits;
	}
	
	public String getID() {
		return courseID;
	}
	
	public String getRoomNum() {
		return roomNum;
	}
	
	public String getInstructor() {
		return instructorName;
	}
	
	@Override
	public String toString() {
		return String.format("\nCourse:%s CRN:%d Credits:%d Instructor:%s Room:%s", 
				getID(), getCRN(), getCredits(), getInstructor(), getRoomNum());
	}
	
	@Override
	public boolean equals(Object o) {
		CourseDBElement cde = (CourseDBElement) o;
		return this.getCRN() == cde.getCRN();
	}
	

	@Override
	public int compareTo(CourseDBElement o) {
		return this.getCRN() - o.getCRN();
	}

}

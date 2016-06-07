import java.util.Scanner;

public class Student {
	
	/*
	 * Properties
	 */
	String studentName;
	private Schedule schedule = new Schedule(); 
	static Scanner console;
	
	public void getStudentName() {
		  System.out.println("Please type your first and last name.");
		  studentName = console.next();
		  
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	

}
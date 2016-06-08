import java.util.Scanner;

public class LoginManager {
	
	
	/*
	 * Properties
	 */
	private Student student;
	private FileHandler filehandler = new FileHandler();
	String id;
	static Scanner console;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FileHandler getFilehandler() {
		return filehandler;
	}

	public void setFilehandler(FileHandler filehandler) {
		this.filehandler = filehandler;
	}

	
	/*
	 * Create a new student
	 *  assign the id and name 
	 *    --  for now use System.getProperty("user.name"
	 *  future implementation to do :
	 *  create a login procedure to get id name or create new
	 */
	public void logIn() {
		this.student = new Student();
		System.out.println("Please create user name.");
		String id = console.next();
		this.student.setId(id);
		this.student.setName(this.student.getId());
	}
}
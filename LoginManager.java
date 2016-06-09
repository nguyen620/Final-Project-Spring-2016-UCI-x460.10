import java.util.Scanner;

public class LoginManager {
	
	
	/*
	 * Properties
	 */
	private Student student;
	private FileHandler filehandler = new FileHandler();
	String id;
	Scanner console = new Scanner(System.in);

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
	 *  User will type user name in console.
	 */
	public void logIn() {
		this.student = new Student();
		System.out.println("Please create user name.");
		String id = console.nextLine();
		this.student.setId(id);
		this.student.setName(this.student.getId());
	}
}

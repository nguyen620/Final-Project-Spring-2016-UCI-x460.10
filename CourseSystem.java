
import java.util.Scanner;


public class CourseSystem {


	static String CATALOG_FILE = "courses.txt";
	static String STUDENT_FILE = "student.txt";
	static String SCHEDULE_FILE = "schedule.txt";

	static Screen scheduleScreen = new Screen();
	static Screen catalogScreen = new Screen();
	static Screen registerScreen = new Screen();
	static Screen welcomeScreen = new Screen();
	static Screen greetingScreen = new Screen();
	static Screen inputScreen = new Screen();

	static Catalog catalog = new Catalog();
	static LoginManager loginManger = new LoginManager();
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		init();
		start();
		login();
		while (true) {
			System.out.print(inputScreen.toString());
			String s = input.nextLine();
			if (inputValid(s)) {
				performAction(s);
			}
		}

	}
	
	private static void login() {
		loginManger.logIn();
		greetingScreen.setHead("Login successful for "+ loginManger.getStudent().getName());
		System.out.println(greetingScreen.toString());
		setupStudent();
		
	}

	private static void init() {
		CourseSystem.setScreens();
		catalog.getFileHandler().setFile(CATALOG_FILE);
		catalog.loadCourses();

	}

	private static void setScreens() {

		scheduleScreen.setHead("  Current Course Schedule :\n\n");
		scheduleScreen.setBody("No courses Scheduled");
		scheduleScreen.setFooter("\n");

		catalogScreen.setHead("\t All Courses in Catalog:\n\n");
		catalogScreen.setBody("No courses available");
		catalogScreen.setFooter("\n");

		registerScreen.setHead("Register Results :");
		registerScreen.setBody("No results");
		registerScreen.setFooter("\n");

		welcomeScreen.setHead("Welcome to Team B's Course Register System");
		welcomeScreen.setBody("\tLogging in ...");
		welcomeScreen.setFooter("\n");

		greetingScreen.setHead("Login successful ");
		greetingScreen.setBody("");
		greetingScreen.setFooter("\n");

		inputScreen.setHead("\nPlease enter an action to perform  ( catalog , schedule , register , unregister, exit ) : ");
		inputScreen.setBody("");
		inputScreen.setFooter("");
	}
	
	private  static void start() {
		System.out.println(welcomeScreen.toString());

	}
	
	
	private static void performAction(String action) {
		if (action.toLowerCase().equals("catalog")) {
			showCatalog();
		}
		if (action.toLowerCase().equals("schedule")) {
			showSchedule();
		}
		if (action.toLowerCase().equals("register")) {
			registerCourse();
		}
		if (action.toLowerCase().equals("unregister")) {
			unregisterCourse();
		}
		if (action.toLowerCase().equals("exit")) {
			System.out.println("Goodbye");
			System.exit(0);
		}

	}

	private static void unregisterCourse() {
		int id ;
		while (true) {
			System.out.print("\nPlease enter a Course Id to unregister or 'return' to return to main screen :");
			String s = input.nextLine();
			if (s.toLowerCase().equals("return")) {
				break;
			}
			try{
				id = Integer.parseInt(s,10);
				loginManger.getStudent().getSchedule().removeCourse(id);
			}catch(Exception e){
				System.out.println("Not a valid Course number " + e.toString() );
			}
			
		}

	}

	private static void registerCourse() {
		int id ;
		while (true) {
			System.out.print("\nPlease enter a Course Id to register or 'return' to return to main screen :");
			String s = input.nextLine();
			if (s.toLowerCase().equals("return")) {
				break;
			}
			try{
				id = Integer.parseInt(s,10);
				loginManger.getStudent().getSchedule().addCourse(id);
			}catch(Exception e){
				System.out.println("Not a valid Course number " + e.toString() );
			}
			
		}

	}
	private static void setupStudent(){
		loginManger.getStudent().getSchedule().setStudentId(loginManger.getStudent().getId());
		loginManger.getStudent().getSchedule().setCatalog(catalog);
		loginManger.getStudent().getSchedule().getFileHandler().setFile(SCHEDULE_FILE);
		loginManger.getStudent().getSchedule().loadSchedule();
	}
	
	private static void showSchedule() {
		
		scheduleScreen.setBody(loginManger.getStudent().getSchedule().toString());
		System.out.println(scheduleScreen.toString());

	}

	private static void showCatalog() {
		catalogScreen.setBody(catalog.toString());
		//System.out.print("\033[H\033[2J");  
	    //System.out.flush();
		//System.out.println(catalogScreen.toString());
		char s = 27 ;
		System.out.print(s+"[2J");
	    System.out.flush();

	}

	private static boolean inputValid(String s) {
		boolean valid = false;
		valid = s.toLowerCase().equals("catalog") || s.toLowerCase().equals("schedule")
				|| s.toLowerCase().equals("register") || s.toLowerCase().equals("unregister")
				|| s.toLowerCase().equals("exit");
		if (!valid) {
			System.out.println("Input not valid");
		}
		return valid;
	}


	


}

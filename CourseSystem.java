
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
		greetingScreen.setHead("Login successful for " + loginManger.getStudent().getName());
		System.out.println(greetingScreen.toString());
		setupStudent();

	}

	private static void init() {
		initCourseFile(CATALOG_FILE);
		initScheduleFile(SCHEDULE_FILE);
		inittudentFile(STUDENT_FILE);
		CourseSystem.setScreens();
		catalog.getFileHandler().setFile(CATALOG_FILE);
		catalog.loadCourses();

	}

	private static void setScreens() {

		scheduleScreen.setHead("  Current Course Schedule :\r\n\r\n");
		scheduleScreen.setBody("No courses Scheduled");
		scheduleScreen.setFooter("\r\n");

		catalogScreen.setHead("\t All Courses in Catalog:\r\n\r\n");
		catalogScreen.setBody("No courses available");
		catalogScreen.setFooter("\r\n");

		registerScreen.setHead("Register Results :");
		registerScreen.setBody("No results");
		registerScreen.setFooter("\r\n");

		welcomeScreen.setHead("Welcome to Team B's Course Register System");
		welcomeScreen.setBody("\tLogging in ...");
		welcomeScreen.setFooter("\r\n");

		greetingScreen.setHead("Login successful ");
		greetingScreen.setBody("");
		greetingScreen.setFooter("\r\n");

		inputScreen.setHead(
				"\r\nPlease enter an action to perform  ( catalog , schedule , register , unregister, exit ) : ");
		inputScreen.setBody("");
		inputScreen.setFooter("");
	}

	private static void start() {
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
		int id;
		while (true) {
			System.out.print("\r\nPlease enter a Course Id to unregister or 'return' to return to main screen :");
			String s = input.nextLine();
			if (s.toLowerCase().equals("return")) {
				break;
			}
			try {
				id = Integer.parseInt(s, 10);
				loginManger.getStudent().getSchedule().removeCourse(id);
			} catch (Exception e) {
				System.out.println("Not a valid Course number " + e.toString());
			}

		}

	}

	private static void registerCourse() {
		int id;
		while (true) {
			System.out.print("\r\nPlease enter a Course Id to register or 'return' to return to main screen :");
			String s = input.nextLine();
			if (s.toLowerCase().equals("return")) {
				break;
			}
			try {
				id = Integer.parseInt(s, 10);
				loginManger.getStudent().getSchedule().addCourse(id);
			} catch (Exception e) {
				System.out.println("Not a valid Course number ");
			}

		}

	}

	private static void setupStudent() {
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
		System.out.println(catalogScreen.toString());

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

	private static void initCourseFile(String file) {
		File f = new File(file);
		if (f.isFile()) {
			// do nothing
		} else {
			// create default file
			PrintWriter writer;
			try {
				writer = new PrintWriter(file, "UTF-8");

				writer.println("3	Algebra 101	01/02/2016 - 04/01/2016 m w f	Intro to Algebra	20	10");
				writer.println("4	Algebra 102	01/02/2016 - 04/01/2016 m w f	Advanced Algebra	20	11");
				writer.println("1	Biology 101	01/02/2016 - 04/01/2016 m w f	Beginning Biology	20	12");
				writer.println("2	Biology 102	01/02/2016 - 04/01/2016 m w f	Advanced Biology	20	14");
				writer.println("8	English 102	01/02/2016 - 04/01/2016 m w f	College Prep English	20	10");
				writer.println("7	English 101	01/02/2016 - 04/01/2016 m w f	Advanced AP English	20	10");
				writer.println("6	US History	01/02/2016 - 04/01/2016 m w f	Guide to US History	20	10");
				writer.println("5	World History	01/02/2016 - 04/01/2016 m w f	Intro to World History	20	10");
				writer.println("9	Psychology 101	05/01/2016 - 08/01/2016 m w f	Learning Psychology	10	10");
				writer.println("10	Sociology 101	05/01/2016 - 08/01/2016 m w f	Learning Sociology	10	0");
				writer.println("11	Computing 101	05/01/2016 - 08/01/2016 m w f	Beginning Computers	15	0");
				writer.println("12	Computing 102	05/01/2016 - 08/01/2016 m w f	Intermediate Computers	15	0");
				writer.println("13	Computing 103	05/01/2016 - 08/01/2016 m w f	Advanced Computers	15	0");
				writer.println("14	Arts 101	05/01/2016 - 08/01/2016 m w f	Arts for Beginners	25	0");
				writer.println("15	Arts 103	05/01/2016 - 08/01/2016 m w f	Arts for Professionals	25	0");

				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void initScheduleFile(String file) {
		File f = new File(file);
		if (f.isFile()) {
			// do nothing
		} else {
			// create default file
			PrintWriter writer;
			try {
				writer = new PrintWriter(file, "UTF-8");
				writer.println("gsf17	8");
				writer.println("gsf17	2");
				writer.println("gsf17	1");
				writer.println("gsf17	3");
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void inittudentFile(String file) {
		File f = new File(file);
		if (f.isFile()) {
			// do nothing
		} else {
			// create default file
			PrintWriter writer;
			try {
				writer = new PrintWriter(file, "UTF-8");
				writer.println("gsf17	gsf17	password123");
				writer.println("admin	admin	admin");
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

import java.util.ArrayList;
import java.util.Collections;

public class Schedule {

	// default constructor
	public Schedule() {

	}
  
	public Schedule(Student student, Catalog catalog){
		studentId = student.getId();
		this.catalog = catalog;		
			 
	}

	// properties
	private String file = "schedule.tab";
	private String studentId ;
	private Catalog catalog ;
	private FileHandler fileHandler = new FileHandler();
	private ArrayList<Integer> courseIds = new ArrayList<Integer>();
	private String HEADER = "Id\tName\t\tDates\t\t\t\tSummary\t\t\tSeats\tStudents\n" + 
			  	"-------------------------------------------------------------------------------------------------\n";	
		
	// get file
	public String getFile() {
		return file;
	}

	// set file
	public void setFile(String file) {
		this.file = file;			 
	}

	// get student id
	public String getStudentId() {
		return studentId;
	}

	// set student id
	public void setStudentId(String studentId) {
		this.studentId = studentId;	 
	}

	// get catalog
	public Catalog getCatalog() {
		return catalog;	 
	}

	// set catalog
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog; 
	}

	// get file handler
	public FileHandler getFileHandler() {
		return fileHandler;
	}

	// set file handler
	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler; 
	}

	// get course ids
	public ArrayList<Integer> getCourseIds() {
		return courseIds;
	}

	// set course ids
	public void setCourseIds(ArrayList<Integer> courseIds) {
		this.courseIds = courseIds; 
	}

	// load schedule from file
	public void loadSchedule (){
		// get data from file handler
		ArrayList<String> data = fileHandler.getData();

		// create a new array list 
		courseIds = new ArrayList<Integer>();
	
		// traverse through the data to find student's courses
		for (String temp : data) {
			// Split string by tab
			String[] entry = temp.split("\t");

			// if studenId equals the one from the file, add course
			if (entry[0].equals(studentId)) {
				courseIds.add(new Integer(Integer.parseInt(entry[1])));
			}
		}		 
	}

	// save schedule to file
	public void saveSchedule(){
		// temp arraylist of string
		ArrayList<String> temp = new ArrayList<String>();

		// put the course ids into the temp arraylist
		for (Integer courseId : courseIds) {
			temp.add(studentId + "\t" + courseId.toString());
		}

		// save file
		fileHandler.saveData(temp); 
	}

	// print schedule
	public void printSchedule(){
		// traverse through courses and print
		for (Integer courseId : courseIds) {
			System.out.println(catalog.getCourse(courseId));
		}	
	} 

	// add course to schedule
	public void addCourse(int id){
		Course course;
		
		// check to see if course is already in schedule
		if (courseInSchedule(id)) {
			System.out.println("Already registered for Course " + String.valueOf(id) + " " + catalog.getCourse(id).getName());
		}
		else {
			course = catalog.getCourse(id);

			// check to see if exists
			if (course == null)
			{
				System.out.println("Course " + String.valueOf(id) + " is not in Catalog");
			}
			// check to see if course is full
			else if (course.getSize() >= course.getLimit()) {
				System.out.println("Course " + course.getId() + " " + course.getName() + " is Full");
			}
			// add course
			else {
				courseIds.add(new Integer(course.getId()));
				
				// update course size and save
				course.setSize(course.getSize() + 1);
				catalog.saveCourses();
				saveSchedule();

				System.out.println("Cousre " + course.getId() + " " + course.getName() + " added");
			}
		}		
	}

	// remove course from schedule
	public void removeCourse(int id) {
		Course course;

		// check to see if course is in schedule
		if (!courseInSchedule(id)) {
			System.out.println("Course " + String.valueOf(id) + " is not in your schedule");
		}
		// remove course
		else {
			course = catalog.getCourse(id);
			courseIds.remove(new Integer(course.getId()));
			
			// update size and save
			course.setSize(course.getSize() - 1);
			catalog.saveCourses();
			saveSchedule();
			
			System.out.println("Course " + course.getId() + " " + course.getName() + " removed");
		}
	}

	// check to see if course is already in schedule
	public boolean courseInSchedule( int id ){
		return courseIds.contains(id);
	}

	// concat HEADER and list of courses (in order)
	@Override
	public String toString() {
		String string = HEADER;
		ArrayList<Course> courses = new ArrayList<Course>();

		// go through schedule and add to arraylist to srot
		for (Integer courseId : courseIds) {
			courses.add(catalog.getCourse(courseId));
		}
		
		Collections.sort(courses);
	
		// add courese to the string
		for (Course course : courses) {
			string += course.toString() + "\n";
		}

		return string;
	}
}

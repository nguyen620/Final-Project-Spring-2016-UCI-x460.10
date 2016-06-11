import java.util.ArrayList;
import java.util.Collections;

public class Schedule {

	/*
	 * Defualt constructor
	 */
	public Schedule() {

	}

	/*
	 * Overloaded constructor for more creation control and convienance
	 */
	public Schedule(Student student, Catalog _catalog) {
		// needs implemeted

	}

	/*
	 * Properties
	 */
	private String file = "schedule.tab";
	private String studentId;
	private Catalog catalog;
	private FileHandler fileHandler = new FileHandler();
	private ArrayList<Integer> courseIds = new ArrayList<Integer>();

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public FileHandler getFileHandler() {
		return fileHandler;
	}

	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}

	public ArrayList<Integer> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(ArrayList<Integer> courseIds) {
		this.courseIds = courseIds;
	}

	/*
	 * 1.Create array list and get data from filehandler
	 * 2. create new arraylist for courseIds
	 * 3. iterate over data
	 * 3a. -- split each line using \t
	 * 3.b  if the id in line equals the student id  
	 * 3.c  then add the courseid to the courseIds
	 */
	public void loadSchedule() {
		ArrayList<String> data = fileHandler.getData();
		courseIds = new ArrayList<Integer>();
		for (String l : data) {
			String[] line = l.split("\t");
			if (line[0].equals(studentId)) {
				courseIds.add(new Integer(Integer.parseInt(line[1])));
			}
		}
	}

	/*
	 * 1. create arrayList to hold newData
	 * 2. iterate over courseIds
	 * 2a. -- add StudentId + courseId.to_string()  to new date ,  delimit with \t
	 * 3. save data with filehandler
	 */
	public void saveSchedule() {
		ArrayList<String> newData = new ArrayList<String>();
		for (Integer courseId : courseIds) {
			newData.add(studentId + "\t" + courseId.toString());
		}
		fileHandler.saveData(newData);

	}

	/*
	 * 1. Iterate over courseIds
	 * 1a -- get course from catalog with id  and print it out to system.
	 */
	public void printSchedule() {
		for (Integer courseId : courseIds) {
			System.out.println(catalog.getCourse(courseId));
		}

	}

	/*
	 * 1. Create a Type Course reference, course 
	 * 2.check to see if id is in Schedule
	 * 3 if it is then output to system 'course is already resgistered'
	 * 4. else get course from catalog with the id  and assign to course
	 * 5. if course is null 
	 * 6. then output to system 'Course not in catalog' 
	 * 7. else if course size is greater or equal to course limit
	 * 8. then output to system course is full
	 * 9. else add course id to courseIDs
	 * 10 and increase course size by 1
	 * 11 and save catalog
	 * 12 and save schedule
	 * 13 and output to system success of adding course .. inlcude course name
	 */
	public void addCourse(int id) {
		Course course;
		if (courseInSchedule(id)) {
			System.out.println("Already registered for Course");
		} else {
			course = catalog.getCourse(id);

			if (course == null) {

				System.out.println("Course is not in Catalog");
			} else if (course.getSize() >= course.getLimit()) {

				System.out.println("Course is Full");
			} else {

				courseIds.add(new Integer(course.getId()));
				course.setSize(course.getSize() + 1);
				catalog.saveCourses();
				saveSchedule();
				System.out.println("Course " + course.getId() + " " + course.getName() + " added ");

			}
		}

	}

	/*
	 * 1. create a Course type reference 
	 * 2. if id is not in schedule 
	 * 3. then output to system "Course is not currently in schedule "
	 * 4. else get Course from catalog with ID and assign to course
	 * 5. and remove ID from coursids
	 * 6.  and save catalog
	 * 7. and saveSchedule
	 * 8. output to system success of removing course  include course name
	 */
	public void removeCourse(int id) {
		Course course;
		if (!courseInSchedule(id)) {
			System.out.println("Course is not currently in schedule ");
		} else {
			course = catalog.getCourse(id);
			courseIds.remove(new Integer(course.getId()));
			course.setSize(course.getSize() - 1);
			catalog.saveCourses();
			saveSchedule();
			System.out.println("Course " + course.getId() + " " + course.getName() + " removed ");

		}

	}

	
	/*
	 * check if id is in courseIDs
	 * return results
	 */
	public boolean courseInSchedule(int id) {
		boolean present = false;
		for (Integer courseId : courseIds) {
			if (courseId == id) {
				present = true;
				break;
			}
		}
		return present;
	}
	
	/*
	 * 1.Create String , list
	 * 2. create new Arraylist,  courses
	 * 3. iterate over courseIds
	 * 3a. -- get Course from catalog with Id
	 * 3b --  add course to courses
	 * 4.  sort courses
	 * 5. add header to list "Id\tName\t\tDates\t\t\t\tSummary\t\t\tSeats\tStudents\n"
	 * 6. iterate over courses
	 * 6a. -- add courses.to_string() to list
	 * 7. return list
	 */


	@Override
	public String toString() {
		String list = "";
		ArrayList<Course> courses = new ArrayList<Course>();
		for (Integer courseId : courseIds) {
			courses.add(catalog.getCourse(courseId));
		}
		Collections.sort(courses);
		list ="Id\tName\t\tDates\t\t\t\tSummary\t\t\tSeats\tStudents\n" +
				"-------------------------------------------------------------------------------------------------\n";
		for (Course c : courses) {
			list += c.toString() + "\n";
		}

		return list;
	}
}

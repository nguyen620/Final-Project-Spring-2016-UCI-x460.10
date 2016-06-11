import java.util.ArrayList;
import java.util.Collections;

public class Catalog {


     // Instantiate FileHandler Object and Courses ArrayList

     private FileHandler fileHandler = new FileHandler();
     private ArrayList<Course> courses;
	
     // Setter and Getter Methods

     public FileHandler getFileHandler() {
          return fileHandler;
     }
     public void setFileHandler(FileHandler fileHandler) {
          this.fileHandler = fileHandler;
     }
     public ArrayList<Course> getCourses() {
          return courses;
     }
     public void setCourses(ArrayList<Course> courses) {
          this.courses = courses;
     }

     /* Create Course Reference Course
      * Iterate over the courses
      * If course id matches courses id, break and return course
      */

     public Course getCourse(int id ){
          Course course = null;
          for(Course c : courses){
               if( c.getId() == id){
                    course = c;
		    break;
	       }
	  }
	  return course;
     }

     /*  Read courses from file
      *  1.Loads a course
      *  2.Get ArrayList of data from fileHandler
      *  3.Create a new ArrayList for courses
      *  4.Iterate over data and split each line with "\t" token
      *  4a. -- create a new Course and set values from data 
      *  4b. -- add course object to courses ArrayList
      *  5. Sort courses
      */

     public void loadCourses() {
          ArrayList<String> data = fileHandler.getData();
          courses = new ArrayList<Course>();
          for(String l : data){
               String[] line = l.split("\t"); 
               Course course = new Course();
               course.setId(Integer.parseInt(line[0],10));
               course.setName(line[1]);
               course.setDates(line[2]);
               course.setSummary(line[3]);
               course.setLimit(Integer.parseInt(line[4],10));
               course.setSize(Integer.parseInt(line[5],10));
               courses.add(course);
          }
          Collections.sort(courses);			 
     }

     /*  Save courses to file
      *  1.Create ArrayList to hold data
      *  2.Iterate over courses
      *  2a. --  Add courses.to_string() to dataList
      *  3.Save dataList using fileHandler
      */
     
     public void saveCourses(){
          ArrayList<String> data = new ArrayList<String>();
          for(Course c : courses){
               data.add(c.toString());
          }
          fileHandler.saveData(data);
     }

     /* List Courses in Catalog
      * 1. Create a list string 
      * 2. Add a header to the string for example "Id\tName\t\tDates\t\t\t\tSummary\t\t\tSeats\tStudents\n" 
      * 3. Iterate over courses
      * 3a. -- Add course.to_string() to the list string
      * 4. Return list string
      */

     public String toString() {
          String list ="Id\tName\t\tDates\t\t\t\tSummary\t\t\tSeats\tStudents\n" +
				"-------------------------------------------------------------------------------------------------\n";
          for(Course c : courses){
               list += c.toString() + "\n";
          }
          return list;
     }

}
public class Course implements Comparable<Course> {


     // Instance Variables (Properties)

     private int id;
     private String name = "";
     private String summary = "";
     private int limit = 0;
     private int size = 0;
     private String dates = "";  
     
     // Constructor

     public Course() {
		
     }

     // Getter and Setter Methods

     public int getId() {
          return id;
     }
     public void setId(int id) {
          this.id = id;
     }
     public String getName() {
          return name;
     }
     public void setName(String name) {
          this.name = name;
     }
     public String getSummary() {
          return summary;
     }
     public void setSummary(String summary) {
          this.summary = summary;
     }
     public int getLimit() {
          return limit;
     }
     public void setLimit(int limit) {
          this.limit = limit;
     }
     public int getSize() {
          return size;
     }
     public void setSize(int size) {
          this.size = size;
     }
     public String getDates() {
          return dates;
     }
     public void setDates(String dates) {
          this.dates = dates;
     }

     // Override to_string()  
     // Return the concat of : id , name, dates, summary, limit, size 
     // -- delimited with /t 

     public String toString() {
          return getId() + "\t" +getName() + "\t" + getDates() + "\t" +getSummary()+"\t"+ getLimit() + "\t" + getSize();
     }

     // Implement compareTo method for sorting courses alphabetically

     public int compareTo(Course o) {
          return this.getName().compareTo(o.getName());
     }	 
   
}
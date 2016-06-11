
public class Student {
	
	/*
	 * Properties
	 */
	private String id = "";
	private String name = "";
	private Schedule schedule = new Schedule(); 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	

}

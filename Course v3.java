interface CourseInterface{
	int getCourseID();
	void setCourseID(int courseID);
	String getCourseName();
	void setCourseName(String courseName);
}

public class Course implements CourseInterface{
	// variables
	private int courseID;
	private String courseName;
	
	// constructor
	public Course() {
	}
	public Course(int cID, String cName) {
		courseID = cID;
		courseName = cName;
	}
	
	// getters and setters
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
			
}	
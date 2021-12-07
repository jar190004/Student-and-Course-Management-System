import java.util.ArrayList;

interface StudentInterface{
	String getFirst();
	void setFirst(String first);
	String getLast();
	void setLast(String last);
	String getLvl();
	void setLvl(String lvl);
	int getID();
	void setID(int iD);
	boolean isActive();
	void setActive(boolean status);
}

class StudentEmployee extends Student{
	// variables
	protected String newJob;
	protected String newJobType;
	
	// constructors
	public StudentEmployee(Student workStudent, String job, String jobType) {
		first = workStudent.getFirst();
		last = workStudent.getLast();
		ID = workStudent.getID();
		newJob = job;
		newJobType = jobType;
	}
	public StudentEmployee() {
	}
	
	// methods
	
	/*
	 * Name: displayEmployee 
	 * Description: display all students who are employed
	 */
	public void displayEmployee (ArrayList <StudentEmployee> employeeList) {
		// loop to get each student off of arraylist
		for (int i = 0; i < employeeList.size(); i++) {
			sortEmployee(employeeList); // sort employee in alphabetical order
			
			// print info of employees
			System.out.println(employeeList.get(i).getFirst() + " " + employeeList.get(i).getLast());
			System.out.println("ID - " + employeeList.get(i).getID());
			System.out.println(employeeList.get(i).getNewJobType() + " " + employeeList.get(i).getNewJob() + "\n");
		}
		if(employeeList.isEmpty()) {
			System.out.print("There are currently no students that have a job. Try adding a job to a student by pressing '5'\n");
		}
	}
	
	/*
	 * Name: sortEmployee
	 * Description: sort by first names from employeeList
	 */
	public void sortEmployee (ArrayList<StudentEmployee> employeeList) {
		ArrayList<StudentEmployee> tempList = employeeList;
		
		// 2 loops to get 2 first names
		for(int i = 0; i < tempList.size(); i++) {
			for(int j = i + 1; j < tempList.size() ; j++) {
				
				// get first names, compare
				if (tempList.get(i).getFirst().compareTo(tempList.get(j).getFirst()) > 0) { 
					swapEmployee(tempList, i, j);
				}
			}
		}
		employeeList = tempList;
	}
	
	/*
	 * Name: swapEmployee
	 * Description: swap positions of array location from sortEmployee method if needed to achieve ascending order
	 */
	public void swapEmployee (ArrayList<StudentEmployee> tempList, int i, int j) {
		StudentEmployee tempEmployee = tempList.get(i);
		tempList.set(i, tempList.get(j));
		tempList.set(j, tempEmployee);
	}
	
	// getters and setters
	public String getNewJob() {
		return newJob;
	}

	public void setNewJob(String newJob) {
		this.newJob = newJob;
	}

	public String getNewJobType() {
		return newJobType;
	}

	public void setNewJobType(String newJobType) {
		this.newJobType = newJobType;
	}
	
}

public class Student implements StudentInterface{
	
	// variables
	protected String first;
	protected String last;
	private String lvl;
	protected int ID;
	private boolean active;
	
	//constructors
	public Student() {
	}
	
	public Student(String fName, String lName, String level, int iD ) {
		first = fName;
		last = lName;
		lvl = level;
		ID = iD;
		active = true;
	}
	
	// getters and setters
	
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getLvl() {
		return lvl;
	}

	public void setLvl(String lvl) {
		this.lvl = lvl;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean status) {
		this.active = status;
	}

}

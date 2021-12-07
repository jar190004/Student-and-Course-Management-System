/*
 * Name: Joselito Reyes
 * Course Section: ITSS3312.002
 * Description: Project 3 - Student Management System & Course Management System
 * Implement a Java program that creates a Student Management System and a Course Management System
 * Date: November 16, 2021
 */
import java.util.*;
import java.io.*;

public class JoselitoReyes_Section002_Project2 {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// variables, objects
		ArrayList<Student> studentList = new ArrayList<Student>(); 
		ArrayList<StudentEmployee> employeeList = new ArrayList<StudentEmployee>(); 
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		StudentEmployee studentEmployee = new StudentEmployee();
		boolean closeProgram = false;
		
		// print introduction statement, center aligned
		String introLine = centerString(75, "Welcome to Student and Course Management System!\n");
		System.out.println(introLine);
		System.out.print("This system will allow you to manage students and courses. Let’s start with the number \nof students this system will have: ");
		int studentNum = input.nextInt(); // set the number of students allowed to be input 
		
		// MAIN MENU 
		while (!closeProgram) {
			System.out.println("\n***Welcome to Student and Management System***");
			System.out.println("\nPress ‘1’ for Student Management System (SMS)");
			System.out.println("Press ‘2’ for Course Management System (CMS)");
			System.out.println("Press ‘0’ to exit\n");
			int mainChoice = input.nextInt();
			
			// SMS sub menu
			if (mainChoice == 1) { 
				boolean exit = false;
				while (!exit) {
					System.out.println("\n***Welcome to SMS***");
					System.out.println("\nPress ‘1’ to add a student");
					System.out.println("Press ‘2’ to deactivate a student");
					System.out.println("Press ‘3’ to display all students");
					System.out.println("Press ‘4’ to search for a student by ID");
					System.out.println("Press ‘5’ to assign on-campus job");
					System.out.println("Press ‘6’ to display all students with on-campus jobs");
					System.out.println("Press ‘0’ to exit SMS\n");
					int smsChoice = input.nextInt();
					
					// switch cases for user inputs
					switch(smsChoice) {
						case 1: // send to addStudent method
							if (studentList.size() < studentNum) { // makes sure that number of students able to be added does not go over user input size
								Student stud = addStudent();
								studentList.add(stud);
							}
							else {
								System.out.println("You have exceed the number of student allowed to be input in the system. Try a different option.");
							}
							break;
							
						case 2: // send to deactivateStudent method
							deactivateStudent(input, studentList);
							break;
							
						case 3: // send to displayStudent method
							displayStudent(studentList);
							writeToFile(studentList);
							break;
							
						case 4: // send to searchStudent method
							searchStudent(input, studentList);
							break;
							
						case 5: // assign on-campus job
							assignJob(studentList, employeeList);
							break;
							
						case 6: // display all students with on-campus jobs
							studentEmployee.displayEmployee(employeeList);
							break;
						case 0: // return to main menu
							exit = true;
							break;
							
						default: // default print statement if user input is not within cases
							System.out.println("Invalid choice, the only options are \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", or \"0\". Try again."); 	
							break;
					}
				}
			}
			
			// CMS sub menu
			else if (mainChoice == 2){
				boolean exit = false;
				System.out.println("\n***Welcome to CMS***");
				
				while (!exit) {
					System.out.println("\nPress ‘1’ to add a new course");
					System.out.println("Press ‘2’ to assign student a new course");
					System.out.println("Press ‘3’ to display student with assigned courses");
					System.out.println("Press ‘0’ to exit CMS\n");
					int smsChoice = input.nextInt();
					
					// switch cases for user inputs
					switch(smsChoice) {
						case 1: //add to Course object and write to Courses.txt
							addCourse(courseList);
							break;
							
						case 2: //write to CourseAssignment.txt
							assignCourse(studentList, courseList);
							break;
							
						case 3: //read from CourseAssignment.txt
							displayStudentCourse();
							break;
						
						case 0: // return to main menu
							exit = true;
							break;
							
						default: // default print statement if user input is not within cases
							System.out.println("Invalid choice, the only options are \"1\", \"2\", \"3\", or \"0\". Try again."); 	
							break;
					}
				}
			}
			
			// close program
			else if(mainChoice == 0) {
				System.out.println("\n\nGood Bye!!!");
				closeProgram = true;
				break;
			}
			
			// default print statement if user input is not within cases
			else {
				System.out.println("Invalid choice, the only options are \"1\", \"2\", or \"0\". Try again.");
			}
		}
	}
	
	// all methods below

	static Scanner input = new Scanner(System.in);
	/*
	 * Name: addStudent
	 * Description: add student information such as first name, last name, level, and ID # into an arraylist of the student class
	 */
	private static Student addStudent() {
		// variables
		double x;
		boolean valid = true;
		String lvl = null;
		
		// ask user for first name, last name, and level
		System.out.print("Enter first name: ");
		String fName = input.nextLine();
		
		System.out.print("Enter last name: ");
		String lName = input.nextLine();
	
		while (valid) { // options for student level = Freshman, Sophomore, Junior, or Senior
			System.out.print("Enter level of the student: ");
			lvl = input.nextLine();	
		
			if (lvl.matches("Freshman") || lvl.matches("Sophomore") || lvl.matches("Junior")  || lvl.matches("Senior")) {
				valid = false;
			}
			else { // invalid input, default statement 
				System.out.println("Try again, the only options are \"Freshman\", \"Sophomore\", \"Junior\", or \"Senior\"");
			}
		}
				
		// generate a random integer ID number between 0-99 that will be assigned to each student
		double randNumber = Math.random();
		x = randNumber * 100;
		int iD = (int)x;
		
		// print summary
		System.out.print("\n" + fName + " " + lName +" has been added as a " + lvl + " with ID " + iD + "\n");
		
		return new Student(fName, lName, lvl, iD); // return to be saved into Student class	
	}
	
	/*
	 * Name: deactivateStudent
	 * Description: search the arraylist by ID number through a loop and remove that from the list if user input = ID #
	 */
	private static void deactivateStudent(Scanner input, ArrayList<Student> studentList) {
		try {	
			// ask user which student based on ID number to deactivate
			System.out.print("Enter the ID for the student you want to deactivate: ");
			int userInput = input.nextInt();
			
			// loop to search through each ID in the arraylist
			for (int i = 0; i < studentList.size(); i++) {
				Student tempStudent = studentList.get(i);
				int id = tempStudent.getID();
				
				// if ID# matches input ID#, set boolean to false = "deactivate"
				if (id == userInput) {
					System.out.println("\n" + studentList.get(i).getFirst() + " " + studentList.get(i).getLast() + " has been deactivated");
					studentList.get(i).setActive(false);
				}
			}
		}catch(InputMismatchException e){
			System.out.print("\nAn Error Occured. \nYou need to input digits for Student ID. You've been sent back to the SMS Menu.\n ");
			input.nextLine();
		}
	}

	/*
	 * Name: dispayStudent 
	 * Description: display all current active students
	 */
	private static void displayStudent (ArrayList<Student> studentList) {
		// loop to get each student off of arraylist
		for (int i = 0; i < studentList.size(); i++) {
			sortStudent(studentList);//sort students in alphabetical order based on first names
			
			//print info
			System.out.println("\n" + studentList.get(i).getFirst() + " " + studentList.get(i).getLast());
			System.out.println("ID: " + studentList.get(i).getID());
			System.out.println("Level: " + studentList.get(i).getLvl());
			
			// for status if true = active , false = deactivated
			if(studentList.get(i).isActive()) {
				System.out.println("Status: Active");
			}
			else {
				System.out.println("Status: Deactivated");
			}
		}
	}
	
	/*
	 * Name: writeToFile
	 * Description: writes to a file named StudentReport.txt
	 */
	private static void writeToFile (ArrayList<Student> studentList) {
		try{
			// create file
			File file =new File("StudentReport.txt");
			if(!file.exists()){
			 	file.createNewFile();
			  }
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println("");
			int size = studentList.size();
			
			for(int i = 0; i < size; i++) { // output all info into StudentReport.txt
				pw.println("");
				pw.write(studentList.get(i).getFirst() + " " + studentList.get(i).getLast() + "\n");
				pw.write("ID: " + studentList.get(i).getID() + "\n");
				pw.write("Level: " + studentList.get(i).getLvl() + "\n");
				if(studentList.get(i).isActive()) {
					pw.write("Status: Active\n");
				}
				else {
					pw.write("Status: Deactivated\n");
				}
			}
			pw.flush();
			pw.close();

		}catch(IOException e){
			System.out.println("Exception occurred:");
			e.printStackTrace();
		}	
	}
	
	/*
	 * Name: searchStudent 
	 * Description: search a student by ID number through a loop and print out their information if user inpout = ID #
	 */
	private static void searchStudent (Scanner input, ArrayList<Student> studentList) {
		//ask for ID
		try {
		System.out.print("Enter Student ID: ");
		int userInput = input.nextInt();
		
			//loop to search student info in arraylist
			for (int i = 0; i < studentList.size(); i++) {
				Student tempStudent = studentList.get(i);
				int id = tempStudent.getID();
				
				// if ID# is = userinput, print info
				if (id == userInput) {
					System.out.println("\n" + studentList.get(i).getFirst() + " " + studentList.get(i).getLast());
					System.out.println("ID: " + studentList.get(i).getID());
					System.out.println("Level: " + studentList.get(i).getLvl());
					
					// for status if true = active, false = deactivated
					if(studentList.get(i).isActive()) {
						System.out.println("Status: Active");
					}
					else {
						System.out.println("Status: Deactivated");
					}
				}
			}
		}catch(InputMismatchException e){
			System.out.print("\nAn Error Occured. \nYou need to input digits for Student ID. You've been sent back to the SMS Menu.\n ");
			input.nextLine();
		}
	}
	
	/*
	 * Name: assignJob
	 * Description: adds job and job type to object 
	 */
	private static void assignJob(ArrayList<Student> studentList, ArrayList<StudentEmployee> employeeList) {
		try {
			// variables
			boolean notFound = true;
			boolean jobValid = true;
			boolean jobTypeValid = true;
			String job = null;
			String jobType = null;
			
			// ask user for student's ID, job, and job type
			System.out.print("Enter student ID: ");
			int userInput = input.nextInt();
			
			while (jobValid) { // only choices = TA or RA
				System.out.print("Enter job: ");
				job = input.next();
			
				if (job.matches("TA") || job.matches("RA")) {
					jobValid = false;
				}
				else { // invalid input, default statement 
					System.out.println("Try again, the only options are \"TA\" or \"RA\"");
				}
			}
			
			while (jobTypeValid) { // only choices full-time or part-time
				System.out.print("Enter job type: ");
				jobType = input.next();
			
				if (jobType.matches("Full-time") || jobType.matches("Part-time")) {
					jobTypeValid = false;
				}
				else { // invalid input, default statement 
					System.out.println("Try again, the only options are \"Full-time\" or \"Part-time\"");
				}
			}
	
			//loop to search student info in arraylist
			for (int i = 0; i < studentList.size(); i++) {
				Student tempStudent = studentList.get(i);
				int id = tempStudent.getID();
				
				// if ID# is = userinput, set new info and print info
				if (id == userInput) {
					// add new job and job type to Array List
					System.out.println(studentList.get(i).getFirst() + " " + studentList.get(i).getLast() + " has been assigned " + jobType + " " + job + " job");
					employeeList.add(new StudentEmployee(studentList.get(i), job, jobType));
					notFound = false;
				}
			}
			
			if(notFound) { // if not found, default print statement
				System.out.println("Sorry, this student ID# does not exist. Try again.");
			}
		}catch(InputMismatchException e){
			System.out.print("\nAn Error Occured. \nYou need to input digits for Student ID. You've been sent back to the SMS Menu.\n ");
			input.nextLine();
		}
	}
	
	/*
	 * Name: sortStudent
	 * Description: sort using first names from studentList in ascending order
	 */
	public static void sortStudent (ArrayList<Student> studentList) {
		ArrayList<Student> tempList = studentList;
		
		// loop to get 2 positions of array
		for(int i = 0; i < tempList.size(); i++) {
			for(int j = i + 1; j < tempList.size() ; j++) {
				
				// get first names, compare
				if (tempList.get(i).getFirst().compareTo(tempList.get(j).getFirst()) > 0) { 
					swapStudent(tempList, i, j);
				}
			}
		}
		studentList = tempList;
	}
	
	/*
	 * Name: swapStudent
	 * Description: swap positions of array location from sortStudent method if needed to achieve ascending order
	 */
	public static void swapStudent (ArrayList<Student> tempList, int i, int j) {
		Student tempStudent = tempList.get(i);
		tempList.set(i, tempList.get(j));
		tempList.set(j, tempStudent);
	}
	
	/*
	 * Name: centerString
	 * Description: center aligns a string
	 */
	public static String centerString (int width, String s) {
	    return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}
	
	/*
	 * Name: addCourse
	 * Description: add course into Course object
	 */
	public static void addCourse(ArrayList<Course> courseList) {
		boolean found = false;
		try {
			// ask for ID and course name
			System.out.print("Enter course ID: ");
			Integer courseID = input.nextInt();
			input.nextLine();
			
			System.out.print("Enter course name: ");
			String courseName = input.nextLine();
	
			if(courseList.isEmpty()) {// if courseList is empty, skip to adding it
			}
			else { //if not empty, for loop to iterate through all arrays
				for (int i = 0; i < courseList.size(); i++) {
					Course tempCourse = courseList.get(i);
					int id = tempCourse.getCourseID();
					
					//if found, break out the loop
					if (id == courseID) {
						found = true;
						break;
					}
				}
			}
			
			if(found) { // if found, default print statement
				System.out.println("Sorry, this course ID already exists. Try again.");
			}	
			else {
				courseList.add(new Course(courseID, courseName));
				try{
					File file =new File("Courses.txt"); // create file named Courses.txt
					if(!file.exists()){// create only if it doesn't exist yet
					 	file.createNewFile();
					  }
					FileWriter fw = new FileWriter(file, true); // continually add
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter pw = new PrintWriter(bw);
					
					// write info
					pw.println("");
					pw.write("Course Number: " + courseID.toString() + "\n");
					pw.write("Course Name: " + courseName + "\n");
					pw.flush();
					pw.close();
					
					// print summary
					System.out.println("Confirmation: new course " + courseID + " has been added");
				
				}catch(IOException e){
					System.out.println("Exception occurred:");
					e.printStackTrace();
				}
			}
		}catch(InputMismatchException e){
			System.out.print("\nAn Error Occured. \nYou need to input digits for Course ID. You've been sent back to the CMS Menu.\n ");
			input.nextLine();
		}	
	}
	
	/*
	 * Name: assignCourse
	 * Description: assign course to a student then write it to a file named CourseAssignment.txt
	 */
	public static void assignCourse (ArrayList <Student> studentList, ArrayList <Course> courseList) {
		try {	
			// ask for ID and course ID
			System.out.print("Enter Student ID: ");
			Integer userInput = input.nextInt();
			
			System.out.print("Enter Course ID: ");
			Integer courseID = input.nextInt();
			
			// nested loops to make sure courseID and student ID are matching. get info if it does
			for (int i = 0; i < courseList.size(); i++) {
				Course tempCourse = courseList.get(i);
				int id = tempCourse.getCourseID();
				
				if (id == courseID) {
					for (int j = 0; j < studentList.size(); j++) {
						Student tempStudent = studentList.get(j);
						int id2 = tempStudent.getID();
						
						// if ID# is = userinput, print info
						if (id2 == userInput) {
							System.out.println("Confirmation: " + studentList.get(j).getFirst() + " " + studentList.get(j).getLast() + " has been assigned course " + courseList.get(i).getCourseID());			
							
							//write to file named CourseAssignment.txt if everything checks out
							try{
								File file =new File("CourseAssignment.txt");// create file named CourseAssignment.txt
								if(!file.exists()){// create only if it doesnt exist yet
								 	file.createNewFile();
								  }
								FileWriter fw = new FileWriter(file, true); 
								BufferedWriter bw = new BufferedWriter(fw);
								PrintWriter pw = new PrintWriter(bw);
								
								// write info to file
								pw.println("");
								pw.write(studentList.get(j).getFirst() + " " + studentList.get(j).getLast() + "\n");
								pw.write("ID: " + studentList.get(j).getID() + "\n");
								pw.write("Courses: " + courseList.get(i).getCourseID() + "\n");
								pw.flush();
								pw.close();
							
							}catch(IOException e){
								System.out.println("Exception occurred:");
								e.printStackTrace();
							}	
						}
					}					
				}
			}
		}catch(InputMismatchException e){
			System.out.print("\nAn Error Occured. \nYou need to input digits for Student ID or Course ID. You've been sent back to the CMS Menu.\n ");
			input.nextLine();
		}
	}
	
	/*
	 * Name: displayStudentCourse
	 * Description: read everything that is in CourseAssignment.txt and print on console
	 */
	public static void displayStudentCourse() {
		try {
			BufferedReader reader = new BufferedReader (new FileReader("CourseAssignment.txt"));
			String line;
			
			//read and print every line
			while((line = reader.readLine()) !=null) { 
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

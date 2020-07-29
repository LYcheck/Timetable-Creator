// ICS4U0 Final Project - Timetable Generator
// Jan 18, 2020
// Lucas, Al-Ansar, Omar
// Program brute forces through an imported list of students to find an optimal timetable configuration in which NO CONFLICTIONS
// are found, then prints a master timetable to a text file.		//OPEN TEXT FILE WITH WORDPAD//

// Program is functional.

import java.util.ArrayList;

public class TimetableProg {
	public static void main(String[] args) {
		
		ArrayList<Student> masterList = new ArrayList<Student>(); // Initialize an arraylist of students
		
		masterList = FileFunctions.importStudents("wishlist.csv"); // Import to arraylist from CSV
		
		int choice = 0;
		boolean proceed = true;
		
		while(proceed == true) {	// Loop dependent on if user wants to continue
			choice = TimetableFunctions.menuPrint();	// Returns user menu choice
			
			switch(choice) {		// Initiates switch structure dependent on user choice
			
			case 1:	// GENERATE
				
				TimetableFunctions.desireCalculation(masterList);
				TimetableFunctions.classesCalculation();
				ArrayList<ArrayList<Class>> allClasses = TimetableFunctions.createClasses();
				
				TimetableFunctions.addStudentsToLists(masterList);
				TimetableFunctions.fillScheduleForAllCourses(allClasses);
				
				TimetableFunctions.generate(allClasses);
				FileFunctions.displayTimetable();
				
				proceed = false;
				break;
				
			case 2:	// List gr9s
				for(Student tempStudent : masterList) {
					if(Integer.parseInt(tempStudent.grade) == 9) {
					
						System.out.println(tempStudent.name + " // " + tempStudent.studNum);
					}
				}
				continue;
				
			case 3:	// List gr10s
				for(Student tempStudent : masterList) {
					if(Integer.parseInt(tempStudent.grade) == 10) {
						
						System.out.println(tempStudent.name + " // " + tempStudent.studNum);
					}
				}
				continue;
			
			case 4:	// List gr11s
				for(Student tempStudent : masterList) {
					if(Integer.parseInt(tempStudent.grade) == 11) {
						
						System.out.println(tempStudent.name + " // " + tempStudent.studNum);
					}
				}
				continue;
				
			case 5:	// Quit
				proceed = false;
				break;
			}
		}
		System.out.println("Thank you for using our timetable generator!");
		System.out.println("If you created a timetable, check your folder dedicated to the project to find your text file.");
		System.out.println("Farewell!");
		
	}
}
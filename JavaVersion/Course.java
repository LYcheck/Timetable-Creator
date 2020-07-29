// ICS4U0 Final Project - Timetable Generator
// Jan 18, 2020
// Lucas, Al-Ansar, Omar
// Program brute forces through an imported list of students to find an optimal timetable configuration in which NO CONFLICTIONS
// are found, then prints a master timetable to a text file.		//OPEN TEXT FILE WITH WORDPAD//

// Program is functional.

public class Course {
	String code;		// Initializing
	int desire;
	int numOfClasses;
	
	//Constructor
	public Course(String code) {
		this.code = code;		// Declaring
		desire = 0;
	}
}
// ICS4U0 Final Project - Timetable Generator
// Jan 18, 2020
// Lucas, Al-Ansar, Omar
// Program brute forces through an imported list of students to find an optimal timetable configuration in which NO CONFLICTIONS
// are found, then prints a master timetable to a text file.		//OPEN TEXT FILE WITH WORDPAD//

// Program is functional.

import java.util.ArrayList;

public class Class {
	ArrayList<Student> studentsInClass = new ArrayList<Student>();
	String code, course;
	
	//Constructors
	public Class() { } //default constructor, used for student courses list in student class
	
	public Class(String code) {
		this.code = code;
		course = code.substring(0,6);
	}
}
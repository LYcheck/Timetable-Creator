// ICS4U0 Final Project - Timetable Generator
// Jan 18, 2020
// Lucas, Al-Ansar, Omar
// Program brute forces through an imported list of students to find an optimal timetable configuration in which NO CONFLICTIONS
// are found, then prints a master timetable to a text file.		//OPEN TEXT FILE WITH WORDPAD//

// Program is functional.

import java.util.ArrayList;

public class Student {
	String name, studNum, grade;				// Initializing
	String c1, c2, c3, c4, c5, c6, c7, c8;
	ArrayList<String> wishlist = new ArrayList<String>();
	ArrayList<Class> courses = new ArrayList<Class>();
	
	// Constructor
	public Student(String name, String studNum, String grade, String c1, String c2,
			String c3, String c4, String c5, String c6, String c7, String c8) {
		
		this.name = name;				// Declaration
		this.studNum = studNum;
		this.grade = grade;
		
		wishlist.add(c1);
		wishlist.add(c2);
		wishlist.add(c3);
		wishlist.add(c4);
		wishlist.add(c5);
		wishlist.add(c6);
		wishlist.add(c7);
		wishlist.add(c8);
	}
}
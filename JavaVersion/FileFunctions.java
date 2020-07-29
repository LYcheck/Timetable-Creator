// ICS4U0 Final Project - Timetable Generator
// Jan 18, 2020
// Lucas, Al-Ansar, Omar
// Program brute forces through an imported list of students to find an optimal timetable configuration in which NO CONFLICTIONS
// are found, then prints a master timetable to a text file.		//OPEN TEXT FILE WITH WORDPAD//

// Program is functional.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FileFunctions {
	public static ArrayList<Student> importStudents(String filename) {
		String csvFile = filename;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		ArrayList<Student> students = new ArrayList<Student>();
		
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {			// For each loop, creates a new student into list using parameters imported
																// from CSV
				String[] attributes = line.split(csvSplitBy);
				
				students.add(new Student(
						attributes[0], attributes[1], attributes[2], attributes[3], 
						attributes[4], attributes[5], attributes[6], attributes[7],
						attributes[8], attributes[9], attributes[10]));
			}
		}
		catch(FileNotFoundException e) {		// Exception catching
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {					// Once at end of list, reader closes + error checked
				try {
					br.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return students;		// Returns masterlist of students
	}
	
	
	public static void displayTimetable() {
			
        try (FileWriter writer = new FileWriter("Master Timetable.txt");		// If file doesn't exist, method will create and add to it
            BufferedWriter bw = new BufferedWriter(writer)) {			 		// If file exists, method will truncate and add to it
        		
        	for(Student tempStudent : TimetableFunctions.gr9s) {
        		bw.write("----------------------------------------------------------------\n");
        		bw.write("Name: " + tempStudent.name + "     Student number: " + tempStudent.studNum + "     Grade: 9");
        		bw.write("\n");
        		
        		for(int count = 0; count < tempStudent.courses.size(); count++) {		// Printing algorithm by grade
        			if(count == 0) { bw.write("\nSemester 1     "); }
        			if(count == 4) { bw.write("\nSemester 2     "); }
        			
        			bw.write(tempStudent.courses.get(count).code + "     ");
        		}
        		bw.write("\n");
        	}
        	
        	for(Student tempStudent : TimetableFunctions.gr10s) {
        		bw.write("----------------------------------------------------------------\n");
        		bw.write("Name: " + tempStudent.name + "     Student number: " + tempStudent.studNum + "     Grade: 10");
        		bw.write("\n");
      
        		for(int count = 0; count < tempStudent.courses.size(); count++) {
        			if(count == 0) { bw.write("\nSemester 1     "); }
        			if(count == 4) { bw.write("\nSemester 2     "); }
        			
        			bw.write(tempStudent.courses.get(count).code + "     ");
        		}
        		bw.write("\n");
        	}
        	
        	for(Student tempStudent : TimetableFunctions.gr11s) {
        		bw.write("---------------------------------------------------------------\n");
        		bw.write("Name: " + tempStudent.name + "     Student number: " + tempStudent.studNum + "     Grade: 11");
        		bw.write("\n");
      
        		for(int count = 0; count < tempStudent.courses.size(); count++) {
        			if(count == 0) { bw.write("\nSemester 1     "); }
        			if(count == 4) { bw.write("\nSemester 2     "); }
        			
        			bw.write(tempStudent.courses.get(count).code + "     ");
        		}
        		bw.write("\n");
        	}

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);		// Formatted exception throw
        }

    }
}
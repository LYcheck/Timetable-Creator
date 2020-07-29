// ICS4U0 Final Project - Timetable Generator
// Jan 18, 2020
// Lucas, Al-Ansar, Omar
// Program brute forces through an imported list of students to find an optimal timetable configuration in which NO CONFLICTIONS
// are found, then prints a master timetable to a text file.		//OPEN TEXT FILE WITH WORDPAD//

// Program is functional.

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TimetableFunctions {
	static Scanner choiceScan = new Scanner(System.in); //initializes static scanner object for use later on
	
	static ArrayList<Course> gr9CourseList = new ArrayList<Course>(); // Initialization of course based arrayList for later use
	static ArrayList<Course> gr10CourseList = new ArrayList<Course>();
	static ArrayList<Course> gr11CourseList = new ArrayList<Course>();
	
	static ArrayList<ArrayList<Class>> gr9Schedule = new ArrayList<ArrayList<Class>>();
	static ArrayList<ArrayList<Class>> gr10Schedule = new ArrayList<ArrayList<Class>>();
	static ArrayList<ArrayList<Class>> gr11Schedule = new ArrayList<ArrayList<Class>>();
	
	static double BestGr9SuccessPercentage = 0;
	static double BestGr10SuccessPercentage= 0;
	static double BestGr11SuccessPercentage= 0;
	
	static ArrayList<Student> gr9s = new ArrayList<Student>();
	static ArrayList<Student> gr10s = new ArrayList<Student>();
	static ArrayList<Student> gr11s = new ArrayList<Student>();
	
	
//-----------------------------------------------------------------------------------------------------
// Initialization of arrays containing all possible courses in data set, sorted by grade

	static String[] gr9CourseNames = { "MPM1D0", "ENG1D0", "FSF1D0", "CGC1D0", "SNC1D0", "AVI1O0", "PPL1OM", "TIJ1O0",
			"GLE1O0", "GLS1O0", "HIF1O0", "BBT1O0", "MFM1P0", "ENG1P0", "FSF1P0", "SNC1P0", "CGC1P0", "ADA1O0",
			"PPL1OF", "AMU1O0" };

	static String[] gr10CourseNames = { "AMI2O0", "TTJ2O0", "AMU2O0", "MPM2D0", "ENG2D0", "SNC2D0", "CHC2D0", "CHV2O0",
			"TEJ200", "BBI2O0", "MFM2P0", "ENG2P0", "SNC2P0", "CHC2P0", "PPL2OF", "ASM2O0", "AVI2O0", "PAP2OF",
			"ADA2O0", "TCJ2O0", "PPL2OM", "MAT2L0", "GLE2O0", "ESLA-D", "GLC2O0", "CHV2OL", "TGJ2O0" };

	static String[] gr11CourseNames = { "MCR3U0", "ENG3U0", "BAF3M0", "PAF3OM", "HSP3C0", "AVI3M0", "AMI3M0", "TGJ3M0",
			"ADA3M0", "HSP3U0", "SVN3M0", "ASM3M0", "CLU3M0", "HNC3C0", "MEL3E0", "PAL3O0", "SBI3C0", "FSF3U0",
			"CGF3M0", "CGG3O0", "MBF3C0", "ENG3C0", "ICS3U0", "TTJ3C0", "PPL3OF", "PPL3OM", "SPH3U0", "TDJ3M0",
			"HPC3O0", "CHE3O0", "AMU3M0", "HFC3M0", "TCJ3C0", "BMI3C0", "PAF3OF", "CHW3M0", "SCH3U0", "SBI3U0",
			"MCF3M0", "NDA3M0", "ELSCO0", "NBE3U0", "GLE3O0", "AWP3O0" };

//-----------------------------------------------------------------------------------------------------

	public static int menuPrint() { //PRINTS MAIN MENU AND RETURNS VALID MENU OPTION
		
		String menu = ""		// Stores menu in string for ease of printing
				+ "\n1.	Generate master timetable list"
				+ "\n2.	List grade 9 students"
				+ "\n3.	List grade 10 students"
				+ "\n4.	List grade 11 students"
				+ "\n5.	Quit"
				+ "\nEnter your choice: ";
		
		System.out.println(menu);
	
		int choice = getValidInt();		// Retrieves valid integer using defined method
		
		while(choice < 1 || choice > 5){		// Makes sure option is between option boundaries
			System.out.println("Enter an option between 1-9.");
			choice = getValidInt();
		}
		
		return choice;		// Returns menu choice
	}
	
	public static int getValidInt(){ //MAKES SURE INTEGER INPUT IS NOT IN INCORRECT DATA TYPE
	
		while(true){
			try { return choiceScan.nextInt(); }		// Tries to parse next user input line into integer
			
			catch (InputMismatchException e){
			   System.out.println("Enter a valid integer: ");
			   choiceScan.next();		// If unable, prompts again
			   }
			}
	}
		
	public static void desireCalculation(ArrayList<Student> tempList) {

		for (String course : gr9CourseNames) {
			gr9CourseList.add(new Course(course));
		} // Uses strings from earlier course name array
		for (String course : gr10CourseNames) {
			gr10CourseList.add(new Course(course));
		} // to create course objects to append into
		for (String course : gr11CourseNames) {
			gr11CourseList.add(new Course(course));
		} // a final grade-course list

		for (Student tempStudent : tempList) {
			if(tempStudent.grade.equals("9")) {
				for(Course courseToCompare : gr9CourseList) {

					for(String studentCourseToCompare : tempStudent.wishlist) {
						
						if(courseToCompare.code.equals(studentCourseToCompare)) { courseToCompare.desire++; }
					}
				}
			}

			if(tempStudent.grade.equals("10")) {
				for(Course courseToCompare : gr10CourseList) {

					for(String studentCourseToCompare : tempStudent.wishlist) {

						if(courseToCompare.code.equals(studentCourseToCompare)) { courseToCompare.desire++; }
					}
				}
			}

			if (tempStudent.grade.equals("11")) {
				for (Course courseToCompare : gr11CourseList) {

					for (String studentCourseToCompare : tempStudent.wishlist) {

						if (courseToCompare.code.equals(studentCourseToCompare)) {
							courseToCompare.desire++;
						}
					}
				}
			}
		}
	}

	public static void classesCalculation() {
		int totalGrade9 = 0;
		int totalGrade10 = 0;
		int totalGrade11 = 0;
		
		// GRADE 9 CLASS QUANTITY CALCULATION
		System.out.println("Total grade 9 classes: ");
		for (Course x : gr9CourseList) {
			int numClasses;
			
			if((x.desire % 18) >= 20) { numClasses = (x.desire / 18) + 3; }
			else { numClasses = x.desire / 18 + 2; }
			
			x.numOfClasses = numClasses;
			//System.out.println("We need " + numClasses + " classes for " + x.code);
			totalGrade9+=numClasses;
		}
		System.out.println(totalGrade9+"\n");

		// GRADE 10 CLASS QUANTITY CALCULATION
		System.out.println("Total grade 10 classes: ");
		for (Course x : gr10CourseList) {
			int numClasses;
			
			if(x.desire % 18 >= 20) { numClasses = (x.desire / 18) + 3; }
			else { numClasses = x.desire / 18 + 2; }
			
			x.numOfClasses = numClasses;
			//System.out.println("We need " + numClasses + " classes for " + x.code);
			totalGrade10+=numClasses;
		}
		System.out.println(totalGrade10+"\n");
		
		// GRADE 11 CLASS QUANTITY CALCULATION
		System.out.println("Total grade 11 classes: ");
		for(Course x : gr11CourseList) {
			int numClasses;
			
			if(x.desire % 20 >= 20) { numClasses = (x.desire / 20) + 3; }
			else { numClasses = x.desire / 20 + 2; }
			
			x.numOfClasses = numClasses;
			
			//System.out.println("We need " + numClasses + " classes for " + x.code);
			totalGrade11 += numClasses;
		}
		System.out.println(totalGrade11 + "\n");
		
	}

	public static ArrayList<ArrayList<Class>> createClasses() {
		String gr9courseString;
		String gr10courseString;
		String gr11courseString;
		
		ArrayList<Class> allgr9Classes = new ArrayList<Class>();
		ArrayList<Class> allgr10Classes = new ArrayList<Class>();
		ArrayList<Class> allgr11Classes = new ArrayList<Class>();
		
		ArrayList<ArrayList<Class>> allClasses = new ArrayList<ArrayList<Class>>();
		
		//System.out.println("All classes being created:\n");
		// CREATES CLASSES IN GRADE 9 BASED ON CLASS QUANTITY CALCULATION
		for(Course x : gr9CourseList) {
			for(int y = 1; y < x.numOfClasses + 1; y++) {
				gr9courseString = x.code + "-" + y;
				allgr9Classes.add(new Class(gr9courseString));
				//System.out.println(gr9courseString);
			}
		}
		
		// CREATES CLASSES IN GRADE 10 BASED ON CLASS QUANTITY CALCULATION
		for(Course x : gr10CourseList) {
			for(int y = 1; y < x.numOfClasses + 1; y++) {
				gr10courseString = x.code + "-" + y;
				allgr10Classes.add(new Class(gr10courseString));
				//System.out.println(gr10courseString);
			}
		}
		
		// CREATES CLASSES IN GRADE 11 BASED ON CLASS QUANTITY CALCULATION
		for(Course x : gr11CourseList) {
			for(int y = 1; y < x.numOfClasses + 1; y++) {
				gr11courseString = x.code + "-" + y;
				allgr11Classes.add(new Class(gr11courseString));
				//System.out.println(gr11courseString);
			}
		}
		
		
		allClasses.add(allgr9Classes);
		allClasses.add(allgr10Classes);
		allClasses.add(allgr11Classes);
		
		return allClasses;
	}

	public static void fillgr9Classes(ArrayList<Class> gr9Classes) {
		Random rand = new Random();
		
		for(int i = 0; i < ((gr9Classes.size())); i++) {	// Iterates through every grade 9 class
			int n = rand.nextInt(8);						// Selects random period
			gr9Schedule.get(n).add(gr9Classes.get(i));		// Adds class to selected period
		}
	}
		
	public static boolean fillGr9Timetables() {
		for(ArrayList<Class> period : gr9Schedule) {
			for(Class classToBeCleared : period) {
				classToBeCleared.studentsInClass.clear();
			}
		}
		
		double successPercentage = 0;
		for(Student sampleStudent : gr9s) {
			ArrayList<Class> successfulTimeTable= new ArrayList<Class>();
			boolean fullTimeTableAcheived= false;

			ArrayList<ArrayList<String>> allCombos= Recursion.permute(sampleStudent.wishlist);
			for(ArrayList<String> onePossibleCombination : allCombos) {
				successfulTimeTable.clear();
				int counter = 0; 
				
				while(counter < 8) {

				//	OUTER_LOOP: // OUTER_LOOP is a label
				for( String oneCourseFromPossibleCombination: onePossibleCombination) {
					boolean foundMatchingTime= false;
					ArrayList<Class> thatSpecificPeriod= gr9Schedule.get(counter);
					
					for(Class classInThatPeriod : thatSpecificPeriod) {
						
						if(classInThatPeriod.studentsInClass.size() > 25) {continue;}
						
						else if( oneCourseFromPossibleCombination.equals(classInThatPeriod.course)) {
							successfulTimeTable.add(classInThatPeriod);
							foundMatchingTime = true;
							
							break;
							//this is where youd add the student to the course officially( still thinking how to approach this)
						}		
					}
					
					if(foundMatchingTime) { counter++;}
					
					else {counter = 9; successfulTimeTable.clear(); break;}
				}
				
				}
				
				
				if(counter == 8) {
					sampleStudent.courses= successfulTimeTable;
					for(Class x : successfulTimeTable) {
						x.studentsInClass.add(sampleStudent);
						//System.out.println(x.studentsInClass);
					
					}
					//checkFullClass9();
					fullTimeTableAcheived= true;
					successPercentage+= 1;
					break;
				}
				
			}
			if (fullTimeTableAcheived) {//System.out.println(fullTimeTableAcheived);
			}
		}
		
		successPercentage = successPercentage / gr9s.size() * 100;
		System.out.println("Success for this trial: " + successPercentage);
		int unfilledClassCount=0;
		for (ArrayList<Class> period : gr9Schedule) {
			for (Class className: period) {
				if(className.studentsInClass.size()<15 && className.studentsInClass.size() > 0) {
				System.out.println(className.code + ": " + className.studentsInClass.size());
				unfilledClassCount++;
				}
			}
		}
		
		System.out.println("Total unfilled: " + unfilledClassCount + "\n\n");
		
		if((int)successPercentage == 100 && unfilledClassCount < 41){ return true; }
		else{ return false; }
	}

	public static void fillgr10Classes(ArrayList<Class> gr10Classes) {
		Random rand = new Random();
		
		for(int i = 0; i < ((gr10Classes.size())); i++) {	// Iterates through every grade 9 class
			int n = rand.nextInt(8);						// Selects random period
			gr10Schedule.get(n).add(gr10Classes.get(i));	// Adds class to selected period
		}
	}

	public static boolean fillGr10Timetables() {
		for(ArrayList<Class> period : gr10Schedule) {
			for(Class classToBeCleared : period) {
				classToBeCleared.studentsInClass.clear();
			}
		}
		
		double successPercentage = 0;
		for( Student sampleStudent : gr10s) {
			ArrayList<Class> successfulTimeTable= new ArrayList<Class>();
			boolean fullTimeTableAcheived= false;

			ArrayList<ArrayList<String>> allCombos= Recursion.permute(sampleStudent.wishlist);
			for( ArrayList<String> onePossibleCombination : allCombos) {
				successfulTimeTable.clear();
				int counter = 0; 
				
				while( counter < 8) {

				//	OUTER_LOOP: // OUTER_LOOP is a label
				for( String oneCourseFromPossibleCombination: onePossibleCombination) {
					boolean foundMatchingTime= false;
					ArrayList<Class> thatSpecificPeriod= gr10Schedule.get(counter);
					
					for( Class classInThatPeriod : thatSpecificPeriod) {
						
						if(classInThatPeriod.studentsInClass.size() > 25) {continue;}
						
						else if( oneCourseFromPossibleCombination.equals(classInThatPeriod.course)) {
							successfulTimeTable.add(classInThatPeriod);
							foundMatchingTime = true;
							
							break;
							//this is where youd add the student to the course officially( still thinking how to approach this)
						}		
					}
					
					if(foundMatchingTime) { counter++;}
					
					else {counter = 10; successfulTimeTable.clear(); break;}
				}
				
				}
				
				
				if(counter == 8) {
					sampleStudent.courses= successfulTimeTable;
					//System.out.println(sampleStudent.courses.get(0).code);
					for(Class x : successfulTimeTable) {
						x.studentsInClass.add(sampleStudent);
						//System.out.println(x.studentsInClass);
					

					}
					//checkFullClass10();
					fullTimeTableAcheived= true;
					successPercentage+= 1;
					break;
				}
				
			}
			if (fullTimeTableAcheived) {//System.out.println(fullTimeTableAcheived);
			}
		}
		successPercentage = successPercentage / gr10s.size() * 100;
		System.out.println("Success for this trial: " + successPercentage);
		int unfilledClassCount=0;
		for (ArrayList<Class> period : gr10Schedule) {
			for (Class className: period) {
				if(className.studentsInClass.size()<15 && className.studentsInClass.size() > 0) {
				System.out.println(className.code + ": " + className.studentsInClass.size());
				unfilledClassCount++;
			}
				}
			
			//gr9CourseList.add(new Course(course));
		}
		System.out.println("Total unfilled: " + unfilledClassCount + "\n\n");
		
		if((int)successPercentage == 100 && unfilledClassCount < 51){return true;
		}else{
			return false;}
		
		
	}

	public static void fillGr11Classes(ArrayList<Class> gr11Classes) {
		Random rand = new Random();
		for(int i = 0; i < ((gr11Classes.size())); i++) {	//Iterates through every grade 9 class	
			int n = rand.nextInt(8);						// Selects random period
			gr11Schedule.get(n).add(gr11Classes.get(i));	// Adds class to selected period
		}	
	}
	
	public static boolean fillGr11Timetables() {
		for(ArrayList<Class> period : gr11Schedule) {
			for(Class classToBeCleared : period) {
				classToBeCleared.studentsInClass.clear();
			}
		}
		
		double successPercentage = 0;
		for( Student sampleStudent : gr11s) {
			ArrayList<Class> successfulTimeTable= new ArrayList<Class>();
			boolean fullTimeTableAcheived= false;

			ArrayList<ArrayList<String>> allCombos= Recursion.permute(sampleStudent.wishlist);
			for( ArrayList<String> onePossibleCombination : allCombos) {
				successfulTimeTable.clear();
				int counter = 0; 
				
				while( counter < 8) {

				//	OUTER_LOOP: // OUTER_LOOP is a label
				for( String oneCourseFromPossibleCombination: onePossibleCombination) {
					boolean foundMatchingTime= false;
					ArrayList<Class> thatSpecificPeriod= gr11Schedule.get(counter);
					
					for( Class classInThatPeriod : thatSpecificPeriod) {
						
						if(classInThatPeriod.studentsInClass.size() > 22) {continue;}
						
						else if( oneCourseFromPossibleCombination.equals(classInThatPeriod.course)) {
							successfulTimeTable.add(classInThatPeriod);
							foundMatchingTime = true;
							
							break;
							//this is where youd add the student to the course officially( still thinking how to approach this)
						}		
					}
					
					if(foundMatchingTime) { counter++;}
					
					else {counter = 11; successfulTimeTable.clear(); break;}
				}
				
				}
				
				
				if(counter == 8) {
					sampleStudent.courses= successfulTimeTable;
					//System.out.println(sampleStudent.courses.get(0).code);
					for(Class x : successfulTimeTable) {
						x.studentsInClass.add(sampleStudent);
						//System.out.println(x.studentsInClass);
					

					}
					//checkFullClass11();
					fullTimeTableAcheived= true;
					successPercentage+= 1;
					break;
				}
				
			}
			if (fullTimeTableAcheived) {//System.out.println(fullTimeTableAcheived);
			}
		}
		successPercentage = successPercentage / gr11s.size() * 100;
		System.out.println("Success for this trial: " + successPercentage);
		int unfilledClassCount=0;
		for (ArrayList<Class> period : gr11Schedule) {
			for (Class className: period) {
				if(className.studentsInClass.size()<15 && className.studentsInClass.size() > 0) {
				System.out.println(className.code + ": " + className.studentsInClass.size());
				unfilledClassCount++;
			}
				}
			
			//gr9CourseList.add(new Course(course));
		}
		System.out.println("Total unfilled: " + unfilledClassCount + "\n\n");
		
		if((int)successPercentage > 89 && unfilledClassCount < 100) {return true;
		}else{
			return false;}
		
		
	}
	
	public static void addStudentsToLists(ArrayList<Student> masterList) {
		
		//initalizes all student lists
		for (Student student: masterList) {
			if (student.grade.equals("9")){gr9s.add(student);}
			if (student.grade.equals("10")) {gr10s.add(student);}
			if (student.grade.equals("11")) {gr11s.add(student);}
		}
		System.out.println("Grade sizes: g9, g10, g11:");
		System.out.println(gr9s.size()+"," + gr10s.size()+","+gr11s.size() + "\n\n");
	
		//TimetableFunctions.fillGr9Timetables();
	}
	
	public static void fillScheduleForAllCourses(ArrayList<ArrayList<Class>> allClasses) {
		gr9Schedule.clear(); // Initializes all schedules; makes sure they're cleared for each function call
		gr10Schedule.clear();
		gr11Schedule.clear();

		for(int i = 0; i<8;i++) {//adds all the periods into each of the grade schedules
			ArrayList<Class> gr9period = new ArrayList<Class>();
			gr9Schedule.add(gr9period);
			
			ArrayList<Class> gr10period = new ArrayList<Class>();
			gr10Schedule.add(gr10period);
			
			ArrayList<Class> gr11period = new ArrayList<Class>();
			gr11Schedule.add(gr11period);

		}
		
		
		TimetableFunctions.fillgr9Classes(allClasses.get(0));	// Creates grade 9 course schedule
		TimetableFunctions.fillgr10Classes(allClasses.get(1));	// Creates grade 10 course schedule
		TimetableFunctions.fillGr11Classes(allClasses.get(2));	// Creates grade 11 course schedule
		
	}

	public static void generate(ArrayList<ArrayList<Class>> allClasses) {
		boolean perfectSuccessRate = false;
		while(!perfectSuccessRate) {
			fillScheduleForAllCourses(allClasses);
			perfectSuccessRate = fillGr9Timetables();
		}
		perfectSuccessRate = false;
		while(!perfectSuccessRate) {
			fillScheduleForAllCourses(allClasses);
			perfectSuccessRate = fillGr10Timetables();
		}
		perfectSuccessRate = false;
		while(!perfectSuccessRate) {
			fillScheduleForAllCourses(allClasses);
			perfectSuccessRate = fillGr11Timetables();
		}
	}
}


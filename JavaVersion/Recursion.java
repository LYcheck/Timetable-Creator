// ICS4U0 Final Project - Timetable Generator
// Jan 18, 2020
// Lucas, Al-Ansar, Omar
// Program brute forces through an imported list of students to find an optimal timetable configuration in which NO CONFLICTIONS
// are found, then prints a master timetable to a text file.		//OPEN TEXT FILE WITH WORDPAD//

// Program is functional.
 
import java.util.ArrayList;

public class Recursion {
	public static ArrayList<ArrayList<String>> permute(ArrayList<String> nums) {
	    ArrayList<ArrayList<String>> result = new ArrayList<>();
	    helper(0, nums, result);
	    return result;
	}
	 
	private static void helper(int start, ArrayList<String> nums, ArrayList<ArrayList<String>> result){
	    if(start==nums.size()-1){
	        
	    	ArrayList<String> list = new ArrayList<>();
	        for(String num: nums){ list.add(num); }
	        
	        result.add(list);
	        return;
	    }
	 
	    for(int i=start; i<nums.size(); i++){
	        swap(nums, i, start);
	        helper(start+1, nums, result);
	        swap(nums, i, start);
	    }
	}
	 
	private static void swap(ArrayList<String> nums, int i, int j){
	    String temp = nums.get(i);
	    nums.set(i, nums.get(j));
	    nums.set(j, temp);
	}
}
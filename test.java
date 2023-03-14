import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class test {

	static File myfile = new File("csvTest.csv"); 
  
    
	public static void main(String[] args) {
		WeatherManager wm = new WeatherManager(myfile);
		System.out.println(wm.getReadingCount()); //25
		CityListStats city1 = wm.getCityListStats("Alabama", "Birmingham");
		CityListStats city2 = wm.getCityListStats("California", "San Diego");
		CityListStats city3 = wm.getCityListStats("Mississippi", "Jackson");
		System.out.println("Count for Birmingham, AL: " + city1.count()+", first index: "+  city1.startingIndex() + " Years: " +  Arrays.toString(city1.years()));
		//Count for Birmingham, AL: 4, first index: 1 Years: [1995]
		System.out.println("Count for San Diego, CA: " + city2.count()+", first index: "+  city2.startingIndex() + " Years: " +  Arrays.toString(city2.years()));
		//Count for San Diego, CA: 3, first index: 5 Years: [2008]
		System.out.println("Count for Jackson, MI " + city3.count()+", first index: "+  city3.startingIndex() + " Years: " +  Arrays.toString(city3.years()));
		//Count for Jackson, MI 3, first index: 12 Years: [2019]
		
		
	}
	



}

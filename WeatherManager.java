import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class WeatherManager implements WeatherManagerInterface {

	static File myfile = new File("csvTest.csv"); 
	static ArrayList<WeatherReading> weathersArray = new ArrayList<WeatherReading>(); 
  
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WeatherManager(myfile);
	
		System.out.println(weathersArray); //for testing
	
		

	}
	

	public WeatherManager(File file) {
		
		Scanner scCityTemp = null; //initialize city temperature as null scanners objects
		

				try {
					scCityTemp = new Scanner(myfile);
					
				} catch (FileNotFoundException e) { //throwing FileNotFoundException if not found
					e.printStackTrace();
				}
				
				scCityTemp.nextLine(); //skipping first line
				String line;
				
				while(scCityTemp.hasNextLine()) {
					line = scCityTemp.nextLine(); 
					String[] tempArray1 = line.split(","); //splitting text by comma and store them to String array
					WeatherReading tempWeather = new WeatherReading(tempArray1[0],tempArray1[1],Integer.parseInt(tempArray1[2]),Integer.parseInt(tempArray1[3]),Integer.parseInt(tempArray1[4]),Double.parseDouble(tempArray1[5]));
				    weathersArray.add(tempWeather);
				   
				}
				Collections.sort(weathersArray); 		
		
	}

	@Override
	/**
     * Retrieves a count of readings
     * @return      count of readings
     */
	public int getReadingCount() {
		// TODO Auto-generated method stub	
		return weathersArray.size();
	}

	@Override
	 /**
     * Retrieves the weather reading at the specified index.
     * @param index     the index for the desired reading; must be a valid element index.
     * @return          the reading at the specified index.
     */
	public WeatherReading getReading(int index) {
		// TODO Auto-generated method stub
		return weathersArray.get(index);
	}

	@SuppressWarnings("null")
	@Override
	  /**
     * Retrieves a set of weather readings.
     * @param index     the index of the first reading; must be a valid index.
     * @param count     the count of readings to potentially include.  Must be at least 1.  Must imply a valid range;
     *                  index + count must be less than the total reading count.
     * @return          an array of readings.
     */
	public WeatherReading[] getReadings(int index, int count) {
		// TODO Auto-generated method stub
		if(count >= 1) {
		WeatherReading[] result = null; 
		for(int i = index; i < count; i++) {
			result[i] = getReading(i);
		}
		return result;	
		}else {
			return null;
		}		
	}

	@SuppressWarnings("null")
	@Override
	 /**
     * Retrieves a set of weather readings.
     * @param index     the index of the first reading; must be a valid index.
     * @param count     the count of readings to potentially include.  Must be at least 1.  Must imply a valid range;
     *                  index + count must be less than the total reading count.
     * @param month     the month to filter; must be a valid month (1 to 12).
     * @param day       the day to filter; must be a valid day (1 to 31).
     * @return          an array of readings matching the specified criteria.
     */
	public WeatherReading[] getReadings(int index, int count, int month, int day) {
		// TODO Auto-generated method stub
		//use binary search for month and day in that range
		if(count < 1 || month < 1 || month > 12 || day < 1 || day > 31) { //validating user input
			return null;
		}		
		WeatherReading[] weathers = getReadings(index,count); 
		WeatherReading[] result = null;  //this array will store the result
		for(WeatherReading i : weathers) {
			if(month == i.month() && day == i.day()) {
				WeatherReading[] copy = Arrays.copyOf(result,result.length + 1);///duplicate an array with new size		
				copy[weathers.length] = i;//add the WeatherReading object to this array
				result = copy;
			}
		}
		return result;
			
		
	}

	@Override
	/**
     * Retrieves key list statistics for the specified country/state/city.
     * Student note:  okay to use an additional ArrayList in this method.
     *
     * @param state     the state of interest; must not be null or blank.
     * @param city      the city of interest; must not be null or blank.
     * @return          the list stats for the specified city, or null if not found.
     */
	public CityListStats getCityListStats(String state, String city) {
		// TODO Auto-generated method stub
		int startIndex = 0, count = 1, min = 0, max = weathersArray.size() - 1 ; //count is one as default because there will be at least 1 city which is the first city				
		
		while (min <= max) {
			int mid = min + (max - min) / 2;
			int compare =getReading(mid).state().compareTo(state)  + getReading(mid).city().compareTo(city);
			if(compare == 0) { //if middle index's object has state and city matches
				//int searchNum = mid;
				while(getReading(mid-1).state().equalsIgnoreCase(state) && getReading(mid-1).city().equalsIgnoreCase(city)) {
					mid--;
					
				} //after finding startIndex
				startIndex = mid;//get the start index
				int fromStartIndex = startIndex;				
				int[] tempYear = {getReading(fromStartIndex).year()}; //initializing an array to store the year of the first city
				while(getReading(fromStartIndex).city().compareTo(city) == 0 && fromStartIndex < max) { //loop from the first city until it's a different one which compareTo will return a number beside zero
					
					count++; //count how many records
					
					if(getReading(fromStartIndex).year() != tempYear[tempYear.length -1]) {//compare if the very last year in the array is different with the current year of the record
						int[] copy = Arrays.copyOf(tempYear,tempYear.length + 1);	///duplicate an array with new size		
						copy[tempYear.length] = getReading(fromStartIndex).year();//add the year from WeatherReading object to this array
						tempYear = copy;
					}
					fromStartIndex++; //update index
					
					
				}
			return new CityListStats(startIndex, count, tempYear);	
				
			}else if(compare < 0){ 
				min = mid + 1; //too small keep the right hand
			}else {
				max = mid - 1; //too large keep the left hand
			}
			
		}
		
		return null;
	}

	@Override
	 /**
     * Retrieves an iterator over all weather readings.
     * @return      strongly typed iterator for.
     */
	public Iterator<WeatherReading> iterator() {
		// TODO Auto-generated method stub
		Iterator<WeatherReading> weather = weathersArray.iterator();			
		return weather;
	}
	
	

}

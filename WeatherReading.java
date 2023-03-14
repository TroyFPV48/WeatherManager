
public record WeatherReading(String state, String city, int month, int day, int year, double avgTemperature )  implements Comparable<WeatherReading>{
/**TODO: 
 * Add natural ordering using a multi-level approach using these fields,
 *  in ascending order:state, city, year, month, day. 
 *  
 * Add a full/properequals override that is harmonious with natural ordering2.Ask if you are unsure.
 * **/ 
	@Override
	public int compareTo(WeatherReading wr)
    {
        if(state.compareTo(wr.state)!= 0) {
        	return state.compareTo(wr.state);
        }else if(city.compareTo(wr.city)!=0){
        	return city.compareTo(wr.city);
        }else if(year != wr.year){
        	return year - wr.year;
        }else if(month != wr.month){
        	return month - wr.month;
        }else {
        	return day - wr.day;
        }  		
    }
	
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass() != getClass()) {
			return false;
		}else {
			return compareTo((WeatherReading)obj)==0;
		}
	}


	
	
 
}

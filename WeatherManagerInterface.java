import java.util.Iterator;

public interface WeatherManagerInterface {

    // Constructor should take a single parameter, a File reference for the file to be read.
    // It is expected that the constructor might throw a FileNotFoundException.

    /**
     * Retrieves a count of readings
     * @return      count of readings
     */
    public int getReadingCount();

    /**
     * Retrieves the weather reading at the specified index.
     * @param index     the index for the desired reading; must be a valid element index.
     * @return          the reading at the specified index.
     */
    public WeatherReading getReading(int index);

    /**
     * Retrieves a set of weather readings.
     * @param index     the index of the first reading; must be a valid index.
     * @param count     the count of readings to potentially include.  Must be at least 1.  Must imply a valid range;
     *                  index + count must be less than the total reading count.
     * @return          an array of readings.
     */
    public WeatherReading[] getReadings(int index, int count);

    /**
     * Retrieves a set of weather readings.
     * @param index     the index of the first reading; must be a valid index.
     * @param count     the count of readings to potentially include.  Must be at least 1.  Must imply a valid range;
     *                  index + count must be less than the total reading count.
     * @param month     the month to filter; must be a valid month (1 to 12).
     * @param day       the day to filter; must be a valid day (1 to 31).
     * @return          an array of readings matching the specified criteria.
     */
    public WeatherReading[] getReadings(int index, int count, int month, int day);

    /**
     * Retrieves key list statistics for the specified country/state/city.
     * Student note:  okay to use an additional ArrayList in this method.
     *
     * @param state     the state of interest; must not be null or blank.
     * @param city      the city of interest; must not be null or blank.
     * @return          the list stats for the specified city, or null if not found.
     */
    public CityListStats getCityListStats(String state, String city);

    /**
     * Retrieves an iterator over all weather readings.
     * @return      strongly typed iterator for.
     */
    public Iterator<WeatherReading> iterator();

}

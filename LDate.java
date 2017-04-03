
public class LDate {

	private int day;
	private int month;
	private int year;

	// month constants
	public final int JANUARY = 1;
	public final int FEBRUARY = 2;
	public final int MARCH = 3;
	public final int APRIL = 4;
	public final int MAY = 5;
	public final int JUNE = 6;
	public final int JULY = 7;
	public final int AUGUST = 8;
	public final int SEPTEMBER = 9;
	public final int OCTOBER = 10;
	public final int NOVEMBER = 11;
	public final int DECEMBER = 12;

	// string formats
	public final int DD_MM_YYYY_INT = 0;
	public final int MM_DD_YYYY_INT = 1;
	public final int DD_MM_YYYY_STR = 2;
	public final int MM_DD_YYYY_STR = 3;

	// string delimiters
	public final int NONE = 0;
	public final int DOT = 1;
	public final int DASH = 2;
	public final int SLASH = 3;

	// the index of MONTHS matches with the value of month constants
	private final String[] MONTHS = { "", "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	/**
	 * Create a new date with in the format "dd/mm/yyyy"
	 * 
	 * @param dd
	 * @param mm
	 * @param yyyy
	 */
	public LDate(int dd, int mm, int yyyy) {
		day = dd;
		month = mm;
		year = yyyy;
	}

	public int getDay() {
		return day;
	}

	/**
	 * Same as toString(LDate.MM_DD_YYYY_STR,LDate.NONE);
	 * 
	 * @return String representation of LDate
	 */
	public String toString() {
		return toString(MM_DD_YYYY_STR, NONE);
	}

	/**
	 * Returns the LDate as a string representation of the data
	 * 
	 * @param format
	 * @param delimiter
	 * @return
	 */
	public String toString(int format, int delimiter) {
		// set delimiter
		String delim = " ";
		if(delimiter == this.DOT){
			delim = ".";
		}else if(delimiter == this.DASH){
			delim = "-";
		}else if(delimiter == this.SLASH){
			delim = "/";
		}
		
		// return correct date
		if (format == this.DD_MM_YYYY_INT) {
			return day + delim + month + delim + year;
		} else if (format == this.DD_MM_YYYY_STR) {
			return day + delim + MONTHS[month] + delim + year;
		} else if (format == this.MM_DD_YYYY_INT) {
			return month + delim + day + delim + year;
		} else if (format == this.MM_DD_YYYY_STR) {
			return MONTHS[month] + delim + day + delim + year;
		}
		return toString();
	}
}

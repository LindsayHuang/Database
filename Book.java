/**
 * Class to hold information about book entities and provides access to that information
 * 
 * @author Michael Anderson
 */
public class Book {

	private int id;
	private String title;
	private String author;
	private String genre;
	private String callNumber;
	private String type;

	/**
	 * Constructor for Book
	 * 
	 * @param id
	 * @param title
	 * @param author
	 * @param genre
	 * @param callNumber
	 * @param type
	 */
	public Book(int id, String title, String author, String genre, String callNumber, String type) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.callNumber = callNumber;
		this.type = type;
	}

	/**
	 * returns the id of the book
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}

	/**
	 * returns the title of the book
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * returns the author of the book
	 * 
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * returns the genre of the book
	 * 
	 * @return
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * returns the call number of the book
	 * 
	 * @return
	 */
	public String getCallNumber() {
		return callNumber;
	}

	/**
	 * returns the type of the book
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * returns a negative integer if this < b (this comes before b) returns a positive integer if
	 * this > b (this comes after b) returns zero if they are equal
	 * 
	 * @param b
	 * @return
	 */
	public int compareTo(Book b) {
		return this.id - b.id;
	}
}

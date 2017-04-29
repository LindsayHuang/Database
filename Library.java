import java.util.ArrayList;

/**
 * Class to hold information about Library entities and an ArrayList of the books found there.
 * 
 * @author Michael Anderson
 */
public class Library {

	private String name;
	private String address;
	private ArrayList<Book> books;

	/**
	 * Constructor for Library with a given name and addresss
	 * 
	 * @param name
	 * @param address
	 */
	public Library(String name, String address) {
		this.name = name;
		this.address = address;
		books = new ArrayList<>();
	}

	/**
	 * returns the name of the library
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns the address of the library
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * returns an array of the Book objects in this Library
	 * 
	 * @return
	 */
	public Book[] getBooks() {
		return books.toArray(new Book[books.size()]);
	}

	/**
	 * adds the given Book to the Library
	 * 
	 * @param b
	 */
	public void addBook(Book b) {
		books.add(b);
	}
}

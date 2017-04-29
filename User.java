/**
 * Class to hold information about users and provides access to that data
 * 
 * @author Michael Anderson
 */
public class User {

	private String cardNumber;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String email;

	/**
	 * Constructor for a user with no email. Stores the email as a null value
	 * 
	 * @param num
	 * @param pass
	 * @param first
	 * @param last
	 * @param addr
	 */
	public User(String num, String pass, String first, String last, String addr) {
		cardNumber = num;
		password = pass;
		firstName = first;
		lastName = last;
		address = addr;
		email = null;
	}

	/**
	 * Constructor for a user with an email
	 * 
	 * @param num
	 * @param pass
	 * @param first
	 * @param last
	 * @param addr
	 * @param e
	 */
	public User(String num, String pass, String first, String last, String addr, String e) {
		cardNumber = num;
		password = pass;
		firstName = first;
		lastName = last;
		address = addr;
		email = e;
	}

	/**
	 * returns the card number of the user
	 * 
	 * @return
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * returns the password of the user
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * returns the first name of the user
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * returns to last name of the user
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * returns the address of the user
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * returns the email of the user
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}
}

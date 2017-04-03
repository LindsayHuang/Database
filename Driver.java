
/**
 * PROJECT: Library Catalogue
 * COURSE: CS 364 - Intro to Databases
 * PURPOSE: This program will create a user interface to interact with a library book catalogue
 * FEATURES: 
 * 	- Home page
 * 		- Welcome screen and navigation to other pages
 * 	- User login page
 * 		- User login that opens the userInfo window
 * 		- Checks user's given card number and password against the database
 * 		- Navigation back to home page
 * 	- Search by book page
 * 		- Advanced search options with drop down menu options
 * 			- Search by book title, author, or genre
 * 			- Opens a result window based on search terms
 * 		- Navigation back to home page
 * 	- Search by library page
 * 		- Shows all books of a selected library by popularity
 * 
 * AUTHORS:
 * 	@author Lindsay (Wanqian) Huang
 * 		- RDBMS and function implementation
 * 	@author Jamie Vue
 * 		- RDBMS and function implementation
 * 	@author Alexander Grunwald
 * 		- Database build
 * 	@author Michael Anderson
 * 		- GUI and editing
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Driver implements ActionListener {

	private final Dimension LARGE_BUTTON = new Dimension(220, 100);
	private final Dimension MEDIUM_BUTTON = new Dimension(180, 100);
	private final Dimension SMALL_BUTTON = new Dimension(180, 40);
	private final Dimension TEXT_FIELD = new Dimension(250, 35);
	private final Color BACKGROUND_COLOR = new Color(174, 223, 255);
	private final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	private final Font FONT = new Font("Arial", Font.BOLD, 18);

	// homeWindow variables
	private JFrame homeWindow;
	private JButton userButton;
	private JButton bookButton;
	private JButton libButton;

	// userWindow variables
	private JFrame userWindow;
	private JTextField cardNum;
	private JPasswordField password;
	private JButton userLogin;
	private JButton userReturn;

	// bookWindow variables
	private JFrame bookWindow;
	private JButton bookSearch;
	private JButton bookClear;
	private JButton bookReturn;
	private JComboBox<String> searchBox1;
	private JComboBox<String> searchBox2;
	private JComboBox<String> searchBox3;
	private JTextField searchField1;
	private JTextField searchField2;
	private JTextField searchField3;

	// libWindow variables
	private JFrame libWindow;
	private JComboBox<String> libSelect;
	private JButton libSearch;
	private JButton libReturn;
	private JTextArea libText;

	/**
	 * Initiates the creation of the home window
	 */
	public Driver() {
		makeHomeWindow();
//		Library l = new Library("", "");
	}

	/**
	 * Initiates program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Driver win = new Driver();
	}

	/**
	 * Creates the home window at the start of the program. Should only be called at construction
	 * and never again.
	 * 
	 * userButton goes to userWindow bookButton goes to bookWindow libButton goes to libWindow
	 */
	private void makeHomeWindow() {
		// homeWindow options
		homeWindow = new JFrame("Welcome to the Library Catalogue!");
		homeWindow.setResizable(false);
		homeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homeWindow.getContentPane().setBackground(BACKGROUND_COLOR);
		homeWindow.setBounds(SCREEN_WIDTH / 2 - 800, SCREEN_HEIGHT / 2 - 450, 1600, 800);
		// homeWindow layout
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		homeWindow.setLayout(gridBag);

		// TOP PANEL
		ImageIcon mainImg = new ImageIcon("./LibraryCatalogue.png");
		constraints.insets = new Insets(0, 0, 20, 0);
		constraints.gridy = 0;
		homeWindow.add(new JLabel(mainImg), constraints);

		// BOTTOM PANEL
		JPanel panel = new JPanel();
		panel.setBackground(BACKGROUND_COLOR);
		panel.setLayout(new GridLayout(1, 3, 200, 15));
		// user login button
		userButton = new JButton("User Login");
		userButton.setPreferredSize(LARGE_BUTTON);
		userButton.setFont(FONT);
		panel.add(userButton);
		userButton.addActionListener(this);
		// search by book button
		bookButton = new JButton("Search by Book");
		bookButton.setPreferredSize(LARGE_BUTTON);
		bookButton.setFont(FONT);
		panel.add(bookButton);
		bookButton.addActionListener(this);
		// search by library button
		libButton = new JButton("Search by Library");
		libButton.setPreferredSize(LARGE_BUTTON);
		libButton.setFont(FONT);
		panel.add(libButton);
		libButton.addActionListener(this);
		// add gridBag panel to window
		constraints.insets = new Insets(0, 0, 60, 0);
		constraints.gridy = 1;
		homeWindow.add(panel, constraints);

		// set the window to be visible
		homeWindow.setVisible(true);
		homeWindow.repaint();
	}

	/**
	 * Creates the user login window, if it has not been already. Should not be executed more than
	 * once, but can be called as long as userWindow is not orphaned.
	 * 
	 * userLogin goes to new userInfoWindow loginReturn goes to homeWindow
	 */
	private void makeUserWindow() {
		if (userWindow == null) {
			// userWindow options
			userWindow = new JFrame("Welcome User!");
			userWindow.setResizable(false);
			userWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			userWindow.getContentPane().setBackground(BACKGROUND_COLOR);
			userWindow.setBounds(SCREEN_WIDTH / 2 - 225, SCREEN_HEIGHT / 2 - 225, 450, 450);
			// userWindow layout
			GridBagLayout gridBag = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			userWindow.setLayout(gridBag);

			// card number label
			JLabel usernameLabel = new JLabel("Card Number:", JLabel.CENTER);
			usernameLabel.setFont(FONT);
			c.gridy = 0;
			c.gridx = 0;
			c.insets = new Insets(20, 0, 10, 0);
			userWindow.add(usernameLabel, c);
			// card number text field
			cardNum = new JTextField();
			cardNum.setPreferredSize(TEXT_FIELD);
			cardNum.setFont(FONT);
			cardNum.setVisible(true);
			c.gridy = 1;
			c.gridx = 0;
			c.insets = new Insets(0, 0, 10, 0);
			userWindow.add(cardNum, c);

			// password label
			JLabel passwordLabel = new JLabel("Password:", JLabel.CENTER);
			passwordLabel.setFont(FONT);
			c.gridy = 2;
			c.gridx = 0;
			userWindow.add(passwordLabel, c);
			// *hidden* password text(password) field
			password = new JPasswordField();
			password.setPreferredSize(TEXT_FIELD);
			password.setFont(FONT);
			password.setVisible(true);
			c.gridy = 3;
			c.gridx = 0;
			userWindow.add(password, c);

			// login button
			c.insets = new Insets(30, 0, 0, 0);
			userLogin = new JButton("Login");
			userLogin.setPreferredSize(SMALL_BUTTON);
			userLogin.setFont(FONT);
			c.gridy = 4;
			c.gridx = 0;
			userWindow.add(userLogin, c);
			userLogin.addActionListener(this);

			// return to main button
			c.insets = new Insets(30, 0, 40, 0);
			userReturn = new JButton("Return to Main Window");
			userReturn.setPreferredSize(SMALL_BUTTON);
			userReturn.setFont(FONT);
			c.gridy = 5;
			c.gridx = 0;
			userWindow.add(userReturn, c);
			userReturn.addActionListener(this);

			// set the window to be visible
			userWindow.setVisible(true);
			userWindow.repaint();
		}
	}

	/**
	 * Makes a new window that contains all of the given user's info
	 * 
	 * @param u
	 */
	private void makeUserInfoWindow(User u) {
		// set up window
		JFrame infoWindow = new JFrame("Welcome " + u.getFirstName() + "!");
		infoWindow.setBounds(SCREEN_WIDTH / 2 - 440, SCREEN_HEIGHT / 2 - 350, 740, 700);
		infoWindow.setResizable(true);
		infoWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// create a scroll pane with a text area and add it to the window
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.PLAIN, 25));
		textArea.setBackground(BACKGROUND_COLOR);
		textArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		infoWindow.add(scrollPane);

		// write to the scroll pane
		textArea.append("  Card Number:   " + u.getCardNumber() + "\n");
		textArea.append("               Name:   " + u.getFirstName() + " " + u.getLastName() + "\n");
		textArea.append("           Address:   " + u.getAddress() + "\n");
		if (u.getEmail() == null) {
			textArea.append("               Email:   none\n");
		} else {
			textArea.append("               Email:   " + u.getEmail() + "\n");
		}
		// TODO get fines for given user
		textArea.append("      Total Fines:   TODO\n");
		textArea.append(
				"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
		textArea.append("Current books checked out (overdue books marked OVERDUE)\n");
		textArea.append(
				"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
		// TODO get checked out books for given user
		textArea.append("TODO");

		// make infoWindow visible
		infoWindow.setVisible(true);
		infoWindow.repaint();
	}

	private void makeBookWindow() {
		if (bookWindow == null) {
			// userWindow options
			bookWindow = new JFrame("Search by book");
			bookWindow.setResizable(false);
			bookWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			bookWindow.getContentPane().setBackground(BACKGROUND_COLOR);
			bookWindow.setBounds(SCREEN_WIDTH / 2 - 400, SCREEN_HEIGHT / 2 - 250, 800, 500);

			// window layout
			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			bookWindow.setLayout(layout);

			// SEARCH PANEL
			JPanel searchPanel = new JPanel();
			searchPanel.setBackground(BACKGROUND_COLOR);
			GridBagLayout searchLayout = new GridBagLayout();
			GridBagConstraints searchC = new GridBagConstraints();
			searchPanel.setLayout(searchLayout);
			// add searchPanel to the window
			c.gridx = 0;
			c.weightx = 2;
			bookWindow.add(searchPanel, c);
			// add components to searchPanel
			String[] options = { "-", "Author", "Genre", "Title", "Year" };
			// search components 1
			searchBox1 = new JComboBox<>(options);
			searchBox1.setFont(FONT);
			searchBox1.setSelectedIndex(0);
			searchField1 = new JTextField();
			searchField1.setFont(FONT);
			searchField1.setPreferredSize(new Dimension(400, 32));
			JLabel label1 = new JLabel("Year use format 'YYYY-YYYY', '-YYYY', 'YYYY-'");
			label1.setFont(new Font("Arial", Font.ITALIC, 16));
			searchC.gridx = 0;
			searchC.gridy = 0;
			searchPanel.add(searchBox1, searchC);
			searchC.gridx = 1;
			searchPanel.add(searchField1, searchC);
			searchC.gridy = 1;
			searchC.insets = new Insets(0, 0, 0, 0);
			searchPanel.add(label1, searchC);
			// search components 2
			searchBox2 = new JComboBox<>(options);
			searchBox2.setFont(FONT);
			searchBox2.setSelectedIndex(0);
			searchField2 = new JTextField();
			searchField2.setFont(FONT);
			searchField2.setPreferredSize(new Dimension(400, 32));
			JLabel label2 = new JLabel("Year use format 'YYYY-YYYY', '-YYYY', 'YYYY-'");
			label2.setFont(new Font("Arial", Font.ITALIC, 16));
			searchC.gridx = 0;
			searchC.gridy = 2;
			searchC.insets = new Insets(80, 0, 0, 0);
			searchPanel.add(searchBox2, searchC);
			searchC.gridx = 1;
			searchPanel.add(searchField2, searchC);
			searchC.gridy = 3;
			searchC.insets = new Insets(0, 0, 0, 0);
			searchPanel.add(label2, searchC);
			// search components 3
			searchBox3 = new JComboBox<>(options);
			searchBox3.setFont(FONT);
			searchBox3.setSelectedIndex(0);
			searchField3 = new JTextField();
			searchField3.setFont(FONT);
			searchField3.setPreferredSize(new Dimension(400, 32));
			JLabel label3 = new JLabel("Year use format 'YYYY-YYYY', '-YYYY', 'YYYY-'");
			label3.setFont(new Font("Arial", Font.ITALIC, 16));
			searchC.gridx = 0;
			searchC.gridy = 4;
			searchC.insets = new Insets(80, 0, 0, 0);
			searchPanel.add(searchBox3, searchC);
			searchC.gridx = 1;
			searchPanel.add(searchField3, searchC);
			searchC.gridy = 5;
			searchC.insets = new Insets(0, 0, 0, 0);
			searchPanel.add(label3, searchC);

			// BUTTON PANEL
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(BACKGROUND_COLOR);
			buttonPanel.setLayout(new GridLayout(3, 1, 0, 40));
			// add buttonPanel to the window
			c.gridx = 1;
			c.weightx = 1;
			bookWindow.add(buttonPanel, c);
			// add search button
			bookSearch = new JButton("Search");
			bookSearch.setPreferredSize(MEDIUM_BUTTON);
			bookSearch.setFont(FONT);
			buttonPanel.add(bookSearch);
			bookSearch.addActionListener(this);
			// add clear button
			bookClear = new JButton("Clear");
			bookClear.setPreferredSize(MEDIUM_BUTTON);
			bookClear.setFont(FONT);
			buttonPanel.add(bookClear);
			bookClear.addActionListener(this);
			// add return home button
			bookReturn = new JButton("Home");
			bookReturn.setPreferredSize(MEDIUM_BUTTON);
			bookReturn.setFont(FONT);
			buttonPanel.add(bookReturn);
			bookReturn.addActionListener(this);

			// set the window to be visible
			bookWindow.setVisible(true);
			bookWindow.repaint();
		}
	}

	private void makeLibWindow() {
		if (libWindow == null) {
			// userWindow options
			libWindow = new JFrame("Welcome User!");
			libWindow.setResizable(false);
			libWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			libWindow.getContentPane().setBackground(BACKGROUND_COLOR);
			libWindow.setBounds(SCREEN_WIDTH / 2 - 400, SCREEN_HEIGHT / 2 - 300, 800, 600);
			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			libWindow.setLayout(layout);

			// TOP PANEL
			JPanel panel = new JPanel();
			panel.setBackground(BACKGROUND_COLOR);
			panel.setLayout(new GridLayout(1, 3, 50, 0));
			c.gridy = 0;
			libWindow.add(panel, c);
			// libSelect JComboBox lists all libraries
			// TODO get correct library names
			String[] options = { "", "La Crosse - West", "La Crosse - Main", "Onalaska - Main" };
			libSelect = new JComboBox<>(options);
			libSelect.setFont(FONT);
			panel.add(libSelect);
			// libSearch JButton
			libSearch = new JButton("SEARCH");
			libSearch.addActionListener(this);
			libSearch.setPreferredSize(SMALL_BUTTON);
			libSearch.setFont(FONT);
			panel.add(libSearch);
			// libReturn JButton goes to homeWindow
			libReturn = new JButton("RETURN");
			libReturn.addActionListener(this);
			libReturn.setPreferredSize(SMALL_BUTTON);
			libReturn.setFont(FONT);
			panel.add(libReturn);

			// BOTTOM PANEL
			// add scrollable text area
			libText = new JTextArea();
			libText.setEditable(false);
			libText.setFont(new Font("Arial", Font.PLAIN, 25));
			libText.setBackground(BACKGROUND_COLOR);
			libText.setLineWrap(true);
			JScrollPane scrollPane = new JScrollPane(libText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setPreferredSize(new Dimension(790, 500));
			c.gridy = 1;
			c.insets = new Insets(10,0,0,0);
			c.fill = GridBagConstraints.BOTH;
			libWindow.add(scrollPane, c);

			// set the window to be visible
			libWindow.setVisible(true);
			libWindow.repaint();
		}
	}

	/**
	 * Manages button presses
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(userButton)) {
			// userButton on the homeWindow
			// goes to the userWindow
			homeWindow.setVisible(false);
			makeUserWindow();
			userWindow.setVisible(true);
		} else if (e.getSource().equals(bookButton)) {
			// bookButton on the homeWindow
			// goes to the bookWindow
			homeWindow.setVisible(false);
			makeBookWindow();
			bookWindow.setVisible(true);
		} else if (e.getSource().equals(libButton)) {
			// libButton on the homeWindow
			// goes to the libWindow
			homeWindow.setVisible(false);
			makeLibWindow();
			libWindow.setVisible(true);
		} else if (e.getSource().equals(userLogin)) {
			// userLogin on the userWindow
			// goes to a new userInfoWindow if the login is correct
			/*
			 * TODO Implement a login that checks the cardNum and the password and runs
			 * makeUserInfoWindow on the corresponding user
			 * 
			 * can get the String of cardNum from the window with cardNum.getText();
			 * 
			 * can get the char[] of password from the window with password.getPassword();
			 * 
			 * Also replace the line below
			 */
			makeUserInfoWindow(new User("9571268", "qwerty123", "John", "Doe", "2707 Main St, La Crosse, WI 54669"));
			// reset the cardNum and password fields
			cardNum.setText("");
			password.setText("");
		} else if (e.getSource().equals(userReturn)) {
			// loginReturn on the userWindow
			// goes to homeWindow
			cardNum.setText("");
			password.setText("");
			userWindow.setVisible(false);
			homeWindow.setVisible(true);
		} else if (e.getSource().equals(bookReturn)) {
			// bookReturn on the bookWindow
			// goes to homeWindow
			bookClear.doClick();
			bookWindow.setVisible(false);
			homeWindow.setVisible(true);

		} else if (e.getSource().equals(bookSearch)) {
			// bookSearch on the bookWindow
		} else if (e.getSource().equals(bookClear)) {
			// bookClear on the bookWindow
			searchBox1.setSelectedIndex(0);
			searchBox2.setSelectedIndex(0);
			searchBox3.setSelectedIndex(0);
			searchField1.setText("");
			searchField2.setText("");
			searchField3.setText("");
		} else if (e.getSource().equals(libReturn)) {
			// libReturn on the libWindow
			// goes to homeWindow
			libText.setText("");
			libSelect.setSelectedIndex(0);
			libWindow.setVisible(false);
			homeWindow.setVisible(true);
		} else if (e.getSource().equals(libSearch)) {
			// libSearch on the libWindow
			// populates libText with the popular books from the library selected from libSelect
			/*
			 * TODO populate libText with the selected library's popular books
			 * 
			 * - use libSearch.getText() to get the String of the selected library
			 * 
			 * - use libText.append(String s) to add to the field
			 */
			libText.append("SEARCH!\n"); // TODO REMOVE ME
		}
	}
}

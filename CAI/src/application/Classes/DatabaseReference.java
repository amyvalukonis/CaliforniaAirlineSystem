package application.Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DatabaseReference {
	private ArrayList<User> users;
	private ArrayList<Employee> employees;
	private ArrayList<Customer> customers;
	private ArrayList<Ticket> tickets;
	private ArrayList<Flight> availableFlights;
	private static DatabaseReference databaseReference;
	private Connection databaseConnection;
	
	private DatabaseReference() {
		Connection conn = null;
        try {
            // db parameters
        	Class.forName("org.sqlite.JDBC").newInstance();
            String url = "jdbc:sqlite:/Users/joewijoyo/Desktop/Cal_Poly/CSC_307/307-SeeYouLater/CAI_Sqlite_Database";
            // create a connection to the database
            this.databaseConnection = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()); 
        } catch (Exception e) {
        	System.out.println(e.getMessage());	
        } finally {
           System.out.println(this.databaseConnection);
        }
		/*
		this.users = new ArrayList<User>();
		
		this.users.add(new Employee("employee", "00000000", "Joe", "Wijoyo"));
		this.users.add(new Customer("customer", "00000000"));
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		this.availableFlights = new ArrayList<Flight>();
		this.availableFlights.add(new Flight(new Date(), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0));
		
		this.tickets = new ArrayList<Ticket>();
	    */
	}
	
	private static boolean getDatabaseReference() {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		return true;
	}
	
	public static Connection getDatabaseConnection() {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		return DatabaseReference.databaseReference.databaseConnection;
	
	}
	
	public static void closeDatabaseConnection() {
		try {
            if (DatabaseReference.getDatabaseConnection() != null) {
                DatabaseReference.getDatabaseConnection().close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public static ArrayList<User> getUsersFromDatabase() {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		
		Statement stmt  = null;
        ResultSet rs    = null;
        DatabaseReference.databaseReference.users = new ArrayList<>();
        DatabaseReference.databaseReference.employees = new ArrayList<>();
        DatabaseReference.databaseReference.customers = new ArrayList<>();
		
        try {
			//First get users from employees table
			String sql = "SELECT * FROM Employees";
			
			stmt  = DatabaseReference.getDatabaseConnection().createStatement();
	        rs    = stmt.executeQuery(sql);
	        
	        //Get customers and add them to users array
	        while (rs.next()) {
	        	String username = rs.getString("Username");
	        	String password = rs.getString("Password");
	        	String firstName = rs.getString("First_Name");
	        	String lastName = rs.getString("Last_Name");
	        	String isFlightCrew = rs.getString("isFlightCrew");
	        	Integer id = rs.getInt("Employee_Id");
	        	
	        	Employee employee = new Employee(username, password, firstName, lastName, isFlightCrew, id);
	        	DatabaseReference.databaseReference.users.add(employee);
	        	DatabaseReference.databaseReference.employees.add(employee);
	        }
			
			//Then get users from customers table
	        stmt.close();
			sql = "SELECT * FROM Customers";
	         
	        stmt  = DatabaseReference.getDatabaseConnection().createStatement();
	        rs    = stmt.executeQuery(sql);
	             
	        //Get customers and add them to users array
	        while (rs.next()) {
	        	String username = rs.getString("Username");
	        	String password = rs.getString("Password");
	        	Integer id = rs.getInt("Customer_Id");
	        	
	        	Customer customer = new Customer(username, password, id);
	        	DatabaseReference.databaseReference.users.add(customer);
	        	DatabaseReference.databaseReference.customers.add(customer);
	        }
	        stmt.close();
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
        	try { 
//	        	if  (conn != null) {
//	        		conn.close();
//	        	}
	        	if (stmt != null) {
	        		stmt.close();
	        	}
	        	
        	} catch (SQLException ex) {
        		System.out.println(ex.getMessage());
                return null;
        	}
        	
        }
		
		return DatabaseReference.databaseReference.users;
	}
	
	
	public static ArrayList<Employee> getEmployees() {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		
		DatabaseReference.databaseReference.getUsersFromDatabase();
		
		return DatabaseReference.databaseReference.employees;
	}
	
	public static ArrayList<Flight> getAvailableFlights() {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet destinationLocationRs = null;
        ResultSet departureLocationRs = null;
        DatabaseReference.databaseReference.availableFlights = new ArrayList<>();
		
		try {
			String sql = "SELECT Flights.*, FlightInfos.* FROM Flights INNER JOIN FlightInfos ON Flights.FlightInfo_Id = FlightInfos.FlightInfo_Id";
	       
			conn = DatabaseReference.getDatabaseConnection();
	        stmt  = conn.createStatement();
	        rs    = stmt.executeQuery(sql);
	             
	        // loop through the result set
	        while (rs.next()) {
	        	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd HH:mm");
	        	Date departureDate = dateFormatter.parse(rs.getString("Departure_Time"));
	        	Date arrivalDate = dateFormatter.parse(rs.getString("Arrival_Time"));
	        	Integer seats = rs.getInt("Seats");
	        	Integer seatsTaken = rs.getInt("Seats_Taken");
	        	Double basePrice = rs.getDouble("Base_Price");
	        	String status = rs.getString("Status");
	        	String flightNumber = rs.getString("Flight_Name");
	        	Integer departureLocationId = rs.getInt("Departure_Location_Id");
	        	Integer destinationLocationId = rs.getInt("Destination_Location_Id");
	        	Integer id = rs.getInt("Flight_Id");
	        	
	        	sql = "SELECT * FROM Locations WHERE Location_Id=" + departureLocationId;
	        	stmt = conn.createStatement();
	        	departureLocationRs = stmt.executeQuery(sql);
	        	
	        	
	        	departureLocationRs.next();
	        	String departureLocation = departureLocationRs.getString("Name");
	        	stmt.close();
	        	
	        	
	        	sql = "SELECT * FROM Locations WHERE Location_Id=" + destinationLocationId;
	        	stmt = conn.createStatement();
	        	destinationLocationRs = stmt.executeQuery(sql);
	        	
	        	destinationLocationRs.next();
	        	String destinationLocation = destinationLocationRs.getString("Name");
	        	stmt.close();
	        	
	        	Flight flight = new Flight(departureDate, arrivalDate, flightNumber, departureLocation, destinationLocation, seats, seatsTaken, basePrice, id, status);
	        	DatabaseReference.databaseReference.availableFlights.add(flight);
	        }
		} catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
        	try { 
//	        	if  (conn != null) {
//	        		conn.close();
//	        	}
	        	if (stmt != null) {
	        		stmt.close();
	        	}
	        	if (rs != null) {
	        		rs.close();
	        	}
	        	if (destinationLocationRs != null) {
	        		destinationLocationRs.close();
	        	}
	        	if (departureLocationRs != null) {
	        		departureLocationRs.close();
	        	}
        	} catch (SQLException ex) {
        		System.out.println(ex.getMessage());
                return null;
        	}
        	
        }
		


		Arrays.toString(DatabaseReference.databaseReference.availableFlights.toArray());


		
		return DatabaseReference.databaseReference.availableFlights;
	}
	
	public static ArrayList<Ticket> getTickets() {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		
		
		Connection databaseConnection = DatabaseReference.databaseReference.getDatabaseConnection();
		Statement stmt  = null;
		PreparedStatement queryFlightStatement = null;
		PreparedStatement queryTicketStatement = null;
        ResultSet rs    = null;
        DatabaseReference.databaseReference.tickets = new ArrayList<>();
		
        try {
			//First get users from employees table
			String sql = "SELECT * FROM Tickets";
			
			stmt  = databaseConnection.createStatement();
	        rs    = stmt.executeQuery(sql);
	             
	        //Get customers and add them to users array
	        int id = 0; 
	        while (rs.next()) {
	        	//Query tickets
	        	Boolean checkedIn = rs.getBoolean("Checked_In");
	        	String confirmationCode = rs.getString("Confirmation_Code");
	        	Integer customerOwnerId = rs.getInt("Customer_Owner_Id");
	        	Integer flightId = rs.getInt("Flight_Id");
	        	Integer ticketId = rs.getInt("Ticket_Id");
	        	String firstName = rs.getString("First_Name");
	        	String lastName = rs.getString("Last_Name"); 
	        	String phoneNumber = rs.getString("Phone_Number"); 
	        	
	        	Customer user = new Customer(firstName, lastName, id);
	        	Flight flight = new Flight(); 
	        	Ticket ticket = new Ticket(user, flight, confirmationCode); 
	        	DatabaseReference.databaseReference.tickets.add(ticket);
	        	System.out.println(DatabaseReference.databaseReference.tickets.size());
	        	//Query flight with the flight id obtained
//	        	sql = "SELECT * FROM Flights WHERE Flight_Id=?;";
//	        	queryFlightStatement = databaseConnection.prepareStatement(sql);
//	        	queryFlightStatement.setInt(1, flightId);
	        	
	        	
	        	//Query ticket with the ticket id obtained
	        	
//	        	Employee employee = new Employee(firstName, lastName);
//	        	DatabaseReference.databaseReference.users.add(employee);
	        }
			stmt.close();
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
        	try { 
//	        	if  (conn != null) {
//	        		conn.close();
//	        	}
	        	if (stmt != null) {
	        		stmt.close();
	        	}
	        	
        	} catch (SQLException ex) {
        		System.out.println(ex.getMessage());
                return null;
        	}
        	
        }
		
		return DatabaseReference.databaseReference.tickets;
	}
	
	
	
	public static boolean addFlight(Flight newFlight) {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		DatabaseReference.databaseReference.availableFlights.add(newFlight);
		return true;
	}
	
	public static boolean addFlight(Date departureDate, Date arrivalDate, Integer flightInfoId, Integer seats, Integer seatsTaken,
			Double basePrice, String status) {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		
		Connection databaseConnection = DatabaseReference.databaseReference.getDatabaseConnection();
		Statement stmt  = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(departureDate);
		calendar.add(Calendar.HOUR, -7); //doing this because database always adds 7 for some reason (probably PDT to GMT offset)
		Date departureDateToInsert = calendar.getTime();
		calendar.setTime(arrivalDate);
		calendar.add(Calendar.HOUR, -7); //doing this because database always adds 7 for some reason (probably PDT to GMT offset)
		Date arrivalDateToInsert = calendar.getTime();
		try {
			String sql = "INSERT INTO Flights (" +
                    "Departure_Time," +
                    "Arrival_Time," +
                    "FlightInfo_Id," +
                    "Seats," +
                    "Seats_Taken," +
                    "Base_Price," +
                    "Status" +
                ")" +
                "VALUES (" +
                    "'" + simpleDateFormat.format(departureDateToInsert) + "'," + 
                    "'" + simpleDateFormat.format(arrivalDateToInsert) + "'," +
                    "'" + flightInfoId + "'," +
                    "'" + seats + "'," +
                    "'" + 0 + "'," +
                    "'" + basePrice +"'," +
                    "'" + status + "'" +
                ");";
        	System.out.println(sql);
			stmt  = databaseConnection.createStatement();
	        stmt.executeQuery(sql);
	           
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
        	try { 
	        	if (stmt != null) {
	        		stmt.close();
	        	}
	        	
        	} catch (SQLException ex) {
        		System.out.println(ex.getMessage());
                return false;
        	}
        	
        }
		
		return true;
	}
	
	public static HashMap<String, Object> getFlightInfo(Integer departureLocationId, Integer destinationLocationId) {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
	
		
		Connection databaseConnection = DatabaseReference.databaseReference.getDatabaseConnection();
		Statement stmt  = null;
		ResultSet rs    = null;
		HashMap<String, Object> flightInfo = new HashMap<>();
		
        try {
			String sql = "SELECT * FROM FlightInfos WHERE Departure_Location_Id=" + departureLocationId + " AND Destination_Location_Id=" + destinationLocationId + ";";
			
			stmt  = databaseConnection.createStatement();
	        rs    = stmt.executeQuery(sql);
	           
	        
	        Integer flightInfoId = rs.getInt("FlightInfo_Id");
	        String flightName = rs.getString("Flight_Name");
	        flightInfo.put("Flight_Name", flightName);
	        flightInfo.put("FlightInfo_Id", flightInfoId);
	        

		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
        	try { 
	        	if (stmt != null) {
	        		stmt.close();
	        	}
	        	
        	} catch (SQLException ex) {
        		System.out.println(ex.getMessage());
                return null;
        	}
        	
        }
		
		
		return flightInfo;
		
	}
	
	public static boolean addUser(String username, String password, boolean isCustomer, boolean isFlightCrew, boolean isOfficeEmployee) {
		String tableName = "Customers";
		if (isOfficeEmployee || isFlightCrew) {
			tableName = "Employees";
		}
		
		try {
			//DatabaseReference.databaseReference.users = new ArrayList<>();
			//First get users from employees table
			String sql = "INSERT INTO " + tableName + " ";
			if (isOfficeEmployee || isFlightCrew) {
				sql += "(Username, Password, First_Name, Last_Name, isFlightCrew) VALUES (";
				sql += "\'" + username + "\', \'" + password + "\', \'" + username + "\', \'" + password + "\', \'";
				if (isFlightCrew) {
					sql += "True\');";
				}
				else {
					sql += "False\');";
				}
			}
			else {
				//is customer
			    sql += "(Username, Password) VALUES (";
			    sql += "\'" + username + "\', \'" + password + "\');";
			}
			
			
	         
	        Statement stmt  = DatabaseReference.getDatabaseConnection().createStatement();
	        ResultSet rs    = stmt.executeQuery(sql);
	             
	        
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
		return true;
	}
	
	public static boolean updateEmployee(String firstName, String lastName, Boolean isFlightCrew, Integer id) {
		Statement statement = null;
		Connection databaseConnection = DatabaseReference.getDatabaseConnection();
	    try {
			
//			String sql = "UPDATE Employees " + 
//					"SET  First_Name=?," + 
//					"Last_Name=?," + 
//					"isFlightCrew=?" +
//					"WHERE Employee_Id=?;";
			
			String sql = "UPDATE Employees " + 
					"SET  First_Name=\'" + firstName + "\'," + 
					"Last_Name=\'" + lastName + "\'," + 
					"isFlightCrew=\'" + (isFlightCrew? "True" : "False") + "\' " +
					"WHERE Employee_Id=" + id + ";";
			
			statement = databaseConnection.createStatement();
			statement.executeQuery(sql);
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
		return true;
	}
	
	public static boolean updateFlightStatus(String newFlightStatus, Integer id) {
		Statement statement = null;
		Connection databaseConnection = DatabaseReference.getDatabaseConnection();
	    try {
			
			
			String sql = "UPDATE Flights " + 
					"SET Status=\'" + newFlightStatus + "\' " + 
					"WHERE Flight_Id=" + id + ";";
			statement = databaseConnection.createStatement();
			statement.executeQuery(sql);
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
		return true;
	}
	
	public static boolean deleteEmployee(Integer id) {
		Statement statement = null;
		Connection databaseConnection = DatabaseReference.getDatabaseConnection();
	    try {
			
			String sql = "DELETE FROM Employees WHERE Employee_Id=" + id + ";";
			System.out.println(sql);
			statement = databaseConnection.createStatement();
			statement.executeQuery(sql);
			
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
		return true;
	}
	
	public static HashMap<String, HashMap<String, Integer>> getFlightHistory() {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		
		
		Connection databaseConnection = DatabaseReference.databaseReference.getDatabaseConnection();
		Statement stmt  = null;
		ResultSet rs    = null;
		HashMap<String, HashMap<String, Integer>> flightHistory = new HashMap<>();
		flightHistory.put("San Luis Obispo", new HashMap<>());
		flightHistory.put("Los Angeles", new HashMap<>());
		flightHistory.put("San Francisco", new HashMap<>());
		flightHistory.put("San Diego", new HashMap<>());
		flightHistory.put("Arizona", new HashMap<>());
		flightHistory.put("Seattle", new HashMap<>());
		flightHistory.put("Dallas", new HashMap<>());
		
		
        try {
			//First get users from employees table
			String sql = "SELECT AVG(Duration), Departure_Location_Id, Destination_Location_Id FROM FlightDurationHistory GROUP BY Destination_Location_Id, Departure_Location_Id";
			
			stmt  = databaseConnection.createStatement();
	        rs    = stmt.executeQuery(sql);
	             
	        while (rs.next()) {
	        	Integer departureLocationId = rs.getInt("Departure_Location_Id");
	        	Integer destinationLocationId = rs.getInt("Destination_Location_Id");
	        	Integer duration = (int)rs.getDouble("AVG(Duration)");
	        	
	        	if (departureLocationId == 1) {
	     	    	if (destinationLocationId == 2) {
	     	    		flightHistory.get("San Luis Obispo").put("Los Angeles", duration);
	     	    	}
	     	    	else if (destinationLocationId == 3) {
	     	    		flightHistory.get("San Luis Obispo").put("San Francisco", duration);
	     	    	}
	     	    	else if (destinationLocationId == 4) {
	     	    		flightHistory.get("San Luis Obispo").put("San Diego", duration);
	     	    	}
	     	    	else if (destinationLocationId == 5) {
	     	    		flightHistory.get("San Luis Obispo").put("Arizona", duration);
	     	    	}
	     	    	else if (destinationLocationId == 6) {
	     	    		flightHistory.get("San Luis Obispo").put("Seattle", duration);
	     	    	}
	     	    	else if (destinationLocationId == 7) {
	     	    		flightHistory.get("San Luis Obispo").put("Dallas", duration);
	     	    	}
	     	    	
	     	    }
	     	    else {
	     	    	if (departureLocationId == 2) {
	     	    		flightHistory.get("Los Angeles").put("San Luis Obispo", duration);
	     	    	}
	     	    	else if (departureLocationId == 3) {
	     	    		flightHistory.get("San Francisco").put("San Luis Obispo", duration);
	     	    	}
	     	    	else if (departureLocationId == 4) {
	     	    		flightHistory.get("San Diego").put("San Luis Obispo", duration);
	     	    	}
	     	    	else if (departureLocationId == 5) {
	     	    		flightHistory.get("Arizona").put("San Luis Obispo", duration);
	     	    	}
	     	    	else if (departureLocationId == 6) {
	     	    		flightHistory.get("Seattle").put("San Luis Obispo", duration);
	     	    	}
	     	    	else if (departureLocationId == 7) {
	     	    		flightHistory.get("Dallas").put("San Luis Obispo", duration);
	     	    	}
	     	    	
	     	    }
	
	        }

		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
        	try { 
	        	if (stmt != null) {
	        		stmt.close();
	        	}
	        	
        	} catch (SQLException ex) {
        		System.out.println(ex.getMessage());
                return null;
        	}
        	
        }
		
		
		return flightHistory;
	}
	
	
	public static HashMap<String, HashMap<String, Double>> getFlightEmptySeatsHistory() {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		
		
		Connection databaseConnection = DatabaseReference.databaseReference.getDatabaseConnection();
		Statement stmt  = null;
		ResultSet rs    = null;
		HashMap<String, HashMap<String, Double>> flightHistory = new HashMap<>();
		flightHistory.put("San Luis Obispo", new HashMap<>());
		flightHistory.put("Los Angeles", new HashMap<>());
		flightHistory.put("San Francisco", new HashMap<>());
		flightHistory.put("San Diego", new HashMap<>());
		flightHistory.put("Arizona", new HashMap<>());
		flightHistory.put("Seattle", new HashMap<>());
		flightHistory.put("Dallas", new HashMap<>());
		
		
        try {
			//First get users from employees table
			String sql = "SELECT AVG(CAST(Seats_Taken AS float)/Seats) AS Average_Empty_Seats, Departure_Location_Id, Destination_Location_Id FROM FlightDurationHistory GROUP BY Destination_Location_Id, Departure_Location_Id;";
			stmt  = databaseConnection.createStatement();
	        rs    = stmt.executeQuery(sql);
	             
	        while (rs.next()) {
	        	Integer departureLocationId = rs.getInt("Departure_Location_Id");
	        	Integer destinationLocationId = rs.getInt("Destination_Location_Id");
	        	Double avgEmptySeats = rs.getDouble("Average_Empty_Seats");
	        	
	        	if (departureLocationId == 1) {
	     	    	if (destinationLocationId == 2) {
	     	    		flightHistory.get("San Luis Obispo").put("Los Angeles", avgEmptySeats);
	     	    	}
	     	    	else if (destinationLocationId == 3) {
	     	    		flightHistory.get("San Luis Obispo").put("San Francisco", avgEmptySeats);
	     	    	}
	     	    	else if (destinationLocationId == 4) {
	     	    		flightHistory.get("San Luis Obispo").put("San Diego", avgEmptySeats);
	     	    	}
	     	    	else if (destinationLocationId == 5) {
	     	    		flightHistory.get("San Luis Obispo").put("Arizona", avgEmptySeats);
	     	    	}
	     	    	else if (destinationLocationId == 6) {
	     	    		flightHistory.get("San Luis Obispo").put("Seattle", avgEmptySeats);
	     	    	}
	     	    	else if (destinationLocationId == 7) {
	     	    		flightHistory.get("San Luis Obispo").put("Dallas", avgEmptySeats);
	     	    	}
	     	    	
	     	    }
	     	    else {
	     	    	if (departureLocationId == 2) {
	     	    		flightHistory.get("Los Angeles").put("San Luis Obispo", avgEmptySeats);
	     	    	}
	     	    	else if (departureLocationId == 3) {
	     	    		flightHistory.get("San Francisco").put("San Luis Obispo", avgEmptySeats);
	     	    	}
	     	    	else if (departureLocationId == 4) {
	     	    		flightHistory.get("San Diego").put("San Luis Obispo", avgEmptySeats);
	     	    	}
	     	    	else if (departureLocationId == 5) {
	     	    		flightHistory.get("Arizona").put("San Luis Obispo", avgEmptySeats);
	     	    	}
	     	    	else if (departureLocationId == 6) {
	     	    		flightHistory.get("Seattle").put("San Luis Obispo", avgEmptySeats);
	     	    	}
	     	    	else if (departureLocationId == 7) {
	     	    		flightHistory.get("Dallas").put("San Luis Obispo", avgEmptySeats);
	     	    	}
	     	    	
	     	    }
	
	        }

		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
        	try { 
	        	if (stmt != null) {
	        		stmt.close();
	        	}
	        	
        	} catch (SQLException ex) {
        		System.out.println(ex.getMessage());
                return null;
        	}
        	
        }
		
		
		return flightHistory;
	}
	
	public static boolean checkCustomerLogin(String username, String password) {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
        Connection databaseConnection = DatabaseReference.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
                String sql = "SELECT Username, Password FROM Customers WHERE Username=? AND Password=?;";
                preparedStatement = databaseConnection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                	return true;
                }
        } catch (SQLException ex) {
    	    System.out.println(ex.getMessage());
    		return false;
        }
        return false;
	}
	
	public static boolean checkFlightExists(String flightDeparture) {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
        Connection databaseConnection = DatabaseReference.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
                preparedStatement = databaseConnection.prepareStatement(
                		"SELECT * FROM FLIGHTS WHERE Departure_Time = ?");
                preparedStatement.setString(1, flightDeparture);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                	return true;
                }
        } catch (SQLException ex) {
    	    System.out.println(ex.getMessage());
    		return false;
        }
        return false;
	}
	public static String getFlightStatus(String flightDeparture) {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
        Connection databaseConnection = DatabaseReference.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
                preparedStatement = databaseConnection.prepareStatement(
                		"SELECT Status FROM FLIGHTS WHERE Departure_Time = ?");
                preparedStatement.setString(1, flightDeparture);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                	String status = resultSet.getString("Status");
                	return status;
                }
        } catch (SQLException ex) {
    	    System.out.println(ex.getMessage());
    		return null;
        }
        return null;
	}
	
	public static boolean checkEmployeeLogin(String username, String password) {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
        Connection databaseConnection = DatabaseReference.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
                String sql = "SELECT Username, Password FROM Employees WHERE Username=? AND Password=?";
                preparedStatement = databaseConnection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                System.out.println(username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                	return true;
                }
        } catch (SQLException ex) {
    	    System.out.println(ex.getMessage());
    		return false;
        }
        return false;
	}
	
	public static boolean checkConfirmationCode(String firstName, String lastName, String code) {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		
		Connection databaseConnection = DatabaseReference.getDatabaseConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; 
		try {
			String sql = "SELECT First_Name, Last_Name, Confirmation_Code FROM Tickets WHERE First_Name=? AND Last_Name=? AND Confirmation_Code=?";
			preparedStatement = databaseConnection.prepareStatement(sql);	
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, code); 
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true; 
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage()); 
			return false;
		}
		return false; 
	}
	
	public static boolean addTicket(String firstname, String lastname, String phoneNum, String code) {
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		
		Connection databaseConnection = DatabaseReference.getDatabaseConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; 
		try {
			String sql = "INSERT into Tickets (First_Name, Last_Name, Phone_Number, Confirmation_Code) VALUES ('?','?','?','?');";
			preparedStatement = databaseConnection.prepareStatement(sql);	
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
//			preparedStatement.setString(3, "09/28/1996"); 
			/* how to add dob? */
			preparedStatement.setString(3, phoneNum);
			preparedStatement.setString(4, code);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true; 
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage()); 
			return false;
		}
		return true;
	}
	
	public static boolean addTicket(int flight_id, int customer_id, int employee_id, boolean checked_in, String confirmation_code, 
			String first_name, String last_name, boolean isCustomer, String phone_num) {
		
		if (DatabaseReference.databaseReference == null) {
			DatabaseReference.databaseReference = new DatabaseReference();
		}
		
		String tableName = "Tickets";
		Integer null_int = null;
            
		try {
			String sql = "INSERT INTO " + tableName + " ";
			sql += "(Flight_Id, Customer_Owner_Id, Employee_Owner_Id, Checked_In, Confirmation_Code, First_Name, Last_Name, Phone_Number) VALUES (";
			sql += "\'" + flight_id + "\',";
			
			if(isCustomer) {
				sql += "\'" + customer_id + "\',";
				sql += "\'" + null_int + "\',";
			}
			else {
				sql += "\'" + null_int + "\',";
				sql += "\'" + employee_id + "\',";
			}
			
			if(checked_in) {
				sql += "\'True\',";
			}
			else {
				sql += "\'False\',";
			}
			sql += "\'" + confirmation_code + "\', \'" + first_name + "\', \'" + last_name + "\', \'" + phone_num + "\');";

			Statement stmt  = DatabaseReference.getDatabaseConnection().createStatement();
	        ResultSet rs    = stmt.executeQuery(sql);
	        
		} catch(SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
		}

		return true;
	}
	

}

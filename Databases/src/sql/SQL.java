package sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import grs.GRS;

/**
 * 
 * All database connectivity should be handled from within here.
 *
 */
public class SQL {

	private static PreparedStatement ps;

	/**
	 * Queries the database and prints the results.
	 * 
	 * @param conn a connection object
	 * @param sql  a SQL statement that returns rows: this query is written with the
	 *             Statement class, typically used for static SQL SELECT statements.
	 */
	public static void sqlQuery(Connection conn, String sql) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String value = rsmd.getColumnName(i);
				System.out.print(value);
				if (i < columnCount)
					System.out.print(",  ");
			}
			System.out.print("\n");
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					String columnValue = rs.getString(i);
					System.out.print(columnValue);
					if (i < columnCount)
						System.out.print(",  ");
				}
				System.out.print("\n");
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Queries the database and prints the results.
	 * 
	 * @param conn a connection object
	 * @param sql  a SQL statement that returns rows: this query is written with the
	 *             PrepareStatement class, typically used for dynamic SQL SELECT
	 *             statements.
	 */
	public static void sqlQuery(Connection conn, PreparedStatement sql) {
		try {
			ResultSet rs = sql.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String value = rsmd.getColumnName(i);
				System.out.print(value);
				if (i < columnCount)
					System.out.print(",  ");
			}
			System.out.print("\n");
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					String columnValue = rs.getString(i);
					System.out.print(columnValue);
					if (i < columnCount)
						System.out.print(",  ");
				}
				System.out.print("\n");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Lists equipment
	 */

	public static void ps_list_equipment() {
		try {
			ps = GRS.conn.prepareStatement("SELECT SerialNumber, Weight, Description, Type, Size FROM Equipment");
			sqlQuery(GRS.conn, ps);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Searches equipment
	 */
	public static void ps_search_equipment(int serialNum) {
		try {
			ps = GRS.conn.prepareStatement(
					"SELECT SerialNumber, Weight, Description, Type, Size FROM Equipment WHERE SerialNumber = ?");
			ps.setInt(1, serialNum);
			sqlQuery(GRS.conn, ps);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Adds equipment
	 */
	public static void ps_add_equipment(int serialNum, int weight, String description, int type, int size) {
		try {
			ps = GRS.conn.prepareStatement(
					"INSERT INTO Equipment(SerialNumber, Weight, Description, Type, Size) VALUES(?,?,?,?,?)");
			ps.setInt(1, serialNum);
			ps.setInt(2, weight);
			ps.setString(3, description);
			ps.setInt(4, type);
			ps.setInt(5, size);
			if (ps.executeUpdate() == 1) {
				System.out.println("Insertion Successful");
			} else {
				System.out.println("Insertion Error");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void ps_edit_equipment(int attributeInt, String attributeStr, String selectedAttribute,
			int serialNum) {
		try {
			switch (selectedAttribute) {
			case "SerialNumber":
				ps = GRS.conn.prepareStatement("UPDATE Equipment SET SerialNumber = ? WHERE SerialNumber = ?");
				ps.setInt(1, attributeInt);
				break;
			case "Description":
				ps = GRS.conn.prepareStatement("UPDATE Equipment SET Description = ? WHERE SerialNumber = ?");
				ps.setString(1, attributeStr);
				break;
			case "Weight":
				ps = GRS.conn.prepareStatement("UPDATE Equipment SET Weight = ? WHERE SerialNumber = ?");
				ps.setInt(1, attributeInt);
				break;
			case "Type":
				ps = GRS.conn.prepareStatement("UPDATE Equipment SET Type = ? WHERE SerialNumber = ?");
				ps.setInt(1, attributeInt);
				break;
			case "Size":
				ps = GRS.conn.prepareStatement("UPDATE Equipment SET Size = ? WHERE SerialNumber = ?");
				ps.setInt(1, attributeInt);
				break;
			}
			ps.setInt(2, serialNum);
			if (ps.executeUpdate() == 1) {
				System.out.println("Edit Successful");
			} else {
				System.out.println("Edit Error");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * Deletes equipment
	 */
	public static void ps_delete_equipment(int serialNum) {
		try {
			ps = GRS.conn.prepareStatement("DELETE FROM Equipment WHERE SerialNumber = ?");
			ps.setInt(1, serialNum);
			if (ps.executeUpdate() == 1) {
				System.out.println("Deletion Successful");
			} else {
				System.out.println("Deletion Error");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	public static void renting_checkouts(int customerID) {
		try {
			ps = GRS.conn.prepareStatement(
					"SELECT count(EquipmentSerial) FROM (Allocation NATURAL JOIN RentalOrder) WHERE CustomerID=?");
			ps.setInt(1, customerID);
			sqlQuery(GRS.conn, ps);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void popular_item() {
		String sql = "SELECT EquipmentSerial, TotalDuration, RentalCount FROM \n"
				+ "(SELECT EquipmentSerial, sum(Duration) as TotalDuration FROM Allocation NATURAL JOIN (SELECT RentalID, DueDate - CheckoutDate as Duration FROM RentalOrder) GROUP BY EquipmentSerial ORDER BY sum(Duration) DESC)\n"
				+ " NATURAL JOIN (SELECT EquipmentSerial, count(EquipmentSerial) as RentalCount FROM Allocation GROUP BY EquipmentSerial);";
		sqlQuery(GRS.conn, sql);
	}
	
	public static void popular_manufacturer() {
		String sql = "SELECT Manufacturer FROM Allocation JOIN Inventory ON Allocation.EquipmentSerial=Inventory.SerialNumber GROUP BY Manufacturer ORDER BY count(Manufacturer) DESC LIMIT 1;";
		sqlQuery(GRS.conn, sql);
	}
	
	public static void popular_drone() {
		String sql = "SELECT SerialNumber, MilesFlown FROM (SELECT SerialNumber, count(DroneSerial) as count FROM (Drone LEFT OUTER JOIN Delivery ON DroneSerial=SerialNumber) GROUP BY SerialNumber) LEFT OUTER JOIN \n"
				+ "    (SELECT DroneSerial, sum(WarehouseDistance) as MilesFlown FROM (RentalOrder JOIN Delivery ON RentalOrder.RentalID=Delivery.RentalID) JOIN \n"
				+ "        (Customer JOIN Distance ON Customer.Warehouse=Distance.Warehouse AND Customer.Address=Distance.Address)\n"
				+ "ON Customer.CustomerID=RentalOrder.CustomerID GROUP BY DroneSerial) ON SerialNumber=DroneSerial\n"
				+ "ORDER BY count DESC;";
		sqlQuery(GRS.conn, sql);
	}
	
	public static void items_checked_out() {
		String sql = "SELECT FirstName, LastName, count(EquipmentSerial) FROM (Customer JOIN RentalOrder ON Customer.CustomerID=RentalOrder.CustomerID) NATURAL JOIN\n"
				+ "Allocation GROUP BY Customer.CustomerID ORDER BY count(EquipmentSerial) DESC LIMIT 1;";
		sqlQuery(GRS.conn, sql);
	}
	
	public static void equipment_by_type(String year) {
		try {
			ps = GRS.conn.prepareStatement(
					"SELECT Description FROM Equipment JOIN Inventory ON Equipment.SerialNumber=Inventory.SerialNumber WHERE Year>? ORDER BY Type;");
			ps.setString(1, year);
			sqlQuery(GRS.conn, ps);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

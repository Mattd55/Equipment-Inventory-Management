package equipment_options;

import java.util.Scanner;

import menu.Menu;
import sql.SQL;

/*
 * For each equipment option, takes necessary user input and runs method in SQL to run queries or update the DB
 */

public class Equipment_options {
	public static void search_equipment(Scanner input) {
		System.out.println("Please enter serial number of equipment to search:");
		int snum = Integer.parseInt(input.nextLine());
		Menu.print_line_separator();
		SQL.ps_search_equipment(snum);
		
	}
	public static void delete_equipment(Scanner input) {
		System.out.println("Please enter serial number of equipment to delete:");
		int snum = Integer.parseInt(input.nextLine());
		SQL.ps_delete_equipment(snum);
		
	}
	public static void rent_equipment(Scanner input) {
		int customerId, serialNumber;
		String rentalDate, dueDate;
		System.out.println("Please enter the serial number of equipment to be rented: ");
		serialNumber = Integer.parseInt(input.nextLine());
		System.out.println("Please enter the customer ID of the customer to be rented to: ");
		customerId = Integer.parseInt(input.nextLine());
		System.out.println("Please enter the date the equipment is rented: ");
		rentalDate = input.nextLine();
		System.out.println("Please enter the due date for the equipment: ");
		dueDate = input.nextLine();
		System.out.println("Succesfully rented equipment");
	}
	public static void return_equipment(Scanner input) {
		int rentalID;
		String currentDate;
		System.out.println("Please enter the rental ID: ");
		rentalID = Integer.parseInt(input.nextLine());
		System.out.println("Please enter the date the equipment returned: ");
		currentDate = input.nextLine();
		System.out.println("Successfully returned equipment");	
	}
	public static void pick_up_equipment(Scanner input) {
		int rentalID, droneID;
		String date;
		System.out.println("Please enter the rental ID: ");
		rentalID = Integer.parseInt(input.nextLine());
		System.out.println("Please enter the ID of the Drone to be used: ");
		droneID = Integer.parseInt(input.nextLine());
		System.out.println("Please enter the date the equipment is to be picked up: ");
		date = input.nextLine();
		System.out.println("Successfully scheduled pickup");
	}
	
	public static void edit_equipment(Scanner input) {
		Menu.print_line_separator();
		System.out.println("Please enter serial number of equipment to edit:");
		int snum = Integer.parseInt(input.nextLine());
		Menu.print_menu(5);
		int choice = Integer.parseInt(input.nextLine());
		int attributeInt = 0;
		String attributeStr = "";
		String attribute = "";
		switch(choice){
		case 1:
			attribute = "SerialNumber";
			System.out.println("Please enter new serial number");
			attributeInt = Integer.parseInt(input.nextLine());
			break;
		case 2:
			attribute = "Description";
			System.out.println("Please enter new description");
			attributeStr = input.nextLine();
			break;
		case 3:
			attribute = "Weight";
			System.out.println("Please enter new weight");
			attributeInt = Integer.parseInt(input.nextLine());
			break;
		case 5:
			attribute = "Size";
			System.out.println("Please enter new size");
			attributeInt = Integer.parseInt(input.nextLine());
			break;
		case 4:
			attribute = "Type";
			System.out.println("Please enter new type");
			attributeInt = Integer.parseInt(input.nextLine());
			break;
		
		}
		SQL.ps_edit_equipment(attributeInt, attributeStr, attribute, snum);
		
		
	}
	public static void deliver_equipment(Scanner input) {
		int rentalID, droneID;
		String date;
		System.out.println("Please enter the rental ID: ");
		rentalID = Integer.parseInt(input.nextLine());
		System.out.println("Please enter the ID of the Drone to be used: ");
		droneID = Integer.parseInt(input.nextLine());
		System.out.println("Please enter the date the equipment is to be delivered to: ");
		date = input.nextLine();
		System.out.println("Successfully scheduled delivery");
	}
	public static void list_equipment(Scanner input) {
		Menu.print_line_separator();
		SQL.ps_list_equipment();
	}
	
	
	
	public static void add_equipment(Scanner input) {
		int serialNum, weight, type, size;
		String description;
		System.out.println("Please Enter Serial Number: ");
		serialNum = Integer.parseInt(input.nextLine());
		System.out.println("Please Enter Description:");
		description = input.nextLine();
		System.out.println("Please Enter Weight:");
		weight = Integer.parseInt(input.nextLine());
		System.out.println("Please Enter Type:");
		type = Integer.parseInt(input.nextLine());
		System.out.println("Please Enter Size:");
		size = Integer.parseInt(input.nextLine());
		SQL.ps_add_equipment(serialNum, weight, description, type, size);
	}
	
}

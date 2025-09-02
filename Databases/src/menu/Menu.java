package menu;
import java.util.Stack;

import equipment_options.Equipment_options;
import sql.SQL;

import java.util.Scanner;
public class Menu {
	
	/*
	 * This class handles menu navigation control and view. The control starts at main_menu and then goes to 
	 * methods that get the input and redirect to other menus or sql functions in other classes.
	 */

	
	
	/*
	 * Control for the customer menu
	 */
	
	static void customer_menu(Scanner input) {
		int choice;
		print_menu(2);
		choice = Integer.parseInt(input.nextLine());
		switch(choice){
		case 1:
			main_menu(input);
			break;
		case 2:
			break;
		case 3:
			main_menu(input);
			break;
			
		}
	}
	
	/*
	 * Control for manager menu
	 */
	
	static void manager_menu(Scanner input) {
		print_menu(3);
		int choice;
		choice = Integer.parseInt(input.nextLine());
		switch(choice) {
		case 1:
			equipment_menu(input);
			break;
		case 5:
			useful_reports_menu(input);
			break;
		case 6:
			main_menu(input);
			break;
		}
	}
	
	/**
	 * Control for useful reports menu.
	 */
	static void useful_reports_menu(Scanner input) {
		print_menu(6);
		int choice;
		choice = Integer.parseInt(input.nextLine());
		switch(choice) {
		case 1:
			System.out.println("enter a customer ID:");
			int customerID;
			choice = Integer.parseInt(input.nextLine());
			SQL.renting_checkouts(choice);
			break;
		case 2:
			SQL.popular_item();
			break;
		case 3:
			SQL.popular_manufacturer();
			break;
		case 4:
			SQL.popular_drone();
			break;
		case 5:
			SQL.items_checked_out();
			break;
		case 6:
			System.out.println("enter a year:");
			SQL.equipment_by_type(input.nextLine());
			break;
		case 7:
			main_menu(input);
			break;
		}
	}
	
	/*
	 * Prints a separator between menus
	 */
	
	public static void print_line_separator() {
		System.out.println("----------------------------------");
	}
	
	/*
	 * Control for equipment menu
	 */
	
	public static void equipment_menu(Scanner input) {
		print_menu(4);
		int choice = Integer.parseInt(input.nextLine());
		switch(choice) {
		case 0:
			Equipment_options.list_equipment(input);
			break;
		case 1: 
			Equipment_options.add_equipment(input);
			break;
		case 2:
			Equipment_options.delete_equipment(input);
			break;
		case 3:
			Equipment_options.edit_equipment(input);
			break;
		case 4:
			Equipment_options.rent_equipment(input);
			break;
		case 5: 
			Equipment_options.return_equipment(input);
			break;
		case 6:
			Equipment_options.deliver_equipment(input);
			break;
		case 7:
			Equipment_options.pick_up_equipment(input);
			break;
		case 8:
			Equipment_options.search_equipment(input);
			break;
		case 9:
			manager_menu(input);
			break;
		}
		equipment_menu(input);
	}
	
	/*
	 * Prints the view for each menu based on parameter.
	 */
	
	public static void print_menu(int menu) {
		print_line_separator();
		switch(menu){
		//outer layer
		case 1:
			System.out.println("Welcome to the Database");
			System.out.println("Options:");
			System.out.println("1. Manager");
			System.out.println("2. Customer");
			System.out.println("3. Close");
			break;
		//customer menu (Not Implemented)
		case 2:
			System.out.println("Welcome Customer");
			System.out.println("Options not implemented");
			System.out.println("1. Go Back");
			break;
		
		case 3:
			System.out.println("Welcome Manager");
			System.out.println("Options:");
			System.out.println("1. Manage Equipment:");
			System.out.println("2. Manage Drones (Not Implemented)");
			System.out.println("3. Purchase Inventory (Not Implemented)");
			System.out.println("4. View Rental Orders (Not Implemented)");
			System.out.println("5. Useful reports");
			System.out.println("6. Go Back");
			
			break;
		case 4:
			System.out.println("Equipment Options");
			System.out.println("0. View Equipment");
			System.out.println("1. Add Equpment");
			System.out.println("2. Remove Equipment");
			System.out.println("3. Edit Equipment");
			System.out.println("4. Rent Equipment");
			System.out.println("5. Return Equipment");
			System.out.println("6. Deliver Equipment");
			System.out.println("7. Pickup Equipment");
			System.out.println("8. Search Equipment");
			System.out.println("9. Go Back");
			break;
		case 5:
			System.out.println("Select Attribute to Change");
			System.out.println("1. Serial Number");
			System.out.println("2. Description");
			System.out.println("3. Weight");
			System.out.println("4. Type");
			System.out.println("5. Size");
		case 6:
			System.out.println("Select Report to Generate");
			System.out.println("1. Renting Checkouts");
			System.out.println("2. Popular Item");
			System.out.println("3. Popular Manufacturer");
			System.out.println("4. Popular Drone");
			System.out.println("5. Items Checked Out");
			System.out.println("6. Equipment by type of equipment.");
			System.out.println("7. Back");
		}
		print_line_separator();
	}
	
	
	/*
	 * Control for main menu
	 */
	public static void main_menu(Scanner input) {
		print_menu(1);
		int choice = Integer.parseInt(input.nextLine());
		switch(choice) {
			case 1:
				manager_menu(input);
				break;
			case 2: 
				customer_menu(input);
				break;
			case 3:
				System.out.println("Exiting");
				input.close();
				System.exit(1);
		}
	}
}

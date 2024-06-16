package lab05;
import java.util.Scanner;

/*
 * Lab #5
 * Names: Lucia Liu, Nithya Ramasubramonian
 * Due date: 6/16/24
 * Purpose: The purpose of this lab is to practice implementing a Hash Table through a Dollar modeling scenario.
 */
public class Main5 
{
	public static void main(String[] args) 
	{
		// Step 5
		Dollar[] dollars = new Dollar[20];
		dollars[0] = new Dollar(57.12);
		dollars[1] = new Dollar(23.44);
		dollars[2] = new Dollar(87.43);
		dollars[3] = new Dollar(68.99);
		dollars[4] = new Dollar(111.22);
		dollars[5] = new Dollar(44.55);
		dollars[6] = new Dollar(77.77); 
		dollars[7] = new Dollar(18.36);
		dollars[8] = new Dollar(543.21); 
		dollars[9] = new Dollar(20.21);
		dollars[10] = new Dollar(345.67);
		dollars[11] = new Dollar(36.18);
		dollars[12] = new Dollar(48.48);
		dollars[13] = new Dollar(101.00);
		dollars[14] = new Dollar(11.00);
		dollars[15] = new Dollar(21.00);
		dollars[16] = new Dollar(51.00);
		dollars[17] = new Dollar(1.00);
		dollars[18] = new Dollar(251.00);
		dollars[19] = new Dollar(151.00);
		HashTable hash = new HashTable(20);
	
		for (Dollar dollar : dollars) {
			hash.insert(dollar);
		}
	
		hash.printHash(); 
	
	    // Step 6: Then it will ask the user in a loop to enter a Dollar to search for.
	    // If the Dollar object is found in the hash table, it will print the index
	    // where found, otherwise it will print 'Invalid Data'. Then it will ask the
	    // user if they want to check again or end the program.
	
		Scanner scanner = new Scanner(System.in);
	    
		while (true) {
			System.out.print("Enter a Dollar to search for (or type 'exit' to end the program): ");
			String input = scanner.nextLine();
	
			if (input.equalsIgnoreCase("exit")) {
	     		break;
			}
	      
			try {
	     		int index = hash.search(new Dollar(Double.parseDouble(input)));
	        
	     		if (index != -1) {
	     			System.out.println("Dollar found at index: " + index);
	     		} else {
	     			System.out.println("Invalid Data");
	     		}
	        
			} catch (NumberFormatException e) {
	     		System.out.println("Please enter a valid Dollar value.");
	     	} catch (IllegalArgumentException e) {
	    	  	System.out.println("Please enter a valid Dollar value.");
	     	}
	    }
	
		scanner.close();
	}
}

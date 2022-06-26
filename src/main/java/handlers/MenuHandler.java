package handlers;
import data.Rent;
import data.Tenant;

import java.security.KeyStore;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.UUID;

public class MenuHandler {

    private static MenuHandler instance;
    private static final String MAIN_MENU_PROMPT = "Enter 'i' to input data,\n'd' to display a report, \n'q' to quit program: ";
    private static final String INPUT_PROMPT = "Enter 't' to add tenant,\n'r' to record rent payment, \n'e' to record expense: ";
    private static final String DISPLAY_PROMPT = "Enter 't' to displays tenants,\n'r' to display rents, \n'e' to display expenses, \n'a' to display annual report: ";

    private MenuHandler() {}

    /**
     * Returns instance of MenuHandler.
     * Constructor cannot be accessed, use this for getting instance.
     * @return MenuHandler Instance
     */
    public static MenuHandler getInstance() {
        if (instance == null) {
            instance = new MenuHandler();
        }
        return instance;
    }

    /**
     * Prompts the user for login details.
     * @return True on successful login, False otherwise.
     */
    public boolean promptLogin() {
        return false;
    }

    /**
     * Prompt Main Menu and take user input.
     */
    public void promptMainMenu() {
        System.out.println(MAIN_MENU_PROMPT);
        Scanner obj = new Scanner(System.in);

        char input = 0;
        while(true) {
            input = obj.next().charAt(0);
            if( input == 'i' || input == 'd' || input == 'q') {
                break;
            } else {
                System.out.println( "Invalid input! Reenter your input ('i', 'd', or 'q'): " );
            }
        }

        switch (input){
            case 'i':
                System.out.println(INPUT_PROMPT);
                while(true) {
                    input = obj.next().charAt(0);
                    if( input == 't' || input == 'r' || input == 'e') {
                        break;
                    } else {
                        System.out.println( "Invalid input! Reenter your input ('t', 'r', or 'e'): " );
                    }
                }
                switch (input){
                    case 't':
                        promptTenantMenu();
                        break;
                    case 'r':
                        promptRentMenu();
                        break;
                    case 'e':
                        promptExpenseMenu();
                        break;
                }
                break;
            case 'd':
                System.out.println(DISPLAY_PROMPT);
                break;
            case 'q':
                System.out.println("Quitting....");
                System.exit(1);
                break;

        }
    }

    /**
     * Used to deliver system messages directly to the user.
     */
    public static void systemMessage(String message) {
        System.out.println("[SYSTEM] " + message);
    }

    /**
     * Prompts tenant menu
     */
    public void promptTenantMenu() {
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter tenant's name: ");
        String name = obj.nextLine();
        System.out.print("Enter tenant's apartment number: ");
        int aptNumber = getPositiveInt();

        List<Tenant> tenantList = Tenant.getTenants();
        if (tenantList == null) {
            Tenant.addTenant(name, aptNumber);
        }
        else {
            boolean notOccupied = true;
            // Checks if apartment number is occupied. If not, then add tenant.
            for (Tenant t : tenantList) {
                if (t.getAptNum() == aptNumber) {
                    System.out.println("The apartment is already occupied");
                    notOccupied = false;
                }
            }
            if (notOccupied){
                Tenant.addTenant(name, aptNumber);
            }
        }
    }

    /**
     * Prompts rent menu
     */
    public void promptRentMenu() {
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter tenant's name: ");
        String name = obj.nextLine();

        int duplicateNames = 0;
        //Checks if tenant is already in arraylist. If yes, then record his rent.
        List<Tenant> tenantList = Tenant.getTenants();
        for (Tenant t : tenantList){
            if (t.getName().equals(name)){
                duplicateNames += 1;
            }
        }

        // If there's no duplicates
        if (duplicateNames == 1){
            System.out.print("Enter amount paid: ");
            double amount  = getPositiveDouble();
            System.out.print("Enter month rent is for (1-12): ");
            int month = getIntRange(1,12);
            for (Tenant t : tenantList) {
                Rent.addRent(Tenant.getTenantByID(t.getId()), 1, month, amount);
            }
        }

        // Makes user choose which duplicate name they'd like to record rent for.
        // Uses a hashmap to store the choice and UUID of duplicate.
        int duplicateChoice;
        if (duplicateNames >= 2){
            HashMap<Integer, UUID> map = new HashMap<Integer, UUID>();
            System.out.println("There is a duplicate name in your system!");
            System.out.println("Please choose which person you'd like to choose based on their apartment number: ");
            int choice = 1;
            for (Tenant t : tenantList){
                if (t.getName().equals(name)){
                    System.out.println(choice + ". " + t.getName() + ": Apartment Number " + t.getAptNum());
                    map.put(choice, t.getId());
                    choice += 1;
                }
            }
            duplicateChoice = getIntRange(1, choice);

            System.out.print("Enter amount paid: ");
            double amount  = getPositiveDouble();
            System.out.print("Enter month rent is for (1-12): ");
            int month = getIntRange(1,12);
            Rent.addRent(Tenant.getTenantByID(map.get(duplicateChoice)), 1, month, amount);
        }
    }

    /**
     * Prompts expense menu
     */
    public void promptExpenseMenu() {
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter month (1-12): ");
        int month = getIntRange(1,12);

        System.out.print("Enter day (1-31): ");
        int day = getIntRange(1,31);

        System.out.print("Enter expense category (Repairing, Utilities): ");
        String category = obj.nextLine();

        System.out.print("Enter payee (Bob's Hardware, Big Electric Co): ");
        String payee = obj.nextLine();

        System.out.print("Enter amount: ");
        double amount = getPositiveDouble();

    }

    /**
     * Takes in an input, validates input for correct range and returns it
     * @param low int
     * @param high int
     * @return a valid int that fits in the range of low and high
     */
    public int getIntRange( int low, int high ) {
        Scanner in = new Scanner( System.in );
        int input = 0;
        boolean valid = false;
        while( !valid ) {
            if( in.hasNextInt() ) {
                input = in.nextInt();
                if( input <= high && input >= low ) {
                    valid = true;
                } else {
                    System.out.println( "Invalid number! Reenter your input: " );

                }
            } else {
                in.next();
                System.out.println( "Invalid Input. Reenter your input: " );
            }
        }
        return input;
    }

    /**
     * Takes in an input, validates input for positive int and returns it
     * @return positive int
     */
    public int getPositiveInt( ) {
        Scanner in = new Scanner( System.in );
        int input = 0;
        boolean valid = false;
        while( !valid ) {
            if( in.hasNextInt() ) {
                input = in.nextInt();
                if( input >= 0 ) {
                    valid = true;
                } else {
                    System.out.println( "Reenter a positive integer: " );
                }
            } else {
                in.next();
                System.out.println( "Invalid Input. Reenter a positive integer: " );
            }
        }
        return input;
    }

    /**
     * Takes in an input, validates input for positive double and returns it
     * @return positive double
     */
    public double getPositiveDouble( ) {
        Scanner in = new Scanner( System.in );
        double input = 0.0;
        boolean valid = false;
        while( !valid ) {
            if( in.hasNextDouble() ) {
                input = in.nextDouble();
                if( input >= 0 ) {
                    valid = true;
                } else {
                    System.out.println( "Reenter a positive number: " );
                }
            } else {
                in.next();
                System.out.println( "Invalid Input. Reenter a positive number: " );
            }
        }
        return input;
    }

}

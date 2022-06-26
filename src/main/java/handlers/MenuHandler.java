package handlers;
import java.util.Scanner;

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
     * TODO: Determine valid return type for this.
     */
    public char promptMainMenu() {
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
                return 'i';
            case 'd':
                System.out.println(DISPLAY_PROMPT);
                return 'd';
            case 'q':
                return 'q';

        }
        return 0;
    }

    /**
     * Used to deliver system messages directly to the user.
     */
    public static void systemMessage(String message) {
        System.out.println("[SYSTEM] " + message);
    }

    public void promptTenantMenu() {
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter tenant's name: ");
        String name = obj.nextLine();
        System.out.print("Enter tenant's apartment number: ");
        int number = getPositiveInt();
    }

    public void promptRentMenu() {
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter tenant's name: ");
        String name = obj.nextLine();
        System.out.print("Enter amount paid: ");
        double amount  = getPositiveDouble();
        System.out.print("Enter month rent is for (1-12): ");
        int month = getIntRange(1,12);
    }

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

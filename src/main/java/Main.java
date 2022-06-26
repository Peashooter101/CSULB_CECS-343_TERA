import handlers.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MenuHandler menu = MenuHandler.getInstance();
        FileHandler file = FileHandler.getInstance();
        Scanner obj = new Scanner(System.in);

//        // While Login Invalid...
//        while (!menu.promptLogin()) {
//            // TODO: Invalid Login Details Logic
//        }
//        // Load Saved Data
//        file.loadData();

        while(true) {
            // Prompt Main Menu
            switch(menu.promptMainMenu()){
                case 'i':
                    char input = 0;
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
                            menu.promptTenantMenu();
                            break;
                        case 'r':
                            menu.promptRentMenu();
                            break;
                        case 'e':
                            menu.promptExpenseMenu();
                            break;
                    }

                case 'd':
                    break;

                case 'q':
                    System.out.println("Quitting....");
                    System.exit(1);
                    break;
            }


        }

    }

}

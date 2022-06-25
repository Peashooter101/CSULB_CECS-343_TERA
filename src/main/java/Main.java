import handlers.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MenuHandler menu = MenuHandler.getInstance();
        FileHandler file = FileHandler.getInstance();
        Scanner input = new Scanner(System.in);

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
                    switch (input.next().charAt(0)){
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

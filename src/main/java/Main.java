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
        // Load Saved Data
        file.loadData();

        while(true) {
            // Prompt Main Menu
            menu.promptMainMenu();

        }

    }

}

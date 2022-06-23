import handlers.*;

public class Main {

    public static void main(String[] args) {

        MenuHandler menu = MenuHandler.getInstance();
        FileHandler file = FileHandler.getInstance();

        // While Login Invalid...
        while (!menu.promptLogin()) {
            // TODO: Invalid Login Details Logic
        }

        // Load Saved Data
        file.loadData();

        // Prompt Main Menu
        menu.promptMainMenu();

    }

}

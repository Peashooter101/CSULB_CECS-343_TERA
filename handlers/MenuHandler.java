package handlers;

public class MenuHandler {

    private static MenuHandler instance;

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
    public void promptMainMenu() {

    }

}

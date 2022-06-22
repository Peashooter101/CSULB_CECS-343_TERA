package handlers;

public class MenuHandler {

    private static MenuHandler instance;

    private MenuHandler() {}

    public static MenuHandler getInstance() {
        if (instance == null) {
            instance = new MenuHandler();
        }
        return instance;
    }
}

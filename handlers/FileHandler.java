package handlers;

public class FileHandler {

    private static FileHandler instance;

    private FileHandler() {}

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

}

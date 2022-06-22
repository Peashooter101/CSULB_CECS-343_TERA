package handlers;

public class FileHandler {

    private static FileHandler instance;

    private FileHandler() {}

    /**
     * Returns instance of FileHandler.
     * Constructor cannot be accessed, use this for getting instance.
     * @return FileHandler Instance
     */
    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

    /**
     * Loads all data from saved files.
     * Rent must load AFTER Tenant, each Rent object is associated to a Tenant
     */
    public void loadData() {

    }

}

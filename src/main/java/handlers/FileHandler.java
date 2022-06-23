package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileHandler {

    private static FileHandler instance;
    private static ObjectMapper mapper = new ObjectMapper();
    private static File dir = new File("." + System.getProperty("file.separator") + "save_data");

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
     * Rent must load AFTER Tenant, each Rent object is associated to a Tenant.
     * Takes advantage of Jackson Core for JSON Parsing.
     */
    public void loadData() {

    }

    /**
     * Saves all data from memory.
     * Takes advantage of Jackson Core for JSON Building.
     * @return True if successful, False otherwise.
     */
    public boolean saveData() {
        File fileTenant = new File(dir, "tenant.json");

        // Check for Directory, create if missing...
        if (!dir.isDirectory()) {
            MenuHandler.systemMessage("No directory found, creating directory...");
            dir.mkdirs();
        }

        // Try to create the file.
        try {
            if (fileTenant.createNewFile()) {
                MenuHandler.systemMessage("No file found, created file...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}

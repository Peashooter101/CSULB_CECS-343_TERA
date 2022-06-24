package handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Tenant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    private static FileHandler instance;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File dir = new File("." + System.getProperty("file.separator") + "save_data");
    private static final File fileTenant = new File(dir, "tenant.json");
    private static final File fileRent = new File(dir, "rent.json");
    private static final File fileExpense = new File(dir, "expense.json");

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
    public boolean loadData() {
        try {
            if (fileTenant.exists()) {
                ArrayList<Tenant> data = mapper.readValue(fileTenant, new TypeReference<>() {});
                Tenant.loadTenants(data);
            } else {
                MenuHandler.systemMessage("Tenant save file does not exist, ignoring...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Saves all data from memory.
     * Takes advantage of Jackson Core for JSON Building.
     * @return True if successful, False otherwise.
     */
    public boolean saveData() {

        // Check for Directory, create if missing...
        if (dir.mkdirs()) {
            MenuHandler.systemMessage("No directory found, created directory...");
        }

        // Try to create the files.
        try {
            if (fileTenant.createNewFile()) {
                MenuHandler.systemMessage("No tenant save file found, created file...");
            }
            if (fileRent.createNewFile()) {
                MenuHandler.systemMessage("No rent save file found, created file...");
            }
            if (fileExpense.createNewFile()) {
                MenuHandler.systemMessage("No expense save file found, created file...");
            }

            // Save Tenants
            mapper.writeValue(fileTenant, Tenant.getTenants());

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

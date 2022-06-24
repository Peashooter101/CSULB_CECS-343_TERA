package data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import handlers.FileHandler;
import handlers.MenuHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Tenant {

    private static ArrayList<Tenant> tenants;
    private UUID id;
    private String name;
    private int aptNum;

    /**
     * Default Constructor
     * Used by Jackson for JSON Mapper Reading.
     * Using this on its own accomplishes nothing.
     */
    public Tenant() {}

    /**
     * Private Constructor
     * Creates a new Tenant Object. Constructor called by addTenant().
     */
    private Tenant(String name, int aptNum) {
        this.name = name;
        this.aptNum = aptNum;
        this.id = UUID.randomUUID();
    }

    /**
     * Creates a new Tenant Object and adds it to the ArrayList.
     * This is the only way to add a new Tenant. Constructor is private.
     */
    public static Tenant addTenant(String name, int aptNum) {
        if (tenants == null) {
            tenants = new ArrayList<>();
        }
        Tenant t = new Tenant(name, aptNum);
        tenants.add(t);
        return t;
    }

    /**
     * Loads a Tenant from a String into Memory
     * @return True if successful, False otherwise.
     */
    public static boolean loadTenants(ArrayList<Tenant> data) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        if ((stack.length >= 3) && !(stack[2].getClassName().equals(FileHandler.class.getName()))) {
            MenuHandler.systemMessage("An unknown class tried to edit the Tenants list.");
            return false;
        }
        if (data == null || data.isEmpty()) {
            MenuHandler.systemMessage("No data found in tenant.json... Ignoring...");
            return true;
        }
        Tenant.tenants = data;
        return true;
    }

    /**
     * Retrieves the list of tenants saved to memory.
     * List is unmodifiable to protect the list of Tenants.
     * @return Unmodifiable List of Tenants
     */
    public static List<Tenant> getTenants() {
        return Collections.unmodifiableList(tenants);
    }

    public UUID getId() { return this.id; }
    public String getName() { return this.name; }
    public int getAptNum() { return this.aptNum; }

    /**
     * Returns if the tenant is the same tenant.
     * Only checks for the UUID Value.
     * @param o Object
     * @return True if UUID matches, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (!(o instanceof Tenant t)) { return false; }
        return this.getId().equals(t.getId());
    }

}

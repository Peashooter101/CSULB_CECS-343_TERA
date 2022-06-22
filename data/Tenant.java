package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tenant {

    private static ArrayList<Tenant> tenants;
    private String name;
    private int aptNum;

    /**
     * Private Constructor
     * Creates a new Tenant Object. Constructor called by addTenant().
     */
    private Tenant(String name, int aptNum) {
        this.name = name;
        this.aptNum = aptNum;
    }

    /**
     * Creates a new Tenant Object and adds it to the ArrayList.
     * This is the only way to add a new Tenant. Constructor is private.
     */
    public Tenant addTenant(String name, int aptNum) {
        Tenant t = new Tenant(name, aptNum);
        tenants.add(t);
        return t;
    }

    /**
     * Loads a Tenant from a String into Memory
     * TODO: May require Jackson Core for JSON Serialization.
     * @return True if successful, False otherwise.
     */
    public boolean loadTenant(String data) {
        return false;
    }

    /**
     * Retrieves the list of tenants saved to memory.
     * List is unmodifiable to protect the list of Tenants.
     * @return Unmodifiable List of Tenants
     */
    public static List<Tenant> getTenants() {
        return Collections.unmodifiableList(tenants);
    }

    public String getName() { return this.name; }
    public int getAptNum() { return this.aptNum; }

}

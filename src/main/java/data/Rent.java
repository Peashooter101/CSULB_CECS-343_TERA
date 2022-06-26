package data;

import handlers.FileHandler;
import handlers.MenuHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Rent {

    private static ArrayList<Rent> rent;
    private UUID tenantId;
    private int year;
    private int month;
    private double payment;

    /**
     * Default Constructor
     * Used by Jackson for JSON Mapper Reading.
     * Using this on its own accomplishes nothing.
     */
    public Rent() {}

    /**
     * Private Constructor
     * Creates a new Rent Object. Constructor called by addRent().
     */
    private Rent(UUID tenantId, int year, int month, double payment) {
        this.tenantId = tenantId;
        this.year = year;
        this.month = month;
        this.payment = payment;
    }

    /**
     * Creates a new Tenant Object and adds it to the ArrayList.
     * This is the only way to add a new Tenant. Constructor is private.
     * @param tenant Tenant associated to the Rent
     * @param year Year of the Payment
     * @param month Month of the Payment
     * @param payment Payment Total
     * @return Rent object added.
     */
    public static Rent addRent(Tenant tenant, int year, int month, double payment) {
        if (rent == null) {
            rent = new ArrayList<>();
        }
        if (tenant == null || tenant.getId() == null) {
            MenuHandler.systemMessage("Attempted to add Rent but there is no valid Tenant to associate to.");
            return null;
        }
        if (month < 1 || month > 12) {
            MenuHandler.systemMessage("Attempted to add Rent but the month is invalid.");
            return null;
        }
        if (year < 0) {
            MenuHandler.systemMessage("Attempted to add Rent but the year is invalid.");
            return null;
        }
        Rent r = new Rent(tenant.getId(), year, month, payment);
        rent.add(r);
        return r;
    }

    /**
     * Loads a Rent from a File into Memory
     * Can only be called from FileHandler.class
     * @param data ArrayList loaded from a File.
     * @return True if successful, False otherwise.
     */
    public static boolean loadRent(ArrayList<Rent> data) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        if ((stack.length >= 3) && !(stack[2].getClassName().equals(FileHandler.class.getName()))) {
            MenuHandler.systemMessage("An unknown class tried to edit the Rent list.");
            return false;
        }
        if (data == null || data.isEmpty()) {
            MenuHandler.systemMessage("No data found in rent.json... Ignoring...");
            return true;
        }
        for (Rent r : data) {
            if (Tenant.getTenantByID(r.tenantId) == null) {
                MenuHandler.systemMessage("Invalid data found in rent.json... Ignoring...");
                return false;
            }
        }
        Rent.rent = data;
        return true;
    }

    /**
     * Retrieves the list of rent saved to memory.
     * List is unmodifiable to protect the list of Rent Payments.
     * @return Unmodifiable List of Rent Payments
     */
    public static List<Rent> getRent() {
        return Collections.unmodifiableList(rent);
    }

    public Tenant getTenant() { return Tenant.getTenantByID(tenantId); }
    public int getMonth() { return month; }
    public int getYear() { return year; }
    public double getPayment() { return payment; }


    /**
     * Returns if the rent is the same rent.
     * Performs the checks on all attributes.
     * @param o Object
     * @return True if (this == o), false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (!(o instanceof Rent r)) { return false; }
        return this.getTenant().equals(r.getTenant()) &&
                this.getMonth() == r.getMonth() &&
                this.getYear() == r.getYear() &&
                this.getPayment() == r.getPayment();
    }
}

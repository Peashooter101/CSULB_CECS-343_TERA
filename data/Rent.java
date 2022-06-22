package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rent {

    private static ArrayList<Rent> rent;

    /**
     * Retrieves the list of rent saved to memory.
     * List is unmodifiable to protect the list of Rent Payments.
     * @return Unmodifiable List of Rent Payments
     */
    public static List<Rent> getRent() {
        return Collections.unmodifiableList(rent);
    }

}

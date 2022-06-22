package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Expense {

    private static ArrayList<Expense> expenses;

    /**
     * Retrieves the list of expenses saved to memory.
     * List is unmodifiable to protect the list of Expenses.
     * @return Unmodifiable List of Expenses
     */
    public static List<Expense> getExpenses() {
        return Collections.unmodifiableList(expenses);
    }
}

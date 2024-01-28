package progroupproject.Products;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class extends the Product class, and takes care of the Console object.
 */
public class Console extends Product{
    private List<String> library;

    /**
     * Constructor for the Console class.
     * 
     * @param id       The ID of the console.
     * @param name     The name of the console.
     * @param price    The price of the console.
     * @param company  The company producing the console.
     * @param date     The release date of the console.
     * @param quantity The quantity of consoles available.
     * @param library  The list of games in the console's library.
     */
    public Console(int id, String name, double price, String company, String date, int quantity, List<String> library) {
        super(id, name, price, company, date, quantity);
        sortLibrary(library);
        this.library = library;
    }

    public List<String> getLibrary() {
        return library;
    }

    public void setLibrary(List<String> library) {
        this.library = library;
    }

    public String toString () {
        return "Id: " + getId() + ", Name: " + getName() + ", Price: " + getPrice() + ", Company: " + getCompany() + ", Release Date: " + getDate() + toStringLibrary();
    }

    /**
     * Returns a string representation of the console's library.
     * 
     * @return A string representation of the console's game library.
     */
    public String toStringLibrary () {
        String games = "";
        for (String game : library) {
            games += ", "+ game ;
        }
        return games;
    }

    /**
     * Converts the console's library into a formatted string.
     * 
     * @return The formatted string representing the console's library.
     */
    public String convertLibraryToString () {
        String consoles = "";
        for (String game : library) {
            consoles += game + ", ";
        }
        return consoles;
    }

    /**
    * Sorts the console's library in alphabetical order.
    * 
    * @param library The library of games to be sorted.
    */
    public void sortLibrary (List<String> library) 
    {
        Collections.sort(library, new Comparator<String>() {
            @Override
            public int compare (String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
    }
}

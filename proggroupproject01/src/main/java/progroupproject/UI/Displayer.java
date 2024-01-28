package progroupproject.UI;

import java.util.List;

import progroupproject.Products.Console;
import progroupproject.Products.Game;
import progroupproject.Products.Product;

/**
 * This class is used to display information in the console using print lines.
 */
public class Displayer {

    /**
    * Displays a list of options with corresponding index numbers.
    * 
    * @param options The list of options to be displayed.
    */
    public static void displayOptions (List<String> options) {
        String message = "";
        for (int i = 0; i < options.size(); i++) {
            if (i < 9) {
                message += "(" + (i+1) + ")" + "  | ";
            }
            else {
                message += "(" + (i+1) + ")" + " | ";
            }
                message += options.get(i) + " \n"; 
        }
        System.out.println(message);
        System.out.println();
    }

    /**
     * Displays a list of options with corresponding index numbers starting from a specified index.
     * 
     * @param options The list of options to be displayed.
     * @param start   The starting index for displaying options.
     */
    public static void displayOptions (List<String> options, int start) {
        String message = "";
        for (int i = 0; i < options.size() ; i++) {
            if (i < start) {
                message += "     | ";
            }
            else if (i < 10) {
                message += "(" + (i+1-start) + ")" + "  | ";
            }
            else {
                message += "(" + (i+1-start) + ")" + " | ";
            }
                message += options.get(i) + " \n"; 
        }
        System.out.println(message);
        System.out.println();
    }

    /**
     * Displays a list of strings.
     * 
     * @param options The list of strings to be displayed.
     */
    public static void displayString (List<String> options) {
        String message = "";
        for (int i = 0; i < options.size(); i++) {
            message += " | " + options.get(i) + " \n"; 
        }
        System.out.println(message);
        System.out.println();
    }

    /**
     * Displays a list of strings after a specified cut point.
     * 
     * @param options The list of strings to be displayed.
     * @param cut     The index to start the display from.
     */
    public static void displayString (List<String> options, int cut) {
        String message = "";
        for (int i = 0; i < options.size(); i++) {
            message += " | " + options.get(i) + " \n"; 
        }
        System.out.println(message.substring(cut));
        System.out.println();
    }
    
    /**
     * Displays a message for invalid input.
     */
    public static void displayInvalidMessage () {
        System.out.println("You entered invalid data !");
    }

    /**
     * Displays a list of games.
     * 
     * @param games The list of games to be displayed.
     */
    public static void displayGames (List<Game> games) {
        for (Product game : games) {
            System.out.println(game);
        }
    }
    
     /**
     * Displays a list of consoles.
     * 
     * @param consoles The list of consoles to be displayed.
     */
    public static void displayConsoles (List<Console> consoles) {
        for (Console console : consoles){
            System.out.println(console);
        }
    }

    /**
     * Displays details of a single product (either a game or a console).
     * 
     * @param product The product to be displayed.
     */
    public static void displayOneProduct(Product product){
        if(product instanceof Game){
            Game myGame = (Game) product;
            System.out.println(myGame);
        } else {
            Console myConsole = (Console) product;
            System.out.println(myConsole);
        }
    }

    /**
     * Displays details of multiple products without a specific field.
     * 
     * @param products The list of products to be displayed.
     */
    public static void displayProducts (List<Product> products) {
        for(Product product : products){
            System.out.println(product);
        }
    }
    //Does the same but with only the names.
    public static void displayProductNames (List<Product> products) {
        for(Product product : products){
            System.out.println(product.getName());
        }
    }
    //The same but with prices
    public static void displayProductPrices (List<Product> products) {
        for(Product product : products){
            System.out.println(product.getPrice() + "$, " + product.getName());
        }
    }
    //The same but with companies
    public static void displayProductCompanies (List<Product> products) {
        for(Product product : products){
            System.out.println(product.getCompany() + ", " + product.getName());
        }
    }
    //This one only for games since only games are allowed to have the field genre.
    public static void displayGameGenre (List<Game> games) {
        for(Game game : games){
            System.out.println(game.getGenre() + ", " + game.getName());
        }
    }
}

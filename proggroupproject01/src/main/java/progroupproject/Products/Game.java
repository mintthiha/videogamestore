package progroupproject.Products;

/**
 * This class extends the Product class, and takes care of the Game object.
 */
public class Game extends Product{
    private String genre;

     /**
     * Constructor for the Game class.
     * 
     * @param id       The ID of the game.
     * @param name     The name of the game.
     * @param price    The price of the game.
     * @param company  The company producing the game.
     * @param date     The release date of the game.
     * @param quantity The quantity of the game available.
     * @param genre    The genre of the game.
     */
    public Game(int id, String name, double price, String company, String date, int quantity, String genre) {
        super(id, name, price, company, date, quantity);
        this.genre = genre;
    }

    /**
     * Default constructor for the Game class.
     * Sets default values for unspecified fields.
     */
    public Game() {
        super(0, "   -    ", 0, "   -   ", "1-1-1", 0);
        this.genre = "  -  ";
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String toString () {
        return getName() + ", Price: " + getPrice()  + "$, Company: " + getCompany() + ", Release Date: " + getDate() + ", Genre: " + genre;
    }
}

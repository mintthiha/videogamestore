package progroupproject.Products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import progroupproject.Files.FileImporter;

/**
 * This class manages products including games and consoles. Performs various operations like retrieval, conversion, filtering, and sorting.
 */
public class ProductController {
    public static final String COLOR = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    private List<Product> games;
    private List<Product> consoles;

    /**
    * Initializes ProductController by retrieving game and console data from files.
    */
    public ProductController() {
        this.games = retrieveAllGames("./proggroupproject01/src/main/java/progroupproject/Data/Game.txt");
        this.consoles = retrieveAllConsoles("./proggroupproject01/src/main/java/progroupproject/Data/Console.txt");
    }

    public List<Product> getAllGames(){
        return this.games;
    }

    public List<Product> getAllConsoles(){
        return this.consoles;
    }

    /**
     * Convert a list of products to a list of game.
     * @param products list of products
     * @return
     */
    public List<Game> convertToGame (List<Product> products) {
        List<Game> list = new ArrayList<>();
        for (Product product : products){
            list.add((Game) product);
        }
        return list;
    }

    /**
     * Convert a list of products to a list of console.
     * @param products list of products
     * @return
     */
    public List<Console> convertToConsole (List<Product> products) {
        List<Console> list = new ArrayList<>();
        for (Product product : products){
            list.add((Console) product);
        }
        return list;
    }

    /**
     * This method gets all games from the file
     * @param path the path to the game file
     * @return the list of games as products type
     */
    private List<Product> retrieveAllGames (String path) {
        List<Product> products = new ArrayList<>();
        List<String> lines = FileImporter.getListData(path);
        for (String line : lines) {
            String[] pieces = line.split(",");
            products.add(new Game(Integer.parseInt(pieces[0]), pieces[1], Double.parseDouble(pieces[2]), pieces[3], pieces[4], Integer.parseInt(pieces[5]),pieces[6]));
        }
        return products;
    }

    /**
     * This method gets all consoles from the file
     * @param path the path to the console file
     * @return the list of consoles as products type
     */
    private List<Product> retrieveAllConsoles (String path) {
        List<Product> products = new ArrayList<>();
        List<String> lines = FileImporter.getListData(path);
        for (String line : lines) {
            String[] pieces = line.split(",");
            String[] games = pieces[6].split(";");
            products.add(new Console(Integer.parseInt(pieces[0]), pieces[1], Double.parseDouble(pieces[2]), pieces[3], pieces[4], Integer.parseInt(pieces[5]), Arrays.asList(games)));
        }        
        return products;
    }

    /**
     * This method get all the games and convert to a string.
     * @return A list of Strings of games.
     */
    public List<String> getGamesString () {
        List<String> newList = new ArrayList<>();
        for (Game game : convertToGame(this.games)) {
            newList.add(game.getName() + ", Price: " + game.getPrice() + ", Company: " + game.getCompany() + ", Genre: " + game.getGenre());
        }
        return newList;
    }

    /**
     * This method get the product names that have stock, so more than 0.
     * @param products List of products
     * @return Return A list of string of names.
     */
    public List<String> getProductStockOnly (List<Product> products) {
        List<String> names = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() > 0) {
                names.add(product.getName());
            }
        }
        return names;
    }

    //VIEW

    /**
     * This method returns a list of names.
     * @param products List of products
     * @return A list of String of names.
     */
    public List<String> getAllNames(List<Product> products){
        List<String> names = new ArrayList<>();
        for (Product product: products){
            names.add(product.getName());
        }
        return names;
    }

    //The rest of the methods are the same, with their own fields.

    public List<String> getAllPrices(List<Product> products){
        List<String> names = new ArrayList<>();
        for (Product product: products){
            names.add(product.getPrice() + "$");
        }
        return names;
    }

    public List<String> getAllCompanies(List<Product> products){
        List<String> names = new ArrayList<>();
        for (Product product: products){
            names.add(product.getCompany());
        }
        return names;
    }

    public List<String> getAllQuantities(List<Product> products){
        List<String> names = new ArrayList<>();
        for (Product product: products){
            names.add(product.getQuantity() + "      ");
        }
        return names;
    }

    /**
     * This method is only for games since a console don't have a genre.
     * @param games List of games.
     * @return A list of genre
     */
    public List<String> getAllGenres(List<Game> games){
        Set<String> genres = new HashSet<>();
        for (Game game : games){
            genres.add(game.getGenre());
        }
        List<String> list = new ArrayList<>(genres);
        return list;
    }

    /**
     * This method is only for consoles since a game don't have a library.
     * @param consoles List of consoles.
     * @return A list of libraries
     */
    public List<String> getAllLibrary (List<Console> consoles) {
        List<String> names = new ArrayList<>();
        for (Console console : consoles){
            names.add(console.convertLibraryToString());
        }
        return names;
    }

    /**
     * This method adds a game to the list of games.
     * @param game A game
     */
    public void addGame (Game game) {
        games.add(game);
    };

    /**
     * This method adds a console to the list of consoles.
     * @param console A console
     */
    public void addConsole (Console console) {
        consoles.add(console);
    };  

    /**
     * This method gets the biggest word and gives the max 
     * @param names
     * @return
     */
    public int getMaxLength (List<String> names) {
        String max = "";
        for (String name : names) {
            if (name.length() > max.length()) {
                max = name;
            }
        }
        return max.length();
    }
    
    /**
     * Generates an indented string based on the maximum length and target string.
     * If the target string's length is less than the maximum length, it appends spaces to the target string until it reaches the maximum length.
     * @param maxLength The maximum length for the indentation.
     * @param target The string to be indented.
     * @return Indented string appended with a vertical bar.
     */
    public String getIndent (int maxLength, String target) {
        while (target.length() <= maxLength) {
            target += " ";
        }
        target += "| ";
        return target;
    }

    /**
     * Retrieves a list of formatted strings representing game data based on the provided order.
     * This method is used to get all methods to print all the game data.
     * @param products The list of products to extract game data from.
     * @param order The order by which the game data should be arranged.
     * @return A list of formatted strings representing game data.
     */
    public List<String> getGameData(List<Product> products, String order) {
        List<String> list = new ArrayList<>();
        products = new ArrayList<>(products);
        products.add(new Game());
        List<Game> listOfGames = convertToGame(products);
        String name = getIndent(getMaxLength(getAllNames(products))+9, COLOR + "GAME NAME" + RESET);
        String price = getIndent(getMaxLength(getAllPrices(products))+9, COLOR + "PRICE" + RESET);
        String genre = getIndent(getMaxLength(getAllGenres(listOfGames))+9, COLOR + "GENRE" + RESET);
        String company = getIndent(getMaxLength(getAllCompanies(products))+9, COLOR + "COMPANY" + RESET);
        String quantity = getIndent(getMaxLength(getAllQuantities(products))+9, COLOR + "QUANTITY" + RESET);
        list = decideGameOrder(order, name, price, genre, company, quantity, list);

        for (Game game : listOfGames) {
            name = getIndent(getMaxLength(getAllNames(products)), game.getName());
            price = getIndent(getMaxLength(getAllPrices(products)), game.getPrice() + "$");
            genre = getIndent(getMaxLength(getAllGenres(listOfGames)), game.getGenre());
            company = getIndent(getMaxLength(getAllCompanies(products)), game.getCompany());
            quantity = getIndent(getMaxLength(getAllQuantities(products)), game.getQuantity() + "");
            list = decideGameOrder(order, name, price, genre, company, quantity, list);
        }
        products.remove(products.size() - 1);
        list.remove(list.size() - 1);
        return list;
    }
    
    /**
     * Decides the order of game data arrangement based on the specified order parameter.
     * Adds formatted game data strings to the list based on the provided order.
     * @param order The order by which the game data should be arranged.
     * @param name The formatted string representing game names.
     * @param price The formatted string representing game prices.
     * @param genre The formatted string representing game genres.
     * @param company The formatted string representing game companies.
     * @param quantity The formatted string representing game quantities.
     * @param list The list to which formatted game data strings are added.
     * @return The updated list containing formatted game data strings as per the order.
     */
    private List<String> decideGameOrder (String order, String name, String price, String genre, String company, String quantity, List<String> list) {
        switch (order) {
                case "name": 
                    list.add(name + price + genre + company);
                    break;
                case "price": 
                    list.add(price + name + genre + company);
                    break;
                case "company": 
                    list.add(company + name + price + genre);
                    break;
                case "genre": 
                    list.add(genre + name + price + company);
                    break;
                case "quantity" :
                    list.add(name + price + genre + company + quantity);
                break;
            }
        return list;
    }

    //Does the same as getGameData but for consoles
    public List<String> getConsoleData (List<Product> products, String order){
        List<String> list = new ArrayList<>();
        products = new ArrayList<>(products);
        products.add(new Console(0, "CONSOLE NAME", 0.0, "COMPANY","-",  0, Arrays.asList(new String[] {"LIBRARY"})));
        List<Console> listOfConsoles = convertToConsole(products);
        String name = getIndent(getMaxLength(getAllNames(products))+9, COLOR + "CONSOLE NAME" + RESET);
        String price = getIndent(getMaxLength(getAllPrices(products))+9, COLOR + "PRICE" + RESET);
        String library = getIndent(getMaxLength(getAllLibrary(listOfConsoles))+9, COLOR + "LIBRARY" + RESET);
        String company = getIndent(getMaxLength(getAllCompanies(products))+9, COLOR + "COMPANY" + RESET);
        String quantity = getIndent(getMaxLength(getAllQuantities(products))+9, COLOR + "QUANTITY" + RESET);
        list = decideConsoleOrder(order, name, price, library, company, quantity, list);

        for (Console console : listOfConsoles){
            name = getIndent(getMaxLength(getAllNames(products)),  console.getName());
            price = getIndent(getMaxLength(getAllPrices(products)), console.getPrice() + "$");
            library = getIndent(getMaxLength(getAllLibrary(listOfConsoles)), console.convertLibraryToString());
            company = getIndent(getMaxLength(getAllCompanies(products)), console.getCompany());
            quantity = getIndent(getMaxLength(getAllQuantities(products)), console.getQuantity() + "");
            list = decideConsoleOrder(order, name, price, library, company, quantity, list);
        }
        products.remove(products.size()-1);
        list.remove(list.size()-1);
        return list;
    }

    //The same as decideGameOrder but for consoles
    private List<String> decideConsoleOrder (String order, String name, String price, String library, String company, String quantity, List<String> list) {
        switch (order) {
                case "name": 
                    list.add(name + price + company + library);
                    break;
                case "price": 
                    list.add(price + name + company + library);
                    break;
                case "company": 
                    list.add(company + name + price + library);
                    break;
                case "library": 
                    list.add(library + name + price + company);
                    break;
                case "quantity" :
                    list.add(name + price  + company + quantity + library);
                break;
            }
        return list;
    }

    //Get the id of the last product in the list
    public int getLastId (List<Product> products) {
        int lastId = products.get(products.size()-1).getId();
        return lastId;
    }
    
    // SORT 
    
    /**
     * Sorts the list of products by name in ascending order.
     * @param products The list of products to be sorted.
     */
    public void sortByName(List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            /**
             * Compares two products based on their names in a case-insensitive manner.
             * @param product1 The first product to compare.
             * @param product2 The second product to compare.
             * @return A negative integer, zero, or a positive integer as the first product name
             *         is less than, equal to, or greater than the second product name.
             */
            @Override
            public int compare(Product product1, Product product2) {
                return product1.getName().toLowerCase().compareTo(product2.getName().toLowerCase());
            }
        });
    }

    //Does the same but for companies
    public void sortByCompany (List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product1.getCompany().toLowerCase().compareTo(product2.getCompany().toLowerCase());
            }
        });
    }

    //Does the same but for prices
    public void sortByPrice (List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return Double.compare(product1.getPrice(), product2.getPrice());
            }
        });
    }
    
    //Does the same but for quantity
    public void sortByQuantity (List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return Double.compare(product1.getQuantity(), product2.getQuantity());
            }
        });
    }

    //Same thing but for Games, since consoles dont have genres.
    public void sortGamesByGenre (List<Game> games) {
        Collections.sort(this.games, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                Game game1 = (Game) product1;
                Game game2 = (Game) product2;
                return game1.getGenre().toLowerCase().compareTo(game2.getGenre().toLowerCase());
            }
        });
    }
    
    //Same thing but for Consoles, since games dont have libraries.
    public void sortConsolesByLibrary (List<Console> consoles) {
        Collections.sort(this.consoles, new Comparator<Product>() {
            @Override
            public int compare (Product product1, Product product2) {
                Console console1 = (Console) product1;
                Console console2 = (Console) product2;
                return console1.convertLibraryToString().toLowerCase().compareTo(console2.convertLibraryToString().toLowerCase());
            }
        });
    }

    //FILTER
    
    /**
     * Filters a product from the list based on the provided name (case-insensitive).
     * 
     * @param name     The name used for filtering products.
     * @param products The list of products to be searched.
     * @return The first product found with a name matching the provided name (ignoring case), 
     *         or null if no match is found.
     */
    public Product filterByName(String name, List<Product> products) {
        Product filteredProduct = null;
        for (Product product : products) {
            if (name.equalsIgnoreCase(product.getName())) {
                filteredProduct = product;
                break; // Exit loop after finding the first match
            }
        }
        return filteredProduct;
    }

    //Does the same but for company
    public List<Product> filterProductByCompany (String company, List<Product> products){
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products){
            if (company.equalsIgnoreCase(product.getCompany())){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    //Does the same but for price
    public List<Product> filterProductByPrice (double min, double max, List<Product> products){
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products){
            if (product.getPrice() >= min && product.getPrice() <= max){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    //Does the same but for quantity
    public List<Product> filterProductByQuantity (int quantity, List<Product> products){
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products){
            if (quantity == product.getQuantity()){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    //Only for games, since consoles dont have genre.
    public List<Product> filterGameByGenre (String genre, List<Game> games){
        List<Product> filteredGames = new ArrayList<>();
        for (Game game : games){
            if (genre.equalsIgnoreCase(game.getGenre())){
                filteredGames.add(game);
            }
        }
        return filteredGames;
    }

    //Only for consoles, since games dont have library
    public List<Product> filterConsoleByLibrary(String choosenGame, List<Console> consoles){
        List<Product> filteredConsoles = new ArrayList<>();
        for (Console console : consoles){
            List<String> library = console.getLibrary();
            for(String game : library){
                if(choosenGame.equalsIgnoreCase(game)){
                    filteredConsoles.add(console);
                }
            }
        }
        return filteredConsoles;
    };

    /**
     * Retrieves a list of games within the specified price range.
     * 
     * @param min The minimum price of games to be included (inclusive).
     * @param max The maximum price of games to be included (inclusive).
     * @return A list of games that fall within the provided price range.
     */
    public List<Product> getGamesByPriceRange(double min, double max) {
        List<Product> filteredGames = new ArrayList<>();
        for (Product game : this.games) {
            if (game.getPrice() >= min && game.getPrice() <= max) {
                filteredGames.add(game);
            }
        }
        return filteredGames;
    }

    //Does the same but for consoles.
    public List<Product> getConsolesByPriceRange(double min, double max){
        List<Product> filteredConsoles = new ArrayList<>();
        for (Product console : this.consoles){
            if (console.getPrice() >= min && console.getPrice() <= max) {
                filteredConsoles.add(console);
            }
        }
        return filteredConsoles;
    }
}

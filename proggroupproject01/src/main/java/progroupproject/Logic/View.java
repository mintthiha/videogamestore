package progroupproject.Logic;

import java.util.Arrays;
import java.util.List;

import progroupproject.Products.Console;
import progroupproject.Products.Game;
import progroupproject.Products.Product;
import progroupproject.Products.ProductController;
import progroupproject.UI.Displayer;
import progroupproject.UI.Scan;


/**
 * This class handles logic related to viewing and managing games and consoles.
 * Sorting and filtering games and consoles.
 */
public class View {
    public static final String COLOR = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    /**
     * This method manages logic for viewing games, including sorting and filtering.
     *
     * @param products The product controller object managing game data
     */
    public static void getViewGamesLogic(ProductController products){
        String[] viewOptions = new String[] {"Sort", "Filter", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " VIEW GAMES BY:" + RESET);
        Displayer.displayOptions(Arrays.asList(viewOptions));
        System.out.print("Enter View Game Option: ");
        String viewOption = Scan.getValidString(Arrays.asList(viewOptions));
        Scan.clear(); //Checks if the user wants to sort, filter or none for games.
        switch (viewOption) {
            case "go back" : 
                // Automatically sends back the user
            break;

            case "sort" :
                sortGameLogic(products);
            break;

            case "filter" : 
                filterGameLogic(products);
            break;
        }

    }
    
    /**
     * Manages logic for viewing consoles, including sorting and filtering.
     *
     * @param products The product controller object managing console data
     */
    public static void getViewConsolesLogic (ProductController products) {
        String[] viewOptions = new String[] {"Sort", "Filter", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " CONSOLES:" + RESET);
        Displayer.displayOptions(Arrays.asList(viewOptions));
        System.out.print("Enter view option: ");
        String viewOption = Scan.getValidString(Arrays.asList(viewOptions));
        Scan.clear(); //The same as games but for consoles.

        switch (viewOption) {
            case "go back" : 
                // Automatically sends back the user
            break;

            case "sort" :
                sortConsolesLogic(products);
            break;

            case "filter" : 
                filterConsolesLogic(products);
            break;
        }
    }

    /**
     * This method handles sorting logic for games based on different criteria.
     *
     * @param products The product controller object managing game data
     */
    public static void sortGameLogic (ProductController products) {
        String[] sortOptions = new String[] {"Name", "Price", "Company", "Genre", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " SORT GAMES BY:" + RESET);
        Displayer.displayOptions(Arrays.asList(sortOptions));
        System.out.print("Enter your sort option: ");
        String sortOption = Scan.getValidString(Arrays.asList(sortOptions));
        Scan.clear(); //User chooses the sort option.

        List<Product> gameProducts = products.getAllGames();
        List<Game> games = products.convertToGame(gameProducts); //Converts list of games if needed.

            switch (sortOption) {
            case "name": //Sorts by name.
                Scan.clear();
                products.sortByName(gameProducts);
                games = products.convertToGame(gameProducts);
                Displayer.displayString(products.getGameData(gameProducts, sortOption.toLowerCase())); //Displays based on the order option.
                Scan.enter();
            break;
            case "price": //Price.
                Scan.clear();
                products.sortByPrice(gameProducts);
                games = products.convertToGame(gameProducts);
                Displayer.displayString(products.getGameData(gameProducts, sortOption.toLowerCase())); //Displays based on the order option.
                Scan.enter();
            break;
            case "company": //Company.
                Scan.clear();
                products.sortByCompany(gameProducts);
                games = products.convertToGame(gameProducts);
                Displayer.displayString(products.getGameData(gameProducts, sortOption.toLowerCase())); //Displays based on the order option.
                Scan.enter();
            break;
            case "genre": //Genre.
                Scan.clear();
                products.sortGamesByGenre(games);
                Displayer.displayString(products.getGameData(gameProducts, sortOption.toLowerCase())); //Displays based on the order option.
                Scan.enter();
            break;
        }
    }

    /**
     * This method handles filtering logic for games based on different criteria.
     *
     * @param products The product controller object managing game data
     */
    public static void filterGameLogic (ProductController products) {
        String[] filterOptions = new String[] {"Name", "Price", "Company", "Genre", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " FILTER GAMES BY:" + RESET);
        Displayer.displayOptions(Arrays.asList(filterOptions));
        System.out.print("Enter your filter option: ");
        String filterOption = Scan.getValidString(Arrays.asList(filterOptions));
        Scan.clear(); //Get user filter option choice.

        List<Product> gameProducts = products.getAllGames();
        List<Game> games = products.convertToGame(gameProducts); //Convert to List of games if needed.

        switch (filterOption) {
            case "name": //Filters by name.
                List<String> gameNames = products.getAllNames(products.getAllGames());
                System.out.println(COLOR + "OP:  "  + RESET + "|" + COLOR +  " GAME:" + RESET);
                Displayer.displayOptions(gameNames);
                System.out.print("Choose the game name you would like to filter with: ");
                String choosenName = Scan.getValidString(gameNames);
                Scan.clear();

                gameProducts = Arrays.asList(products.filterByName(choosenName, products.getAllGames()));
                games = products.convertToGame(gameProducts);
            break;
            case "price": //Price
                System.out.print("Enter the min price: ");
                double min = Scan.getValidNumber(0, 100);
                System.out.print("Enter max price: ");
                double max = Scan.getValidNumber(min, 10000);
                Scan.clear();

                gameProducts  = products.filterProductByPrice(min, max, gameProducts);
                games = products.convertToGame(gameProducts);
            break;
            case "company": //Company
                List<String> gameCompanies = products.getAllCompanies(products.getAllGames());
                System.out.println(COLOR + "OP:  "  + RESET + "|" + COLOR +  " COMPANY:" + RESET);
                Displayer.displayOptions(gameCompanies);
                System.out.print("Choose a game company you would like to filter with: ");
                String choosenCompany = Scan.getValidString(gameCompanies);
                Scan.clear();

                gameProducts = products.filterProductByCompany(choosenCompany, products.getAllGames());
                games = products.convertToGame(gameProducts);
            break;
            case "genre": //Genre
                List<String> gameGenres = products.getAllGenres(games);
                System.out.println(COLOR + "OP:  "  + RESET + "|" + COLOR +  " GENRE:" + RESET);
                Displayer.displayOptions(gameGenres);
                System.out.println("Choose a game genre you would like to filter with: ");
                String chosenGenre = Scan.getValidString(gameGenres);
                Scan.clear();

                gameProducts = products.filterGameByGenre(chosenGenre, games);
                games = products.convertToGame(gameProducts);
            break;
        }

        Displayer.displayString(products.getGameData(gameProducts, "name")); //Displays the filtered games.
        Scan.enter();
    }
  
    /**
     * Handles sorting logic for consoles based on different criteria.
     *
     * @param products The product controller object managing console data
     */
    public static void sortConsolesLogic (ProductController products) {
        String[] sortOptions = new String[] {"Name", "Price", "Company", "Library", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " SORT CONSOLES BY:" + RESET);
        Displayer.displayOptions(Arrays.asList(sortOptions));
        System.out.print("Enter your sort option: ");
        String sortOption = Scan.getValidString(Arrays.asList(sortOptions));
        Scan.clear(); //Sort consoles, same concept as games.

        List<Product> consoleProducts = products.getAllConsoles();
        List<Console> consoles = products.convertToConsole(consoleProducts);

            switch (sortOption) {
            case "name":
                Scan.clear();
                products.sortByName(consoleProducts);
                consoles = products.convertToConsole(consoleProducts);
                Displayer.displayString(products.getConsoleData(consoleProducts, "name"));
                Scan.enter();
            break;
            case "price":
                Scan.clear();
                products.sortByPrice(consoleProducts);
                consoles = products.convertToConsole(consoleProducts);
                Displayer.displayString(products.getConsoleData(consoleProducts, "price"));
                Scan.enter();
            break;
            case "company":
                Scan.clear();
                products.sortByCompany(consoleProducts);
                consoles = products.convertToConsole(consoleProducts);
                Displayer.displayString(products.getConsoleData(consoleProducts, "company"));
                Scan.enter();
            break;
            case "library":
                Scan.clear();
                products.sortConsolesByLibrary(consoles);
                Displayer.displayString(products.getConsoleData(consoleProducts, "library"));
                Scan.enter();
            break;
        }
    }

    /**
     * This method handles filtering logic for consoles based on different criteria.
     *
     * @param products The product controller object managing console data
     */
    public static void filterConsolesLogic (ProductController products) {
        String[] filterOptions = new String[] {"Name", "Price", "Company", "Game", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " FILTER CONSOLES BY:" + RESET);
        Displayer.displayOptions(Arrays.asList(filterOptions));
        System.out.print("Enter your filter option: ");
        String filterOption = Scan.getValidString(Arrays.asList(filterOptions));
        Scan.clear(); //Sort consoles, same concept as games.

        List<Product> consoleProducts = products.getAllConsoles();
        List<Console> consoles = products.convertToConsole(consoleProducts);

        switch (filterOption) {
            case "name":
                List<String> consoleNames = products.getAllNames(products.getAllConsoles());
                System.out.println(COLOR + "OP:  "  + RESET + "|" + COLOR +  " CONSOLE:" + RESET);
                Displayer.displayOptions(consoleNames);
                System.out.print("Choose the console name you would like to filter with: ");
                String choosenName = Scan.getValidString(consoleNames);
                Scan.clear();

                consoleProducts = Arrays.asList(products.filterByName(choosenName, products.getAllConsoles()));
                consoles = products.convertToConsole(consoleProducts);
            break;
            case "price":
                System.out.print("Enter the min price: ");
                double min = Scan.getValidNumber(0, 100);
                System.out.print("Enter max price: ");
                double max = Scan.getValidNumber(min, 10000);
                Scan.clear();

                consoleProducts  = products.filterProductByPrice(min, max, consoleProducts);
                consoles = products.convertToConsole(consoleProducts);
            break;
            case "company":
                List<String> consoleCompanies = products.getAllCompanies(products.getAllConsoles());
                System.out.println(COLOR + "OP:  "  + RESET + "|" + COLOR +  " COMPANY:" + RESET);
                Displayer.displayOptions(consoleCompanies);
                System.out.print("Choose a console company you would like to filter with: ");
                String choosenCompany = Scan.getValidString(consoleCompanies);
                Scan.clear();

                consoleProducts = products.filterProductByCompany(choosenCompany, products.getAllConsoles());
                consoles = products.convertToConsole(consoleProducts);
            break;
            case "game":
                List<String> gameNames = products.getAllNames(products.getAllGames());
                System.out.println(COLOR + "OP:  "  + RESET + "|" + COLOR +  " GAME:" + RESET);
                Displayer.displayOptions(gameNames);
                System.out.println("Choose a game you would like to filter with: ");
                String chosenGame = Scan.getValidString(gameNames);
                Scan.clear();

                consoleProducts = products.filterConsoleByLibrary(chosenGame, consoles);
                consoles = products.convertToConsole(consoleProducts);
            break;
        }

        Displayer.displayString(products.getConsoleData(consoleProducts, "name")); 
        Scan.enter();
    }
}

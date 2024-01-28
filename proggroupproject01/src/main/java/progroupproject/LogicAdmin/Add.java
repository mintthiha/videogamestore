package progroupproject.LogicAdmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import progroupproject.Products.*;
import progroupproject.UI.*;
import progroupproject.Users.*;

/**
 * This class handles logic related to adding games, consoles, and users.
 */
public class Add {
    public static final String COLOR = "\u001B[32m";
    public static final String COLORRED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    
    /**
     * This method adds a new game to the product controller.
     *
     * @param products The product controller managing game data
     */
    public static void addGame (ProductController products) {
        List<Product> gameProducts = products.getAllGames();
        Game emptyGame = new Game(products.getLastId(gameProducts), COLOR, 0, RESET, COLORRED, 0, COLOR);
        Product productGame = emptyGame;

        String[] data = Scan.getProductData(products, gameProducts);
        // Set name, price company, quantity
        emptyGame.setName(data[0]);
        emptyGame.setPrice(Double.parseDouble(data[1]));
        emptyGame.setCompany(data[2]);
        emptyGame.setQuantity(Integer.parseInt(data[3]));
        // Ask for genre
        System.out.print("Enter the genre name: ");
        String newGenre = Scan.getStringLine();
        emptyGame.setGenre(newGenre);
        Scan.clear();
        // Display summary
        Displayer.displayString(products.getGameData(Arrays.asList(productGame), "quantity"));
        confirmGameCreation(products, productGame, emptyGame);
    }

    /**
     * This method adds a new console to the product controller.
     *
     * @param products The product controller managing console data
     */
    public static void addConsole (ProductController products) {
        List<Product> consoleProducts = products.getAllConsoles();
        Product consoleProduct = new Console(products.getLastId(consoleProducts), "EMPTY", 0, "EMPTY", "EMPTY", 0, new ArrayList<String>());
        Console console = (Console) consoleProduct;

        String[] data = Scan.getProductData(products, consoleProducts);
        console.setName(data[0]);
        console.setPrice(Double.parseDouble(data[1]));
        console.setCompany(data[2]);
        console.setQuantity(Integer.parseInt(data[3]));

        // Ask for library
        List<String> newConsoleLibrary = Scan.getConsoleLibrary();
        console.setLibrary(newConsoleLibrary);
        Scan.clear();
        // Display summary
        Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), "quantity"));
        confirmConsoleCreation (products, consoleProduct, console);
    } 

    /**
     * This method confirms the creation of a game or console based on user input.
     *
     * @param products      The product controller managing product data
     * @param product       The product object to be added
     * @param gameOrConsole The specific game or console object being added
     */
    private static void confirmGameCreation (ProductController products, Product gameProduct, Game game) {
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " FINAL DECISION:" + RESET);
        String[] gameConfrimOptions = new String[] {"Yes", "No"};
        Displayer.displayOptions(Arrays.asList(gameConfrimOptions));
        System.out.print("Are you sure you want to add this product: ");
        String gameConfirm = Scan.getValidString(Arrays.asList(gameConfrimOptions));
        Scan.clear(); //Confirms if the user wants to add the console.

        switch (gameConfirm) {
            case "yes":
                products.addGame(game);
                Displayer.displayString(products.getGameData(products.getAllGames(), "quantity"));
                System.out.println(COLOR + "YOU HAVE SUCCESFULLY CREATED THE GAME!\n" + RESET);
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                Scan.enter();
                break;
            case "no":
                // Sends user back in loop
                break;
        }
    }

    /**
     * This method confirms the creation of a console based on user input.
     *
     * @param products      The product controller managing product data
     * @param product       The console object to be added
     * @param gameOrConsole The specific console object being added
     */
    private static void confirmConsoleCreation (ProductController products, Product consoleProduct, Console console) {
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " FINAL DECISION:" + RESET);
        String[] consoleConfrimOptions = new String[] {"Yes", "No"};
        Displayer.displayOptions(Arrays.asList(consoleConfrimOptions));
        System.out.print("Are you sure you want to add this product: ");
        String consoleConfirm = Scan.getValidString(Arrays.asList(consoleConfrimOptions));
        Scan.clear(); //Same as confirmGameCreation.

        switch (consoleConfirm) {
            case "yes":
                products.addConsole(console);
                Displayer.displayString(products.getConsoleData(products.getAllConsoles(), "quantity"));
                System.out.println(COLOR + "YOU HAVE SUCCESFULLY CREATED THE CONSOLE!\n" + RESET);
                Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), "quantity"));
                Scan.enter();
                break;
            case "no":
                // Sends user back in loop
                break;
        }
    }

    /**
     * This method creates a new user (either member or admin) based on the specified user type.
     *
     * @param users    The user controller managing user data
     * @param userType The type of user to create (member or admin)
     */
    public static void createUser (UserController users, String userType) {
        System.out.println("Enter your first name: ");
        String firstName = Scan.getString();

        System.out.println("Enter your last name: ");
        String lastName = Scan.getString();

        List<String> emails = users.getTypeEmails(userType);
        System.out.println("Enter your email: ");
        String email = Scan.getString();
        email = Scan.validateEmail(email, emails);
        Scan.clear();

        switch (userType) { //Depending on what the user wants to create, the program willl call the appropriate method.
            case "member":
                createMember(users, firstName, lastName, email);
            break;
            case "admin" :
                createAdmin(users, firstName, lastName, email);
            break;
        }
    } 

    /**
     * This method creates a new member with the provided details.
     *
     * @param users     The user controller managing user data
     * @param firstName First name of the member
     * @param lastName  Last name of the member
     * @param email     Email of the member
     */
    private static void createMember (UserController users, String firstName, String lastName, String email) {
        String memberId = "MEMBER" + (users.getLastMemberId() + 1);
                List<Coupon> initalCoupons =new ArrayList<>();
                for (int i = 0; i < 3; i++) { //A member get 3 free coupons they can use upon creating an account.
                    int randomNum = Scan.getRandomInt(users.getCoupons().size());
                    initalCoupons.add(users.getCouponById(randomNum));
                }
                Profile memberProfile = new Member(users.getLastMemberId() + 1, firstName, lastName, email, memberId, 0, 1000, initalCoupons);
                Member member = (Member) memberProfile;
                users.addMember(member);
                Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                System.out.println(COLOR + "BALANCE ADDED: " + RESET + "1000$");
                System.out.println(COLOR + "COUPONS ADDED: " + RESET + member.getCouponsString(initalCoupons));
                System.out.println(COLOR + "\nYOU HAVE SUCCESFULLY CREATED THE MEMBER!" + RESET);
                Scan.enter(); //The program will then display their balance, coupons.
    }

    /**
     * This method creates a new admin with the provided details.
     *
     * @param users     The user controller managing user data
     * @param firstName First name of the admin
     * @param lastName  Last name of the admin
     * @param email     Email of the admin
     */
    private static void createAdmin (UserController users, String firstName, String lastName, String email) {
        List<String> usernames = users.getAllUsernames();
        System.out.print("Enter your username: ");
        String username = Scan.getString();
        username = Scan.validateUsername(username, usernames);
        System.out.print("Enter your password: ");
        String password = Scan.getString();
        Scan.clear(); //The program gets a username and password for the next time they log in.

        Profile adminProfile = new Admin(users.getLastAdminId()+1, firstName, lastName, email, username, password);
        Admin admin = (Admin) adminProfile;
        users.addAdmin(admin);
        
        Displayer.displayString(users.getAdminData(Arrays.asList(adminProfile), "firstName"));
        System.out.println(COLOR + "YOU HAVE SUCCESFULLY CREATED THE ADMIN!" + RESET);
        Scan.enter(); //Display they have successfully added an admin to.
    }

}

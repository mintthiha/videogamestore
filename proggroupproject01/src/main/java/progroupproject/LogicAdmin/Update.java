package progroupproject.LogicAdmin;

import java.util.Arrays;
import java.util.List;

import progroupproject.Products.Game;
import progroupproject.Products.Product;
import progroupproject.Products.ProductController;
import progroupproject.UI.Displayer;
import progroupproject.UI.Scan;
import progroupproject.Users.*;

/**
 * The Update class contains methods to handle updating game, console, admin, and member details.
 */
public class Update {

    public static final String COLOR = "\u001B[32m";
    public static final String COLORRED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    
    /**
     * This class updates the details of a game.
     *
     * @param products The product controller managing the games.
     */
    public static void updateGame (ProductController products) {
        List<String> gamesString = products.getGameData(products.getAllGames(), "name");
        List<String> gameNames = products.getAllNames(products.getAllGames());
        Displayer.displayOptions(gamesString, 1);
        System.out.print("Enter the game you want to update: ");
        String gameName = Scan.getValidString(gameNames);
        Scan.clear(); //Asks user what game they want to update

        Displayer.displayString(products.getGameData(Arrays.asList(products.filterByName(gameName, products.getAllGames())), "quantity"));
        String[] gameOptions = new String[] {"Name", "Price", "Genre", "Company", "Quantity", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " UPDATE:" + RESET);
        Displayer.displayOptions(Arrays.asList(gameOptions));
        System.out.print("What aspect to update: ");
        String aspect = Scan.getValidString(Arrays.asList(gameOptions));
        Scan.clear(); //Asks user what aspect of the game they want to update. 

        Product gameProduct = products.filterByName(gameName, products.getAllGames()); 

        switch (aspect) {
            case "name": //If the user wants to update the name.
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                System.out.print("Enter new name: ");
                String newName = Scan.getString();
                newName = Scan.validateProductName(newName, products, products.getAllGames());
                Scan.clear(); //Get new name

                gameProduct.setName(newName);
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                System.out.println(COLOR + "YOU HAVE UPDATED THE GAME NAME!" + RESET);
                Scan.enter(); //Update name of the game.
                break;
            case "price" : //Does the same for price.
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                System.out.print("Enter new price: ");
                double newPrice = Scan.getValidNumber(0, 10000);
                Scan.clear();

                gameProduct.setPrice(newPrice);
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                System.out.println(COLOR + "YOU HAVE UPDATED THE GAME PRICE!" + RESET);
                Scan.enter();
            break;
            case "company" : //Does the same for company.
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                System.out.print("Enter new company name: ");
                String newCompany = Scan.getString();
                Scan.clear();

                gameProduct.setCompany(newCompany);
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                System.out.println(COLOR + "YOU HAVE UPDATED THE COMPANY NAME!" + RESET);
                Scan.enter();
            break;
            case "quantity" : //Does the same for quantity.
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                System.out.print("Enter new quantity: ");
                int newGameQuantity = (int) Scan.getValidNumber(0, 10000);
                Scan.clear();

                gameProduct.setQuantity(newGameQuantity);
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                System.out.println(COLOR + "YOU HAVE UPDATED THE QUANTITY!" + RESET);
                Scan.enter();
            break;
            case "genre" : //Does the same for genre.
                Game game = (Game) gameProduct;
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                System.out.print("Enter new company genre: ");
                String newGenre = Scan.getString();
                Scan.clear();

                game.setGenre(newGenre);
                Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "quantity"));
                System.out.println(COLOR + "YOU HAVE UPDATED THE GENRE!" + RESET);
                Scan.enter();
            break;

        }
    }
    
    /**
     * This method updates the details of a member user.
     *
     * @param users The user controller managing member users.
     */
    public static void updateConsole (ProductController products) {
        List<String> consolesString = products.getConsoleData(products.getAllConsoles(), "quantity");
        List<String> consoleNames = products.getAllNames(products.getAllConsoles());
        Displayer.displayOptions(consolesString, 1);
        System.out.print("Enter the console you want to update: ");
        String consoleName = Scan.getValidString(consoleNames);
        Scan.clear(); //User choose what console to update.

        Displayer.displayString(products.getConsoleData(Arrays.asList(products.filterByName(consoleName, products.getAllConsoles())), "quantity"));
        String[] consoleOptions = new String[] {"Name", "Price", "Company", "Quantity", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " UPDATE:" + RESET);
        Displayer.displayOptions(Arrays.asList(consoleOptions));
        System.out.print("What aspect to update: ");
        String consoleAspect = Scan.getValidString(Arrays.asList(consoleOptions));
        Scan.clear(); //Get which part to update.

        Product consoleProduct = products.filterByName(consoleName, products.getAllConsoles());

        switch (consoleAspect) {
             case "name": //Updates name.
                Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), ""));
                System.out.print("Enter new name: ");
                String newName = Scan.getString();
                newName = Scan.validateProductName(newName, products, products.getAllConsoles());
                Scan.clear();

                consoleProduct.setName(newName);
                Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), "quantity"));
                System.out.println(COLOR + "YOU HAVE UPDATED THE CONSOLE NAME!" + RESET);
                Scan.enter();
                break;
            case "price" : //Updates price.
                Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), "quantity"));
                System.out.print("Enter new price: ");
                double newPrice = Scan.getValidNumber(0, 10000);
                Scan.clear();

                consoleProduct.setPrice(newPrice);
                Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), "quantity"));
                System.out.println(COLOR + "YOU HAVE UPDATED THE CONSOLE PRICE!" + RESET);
                Scan.enter();
            break;
            case "company" : //Updates company name.
                Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), "quantity"));
                System.out.print("Enter new company name: ");
                String newCompany = Scan.getString();
                Scan.clear();

                consoleProduct.setCompany(newCompany);
                Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), "quantity"));
                System.out.println(COLOR + "YOU HAVE UPDATED THE COMPANY NAME!" + RESET);
                Scan.enter();
            break;
            case "quantity" : //Updates quantity.
                Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), "quantity"));
                System.out.print("Enter new quantity: ");
                int newConsoleQuantity = (int) Scan.getValidNumber(0, 10000);
                Scan.clear();

                consoleProduct.setQuantity(newConsoleQuantity);
                Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), "quantity"));
                System.out.println(COLOR + "YOU HAVE UPDATED THE QUANTITY!" + RESET);
                Scan.enter();
            break;
        }
    }

    /**
     * This method updates the details of an admin user.
     *
     * @param users    The user controller managing admin users.
     * @param username The username of the admin performing the update.
     */
    public static void updateAdmin (UserController users, String username) {
        Displayer.displayString(users.getAdminData(Arrays.asList((Profile) users.getAdmin(username)), "firstName"));
        String[] adminOptions = new String[] {"First Name", "Last Name", "Email", "Password", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " UPDATE:" + RESET);
        Displayer.displayOptions(Arrays.asList(adminOptions));
        System.out.print("What aspect to update: ");
        String adminAspect = Scan.getValidString(Arrays.asList(adminOptions));
        Scan.clear(); //User choice what part of the admin they want to modify.
        Profile adminUser = users.getAdmin(username);
        Admin admin = (Admin) adminUser;

        switch (adminAspect) {
            case "first name":
                Displayer.displayString(users.getAdminData(Arrays.asList(adminUser), "firstName"));
                System.out.print("Enter new first name: ");
                String newFirstName = Scan.getString();
                Scan.clear(); //Get new first name if they want to change it.

                adminUser.setFirstName(newFirstName);
            break;

            case "last name":
                Displayer.displayString(users.getAdminData(Arrays.asList(adminUser), "firstName"));
                System.out.print("Enter new last name: ");
                String newLastName = Scan.getString();
                Scan.clear(); //Do the same for last name.

                adminUser.setLastName(newLastName);
            break;

            case "email":
                Displayer.displayString(users.getAdminData(Arrays.asList(adminUser), "firstName"));
                System.out.print("Enter new email: ");
                String newEmail = Scan.getString();
                newEmail = Scan.validateEmail(newEmail, users.getEmails(users.getAdmins()));
                Scan.clear(); //Do the same for email.

                adminUser.setEmail(newEmail);
            break;

            case "password":
                Displayer.displayString(users.getAdminData(Arrays.asList(adminUser), "firstName"));
                System.out.print("Enter new password: ");
                String newPassword = Scan.getString();
                Scan.clear(); //Do the same for password.

                admin.setPassword(newPassword);
            break;
        }
        Displayer.displayString(users.getAdminData(Arrays.asList(adminUser), "firstName"));
        System.out.println(COLOR + "YOU HAVE UPDATED THE " + adminAspect.toUpperCase() + "!" + RESET);
        Scan.enter();
    }

    /**
     * This method updates the details of a member user.
     *
     * @param users The user controller managing member users.
     */
    public static void updateMember (UserController users)  {
        List<String> memberNames = users.getMemberData(users.getMembers(), "firstName");
        Displayer.displayOptions(memberNames, 1);
        System.out.print("Enter member id: ");
        String memberId = Scan.getValidString(users.getAllMemberIds());
        Scan.clear(); //The user enter which member to update.

        Displayer.displayString(users.getMemberData(Arrays.asList((Profile) users.getMember(memberId)), "firstName"));
        String[] memberOptions = new String[] {"First Name", "Last Name", "Email", "Balance", "Points", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " UPDATE:" + RESET);
        Displayer.displayOptions(Arrays.asList(memberOptions));
        System.out.print("What aspect to update: ");
        String memberAspect = Scan.getValidString(Arrays.asList(memberOptions));
        Scan.clear(); //The user chooses what field to update.

        Profile memberProfile =  users.getMember(memberId);
        Member member = (Member) memberProfile;
        switch (memberAspect) {
            case "first name":
                Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                System.out.print("Enter new first name: ");
                String newFirstName = Scan.getString();
                Scan.clear(); //Updates the first name.

                memberProfile.setFirstName(newFirstName);
            break;

            case "last name":
                Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                System.out.print("Enter new last name: ");
                String newLastName = Scan.getString();
                Scan.clear();

                memberProfile.setLastName(newLastName);
            break;

            case "email":
                Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                System.out.print("Enter new email: ");
                String newEmail = Scan.getString();
                newEmail = Scan.validateEmail(newEmail, users.getEmails(users.getAdmins()));
                Scan.clear();

                memberProfile.setEmail(newEmail);
            break;

            case "balance":
                Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                System.out.print("Enter new balance: ");
                double newBalance = Scan.getValidNumber(0, 10000);
                Scan.clear();

                member.setBalance(newBalance);
            break;

            case "points":
                Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                System.out.print("Enter new points: ");
                double newPoints= Scan.getValidNumber(0, 10000);
                Scan.clear();

                member.setPoints((int) Math.round(newPoints));
            break;
        }
        Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
        System.out.println(COLOR + "YOU HAVE UPDATED THE " + memberAspect.toUpperCase() +"!" + RESET);
        Scan.enter(); //Display the update done.
    }
}

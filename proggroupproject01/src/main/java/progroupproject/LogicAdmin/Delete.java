package progroupproject.LogicAdmin;

import java.util.Arrays;
import java.util.List;

import progroupproject.Products.Product;
import progroupproject.Products.ProductController;
import progroupproject.UI.Displayer;
import progroupproject.UI.Scan;
import progroupproject.Users.Profile;
import progroupproject.Users.UserController;

/**
 * This class handles deletion of games, consoles, and members from the system.
 */
public class Delete {
    public static final String COLOR = "\u001B[32m";
    public static final String COLORRED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    
    /**
     * This method deletes a game from the product controller.
     *
     * @param products The product controller managing game data
     */
    public static void deleteGame (ProductController products) {
        List<String> gamesString = products.getGameData(products.getAllGames(), "name");
        List<String> gameNames = products.getAllNames(products.getAllGames());
        Displayer.displayOptions(gamesString, 1);
        System.out.print("Enter the game you want to delete: ");
        String gameName = Scan.getValidString(gameNames);
        Scan.clear(); //Program get the user game they want to delete.

        Product gameProduct = products.filterByName(gameName, products.getAllGames());
        products.getAllGames().remove(gameProduct);
        Displayer.displayString(products.getGameData(products.getAllGames(), "name"));
        System.out.println(COLOR + "  YOU HAVE DELETED THE GAME:\n" + RESET);
        Displayer.displayString(products.getGameData(Arrays.asList(gameProduct), "name"));
        Scan.enter(); //Displays the user game they deleted.
    }
    
    /**
     * This method deletes a console from the product controller.
     *
     * @param products The product controller managing console data
     */
    public static void deleteConsole (ProductController products) {
        List<String> consolesString = products.getConsoleData(products.getAllConsoles(), "name");
        List<String> consoleNames = products.getAllNames(products.getAllConsoles());
        Displayer.displayOptions(consolesString, 1);
        System.out.print("Enter the console you want to delete: ");
        String consoleName = Scan.getValidString(consoleNames);
        Scan.clear(); //Does the same as console.

        Product consoleProduct = products.filterByName(consoleName, products.getAllConsoles());
        products.getAllConsoles().remove(consoleProduct);
        Displayer.displayString(products.getConsoleData(products.getAllConsoles(), "name"));
        System.out.println(COLOR + "  YOU HAVE DELETED THE CONSOLE:\n" + RESET);
        Displayer.displayString(products.getConsoleData(Arrays.asList(consoleProduct), "name"));
        Scan.enter();
    }

    /**
     * This method deletes a member from the user controller.
     *
     * @param users The user controller managing member data
     */
    public static void deleteMember (UserController users) {
        List<String> memberNames = users.getMemberData(users.getMembers(), "firstName");
        Displayer.displayOptions(memberNames, 1);
        System.out.print("Enter member id: ");
        String memberId = Scan.getValidString(users.getAllMemberIds());
        Scan.clear(); //Does the same as game and console.

        Profile memberProfile = users.getMember(memberId);
        users.getMembers().remove(memberProfile);
        Displayer.displayString(users.getMemberData(users.getMembers(), "firstName"));
        System.out.println(COLOR + "  YOU HAVE DELETED THE MEMBER:\n" + RESET);
        Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
        Scan.enter();
    }
}

package progroupproject.LogicAdmin;
import java.util.Arrays;

import progroupproject.Products.ProductController;
import progroupproject.UI.Displayer;
import progroupproject.UI.Scan;
import progroupproject.Users.*;

/**
 * This class manages the high-level logic for modifying products and users within the system.
 */
public class Logic {
    public static final String COLOR = "\u001B[32m";
    public static final String COLORRED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    
    /**
     * This method retrieves the modification logic based on user input.
     *
     * @param products The product controller
     * @param users The user controller
     * @param user The current user type
     */
    public static void getModifyLogic(ProductController products, UserController users, String user){
        String[] options = new String[] {"Add", "Update", "Delete", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " MODIFY:" + RESET);
        Displayer.displayOptions(Arrays.asList(options));
        System.out.print("What modifiction: ");
        String modifyOptions = Scan.getValidString(Arrays.asList(options));
        Scan.clear(); //Program asks user their choice.

        switch (modifyOptions) {
            case "add": 
                getAddLogic(products, users);
                break;
            case "update": 
                getUpdateLogic(products, users, user);
                break;
            case "delete":
                getDeleteLogic (products, users, user);
                break;
        } 
    }

    /**
     * This method retrieves the logic for adding products or users based on user input.
     *
     * @param products The product controller
     * @param users The user controller
     */
    public static void getAddLogic (ProductController products, UserController users) {
        String[] options = new String[] {"Game", "Console", "Admin", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " ADD:" + RESET);
        Displayer.displayOptions(Arrays.asList(options));
        System.out.print("What to add: ");
        String addOptions = Scan.getValidString(Arrays.asList(options));
        Scan.clear(); //Asks what the user wants to add.

        switch (addOptions) {
            case "game":
                Add.addGame(products);
            break;
            case "console" :
                Add.addConsole(products);
            break;
            case "admin" :
                Add.createUser(users, "admin");
            break;
        }
    }

    /**
     * This method retrieves the logic for updating products or users based on user input.
     *
     * @param products The product controller
     * @param users The user controller
     * @param currentAdmin The current admin username
     */
    public static void getUpdateLogic (ProductController products, UserController users, String currentAdmin) {
        String[] options = new String[] {"Game", "Console", "Admin", "Member", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " UPDATE:" + RESET);
        Displayer.displayOptions(Arrays.asList(options));
        System.out.print("What to update: ");
        String updateOption = Scan.getValidString(Arrays.asList(options));
        Scan.clear(); //Asks user what to update.

        switch (updateOption) {
            case "game":
                Update.updateGame(products);
                break;
            case "console":
                Update.updateConsole (products);
                break;
            case "admin":
                Update.updateAdmin (users, currentAdmin);
                break;
            case "member":
                Update.updateMember (users); 
                break;
        }
    }

    /**
     * This method retrieves the logic for deleting products or users based on user input.
     *
     * @param products The product controller
     * @param users The user controller
     * @param currentAdmin The current admin username
     */
    public static void getDeleteLogic (ProductController products, UserController users, String currentAdmin) {
        String[] deleteOptions = new String[] {"Game", "Console", "Member", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " DELETE:" + RESET);
        Displayer.displayOptions(Arrays.asList(deleteOptions));
        System.out.print("What to delete: ");
        String deleteOption = Scan.getValidString(Arrays.asList(deleteOptions));
        Scan.clear(); //Asks user what to delete.

        switch (deleteOption) {
            case "game":
                Delete.deleteGame(products);
                break;
            case "console" :
                Delete.deleteConsole (products);
                break;
            case "member" :
                Delete.deleteMember (users);
                break;
        }
    }
}

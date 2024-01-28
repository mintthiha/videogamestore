package progroupproject;

import java.util.Arrays;
import progroupproject.Logic.*;
import progroupproject.LogicAdmin.Logic;
import progroupproject.Products.ProductController;
import progroupproject.Files.*;
import progroupproject.UI.*;
import progroupproject.Users.*;

public class App 
{
    public static final String COLOR = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    /**
    * The main entry point for the application.
    *
    * @param args The command line arguments passed to the application.
    */
    public static void main( String[] args )
    {
        String userChoice = "";
        UserController users = new UserController();
        String[] roles = new String[] {"User", "Member", "Admin", "Exit"};
        String[] options = new String[] {"View Games", "View Consoles", "Buy", "Modify", "Go Back"};
        boolean canExit = false;
        String currentMember = "";
        ProductController products = new ProductController();
        Scan.clear();

        // Main application loop
        do {
            System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " USER TYPE:" + RESET);
            Displayer.displayOptions(Arrays.asList(roles));
            System.out.print("Who are you : ");
            String userType = Scan.getValidString(Arrays.asList(roles));
            boolean validated = false;
            Scan.clear(); //Displays different profiles the user can use and handles the logic

            switch (userType) {
            case "user" :
                    options = new String[] {"View Games", "View Consoles", "Go Back"};
                    validated = true;
            break;
            case "member": 
                currentMember = Login.validateMember(users);
                if (!currentMember.equalsIgnoreCase("Invalid Member")) {
                    options = new String[] {"View Games", "View Consoles", "Buy", "Go Back"};
                    validated = true;
                }
            break;
            case "admin" :
                currentMember = Login.validateAdmin(users);
                if (!currentMember.equalsIgnoreCase("Invalid Admin")){
                    options = new String[] {"View Games", "View Consoles", "Modify", "Go Back"};
                    validated = true;
                }
            break;
            case "exit" :
                canExit = true;
            break;
            }  

            while (!canExit && validated) {
                Scan.clear();
                System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " NEXT ACTION:" + RESET);
                Displayer.displayOptions(Arrays.asList(options));
                System.out.print("Which action would you like to do today: ");
                userChoice = Scan.getValidString(Arrays.asList(options));
                Scan.clear(); // Display available actions and handle user choice and logic

                switch (userChoice) {
                case "view games":
                    View.getViewGamesLogic(products);
                break;

                case "view consoles":
                    View.getViewConsolesLogic(products);
                break;
                case "buy":
                    Buy.getBuyLogic(products, users, currentMember);

                break;
                case "modify":
                    Logic.getModifyLogic (products, users, currentMember);
                break;

                case "go back":
                    validated = false;
                    Scan.clear();
                break;
                }
            } 
        }while (!canExit); //Exits when the user exits

        // Write data to files and perform cleanup
        FileWriter.writeAllConsoles(products.getAllConsoles());
        FileWriter.writeAllGames(products.getAllGames());
        FileWriter.writeAllAdmins(users.getAdmins());
        FileWriter.writeAllMembers(users.getMembers());
        FileWriter.writeAllCoupons(users.getCoupons());
        Scan.close();
        System.exit(0);
    }
}

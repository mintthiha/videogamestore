package progroupproject.Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import progroupproject.LogicAdmin.Add;
import progroupproject.UI.Displayer;
import progroupproject.UI.Scan;
import progroupproject.Users.UserController;

/**
 * This class handles the login functionality for both members and administrators.
 */
public class Login {
    public static final String COLOR = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String COLORRED = "\u001B[31m";

    /**
     * This method validates member login or registration.
     *
     * @param users The user controller object managing member data
     * @return A string representing the member's status or identification
     */
    public static String validateMember (UserController users) {
        boolean canProceed = false;
        String member = "";
            do {
                String[] memberOptions = new String[] {"Login", "Become Member", "Go Back"};
                System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " ACTIONS:" + RESET);
                Displayer.displayOptions(Arrays.asList(memberOptions));
                System.out.print("Choose an action: ");
                String memberChoice = Scan.getValidString(Arrays.asList(memberOptions));
                Scan.clear();

                switch (memberChoice) {
                case "login":
                    System.out.print("Enter your member id: ");
                    String currentMember = Scan.getString();
                    List<String> allMemberIds = users.getAllMemberIds();
                    Scan.clear();
                    if (Scan.checkIfExist(currentMember, allMemberIds)) {
                        member = currentMember;
                        canProceed = true;
                        Scan.clear();
                    }
                    else {
                        member = "Invalid Member";
                        System.out.println(COLORRED + "MEMBER DOES NOT EXISTS!" + RESET);
                        Scan.enter();
                    }
                break;
                case "become member" :
                    Add.createUser(users, "member");
                break;
                case "go back" :
                    canProceed = true;
                    Scan.clear();
                break;
                }
                } while (!canProceed);
        return member;
    }

    /**
     * this method validates admin login credentials.
     *
     * @param users The user controller object managing admin data
     * @return A string representing the admin's username or status
     */
    public static String validateAdmin (UserController users) {
        String username = "";
        System.out.print("Enter your username: ");
        String usernameChoice = Scan.getString();
    
        System.out.print("Enter your password: ");
        String passwordChoice = Scan.getString();
        
        if (Scan.adminExist(usernameChoice, passwordChoice, users)) {
            username = usernameChoice;
        }
        else {
            username = "Invalid Admin";
            System.out.println("Admin does not exist!");
            Scan.enter();            
        }
        return username;
    }

    /**
     * This method retrieves available options based on user permissions.
     *
     * @param permission The level of user permission
     * @param options    An array of available options
     * @return A list containing available options based on the provided permission level
     */
    public static List<String> getAvailableOptions (int permission, String[] options) {
        List<String> availableOptions = new ArrayList<>();
        for (int i = 0; i < permission; i++) {
            availableOptions.add(options[i]);
        }
        return availableOptions;
    }
}

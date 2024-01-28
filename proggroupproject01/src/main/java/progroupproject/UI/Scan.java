package progroupproject.UI;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import progroupproject.Products.Product;
import progroupproject.Products.ProductController;
import progroupproject.Users.Admin;
import progroupproject.Users.UserController;

/**
 * A utility class for handling user input through the scanner.
 */
public class Scan {
    public static Scanner scan;
    public static final String COLOR = "\u001B[32m";
    public static final String COLORRED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    /**
     * Gets a valid string input from the user based on provided options.
     * will loop until the user provides the right input.
     * @param options The list of options available for user input.
     * @return The validated input in lowercase.
     */
    public static String getValidString (List<String> options) {
        scan = new Scanner(System.in);
        String userInput = scan.next();
        String validInput = "";
        if (isNumber(userInput)) {
            int userNumber = convertToInt(userInput);
            validInput = getTargetOption(options, userNumber);
        }
        else {
            validInput = loopUntilValid(options, userInput);
        }
        
        return validInput.toLowerCase();
    }

    /**
     * Reads a string input from the user.
     * 
     * @return The string input from the user.
     */
    public static String getString () {
        scan = new Scanner(System.in);
        return scan.next();
    }

     /**
     * Reads a line of string input from the user.
     * 
     * @return The string input line from the user.
     */
    public static String getStringLine () {
        scan = new Scanner(System.in);
        return scan.nextLine();
    }

    /**
     * Validates the product name to ensure it's unique.
     * 
     * @param newName   The new name to be validated.
     * @param products  The product controller to check for existing names.
     * @param list      The list of products to compare against.
     * @return          The validated unique product name.
     */
    public static String validateProductName (String newName, ProductController products, List<Product> list) {
        while (Scan.checkIfExist(newName, products.getAllNames(list))) {
            Scan.clear();
            System.out.println(COLORRED + "NAME ALREADY TAKEN!" + RESET);
            System.out.print("Enter the name: ");
            newName = Scan.getStringLine();
        } 
        return newName;
    }

    /**
     * Retrieves product data from the user input.
     * 
     * @param products  The product controller.
     * @param list      The list of products.
     * @return          An array containing product data.
     */
    public static String[] getProductData (ProductController products, List<Product> list) {
        String[] data = new String[4];
        System.out.print("Enter the name: ");
        data[0] = Scan.getStringLine();
        data[0] = validateProductName(data[0], products, list);
        Scan.clear();
        // Ask for price
        System.out.print("Enter the price: ");
        data[1] = Scan.getValidNumber(0) + "";
        Scan.clear();
        // Ask for company
        System.out.print("Enter the company name: ");
        data[2] = Scan.getStringLine();
        Scan.clear();
        // Ask for quantity
        System.out.print("Enter the quantity: ");
        data[3] = (int) Math.round(Scan.getValidNumber(1, 1000)) + "";
        Scan.clear();
        return data;
    }

    /**
     * Collects a list of console game names entered by the user until "exit" is typed.
     *
     * @return A list of entered game names excluding "exit".
     */
    public static List<String> getConsoleLibrary () {
        List<String> library = new ArrayList<>();
        String input = "";
        do {
            if (!input.equalsIgnoreCase("exit")) {
                System.out.print("Enter the game name (Type \"exit\" when finished): ");
                input = getString();
                library.add(input);
            }
        } while (!input.equalsIgnoreCase("exit"));
        return library;
    }

     /**
     * Checks if a given user input is valid based on a list of options.
     *
     * @param userInput The user input to be checked.
     * @param options The list of available options.
     * @return True if the input is valid, otherwise false.
     */
    public static boolean checkIfValid (String userInput, List<String> options) {
        boolean isValid = false;
        for (String option : options) {
            if (userInput.equalsIgnoreCase(option)) {
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * Checks if the provided input is a number.
     *
     * @param input The input string to be checked.
     * @return True if the input is a number, otherwise false.
     */
    private static boolean isNumber (String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a user input exists in a list of options.
     *
     * @param userInput The user input to be checked.
     * @param options The list of available options.
     * @return True if the input exists in the options, otherwise false.
     */
    public static boolean checkIfExist (String userInput, List<String> options) {
        for (String option : options) {
            if (userInput.equalsIgnoreCase(option)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the targeted option from a list of options based on a given number.
     *
     * @param options The list of available options.
     * @param userNumber The targeted number representing an option.
     * @return The selected option based on the number provided.
     */
    private static String getTargetOption (List<String> options, int userNumber) {
        String input = "";
        if (userNumber <= 0 || userNumber > options.size()) {
            input = loopUntilValid(options, input);
        }
        else {
            input = options.get(userNumber-1);
        }
        return input;
    }

    /**
     * Validates user input by looping until a valid input is received.
     *
     * @param options The list of available options.
     * @param userInput The user input to be validated.
     * @return A valid user input from the available options.
     */
    private static String loopUntilValid (List<String> options, String userInput) {
        scan = new Scanner(System.in);
        while (!isValid(options, userInput)){
            Displayer.displayInvalidMessage();;
            Displayer.displayOptions(options);
            userInput = scan.next();
            if (isNumber(userInput)) {
                int userNumber = convertToInt(userInput);
                userInput = getTargetOption(options, userNumber);
            }
        }
        return userInput;
    }

    /**
     * Checks the validity of the input against available options.
     *
     * @param options The list of available options.
     * @param input The input string to be checked.
     * @return True if the input matches an available option, otherwise false.
     */
    private static boolean isValid (List<String> options, String input) {
        boolean validity = false;
        for (String option : options) {
            if (input.equalsIgnoreCase(option) && !validity){
                validity = true;
            }
        }
        return validity;
    }

    /**
     * Converts a string input to a double value.
     *
     * @param input The input string to be converted.
     * @return The converted double value, or -1 if conversion fails.
     */
    private static double convertToDouble (String input){
        try {
            double userNumber = Double.parseDouble(input);
            return userNumber;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Converts a string input to an integer.
     *
     * @param input The string to be converted to an integer.
     * @return The converted integer value, or -1 if conversion fails.
     */
    private static int convertToInt (String input){
        try {
            int userNumber = Integer.parseInt(input);
            return userNumber;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Prompts the user to input a valid number greater than a minimum value.
     *
     * @param min The minimum value the entered number should exceed.
     * @return The valid number entered by the user.
     */
    public static double getValidNumber (double min) {
        scan = new Scanner(System.in);
        String input = scan.next();
        while (!isNumber(input)) {
            System.out.print("Enter a valid number: ");
            input = scan.next();
        }
        double number = convertToDouble(input);
        try {
            while (number <= min) {
            System.out.println("Error!");
            System.out.println("Enter a valid number: ");
            number = scan.nextDouble();
            scan.nextLine();
        }
        }
        catch (InputMismatchException e) {
            System.out.println("You entered a string instead of number !");
        }
        return number;
    }

    /**
     * Prompts the user to input a valid number within a specified range.
     *
     * @param min The minimum value the entered number should exceed.
     * @param max The maximum value the entered number should not exceed.
     * @return The valid number entered by the user.
     */
    public static double getValidNumber (double min, double max) {
        scan = new Scanner(System.in);
        String input = scan.next();
        while (!isNumber(input)) {
            System.out.print("Enter a valid number: ");
            input = scan.next();
        }
        double number = convertToDouble(input);
        try {
            while (number <= min && number >= max) {
            System.out.println("Error!");
            System.out.println("Enter a valid number: ");
            number = scan.nextDouble();
            scan.nextLine();
        }
        }
        catch (InputMismatchException e) {
            System.out.println("You entered a string instead of number !");
        }
        return number;
    }

    /**
     * Generates a random integer within a specified range.
     *
     * @param range The upper bound for the random number.
     * @return A random integer between 0 (inclusive) and the specified range (exclusive).
     */
    public static int getRandomInt(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    /**
     * Clears the console screen by flushing the output.
     */
    public static void clear () {
        System.out.flush();
        System.out.print("\033[H\033[2J");
    }  

    /**
     * Pauses execution until the "Enter" key is pressed.
     */
    public static void enter () {
        scan = new Scanner(System.in);
        System.out.print("\nPress enter to continue...");
        scan.nextLine();
        clear();
    }

    /**
     * Checks if an admin user exists based on provided credentials.
     *
     * @param username The admin username.
     * @param password The admin password.
     * @param users The user controller managing admin data.
     * @return True if an admin user with the provided credentials exists, otherwise false.
     */
    public static boolean adminExist (String username, String password, UserController users) {
        boolean exist = false;
        List<Admin> admins = users.convertToAdmin(users.getAdmins());
        for (Admin admin : admins) {
            if (username.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
                exist = true;
            }
        }
        return exist;
    }

    /**
     * Checks if an admin user exists based on provided credentials.
     *
     * @param username The admin username.
     * @param password The admin password.
     * @param users The user controller managing admin data.
     * @return True if an admin user with the provided credentials exists, otherwise false.
     */
    public static String validateEmail (String email, List<String> emails)  {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

        while (!isValidRegex(email, emailRegex) || Scan.checkIfExist(email, emails)) {
            Scan.clear();
            if (!isValidRegex(email, emailRegex)) {
                System.out.println(COLORRED + "INVALID EMAIL PATTERN!" + RESET);
                System.out.println("Pattern to follow: example@example.example");
                System.out.print("Enter new email: ");
                email = Scan.getString();
            }
            if (Scan.checkIfExist(email, emails)) {
                System.out.println(COLORRED + "EMAIL ALREADY EXIST!" + RESET);
                System.out.print("Enter new email: ");
                email = Scan.getString();
            }
        }
        return email;
    }

    /**
     * Validates an email address pattern and checks if it already exists.
     *
     * @param email The email address to be validated.
     * @param emails List of existing email addresses.
     * @return A validated and non-duplicate email address.
     */
    public static boolean isValidRegex (String input, String regex) {
        boolean isValidPattern = false;
        try {
            Pattern validEmail = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = validEmail.matcher(input);
            return matcher.matches();
        }catch (PatternSyntaxException e){
            System.out.println("You entered invalid regex!!!");
            return isValidPattern;
        }
    }

    /**
     * Validates a username and checks if it already exists.
     *
     * @param username The username to be validated.
     * @param usernames List of existing usernames.
     * @return A non-duplicate username.
     */
    public static String validateUsername (String username, List<String> usernames) {
        while (Scan.checkIfExist(username, usernames)) {
            Scan.clear();
            System.out.println(COLORRED + "USERNAME ALREADY EXIST!" + RESET);
            System.out.print("Enter new username: ");
            username = Scan.getString();
        }
        return username;
    }

    /**
     * Closes the scanner to prevent resource leaks.
     */
    public static void close(){
        scan.close();
    }
}
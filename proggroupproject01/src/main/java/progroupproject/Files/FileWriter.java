package progroupproject.Files;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import progroupproject.Products.*;
import progroupproject.Users.*;


/**
 * This class provides methods for writing various data into separate files.
 */
public class FileWriter {
    
    /**
     * Writes a list of lines into a file specified by the path.
     *
     * @param lines The list of lines to be written into the file
     * @param path  The path of the file to write the data
     */
    public static void writeListIntoFile(List<String> lines, String path){
        try {
            Files.write(Paths.get(path), "".getBytes()); // Clears the file content
            for (String line : lines) {
                Files.write(Paths.get(path), line.getBytes(), StandardOpenOption.APPEND); // Appends each line to the file
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to write data!"); // Error message if data writing fails
        }
    }

    /**
     * Writes a single line into a file specified by the path.
     *
     * @param line The line to be written into the file
     * @param path The path of the file to write the data
     */
    public static void writeLineIntoFile(String line, String path){
        try {
            Files.write(Paths.get(path), line.getBytes(), StandardOpenOption.APPEND); // Appends a single line to the file
            System.out.println("Successfully wrote data!"); // Confirmation message on successful data writing
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to write data!"); // Error message if data writing fails
        }
    }

    /**
     * Writes all admin profiles into the Admin.txt file.
     *
     * @param admins The list of admin profiles to be written into the file
     */
    public static void writeAllAdmins(List<Profile> admins){
        List<String> myLines = new ArrayList<>();
        for(Profile admin : admins){
            Admin myAdmin = (Admin) admin;
            String line = myAdmin.getId() + "," + myAdmin.getFirstName() + "," + myAdmin.getLastName() + "," + myAdmin.getEmail() + "," + myAdmin.getUserName() + "," + myAdmin.getPassword() + "\n";
            myLines.add(line); // Creates lines of admin details to write into the Admin.txt file
        }
        FileWriter.writeListIntoFile(myLines, "./proggroupproject01/src/main/java/progroupproject/Data/Admin.txt"); // Writes admin details into the file
    }

     /**
     * Writes all member profiles into the Member.txt file.
     *
     * @param members The list of member profiles to be written into the file
     */
    public static void writeAllMembers(List<Profile> members){
        List<String> myLines = new ArrayList<>();
        for(Profile member : members){
            Member mem = (Member) member;
            List<Coupon> coupons = mem.getCoupons();
            String builder = "";
            for(Coupon c : coupons){
                builder += c.getCouponId() + ";" + c.getDiscount() + ";"; // Constructs a string containing coupon details for each member
            }
            String line = mem.getId() + "," + mem.getFirstName() + "," + mem.getLastName() + "," + mem.getEmail() + "," + mem.getMemberId() + "," + mem.getPoints() + "," + mem.getBalance() + "," + builder;
            myLines.add(line); // Creates lines of member details to write into the Member.txt file
        }
        FileWriter.writeListIntoFile(myLines, "./proggroupproject01/src/main/java/progroupproject/Data/Member.txt"); // Writes member details into the file
    }

    /**
     * Writes all coupons into the Coupon.txt file.
     *
     * @param coupons The list of coupons to be written into the file
     */
    public static void writeAllCoupons(List<Coupon> coupons){
        List<String> myLines = new ArrayList<>();
        for(Coupon coupon : coupons){
            String line = coupon.getCouponId() + "," + coupon.getDiscount() + "\n";
            myLines.add(line); // Creates lines of coupon details to write into the Coupon.txt file
        }
        FileWriter.writeListIntoFile(myLines, "./proggroupproject01/src/main/java/progroupproject/Data/Coupon.txt"); // Writes coupon details into the file
    }

    /**
     * Writes all games into the Game.txt file.
     *
     * @param games The list of games to be written into the file
     */
    public static void writeAllGames(List<Product> games){
        List<String> myLines = new ArrayList<>();
        for(Product game : games){
            Game myGame = (Game) game;
            String myLine = myGame.getId() + "," + myGame.getName() + "," + myGame.getPrice() + "," + myGame.getCompany() + "," + myGame.getDate() + "," + myGame.getQuantity() + "," + myGame.getGenre() + "\n";
            myLines.add(myLine); // Creates lines of game details to write into the Game.txt file
        }
        FileWriter.writeListIntoFile(myLines, "./proggroupproject01/src/main/java/progroupproject/Data/Game.txt"); // Writes game details into the file
    }

    /**
     * Writes all consoles into the Console.txt file.
     *
     * @param consoles The list of consoles to be written into the file
     */
    public static void writeAllConsoles(List<Product> consoles){
        List<String> myLines = new ArrayList<>();
        for(Product console : consoles){
            Console myConsole = (Console) console;
            String myLine = myConsole.getId() + "," + myConsole.getName() + "," + myConsole.getPrice() + "," + myConsole.getCompany() + "," + myConsole.getDate() + "," + myConsole.getQuantity() + "," + String.join(";", myConsole.getLibrary()) + "\n";
            myLines.add(myLine); // Creates lines of console details to write into the Console.txt file
        }
        FileWriter.writeListIntoFile(myLines, "./proggroupproject01/src/main/java/progroupproject/Data/Console.txt"); // Writes console details into the file
    }  
}
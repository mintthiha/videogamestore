package progroupproject.Files;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import progroupproject.Users.Admin;
import progroupproject.Users.Coupon;
import progroupproject.Users.Member;
import progroupproject.Users.Profile;


/**
 * This class provides methods for retrieving various data from different files.
 */
public class FileImporter {
    
    /**
     * Reads data from a file and returns a list of strings containing each line of the file.
     *
     * @param path The path of the file to read
     * @return List of strings representing each line of the file
     */
    public static List<String> getListData(String path){
        List<String> lines = new ArrayList<>();
        try {
            Path p = Paths.get(path); // Getting the file path
            lines = Files.readAllLines(p); // Reading all lines from the file
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't retrieve data!"); // Error message if data retrieval fails
        }
        return lines; // Returning the list of lines read from the file
    }

    /**
     * Retrieves all admin profiles from a file and returns a list of Profile objects containing Admin details.
     *
     * @param path The path of the file containing admin data
     * @return List of Profile objects containing admin details
     */
    public static List<Profile> retrieveAllAdmins (String path) {
        List<Profile> users = new ArrayList<>();
        List<String> lines = FileImporter.getListData(path); // Getting all lines from the file
        for (String line : lines) {
            String[] pieces = line.split(","); // Splitting each line into pieces using comma as separator
            users.add(new Admin(Integer.parseInt(pieces[0]), pieces[1], pieces[2], pieces[3],  pieces[4], pieces[5])); // Creating new Admin objects from the pieces and adding them to the list
        }
        return users; // Returning the list of Admin profiles
    }

    /**
     * Retrieves all member profiles from a file and returns a list of Profile objects containing Member details.
     *
     * @param path    The path of the file containing member data
     * @param coupons List of available coupons to match with member profiles
     * @return List of Profile objects containing member details
     */
    public static List<Profile> retrieveAllMembers (String path, List<Coupon> coupons) {
        List<Profile> users = new ArrayList<>();
        List<String> lines = FileImporter.getListData(path); // Getting all lines from the file
        for (String line : lines) {
            String[] pieces = line.split(","); // Splitting each line into pieces using comma as separator
            users.add(new Member(Integer.parseInt(pieces[0]), pieces[1], pieces[2], pieces[3], pieces[4],  Integer.parseInt(pieces[5]), Double.parseDouble(pieces[6]), convertToCoupons(Arrays.asList(pieces[7].split(";")), coupons))); // Creating new Member objects from the pieces and adding them to the list
        }
        return users; // Returning the list of Member profiles
    }

    /**
     * Retrieves all coupons from a file and returns a list of Coupon objects.
     *
     * @param path The path of the file containing coupon data
     * @return List of Coupon objects
     */
    public static List<Coupon> retrieveAllCoupons (String path) {
        List<Coupon> users = new ArrayList<>();
        List<String> lines = FileImporter.getListData(path); // Getting all lines from the file
        for (String line : lines) {
            String[] pieces = line.split(","); // Splitting each line into pieces using comma as separator
            users.add(new Coupon(Integer.parseInt(pieces[0]), Double.parseDouble(pieces[1]))); // Creating new Coupon objects from the pieces and adding them to the list
        }
        return users; // Returning the list of Coupon objects
    }

    /**
     * Converts a list of strings into Coupon objects by matching their IDs with a list of available coupons.
     *
     * @param list          List of string IDs to convert to Coupon objects
     * @param listOfCoupons List of available Coupon objects to match with IDs
     * @return List of matched Coupon objects
     */
    public static List<Coupon> convertToCoupons (List<String> list, List<Coupon> listOfCoupons) {
        List<Coupon> coupons = new ArrayList<>();
        for (String id : list) {
            for (Coupon coupon : listOfCoupons) {
                if (id.equalsIgnoreCase(coupon.getCouponId()+""))  {
                    coupons.add(coupon); // Adding matching coupons to the list
                }
            }
        }
        return coupons; // Returning the list of matched Coupon objects
    }
}
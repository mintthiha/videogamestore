package progroupproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import progroupproject.Products.Console;
import progroupproject.Products.Product;


public class ConsoleTest {

    public Product getConsole(){
        List<String> library = Arrays.asList("Gary's mod", "Roblox");
        Product myConsole = new Console(0, "Xbox 390", 789.99, "Xbox", "08-07-20", 20, library);
        return myConsole;
    }

    @Test
    public void testIdGetter(){
        Product myConsole = getConsole();
        assertEquals("Test the id getter method for Console", 0, myConsole.getId());
    }

    @Test
    public void testIdSetter(){
        Product myConsole = getConsole();
        myConsole.setId(1);
        assertEquals("Test the id detter method for Console", 1, myConsole.getId());
    }

    @Test
    public void testNameGetter(){
        Product myConsole = getConsole();
        assertEquals("Test the name getter method for Console", "Xbox 390", myConsole.getName());
    }

    @Test
    public void testNameSetter(){
        Product myConsole = getConsole();
        myConsole.setName("Elden Ring");
        assertEquals("Test the name detter method for Console", "Elden Ring", myConsole.getName());
    }

    @Test
    public void testPriceGetter(){
        Product myConsole = getConsole();
        assertEquals("Test the price getter method for Console", 789.99, myConsole.getPrice(), 0.01);
    }

    @Test
    public void testPriceSetter(){
        Product myConsole = getConsole();
        myConsole.setPrice(20.21);
        assertEquals("Test the price setter method for Console", 20.21, myConsole.getPrice(), 0.01);
    }

    @Test
    public void testDateGetter(){
        Product myConsole = getConsole();
        assertEquals("Test the date getter method for Console", "08-07-20", myConsole.getDate());
    }

    @Test
    public void testDateSetter(){
        Product myConsole = getConsole();
        myConsole.setDate("01-01-01");
        assertEquals("Test the date setter method for Console", "01-01-01", myConsole.getDate());
    }

    @Test
    public void testQuantityGetter(){
        Product myConsole = getConsole();
        assertEquals("Test the quantity getter method for Console", 20, myConsole.getQuantity());
    }

    @Test
    public void testQuantitySetter(){
        Product myConsole = getConsole();
        myConsole.setQuantity(2020);
        assertEquals("Test the quantity setter method for Console", 2020, myConsole.getQuantity());
    }

    @Test
    public void testLibraryGetter(){
        Product myConsole = getConsole();
        if (myConsole instanceof Console) {
            Console console = (Console) myConsole;
            List<String> expectedList = Arrays.asList("Gary's mod", "Roblox");
            assertEquals("Test the library getter method for console", expectedList, console.getLibrary());
        } else {
            fail("myConsole is not an instance of Console.");
        }
    }
    @Test
    public void testLibrarySetter(){
        Product myConsole = getConsole();
        if (myConsole instanceof Console) {
            Console console = (Console) myConsole;
            console.setLibrary(Arrays.asList("Skul: The Hero Slayer", "Clash Royale"));
            List<String> expectedList = Arrays.asList("Skul: The Hero Slayer", "Clash Royale");
            assertEquals("Test the library setter method for console", expectedList, console.getLibrary());
        } else {
            fail("myConsole is not an instance of Console.");
        }
    }    

    @Test
    public void testToString(){
        Console myConsole = (Console) getConsole();
        assertEquals("Id: 0, Name: Xbox 390, Price: 789.99, Company: Xbox, Release Date: 08-07-20, Gary's mod, Roblox", myConsole.toString());
    }
    
    @Test
    public void testConstructorId(){
        try{
            List<String> lib = Arrays.asList("Nth", "noth");
            Console myConsole = new Console(-10, "Piano", 1, "Bing", "01-01-01", 1, lib);
            System.out.println(myConsole);
            fail("Should throw exception, the id is negative.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed successfully");
        }
    }

    @Test
    public void testConstructorPrice(){
        try{
            List<String> lib = Arrays.asList("Nth", "noth");
            Console myConsole = new Console(10, "Piano", -69, "Bing", "01-01-01", 1, lib);
            System.out.println(myConsole);
            fail("Should throw exception, the price is negative.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed successfully");
        }
    }

    @Test
    public void testConstructorQuantity(){
        try{
            List<String> lib = Arrays.asList("Nth", "noth");
            Console myConsole = new Console(10, "Piano", 100, "Bing", "01-01-01", -420, lib);
            System.out.println(myConsole);
            fail("Should throw exception, the quantity is negative.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed successfully");
        }
    }
}   



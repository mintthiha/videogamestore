package progroupproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import progroupproject.Products.Game;
import progroupproject.Products.Product;


public class GameTest {

    public Product getGame(){
        Product myGame = new Game(0, "Test", 9.99, "Nintendo", "02-02-07", 300, "Horror");
        return myGame;
    }

    @Test
    public void testIdGetter(){
        Product myGame = getGame();
        assertEquals("Test the id getter method for game", 0, myGame.getId());
    }

    @Test
    public void testIdSetter(){
        Product myGame = getGame();
        myGame.setId(1);
        assertEquals("Test the id setter method for game", 1, myGame.getId());
    }

    @Test
    public void testNameGetter(){
        Product myGame = getGame();
        assertEquals("Test the name getter method for game", "Test", myGame.getName());
    }

    @Test
    public void testNameSetter(){
        Product myGame = getGame();
        myGame.setName("Rocket League");
        assertEquals("Test the name setter method for game", "Rocket League", myGame.getName());
    }

    @Test
    public void testPriceGetter(){
        Product myGame = getGame();
        assertEquals("Test the price getter method for game", 9.99, myGame.getPrice(), 0.01);
    }

    @Test
    public void testPriceSetter(){
        Product myGame = getGame();
        myGame.setPrice(29.99);
        assertEquals("Test the price setter method for game", 29.99, myGame.getPrice(), 0.01);
    }

    @Test
    public void testDateGetter(){
        Product myGame = getGame();
        assertEquals("Test the date getter method for game", "02-02-07", myGame.getDate());
    }

    @Test
    public void testDateSetter(){
        Product myGame = getGame();
        myGame.setDate("01-01-01");
        assertEquals("Test the date setter method for game", "01-01-01", myGame.getDate());
    }

    @Test
    public void testQuantityGetter(){
        Product myGame = getGame();
        assertEquals("Test the quantity getter method for game", 300, myGame.getQuantity());
    }

    @Test
    public void testQuantitySetter(){
        Product myGame = getGame();
        myGame.setQuantity(10);
        assertEquals("Test the quantity setter method for game", 10, myGame.getQuantity());
    }

    @Test
    public void testGenreGetter(){
        Product myGame = getGame();
        if (myGame instanceof Game) {
            Game game = (Game) myGame;
            assertEquals("Test the genre getter method for game", "Horror", game.getGenre());
        } else {
            fail("myGame is not of an instance of Game.");
        }
    }  

    @Test
    public void testGenreSetter(){
        Product myGame = getGame();
        if (myGame instanceof Game) {
            Game game = (Game) myGame;
            game.setGenre("Comedy");
            assertEquals("Test the genre setter method for game", "Comedy", game.getGenre());
        } else {
            fail("myGame is not of an instance of Game.");
        }
    }  

    @Test
    public void testToString(){
        Product myGame = getGame();
        assertEquals("Test the toString for game", "Test, Price: 9.99$, Company: Nintendo, Release Date: 02-02-07, Genre: Horror", myGame.toString());
    }

    @Test
    public void testConstructorId(){
        try{
            Game myGame = new Game(-10, "OverWatch 2", 0, "Blizzard", "06-04-2015", 53002, "Action");
            System.out.println(myGame);
            fail("Should throw exception, the id is negative.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed successfully");
        }
    }

    @Test
    public void testConstructorPrice(){
        try{
            Game myGame = new Game(10, "OverWatch 2", -10, "Blizzard", "06-04-2015", 53002, "Action");
            System.out.println(myGame);
            fail("Should throw exception, the price is negative.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed successfully");
        }
    }

    @Test
    public void testConstructorQuantity(){
        try{
            Game myGame = new Game(10, "OverWatch 2", 0, "Blizzard", "06-04-2015", -53002, "Action");
            System.out.println(myGame);
            fail("Should throw exception, the quantity is negative.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed successfully");
        }
    }
}
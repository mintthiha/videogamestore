package progroupproject.Logic;

import progroupproject.UI.Scan;
import progroupproject.Users.Member;
import progroupproject.Users.Profile;
import progroupproject.Users.UserController;

import java.util.Arrays;
import java.util.List;

import progroupproject.Products.Product;
import progroupproject.Products.ProductController;
import progroupproject.UI.Displayer;

/**
 * This class contains logic for handling the purchase of games and consoles.
 */
public class Buy {
    public static final String COLOR = "\u001B[32m";
    public static final String COLORRED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    
    /**
     * Handles the logic for buying games or consoles.
     *
     * @param products The product controller object containing game and console data
     * @param users    The user controller object containing member data
     * @param user     The username of the current user
     */
    public static void getBuyLogic(ProductController products, UserController users, String user){
        String[] buyOptions = new String[] {"Games", "Consoles", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " BUY:" + RESET);
        Displayer.displayOptions(Arrays.asList(buyOptions));
        System.out.print("What would you like to buy?");
        String buyChoice = Scan.getValidString(Arrays.asList(buyOptions));
        Scan.clear();

        Member currentMember  = users.getMember(user);

            switch (buyChoice) { //The user is presented options, and they must choose.
            case "games": 
                buyGames(products, users, currentMember); 
                break;
            case "consoles": 
                buyConsoles(products, users, currentMember);
                break;
            case "go back":
                buyChoice = "go back";
            break;
        } 

    }

    /**
     * Handles the logic for purchasing games.
     *
     * @param products       The product controller object containing game data
     * @param users          The user controller object containing member data
     * @param memberProfile  The member profile attempting to purchase the game
     */
    public static void buyGames(ProductController products, UserController users, Profile memberProfile){
        Member member = (Member) memberProfile;
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " BUY:" + RESET);
        Displayer.displayOptions(products.getProductStockOnly(products.getAllGames()));
        System.out.print("Enter the game name: ");
        String gameChoice = Scan.getValidString(products.getProductStockOnly(products.getAllGames()));
        Product game = products.filterByName(gameChoice, products.getAllGames());
        Scan.clear(); //The user chooses what game to buy.

        double memberBalance = member.getBalance();
        int memberPoints = member.getPoints();
        double gamePrice = game.getPrice();
        int gamePricePoints = ((int) Math.round(gamePrice) * 100); 

        Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
        Displayer.displayString(products.getGameData(Arrays.asList(game), "name")); //Data is displayed about the member and the game.

        String[] buyOptions = new String[] {"Apply Balance", "Apply Points", "Apply Coupon", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " PAYMENT TYPE:" + RESET);
        Displayer.displayOptions(Arrays.asList(buyOptions));
        System.out.print("Method of payment: ");
        String buyOption = Scan.getValidString(Arrays.asList(buyOptions));
        Scan.clear(); //The user get different options on how to purchase the game.

        switch (buyOption) {
            case "apply balance" : 
                if (gamePrice <= memberBalance) { //If member uses their balance and have enough, go in here.
                int newPoints = (int) Math.round(gamePrice) * 10;
                game.setQuantity(game.getQuantity()-1); //Game info updated

                Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                Displayer.displayString(products.getGameData(Arrays.asList(game), "name")); //Display information

                System.out.println(COLOR + "UPDATED BALANCE" + RESET);
                System.out.println(member.getBalance() + "$" + " - " + gamePrice + "$ = " + (memberBalance-gamePrice) + "$\n");
                System.out.println(COLOR + "POINTS REWARD" + RESET);
                System.out.println(member.getPoints() + " + " + newPoints + " = " + (memberPoints + newPoints) + "\n");  
                member.setBalance(memberBalance-gamePrice);
                member.setPoints(memberPoints + newPoints);
                System.out.println(COLOR + "SUCCESFULLY PURCHASED: \n"  + RESET + game.getName().toUpperCase());
                Scan.enter();   //Tells the user they have successfully bought the game.
                }
                else {
                    Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                    Displayer.displayString(products.getGameData(Arrays.asList(game), "name"));

                    System.out.println(COLORRED + "UPDATED BALANCE" + RESET);
                    System.out.println(member.getBalance() + "$" + " - " + gamePrice + "$ = " + (memberBalance-gamePrice) + "$\n");
                    System.out.println(COLORRED + "FAILED PURCHASE: "  + RESET);
                    System.out.println(game.getName().toUpperCase());
                    Scan.enter(); //Tells the user the opposite, they failed.
                }
            break;

            case "apply points" : 
                if (gamePricePoints <= memberPoints) { //If the user have enough points, they will be able to purchase, if not, they won't. The same as balance.
                    Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                    Displayer.displayString(products.getGameData(Arrays.asList(game), "name"));

                    System.out.println(COLOR + "POINTS REWARD" + RESET);
                    System.out.println(memberPoints + " - " + gamePricePoints + " = " + (memberPoints - gamePricePoints));  
                    member.setPoints(memberPoints - gamePricePoints);
                    game.setQuantity(game.getQuantity()-1);
                    System.out.println(COLOR + "\nSUCCESFULLY PURCHASED \n"  + RESET + game.getName().toUpperCase());
                    Scan.enter();  
                }
                else {
                    Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                    Displayer.displayString(products.getGameData(Arrays.asList(game), "name"));

                    System.out.println(COLORRED + "POINTS REWARD" + RESET);
                    System.out.println(memberPoints + " - " + gamePricePoints + " = " + (memberPoints - gamePricePoints) +"\n"); 
                    System.out.println(COLORRED + "FAILED PURCHASE "  + RESET);
                    System.out.println(game.getName().toUpperCase());
                    Scan.enter();
                }
            break;

            case "apply coupon" :
                Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                Displayer.displayString(products.getGameData(Arrays.asList(game), "name"));

                List<String> discounts = member.getCouponsList(member.getCoupons());
                System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " PERCENTAGE:" + RESET);
                Displayer.displayOptions(discounts);
                System.out.print("Choose percentage: ");
                String discountOption = Scan.getValidString(discounts);
                Scan.clear(); //The user may choose which coupon to use, and they will always have one because after using one, they will be assigned a new one.

                member.deleteCoupon(Double.parseDouble(discountOption));
                gamePrice = game.getPrice() - (game.getPrice() * (Double.parseDouble(discountOption)/100));
                int randomNum = Scan.getRandomInt(users.getCoupons().size());
                member.addCoupon(users.getCouponById(randomNum)); //Assigned a new coupon.
                

                if (gamePrice <= memberBalance) { //Same as the balance option.
                    int newPoints = (int) Math.round(gamePrice) * 10;
                    game.setQuantity(game.getQuantity()-1);

                    Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                    Displayer.displayString(products.getGameData(Arrays.asList(game), "name"));

                    System.out.println(COLOR + "BALANCE WITH DISCOUNT OF " + discountOption + "%" + RESET);
                    System.out.println(member.getBalance() + "$" + " - " + gamePrice + "$ = " + (memberBalance-gamePrice) + "$\n");
                    System.out.println(COLOR + "POINTS REWARD" + RESET);
                    System.out.println(member.getPoints() + " + " + newPoints + " = " + (memberPoints + newPoints) + "\n");

                    System.out.println(COLOR + "ADDED COUPON: " + RESET);
                    System.out.println(users.getCouponById(randomNum) + "\n");

                    member.setBalance(memberBalance-gamePrice);
                    member.setPoints(memberPoints + newPoints);
                    System.out.println(COLOR + "SUCCESFULLY PURCHASED " + RESET);
                    System.out.println(game.getName().toUpperCase());
                    Scan.enter();   
                }
                else {
                    Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                    Displayer.displayString(products.getGameData(Arrays.asList(game), "name"));

                    System.out.println(COLORRED + "UPDATED BALANCE" + RESET);
                    System.out.println(member.getBalance() + "$" + " - " + gamePrice + "$ = " + (memberBalance-gamePrice) + "$\n");
                    System.out.println(COLORRED + "FAILED PURCHASE"  + RESET);
                    System.out.println(game.getName().toUpperCase());
                    Scan.enter();
                }
            break;
        }
    }

    /**
     * Handles the logic for purchasing consoles.
     *
     * @param products       The product controller object containing console data
     * @param users          The user controller object containing member data
     * @param memberProfile  The member profile attempting to purchase the console
     */
    public static void buyConsoles(ProductController products, UserController users, Profile memberProfile){
        Member member = (Member) memberProfile;
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " BUY:" + RESET);
        Displayer.displayOptions(products.getProductStockOnly(products.getAllConsoles()));
        System.out.print("Enter the console name: ");
        String consoleChoice = Scan.getValidString(products.getProductStockOnly(products.getAllConsoles()));
        Product console = products.filterByName(consoleChoice, products.getAllConsoles());
        Scan.clear();

        double memberBalance = member.getBalance();
        int memberPoints = member.getPoints();
        double consolePrice = console.getPrice();
        int consolePricePoints = ((int) Math.round(consolePrice) * 100);

        Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
        Displayer.displayString(products.getConsoleData(Arrays.asList(console), "name"));

        String[] buyOptions = new String[] {"Apply Balance", "Apply Points", "Apply Coupon", "Go Back"};
        System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " PAYMENT TYPE:" + RESET);
        Displayer.displayOptions(Arrays.asList(buyOptions));
        System.out.print("Method of payment: ");
        String buyOption = Scan.getValidString(Arrays.asList(buyOptions));
        Scan.clear();

        switch (buyOption) { //When buying a console, it has the same logic as buying a game.
            case "apply balance" : 
                if (consolePrice <= memberBalance) {
                int newPoints = (int) Math.round(consolePrice) * 10;
                console.setQuantity(console.getQuantity()-1);

                Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                Displayer.displayString(products.getConsoleData(Arrays.asList(console), "name"));

                System.out.println(COLOR + "UPDATED BALANCE" + RESET);
                System.out.println(member.getBalance() + "$" + " - " + consolePrice + "$ = " + (memberBalance-consolePrice) + "$\n");
                System.out.println(COLOR + "POINTS REWARD" + RESET);
                System.out.println(member.getPoints() + " + " + newPoints + " = " + (memberPoints + newPoints) + "\n");  
                member.setBalance(memberBalance-consolePrice);
                member.setPoints(memberPoints + newPoints);
                System.out.println(COLOR + "SUCCESFULLY PURCHASED: \n"  + RESET + console.getName().toUpperCase());
                Scan.enter();   
                }
                else {
                    Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                    Displayer.displayString(products.getConsoleData(Arrays.asList(console), "name"));

                    System.out.println(COLORRED + "UPDATED BALANCE" + RESET);
                    System.out.println(member.getBalance() + "$" + " - " + consolePrice + "$ = " + (memberBalance-consolePrice) + "$\n");
                    System.out.println(COLORRED + "FAILED PURCHASE: "  + RESET);
                    System.out.println(console.getName().toUpperCase());
                    Scan.enter();
                }
            break;

            case "apply points" :
                if (consolePricePoints <= memberPoints) {
                    Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                    Displayer.displayString(products.getConsoleData(Arrays.asList(console), "name"));

                    System.out.println(COLOR + "POINTS REWARD" + RESET);
                    System.out.println(memberPoints + " - " + consolePricePoints + " = " + (memberPoints - consolePricePoints));  
                    member.setPoints(memberPoints - consolePricePoints);
                    console.setQuantity(console.getQuantity()-1);
                    System.out.println(COLOR + "\nSUCCESFULLY PURCHASED \n"  + RESET + console.getName().toUpperCase());
                    Scan.enter();  
                }
                else {
                    Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                    Displayer.displayString(products.getConsoleData(Arrays.asList(console), "name"));

                    System.out.println(COLORRED + "POINTS REWARD" + RESET);
                    System.out.println(memberPoints + " - " + consolePricePoints + " = " + (memberPoints - consolePricePoints) +"\n"); 
                    System.out.println(COLORRED + "FAILED PURCHASE "  + RESET);
                    System.out.println(console.getName().toUpperCase());
                    Scan.enter();
                }
            break;

            case "apply coupon" :
                Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                Displayer.displayString(products.getConsoleData(Arrays.asList(console), "name"));

                List<String> discounts = member.getCouponsList(member.getCoupons());
                System.out.println(COLOR + "OP:  " + RESET + "|" + COLOR + " PERCENTAGE:" + RESET);
                Displayer.displayOptions(discounts);
                System.out.print("Choose percentage: ");
                String discountOption = Scan.getValidString(discounts);
                Scan.clear();

                member.deleteCoupon(Double.parseDouble(discountOption));
                consolePrice = console.getPrice() - (console.getPrice() * (Double.parseDouble(discountOption)/100));
                int randomNum = Scan.getRandomInt(users.getCoupons().size());
                member.addCoupon(users.getCouponById(randomNum));
                

                if (consolePrice <= memberBalance) {
                    int newPoints = (int) Math.round(consolePrice) * 10;
                    console.setQuantity(console.getQuantity()-1);

                    Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                    Displayer.displayString(products.getConsoleData(Arrays.asList(console), "name"));

                    System.out.println(COLOR + "BALANCE WITH DISCOUNT OF " + discountOption + "%" + RESET);
                    System.out.println(member.getBalance() + "$" + " - " + consolePrice + "$ = " + (memberBalance-consolePrice) + "$\n");
                    System.out.println(COLOR + "POINTS REWARD" + RESET);
                    System.out.println(member.getPoints() + " + " + newPoints + " = " + (memberPoints + newPoints) + "\n");

                    System.out.println(COLOR + "ADDED COUPON: " + RESET);
                    System.out.println(users.getCouponById(randomNum) + "\n");

                    member.setBalance(memberBalance-consolePrice);
                    member.setPoints(memberPoints + newPoints);
                    System.out.println(COLOR + "SUCCESFULLY PURCHASED " + RESET);
                    System.out.println(console.getName().toUpperCase());
                    Scan.enter();   
                }
                else {
                    Displayer.displayString(users.getMemberData(Arrays.asList(memberProfile), "firstName"));
                    Displayer.displayString(products.getConsoleData(Arrays.asList(console), "name"));

                    System.out.println(COLORRED + "UPDATED BALANCE" + RESET);
                    System.out.println(member.getBalance() + "$" + " - " + consolePrice + "$ = " + (memberBalance-consolePrice) + "$\n");
                    System.out.println(COLORRED + "FAILED PURCHASE"  + RESET);
                    System.out.println(console.getName().toUpperCase());
                    Scan.enter();
                }
            break;
        }
    }
}

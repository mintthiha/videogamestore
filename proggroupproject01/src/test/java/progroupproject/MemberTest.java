package progroupproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import progroupproject.Users.*;

public class MemberTest {
    
    public Profile getMember(){
        Coupon coupon1 = new Coupon(0, 0.15);
        Coupon coupon2 = new Coupon(1, 0.20);
        Profile myMember = new Member(0, "Jenny-Roxanne", "Rala", "jennyrr@gmail.com", "MEMBER15", 10000, 40000, Arrays.asList(coupon1, coupon2));
        return myMember;
    }

    @Test
    public void testIdGetter(){
        Profile myMember = getMember();
        assertEquals("Test the id getter for member class", 0, myMember.getId());
    }

    @Test
    public void testIdSetter(){
        Profile myMember = getMember();
        myMember.setId(1);
        assertEquals("Test the id setter for member class", 1, myMember.getId());

    }

    @Test
    public void testFirstNameGetter(){
        Profile myMember = getMember();
        assertEquals("Test the first name getter for the member class", "Jenny-Roxanne", myMember.getFirstName());
    }

     @Test
    public void testFirstNameSetter(){
        Profile myMember = getMember();
        myMember.setFirstName("Thiha");
        assertEquals("Test the first name setter for the member class", "Thiha", myMember.getFirstName());
    }

    @Test
    public void testLastNameGetter(){
        Profile myMember = getMember();
        assertEquals("Test the last name getter for the member class", "Rala", myMember.getLastName());
    }

    @Test
    public void testLastNameSetter(){
        Profile myMember = getMember();
        myMember.setLastName("Min Thein");
        assertEquals("Test the last name setter for the member class", "Min Thein", myMember.getLastName());
    }

    @Test
    public void testEmailGetter(){
        Profile myMember = getMember();
        assertEquals("Test the email getter for the member class", "jennyrr@gmail.com", myMember.getEmail());
    }
    
    @Test
    public void testEmailSetter(){
        Profile myMember = getMember();
        myMember.setEmail("t@t.ca");
        assertEquals("Test the email setter for the member class", "t@t.ca", myMember.getEmail());
    }

    @Test
    public void testMemberIdGetter(){
        Profile myMember = getMember();
        if(myMember instanceof Member){
            Member mem = (Member) myMember;
            assertEquals("Test the memberid getter for the member class", "MEMBER15", mem.getMemberId());
        }
    }

    @Test
    public void testMemberIdSetter(){
        Profile myMember = getMember();
        if(myMember instanceof Member){
            Member mem = (Member) myMember;
            mem.setMemberId("MEMBER70");
            assertEquals("Test the memberid setter for the member class", "MEMBER70", mem.getMemberId());
        }
    }

    @Test
    public void testPointGetter(){
        Profile myMember = getMember();
        if(myMember instanceof Member){
            Member mem = (Member) myMember;
            assertEquals("Test the point getter for the member class", 10000, mem.getPoints());
        }
    }

    @Test
    public void testPointSetter(){
        Profile myMember = getMember();
        if(myMember instanceof Member){
            Member mem = (Member) myMember;
            mem.setPoints(20);
            assertEquals("Test the point setter for the member class", 20, mem.getPoints());
        }
    }

    @Test
    public void testBalanceGetter(){
        Profile myMember = getMember();
        if(myMember instanceof Member){
            Member mem = (Member) myMember;
            assertEquals("Test the balance getter for the member class", 40000, mem.getBalance(), 0.01);
        }
    }

    @Test
    public void testBalanceSetter(){
        Profile myMember = getMember();
        if(myMember instanceof Member){
            Member mem = (Member) myMember;
            mem.setBalance(19);
            assertEquals("Test the balance setter for the member class", 19, mem.getBalance(), 0.01);
        }
    }

    @Test
    public void testCouponsGetter(){
        Profile myMember = getMember();
        if(myMember instanceof Member){
            List<Coupon> expectedCoups = Arrays.asList(new Coupon(0, 0.15), new Coupon(1, 0.20));
            Member mem = (Member) myMember;
            assertEquals("Test the memberid getter for the member class", expectedCoups + "", mem.getCoupons() + "");
        }
    }

    @Test
    public void testCouponsSetter(){
        Profile myMember = getMember();
        if(myMember instanceof Member){
            List<Coupon> expectedCoups = Arrays.asList(new Coupon(9, 0.60), new Coupon(10, 0.75));
            Member mem = (Member) myMember;
            mem.setCoupons(Arrays.asList(new Coupon(9, 0.60), new Coupon(10, 0.75)));
            assertEquals("Test the memberid setter for the member class", expectedCoups + "", mem.getCoupons() + "");
        }
    }

    @Test 
    public void testConstructorId(){
        try{
            Member mem = new Member(-10, "Bobby", "Amends", "BobbyA@email.com", "MEMBER10", 1000, 34, Arrays.asList(new Coupon(0, 0.15), new Coupon(1, 0.20)));
            System.out.println(mem);
            fail("Member id is negative, this should throw an illegal argument exception!");
        } catch (IllegalArgumentException e){
            System.out.println("Passed succesfully!");
        }
    }

    @Test
    public void testConstructorPoints(){
        try{
            Member mem = new Member(1, "Bobby", "Amends", "BobbyA@email.com", "MEMBER10", -1000, 34, Arrays.asList(new Coupon(0, 0.15), new Coupon(1, 0.20)));
            System.out.println(mem);
            fail("Points is negative, this should throw an illegal argument exception!");
        } catch (IllegalArgumentException e){
            System.out.println("Passed succesfully!");
        }
    }

    @Test
    public void testConstructorBalance(){
        try{
            Member mem = new Member(1, "Bobby", "Amends", "BobbyA@email.com", "MEMBER10", 1000, -34, Arrays.asList(new Coupon(0, 0.15), new Coupon(1, 0.20)));
            System.out.println(mem);
            fail("Balance is negative, this should throw an illegal argument exception!");
        } catch (IllegalArgumentException e){
            System.out.println("Passed succesfully!");
        }
    }

    @Test
    public void testConstructorCoupons(){
        try{
            Member mem = new Member(1, "Bobby", "Amends", "BobbyA@email.com", "MEMBER10", 1000, 34, Arrays.asList(new Coupon(0, -0.15), new Coupon(1, -0.20)));
            System.out.println(mem);
            fail("One or many Coupons is negative, this should throw an illegal argument exception!");
        } catch (IllegalArgumentException e){
            System.out.println("Passed succesfully!");
        }
    }
}

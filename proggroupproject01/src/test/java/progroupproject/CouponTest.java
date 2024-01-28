package progroupproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import progroupproject.Users.Coupon;


public class CouponTest {

    private Coupon getCoupon(){
        Coupon myCoupon = new Coupon(0, 0.15);
        return myCoupon;
    }

    @Test
    public void testIdGetter(){
        Coupon myCoupon = getCoupon();
        assertEquals("Testing id getter for coupon class", 0, myCoupon.getCouponId());
    }

    @Test
    public void testIdSetter(){
        Coupon myCoupon = getCoupon();
        myCoupon.setCouponId(1);
        assertEquals("Testing id setter for coupon class", 1, myCoupon.getCouponId());
    }

    @Test
    public void testDiscountGetter(){
        Coupon myCoupon = getCoupon();
        assertEquals("Testing discount getter for coupon class", 0.15, myCoupon.getDiscount(), 0.01);
    }

    @Test
    public void testDiscountSetter(){
        Coupon myCoupon = getCoupon();
        myCoupon.setDiscount(0.20);
        assertEquals("Testing discount setter for coupon class", 0.20, myCoupon.getDiscount(), 0.01);
    }

    @Test 
    public void testToString(){
        Coupon myCoupon = getCoupon();
        assertEquals("Testing the toString for coupon class", "0.15%", myCoupon.toString());
    }

    @Test 
    public void testConstructorId(){
        try{
            Coupon myCoupon = new Coupon(-10, 0.15);
            System.out.println(myCoupon);
            fail("This should throw an illegal argument exception! The id is negative.");
        } catch (IllegalArgumentException e){
            System.out.println("Passed : ) ");
        }
    }

    @Test 
    public void testConstructorDiscount(){
        try{
            Coupon myCoupon = new Coupon(10, -0.15);
            System.out.println(myCoupon);
            fail("This should throw an illegal argument exception! The discount is negative.");
        } catch (IllegalArgumentException e){
            System.out.println("Passed : ) ");
        }
    }
}

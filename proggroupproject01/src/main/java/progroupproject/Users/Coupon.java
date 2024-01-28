package progroupproject.Users;

public class Coupon {
    private int couponId;
    private double discount;

    public Coupon(int couponId, double discount) {
        validateArguments(couponId, discount);
        this.couponId = couponId;
        this.discount = discount;
    }

    // Getters
    public int getCouponId() {
        return couponId;
    }
    public double getDiscount() {
        return discount;
    }

    //Setters
    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    public String toString () {
        return this.discount + "%";
    }

    private void validateArguments(int id, double disc){
        if(id < 0){
            throw new IllegalArgumentException("The coupon id cannot be negative!");
        } else if (disc < 0) {
            throw new IllegalArgumentException("The discount cannot be negative!");
        }
    }
}
                                                                                              
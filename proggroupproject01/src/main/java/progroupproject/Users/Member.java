package progroupproject.Users;

import java.util.ArrayList;
import java.util.List;

public class Member extends Profile{
    private String memberId;
    private int points;
    private List<Coupon> coupons;

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    private double balance;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public Member(int id, String firstName, String lastName, String email, String memberId, int points, double balance, List<Coupon> coupons) {
        super(id, firstName, lastName, email);
        validateArguments(points, balance);
        this.memberId = memberId;
        this.points = points;
        this.balance = balance;
        this.coupons = coupons;
    }

    public Member() {
        super(0, "TEST", "TEST", "TEST");
        this.memberId = "MEMBERTEST";
        this.points = 0;
        this.coupons = new ArrayList<>();
    }

    // Getters
    public String getMemberId() {
        return memberId;
    }
    public int getPoints() {
        return points;
    }
    public double getBalance() {
        return balance;
    }
    public List<Coupon> getCoupons() {
        return coupons;
    }

    public String getCouponsString(List<Coupon> coupons) {
        String names = "";
        for (Coupon coupon : coupons) {
            names += (coupon.getDiscount() + "%, ");
        }
        return names;
    }

    public List<String> getCouponsList(List<Coupon> coupons) {
        List<String> list = new ArrayList<>();
        for (Coupon coupon : coupons) {
            list.add(coupon.getDiscount()+"");
        }
        return list;
    }


    public void deleteCoupon (double discount) {
        Coupon couponToDelete = new Coupon(0, 0);
        for (Coupon coupon : this.coupons) {
            if (discount == coupon.getDiscount()) {
                couponToDelete = coupon;
            }
        }
        this.coupons.remove(couponToDelete);
    }

    public void addCoupon(Coupon coupon) {
        this.coupons.add(coupon);
    }

    private void validateArguments(int points, double balance){
        if(points < 0){
            throw new IllegalArgumentException("The points cannot be negative!!");
        } else if (balance < 0){
            throw new IllegalArgumentException("The balance cannot be negative!!");
        }
    }
}

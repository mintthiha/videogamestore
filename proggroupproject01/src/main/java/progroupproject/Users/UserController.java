package progroupproject.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import progroupproject.Files.FileImporter;

public class UserController {
    public static final String COLOR = "\u001B[32m";
    public static final String COLORRED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    List<Profile> admins;
    List<Profile> members;
    List<Coupon> coupons;

    // Constructor
    public UserController () {
        this.admins = FileImporter.retrieveAllAdmins("./proggroupproject01/src/main/java/progroupproject/Data/Admin.txt");
        this.coupons = FileImporter.retrieveAllCoupons("./proggroupproject01/src/main/java/progroupproject/Data/Coupon.txt");
        this.members = FileImporter.retrieveAllMembers("./proggroupproject01/src/main/java/progroupproject/Data/Member.txt", this.coupons); 
    }

    // Getters
    public List<Profile> getAdmins() {
        return admins;
    }
    public List<Profile> getMembers() {
        return members;
    }
    public List<Coupon> getCoupons() {
        return coupons;
    }

    // Getters last Id only
    public int getLastMemberId () {
        return members.get(members.size()-1).getId();
    }
    public int getLastAdminId () {
        return admins.get(admins.size()-1).getId();
    }
    public int getLastCouponId () {
        return coupons.get(coupons.size()-1).getCouponId();
    }

    // Getters of Profile fields only
    public List<String> getFirstNames (List<Profile> users) {
        List<String> names = new ArrayList<>();
        for (Profile user : users) {
            names.add(user.getFirstName());
        }
        return names;
    }
    public List<String> getLastNames (List<Profile> users) {
        List<String> names = new ArrayList<>();
        for (Profile user : users) {
            names.add(user.getLastName());
        }
        return names;
    }
    public List<String> getEmails (List<Profile> users) {
        List<String> names = new ArrayList<>();
        for (Profile user : users) {
            names.add(user.getEmail());
        }
        return names;
    }

    public List<String> getTypeEmails (String type) {
        List<String> emails = new ArrayList<>();
        List<Profile> users = new ArrayList<>();
        switch (type) {
            case "member":
                users = this.members;
                break;
            case "admin" :
                users = this.admins;
                break;
        }
        for (Profile user : users) {
            emails.add(user.getEmail());
        }
        return emails;
    }

    // Getters for admins only:
    public List<String> getUsernames (List<Admin> users) {
        List<String> names = new ArrayList<>();
        for (Admin user : users) {
            names.add(user.getUserName());
        }
        return names;
    }
    public List<String> getPasswords (List<Admin> users) {
        List<String> passwords = new ArrayList<>();
        for (Admin user : users) {
            passwords.add(user.getPassword());
        }
        return passwords;
    }

    // Getters for members only:

    public List<String> getAllMemberIds(){
        List<String> allMemberIds = new ArrayList<>();
        List<Member> members = convertToMember(this.members);
        for(Member member : members){
            allMemberIds.add(member.getMemberId());
        }
        return allMemberIds;
    }
    
    public List<String> getAllUsernames () {
        List<String> usernames = new ArrayList<>();
        List<Admin> admins = convertToAdmin(this.admins);
        for (Admin admin : admins) {
            usernames.add(admin.getUserName());
        }
        return usernames;
    }
    public List<String> getMemberIds (List<Member> users) {
        List<String> names = new ArrayList<>();
        for (Member user : users) {
            names.add(user.getMemberId());
        }
        return names;
    }
    public List<String> getBalances (List<Member> users) {
        List<String> names = new ArrayList<>();
        for (Member user : users) {
            names.add(user.getBalance()+"$");
        }
        return names;
    }
    public List<String> getPoints (List<Member> users) {
        List<String> names = new ArrayList<>();
        for (Member user : users) {
            names.add(user.getBalance()+" ");
        }
        return names;
    }

    // Getters for coupons only

    public List<String> getCoupons (List<Member> users) {
        List<String> names = new ArrayList<>();
        for (Member user : users) {
            String listOfCoupons = "";
            for (Coupon coupon : user.getCoupons()) {
                listOfCoupons += coupon.getDiscount() + "%, ";
            }
            names.add(listOfCoupons);
        }
        return names;
    }

    public List<String> getCouponsNames () {
        List<String> list = new ArrayList<>();
        for (Coupon coupon : coupons) {
            list.add(coupon.getDiscount() + "");
        }
        return list;
    }

    public Coupon getCouponById (int id) {
        Coupon emptyCoupon = new Coupon(0, 0);
        for (Coupon coupon : this.coupons) {
            if (coupon.getCouponId() == id) {
                emptyCoupon = coupon;
            }
        }
        return emptyCoupon;
    }

    // Filters 

    public Admin getAdmin(String username, String password){
        Admin myAdmin = new Admin();
        List<Admin> admins = convertToAdmin(this.admins);
        for (Admin admin : admins){
            if(admin.getUserName().equals(username) && admin.getPassword().equals(password)){
                myAdmin = admin;
            }
        }
        return myAdmin;
    }

    public Admin getAdmin(String username){
        Admin myAdmin = new Admin();
        List<Admin> admins = convertToAdmin(this.admins);
        for (Admin admin : admins){
            if(admin.getUserName().equals(username)){
                myAdmin = admin;
            }
        }
        return myAdmin;
    }

    public Member getMember(String memberId){
        Member myMember = null;
        List<Member> members = convertToMember(this.members);
        for (Member member : members){
            if(member.getMemberId().equalsIgnoreCase(memberId)){
                myMember = member;
            }
        }
        return myMember;
    }

    public boolean checkIfValidMember(String memberId) {
        boolean ifValid = false;
        List<Member> members = convertToMember(this.members);
        for(Member member : members){
            if(member.getMemberId().toLowerCase().equals(memberId)){
                ifValid = true;
            }
        }
        return ifValid;
    }


    // Converts to list of child instances 
     public List<Admin> convertToAdmin (List<Profile> users) {
        List<Admin> admins = new ArrayList<>();
        for (Profile user : users) {
            admins.add((Admin) user);
        }
        return admins;
    }

    public List<Member> convertToMember (List<Profile> users) {
        List<Member> members = new ArrayList<>();
        for (Profile user : users) {
            members.add((Member) user);
        }
        return members;
    }

    // Calculates the needed indent:
    public int getMaxLength (List<String> names) {
        String max = "";
        for (String name : names) {
            if (name.length() > max.length()) {
                max = name;
            }
        }
        return max.length();
    }
    public String getIndent (int maxLength, String target) {
        while (target.length() <= maxLength) {
            target += " ";
        }
        target += "| ";
        return target;
    }

    // Add users
    public void addMember (Member member) {
        members.add(member);
    }
    public void addAdmin (Admin admin) {
        admins.add(admin);
    }
    public void addCoupon (Coupon coupon) {
        coupons.add(coupon);
    }

    // Decides in what order admins should be displayed
    public List<String> decideMemberOrder (String order, String firstName, String lastName, String email, String memberId, String balance, String points, String coupons, List<String> list) {
        switch (order) {
            case "firstName":
                list.add(firstName + lastName + email + memberId + balance + points + coupons);
                break;
            case "lastName":
                list.add(lastName + firstName + email + memberId + balance + points + coupons);
                break;
            case "email":
                list.add(email +  firstName + lastName + memberId + balance + points + coupons);
                break;
            case "memberId":
                list.add( memberId + firstName + lastName + email + balance + points + coupons);
                break;
            case "balance":
                list.add(balance + firstName + lastName + email + memberId + points + coupons);
                break;
            case "points" :
                list.add(points + firstName + lastName + email + memberId + balance + coupons);
                break;
            case "coupons" :
                list.add(coupons + firstName + lastName + email + memberId + balance + points);
                break;
        }
        return list;
    }

    // Returns String list of members
    public List<String> getMemberData (List<Profile> users, String order){
        Member minMember = new Member(0, "FIRST NAME", "LAST NAME", "EMAIL", "MEMBERID", 0, 0.0, Arrays.asList(new Coupon(0, 0)));
        List<String> list = new ArrayList<>();
        users = new ArrayList<>(users);
        users.add(minMember);
        List<Member> members = convertToMember(users);

        String firstName= getIndent(getMaxLength(getFirstNames(users))+9, COLOR + "FIRST NAME" + RESET);
        String lastName = getIndent(getMaxLength(getLastNames(users))+9, COLOR + "LAST NAME" + RESET);
        String email = getIndent(getMaxLength(getEmails(users))+9, COLOR + "EMAIL" + RESET);
        String memebrId = getIndent(getMaxLength(getMemberIds(members))+9, COLOR + "MEMBERID" + RESET);
        String balance = getIndent(getMaxLength(getBalances(members))+10, COLOR + "BALANCE" + RESET);
        String points = getIndent(getMaxLength(getPoints(members))+9, COLOR + "POINTS" + RESET);
        String coupons = getIndent(getMaxLength(getCoupons(members))+9, COLOR + "COUPONS" + RESET);
        list = decideMemberOrder(order, firstName, lastName, email, memebrId, balance, points, coupons, list);
        for (Member member : members){
            firstName= getIndent(getMaxLength(getFirstNames(users)), member.getFirstName());
            lastName = getIndent(getMaxLength(getLastNames(users)), member.getLastName());
            email = getIndent(getMaxLength(getEmails(users)), member.getEmail());
            memebrId = getIndent(getMaxLength(getMemberIds(members)), member.getMemberId());
            balance = getIndent(getMaxLength(getBalances(members)) + 1, member.getBalance() + "$");
            points = getIndent(getMaxLength(getPoints(members)), member.getPoints() + " ");
            coupons = getIndent(getMaxLength(getCoupons(members)), member.getCouponsString(member.getCoupons()) + "");
            list = decideMemberOrder(order, firstName, lastName, email, memebrId, balance, points, coupons, list);
        }
        users.remove(users.size()-1);
        list.remove(list.size()-1);
        return list;
    }


    // Decides in what order admins should be displayed
    public List<String> decideAdminOrder (String order, String firstName, String lastName, String email, String username, String password, List<String> list) {
        switch (order) {
            case "firstName":
                list.add(firstName + lastName + email + username + password);
                break;
            case "lastName":
                list.add(lastName + firstName + email + username + password);
                break;
            case "email":
                list.add(email +  firstName + lastName +  username + password);
                break;
            case "username":
                list.add(username + password + firstName + lastName + email);
                break;
            case "password":
                list.add(password + username + firstName + lastName + email);
                break;
        }
        return list;
    }

    // Returns String list of admins
    public List<String> getAdminData (List<Profile> users, String order){
        Profile minProfile = new Admin(0, "FIRST NAME", "LAST NAME", "EMAIL", "USERNAME", "PASSWORD");
        List<String> list = new ArrayList<>();
        users = new ArrayList<>(users);
        users.add(minProfile);
        List<Admin> admins = convertToAdmin(users);

        String firstName= getIndent(getMaxLength(getFirstNames(users))+9, COLOR + "FIRST NAME" + RESET);
        String lastName = getIndent(getMaxLength(getLastNames(users))+9, COLOR + "LAST NAME" + RESET);
        String email = getIndent(getMaxLength(getEmails(users))+9, COLOR + "EMAIL" + RESET);
        String username = getIndent(getMaxLength(getUsernames(admins))+9, COLOR + "USERNAME" + RESET);
        String password = getIndent(getMaxLength(getPasswords(admins))+9, COLOR + "PASSWORD" + RESET);
        list = decideAdminOrder(order, firstName, lastName, email, username, password, list);
        for (Admin admin : admins){
            firstName= getIndent(getMaxLength(getFirstNames(users)), admin.getFirstName());
            lastName = getIndent(getMaxLength(getLastNames(users)), admin.getLastName());
            email = getIndent(getMaxLength(getEmails(users)), admin.getEmail());
            username = getIndent(getMaxLength(getUsernames(admins)), admin.getUserName());
            password = getIndent(getMaxLength(getPasswords(admins)), admin.getPassword());
            list = decideAdminOrder(order, firstName, lastName, email, username, password, list);
        }
        users.remove(users.get(users.size()-1));
        list.remove(list.size()-1);
        return list;
    }
}

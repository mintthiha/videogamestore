package progroupproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import progroupproject.Users.Admin;
import progroupproject.Users.Profile;

public class AdminTest {

    public Profile getAdmin(){
        Profile myAdmin = new Admin(0, "Thiha", "Kim", "2@email.com", "mintthers", "PASSWORD");
        return myAdmin;
    }

    @Test
    public void testIdGetter(){
        Profile myAdmin = getAdmin();
        assertEquals("Test id getter for admin class", 0, myAdmin.getId());
    }
 
    @Test 
    public void testIdSetter(){
        Profile myAdmin = getAdmin();
        myAdmin.setId(1);
        assertEquals("Test id setter for admin class", 1, myAdmin.getId());
    }

    @Test
    public void testFirstNameGetter(){
        Profile myAdmin = getAdmin();
        assertEquals("Test first name getter for admin class", "Thiha", myAdmin.getFirstName());
    }

    @Test 
    public void testFirstNameSetter(){
        Profile myAdmin = getAdmin();
        myAdmin.setFirstName("Garry");
        assertEquals("Test first name setter for admin class", "Garry", myAdmin.getFirstName());
    }

    @Test
    public void testLastNameGetter(){
        Profile myAdmin = getAdmin();
        assertEquals("Test last name getter for admin class", "Kim", myAdmin.getLastName());
    }

    @Test 
    public void testLastNameSetter(){
        Profile myAdmin = getAdmin();
        myAdmin.setLastName("Amandea");
        assertEquals("Test last name setter for admin class", "Amandea", myAdmin.getLastName());
    }

    @Test
    public void testEmailGetter(){
        Profile myAdmin = getAdmin();
        assertEquals("Test email getter for admin class", "2@email.com", myAdmin.getEmail());
    }

    @Test 
    public void testEmailSetter(){
        Profile myAdmin = getAdmin();
        myAdmin.setEmail("GA@e.ca");
        assertEquals("Test email setter for admin class", "GA@e.ca", myAdmin.getEmail());
    }

    @Test 
    public void testUserNameGetter(){
        Profile myAdmin = getAdmin();
        if (myAdmin instanceof Admin){
            Admin adm = (Admin) myAdmin;
            assertEquals("Test username getter for admin class", "mintthers", adm.getUserName());
        }
    }

    @Test
    public void testUserNameSetter(){
        Profile myAdmin = getAdmin();
        if (myAdmin instanceof Admin){
            Admin adm = (Admin) myAdmin;
            adm.setUserName("MEMBER129");
            assertEquals("Test username setter for admin class", "MEMBER129", adm.getUserName());
        }
    }

    @Test 
    public void testPasswordGetter(){
        Profile myAdmin = getAdmin();
        if (myAdmin instanceof Admin){
            Admin adm = (Admin) myAdmin;
            assertEquals("Test password getter for admin class", "PASSWORD", adm.getPassword());
        }
    }

    @Test 
    public void testPasswordSetter(){
        Profile myAdmin = getAdmin();
        if (myAdmin instanceof Admin){
            Admin adm = (Admin) myAdmin;
            adm.setPassword("NEWPASSWORD");
            assertEquals("Test password setter for admin class", "NEWPASSWORD", adm.getPassword());
        }
    }

    @Test
    public void testContructorId(){
        try{
            Admin adm = new Admin(-10, "Dmitriy", "Min Thein", "hehe@yahoo.com", "123Bob", "notAPassWord");
            System.out.println(adm);
            fail("The id is negative, should throw illegal argument exception.");
        } catch (IllegalArgumentException e){
            System.out.println("Passed!");
        }
    }
}

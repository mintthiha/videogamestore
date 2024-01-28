package progroupproject.Users;

public class Admin extends Profile{
    private String userName;
    private String password;

    public Admin(int id, String firstName, String lastName, String email, String userName, String password) {
        super(id, firstName, lastName, email);
        this.userName = userName;
        this.password = password;
    }

    public Admin(){
        super(0, "NOT FOUND", "NOT FOUND", "NOT FOUND");
        this.userName = "NOT FOUND";
        this.password = "NOT FOUND";
    }

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isEquals(Object o){
        if(o instanceof Admin){
            Admin myAdmin = (Admin) o;
            if(myAdmin.getUserName().equals(this.userName) && myAdmin.getPassword().equals(this.password)){
                return true;
            }
        }
        return false;
    }
}

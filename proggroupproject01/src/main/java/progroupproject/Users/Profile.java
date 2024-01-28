package progroupproject.Users;

public abstract class Profile {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

     public Profile(int id, String firstName, String lastName, String email) {
        validateArguments(id);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Profile(){

    }
    
    // Getters
     public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    private void validateArguments(int id){
        if(id < 0){
            throw new IllegalArgumentException("The id cannot be negative.");
        }
    }

    public boolean equals(Object o){
        if (o instanceof Member){
            //Imma do it later.
            Member member = (Member) o;
            if (this.id == member.getId()) {
                return true;
            }
        }
        return false;
    }
}

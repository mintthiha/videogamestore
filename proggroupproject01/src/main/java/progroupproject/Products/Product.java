package progroupproject.Products;

/**
 * The base class representing a product within the system.
 * This class is inherited by Game and Console classes.
 */
public abstract class Product{
    private int id;
    private String name;
    private double price;
    private String company;
    private String date;
    private int quantity;

    /**
     * Constructs a product with specified attributes.
     * @param id The ID of the product.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param company The company of the product.
     * @param date The release date of the product.
     * @param quantity The quantity of the product available.
     */
    public Product(int id, String name, double price, String company, String date, int quantity) {
        validateArguments(id, price, quantity);
        this.id = id;
        this.name = name;
        this.price = price;
        this.company = company;
        this.date = date;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }

    public String getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString(){
        String builder = "";
        builder += "Name : " + this.name + ", Price : " + this.price + ", Company : " + this.company + ", Date : " + this.date;
        return builder;
    }    

    /**
     * this method is used when checked between 2 products.
     */
    public boolean equals(Object o){
        if (o instanceof Product){
            Product product = (Product) o;
            if (this.id == product.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
    * Validates the arguments of the product so that none is negative.
    * @param id The ID of the product.
    * @param price The price of the product.
    * @param quantity The quantity of the product.
    * @throws IllegalArgumentException if any argument is negative.
    */
    private void validateArguments(int id, double price, int quantity){
        if(id < 0){
            throw new IllegalArgumentException("Id cannot be negative!");
        } else if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative!");
        } else if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
    }
}

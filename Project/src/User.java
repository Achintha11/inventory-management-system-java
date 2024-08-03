import java.io.Serializable;


// The User class represents a user with a username, password, shopping cart, and a flag indicating their first purchase.
public class User implements Serializable {


    // Attributes of the User class
    private String username ;
    private String password ;
    private  ShoppingCart shoppingCart;
    private boolean firstPurchase ;


    // Constructor for initializing attributes of the User class
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.shoppingCart = new ShoppingCart();
        firstPurchase = true;
    }


    // Getter method for accessing the username attribute
    public String getUsername() {
        return username;
    }


    // Getter method for accessing the password attribute
    public String getPassword() {
        return password;
    }


    // Getter method for accessing the shoppingCart attribute
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


    // Setter method for updating the firstPurchase attribute
    public void setFirstPurchase(boolean firstPurchase) {
        this.firstPurchase = firstPurchase;
    }


    // Getter method for checking if it's the user's first purchase
    public boolean isFirstPurchase() {
        return firstPurchase;
    }

}

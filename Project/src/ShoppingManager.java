
import javax.swing.*;
import java.util.ArrayList;


// ShoppingManager interface defines methods for managing products and users
public interface ShoppingManager {


    // Method to find a product by its ID
    Product findProductById(String productId);


    // Method to add a product to the product list
    void addProduct(Product product);

    // Method to remove a product from the product list
    void removeProduct(String productID);

    // Method to print the product list
    void printProductList();

    // Method to save the product list to a file
    void saveToFileProducts(String fileName);

    // Method to load the product list from a file
    void loadFromFileProducts(String fileName);

    // Method to display products in a table for GUI
    void displayProductsTable(JTable table);

    // Method to get the product list
    ArrayList<Product> getProductList();


    // Method to get the user list
    ArrayList<User> getUserList();


    // Method to check if a user with the given username exists
    User checkUser(String username);


    // Method to save the user list to a file
    void saveToFileUsers(String fileName);



    // Method to load the user list from a file
    void loadFromFileUsers(String fileName);
}
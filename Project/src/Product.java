import java.io.Serializable;

// The abstract class Product represents a general product with basic attributes.
public abstract class Product implements Serializable {


    // Attributes of the Product class
    private String productId ;
    private String productName ;
    private int availableItems;
    private double price;

    // Constructor for initializing the attributes of the Product class
    public Product(String productId, String productName, int availableItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
    }

    // Getter methods for accessing private attributes
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public double getPrice() {
        return price;
    }

    // Setter method for updating the available items
    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    // Method to represent the Product object as a string
    public String toString() {
        return "Product ID: " + productId + "\n\n" +
                "Product Name: " + productName + "\n\n" +
                "Available Items: " + availableItems + "\n\n" +
                "Price: " + price + "\n\n" +
                "Category: " + getCategory() + "\n\n";
    }


    // Abstract method to get information to table
    public abstract  String getInfoTable();

    // Abstract method to get the category of the product
    public abstract String getCategory();

    // Method to decrease the available items by 1
    public void decreaseAvailableItems() {
        if (availableItems > 0) {
            setAvailableItems(getAvailableItems()-1);
        } else {
            System.out.println("No more available items for product: " + productName);
        }

        System.out.println("Decreasing available items for: " + productName);
    }

    // Abstract method to get information to shoppingcart
    public abstract   String getShoppingCartInfo();



}

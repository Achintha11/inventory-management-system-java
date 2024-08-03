import java.io.Serializable;


// The ShoppingCartItem class represents an item in a shopping cart, associating a product and its quantity.
public class ShoppingCartItem implements Serializable {


    // The product associated with the shopping cart item
    private Product product;

    // The quantity of the product in the shopping cart
    private int quantity;


    // Constructor to initialize the shopping cart item with a product and quantity
    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity; 
    }


    // Getter method to retrieve the associated product
    public Product getProduct() {
        return product;
    }


    // Getter method to retrieve the quantity of the product
    public int getQuantity() {
        return quantity;
    }



    // Setter method to update the quantity of the product
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

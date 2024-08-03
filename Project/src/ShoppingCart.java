import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.util.ArrayList;


// The ShoppingCart class represents a shopping cart that holds ShoppingCartItems.
public class ShoppingCart  implements Serializable {


    // List to store shopping cart items
    private ArrayList<ShoppingCartItem> cartItems;


    // Constructor to initialize the shopping cart
    public ShoppingCart(){
        this.cartItems = new ArrayList<>();
    }


    // Method to add a product to the shopping cart
    public void addProductToCart(Product product) {
        ShoppingCartItem existingItem = findCartItemByProductId(product.getProductId());
        if (existingItem == null) {
            ShoppingCartItem newItem = new ShoppingCartItem(product, 1);
            cartItems.add(newItem);

        } else {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        }
    }


    // Method to calculate the total price of products in the shopping cart
    public double calculateTotalPrice(){
        double totalPrice=0;

        for (ShoppingCartItem item : cartItems){
            totalPrice +=item.getProduct().getPrice()*item.getQuantity();
        }

        return totalPrice;

    }

    // Method to calculate the discounted price based on the quantity of certain categories of products
    public double calculateDiscountedPrice() {
        double discountedPrice = 0;

        int eItems = 0;
        int cItems = 0;

        for (ShoppingCartItem item : cartItems) {
            if (item.getProduct().getCategory().equals("Electronics") && item.getQuantity() >= 3) {
                eItems = item.getQuantity();
            }
            if (item.getProduct().getCategory().equals("Clothing") && item.getQuantity() >= 3) {
                cItems = item.getQuantity();
            }
        }

        if (cItems >= 3 || eItems >= 3) {
            discountedPrice = calculateTotalPrice() * 0.2;
        }

        return discountedPrice;
    }


    // Method to calculate the discount for a user's first purchase
    public double calculateUserFirstPurchase(User user){
        double firstPurchaseDiscount=0;

        if (user.isFirstPurchase()==true){
            firstPurchaseDiscount=calculateTotalPrice() *0.1;
        }

        return  firstPurchaseDiscount;


    }



    // Method to calculate the total price after applying discounts
    public double calculateAfterDiscountPrice(User user) {
        double afterDiscountPrice = calculateTotalPrice() -(calculateDiscountedPrice() +calculateUserFirstPurchase(user)) ;
        return afterDiscountPrice;
    }



    // Method to display the shopping cart products in a table for GUI
    public void displayCartProductsTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Product");
        model.addColumn("Quantity");
        model.addColumn("Price");

        for (ShoppingCartItem item : cartItems) {
            Object[] row = new Object[3];
            row[0] = item.getProduct().getShoppingCartInfo();
            row[1] = item.getQuantity();
            row[2] = item.getProduct().getPrice()* item.getQuantity();
            model.addRow(row);
        }

        table.setModel(model);
    }


    // Method to find a shopping cart item by product ID
    public ShoppingCartItem findCartItemByProductId(String productId) {
        for (ShoppingCartItem item : cartItems) {
            if (item.getProduct().getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }



    // Method to clear the shopping cart
    public void clearShoppingCart (){
        cartItems.clear();
    }




}

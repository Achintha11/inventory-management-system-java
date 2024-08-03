// The Clothing class is a specific type of Product, representing clothing items.
public class Clothing extends  Product {

    // Additional attributes specific to Clothing
    private String size;
    private String color;


    // Constructor for initializing attributes of the Clothing class
    public Clothing(String productId, String productName,int availableItems, double price, String size, String color) {
        super(productId, productName,availableItems, price);
        this.size = size;
        this.color = color;
    }


    // Getter method for accessing the size attribute
    public String getSize() {
        return size;
    }


    // Setter method for updating the size attribute
    public void setSize(String size) {
        this.size = size;
    }


    // Getter method for accessing the color attribute
    public String getColour() {
        return color;
    }


    // Override the toString method to include additional information specific to Clothing
    @Override
    public String toString() {
        return super.toString() + "Size: " + size + "\n\n" +
                "Color: " + color + "\n\n";
    }

    // Override the getCategory method to specify the category as "Clothing"
    @Override
    public String getCategory() {
        return "Clothing";
    }


    // Override the getInfoTable method to get information to table specific to clothing
    @Override
    public String getInfoTable() {
        return getSize() + " , " +getColour();
    }



    // Override the getShoppingCartInfo method to get information to shoppingCart specific to clothing
    @Override
    public String getShoppingCartInfo() {
        return super.getProductName()+ " , " +super.getProductId()+ " , " +this.size + " , " + this.color;
    }
}

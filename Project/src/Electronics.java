// The Electronics class is a specific type of Product, representing electronic items.
public class Electronics extends Product  {


    // Additional attributes specific to Electronics
    private String brand;
    private int warrantyPeriod;

    // Constructor for initializing attributes of the Electronics class
    public Electronics(String productId, String productName, int availableItems, double price, String brand, int warrantyPeriod) {
        super(productId,productName,availableItems,price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Getter method for accessing the brand attribute
    public String getBrand() {
        return brand;
    }

    // Getter method for accessing the warrantyPeriod attribute
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }


    // Override the toString method to include additional information specific to Electronics
    @Override
    public String toString() {
        return super.toString() + "Brand: " + brand + "\n\n" +
                "Warranty Period: " + warrantyPeriod + "\n";
    }


    // Override the getCategory method to specify the category as "Electronics"
    @Override
    public String getCategory() {
        return "Electronics";
    }


    // Override the getInfoTable method to get information to table specific in Electronic
    @Override
    public String getInfoTable() {
        return getBrand() + " \n" +getWarrantyPeriod() + " weeks warranty";
    }


    // Override the getShoppingCartInfo method to get information to shoppingCart specific in Electronic
    @Override
    public String getShoppingCartInfo() {
        return super.getProductName()+ " , " +super.getProductId()+ " , " +this.brand + " , " + this.warrantyPeriod + " Weeks";
    }
}

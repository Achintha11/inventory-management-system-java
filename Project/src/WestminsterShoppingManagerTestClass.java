import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WestminsterShoppingManagerTestClass {

    private  WestminsterShoppingManager shoppingManager;

    @Before
    public  void setUp(){

        // Initialize ShoppingManager before each test
        shoppingManager = new WestminsterShoppingManager();
    }


    @Test
    public void testFindProductById() {
        // Test finding a product by its ID
        // Ensure that the correct product is retrieved

        // Add a test product
        Product product = new Electronics("E001", "Laptop", 10, 999.99, "Dell", 12);
        shoppingManager.addProduct(product);

        // Find the product by its ID
        Product foundProduct = shoppingManager.findProductById("E001");

        // Check if the found product matches the original product
        assertNotNull(foundProduct);
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getPrice(), foundProduct.getPrice(), 0.01);
        assertEquals(product.getAvailableItems(), foundProduct.getAvailableItems());
    }


    @Test
    public void testAddProduct(){
        // Test adding a product
        // Ensure that the product is added successfully
        Product product = new Electronics("E001","Laptop",10,100000,"Dell",12);
        shoppingManager.addProduct(product);

        // Check if the product is in the list
        assertNotNull(shoppingManager.findProductById("E001"));
    }

    @Test
    public void testRemoveProduct() {
        // Test removing a product from the WestminsterShoppingManager
        // Ensure that the product is removed successfully
        Product product = new Electronics("E002", "Desktop", 5, 799.99, "HP", 12);
        shoppingManager.addProduct(product);

        // Remove the product and check if it's no longer in the list
        shoppingManager.removeProduct("E002");
        assertNull(shoppingManager.findProductById("E002"));
    }

    @Test
    public void testSaveAndLoadToFile() {
        // Test saving and loading data
        // Ensure that the data is saved and loaded correctly

        // Add some test products
        Product product1 = new Electronics("E001", "Laptop", 10, 999.99, "Dell", 12);
        Product product2 = new Clothing("C001", "Jeans", 3, 39.99, "M", "Blue");

        shoppingManager.addProduct(product1);
        shoppingManager.addProduct(product2);

        // Save data to file
        shoppingManager.saveToFileProducts("saveTestData.txt");

        // Clear the  product list
        shoppingManager.getProductList().clear();


        // Load data from saved file
        shoppingManager.loadFromFileProducts("saveTestData.txt");

        // Check if the loaded data matches the original data
        assertEquals(product1.getProductName(), shoppingManager.findProductById("E001").getProductName());
        assertEquals(product2.getProductName(), shoppingManager.findProductById("C001").getProductName());
    }







}

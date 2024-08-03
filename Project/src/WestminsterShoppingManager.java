import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;


// The WestminsterShoppingManager class implements the ShoppingManager interface and manages products and users.
public class WestminsterShoppingManager implements ShoppingManager {


    // Lists to store products and users
    private static ArrayList<Product> productList=new ArrayList<Product>();
    private static ArrayList<User> userList = new ArrayList<>();



    // Method to display the main menu options
    public void displayMenu(){
        System.out.println();
        System.out.println("Welcome to Westminster Shopping Manager");
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print product list");
        System.out.println("4. Save to file");
        System.out.println("5. Open GUI");
        System.out.println("6. Exit");
        System.out.println();
    }


    // Method to find a product by its ID
    public Product findProductById(String productId){
        for (Product product : productList){
            if (product.getProductId().equals(productId)){
                return  product;
            }
        }
        return  null;
    }



    // Method to add a product to the product list
    public  void addProduct(Product product){

        int allItems=0;
        int availableSpace;

        for (Product pro : productList){
            allItems+=pro.getAvailableItems();
        }

        availableSpace=50-allItems;

        Product existingProduct = findProductById(product.getProductId());

        if (existingProduct!=null){
            int currentAvailableItems = existingProduct.getAvailableItems();

            if (currentAvailableItems<=availableSpace){
                existingProduct.setAvailableItems(currentAvailableItems + product.getAvailableItems());
                System.out.println("Product already exists. Available items updated successfully");
            }

            else {
                System.out.println("Maximum product count is 50 \n you can only add " +availableSpace + " products for now");
            }
        }

        else{

            if (product.getAvailableItems()<=availableSpace){
                productList.add(product);
                System.out.println("Product added Successfully");
            }

            else{
                System.out.println("Maximum product count is 50 \n you can only add " +availableSpace + " products for now");
            }
        }

    }


    // Method to remove a product from the product list
    public void removeProduct(String productID){
        boolean found = false;
        Iterator <Product>  iterator = productList.iterator();

        if (!productList.isEmpty()){

            while (iterator.hasNext()){
                Product product = iterator.next();
                if (product.getProductId().equals(productID)){
                    product.toString();
                    iterator.remove();
                    System.out.println("Product Removed Successfully");
                    found = true;
                    break;
                }
            }

            if (!found){
                System.out.println("There is No Product Matching the Entered Product ID !! ");
            }        }

        else {
            System.out.println("No products in the stock!");

        }

    }

    // Method to print the product list
    public void printProductList(){
        if (productList.isEmpty()){
            System.out.println("There are No Products in Stock!!");
        }

        else {
            productList.sort(Comparator.comparing(Product::getProductId));

            for (Product product : productList){
                System.out.println(product.toString());
            }
        }

    }


    // Method to save the product list to a file
    public void saveToFileProducts(String fileName){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            outputStream.writeObject(productList);
            System.out.println("Product list saved to a  file Successfully!");
        }
        catch (IOException e ){
            System.out.println("Error Saving Product list to File" + e.getMessage());
        }
    }



    // Method to load the product list from a file
    public void loadFromFileProducts(String fileName){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            productList = (ArrayList<Product>)inputStream.readObject();
            System.out.println("Product list loaded From file Successfully!");
        }

        catch (IOException | ClassNotFoundException e ){
            System.out.println("Error Loading Product List From File");
        }
    }



    // Method to display products in a table for GUI
    public void displayProductsTable(JTable table){
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Product ID");
        model.addColumn("Name");
        model.addColumn("Category");
        model.addColumn("Price");
        model.addColumn("Info");

        for (Product product : productList){
            Object [] row = new Object[5];
            row[0]=product.getProductId();
            row[1]=product.getProductName();
            row[2]=product.getCategory();
            row[3]=product.getPrice();
            row[4] = product.getInfoTable();
            model.addRow(row);
        }
        table.setModel(model);
    }


    // Method to get the product list
    public  ArrayList<Product> getProductList() {
        return productList;
    }


    // Method to get the user list
    public  ArrayList<User> getUserList() {
        return userList;
    }


    // Method to check if a user with the given username exists
    public  User checkUser(String username){
        for (User user : userList){
            if (user.getUsername().equals(username)){
                return  user;
            }
        }
        return null;


    }

    // Method to save the user list to a file
    public void saveToFileUsers(String fileName){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            outputStream.writeObject(userList);
            System.out.println("User list saved to file Successfully!");

        }
        catch (IOException e ){
            System.out.println("Error Saving User list to File" + e.getMessage());
        }
    }



    // Method to load the user list from a file
    public void loadFromFileUsers(String fileName){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            userList = (ArrayList<User>)inputStream.readObject();
            System.out.println("User list loaded From file Successfully!");
        }

        catch (IOException | ClassNotFoundException e ){
            System.out.println("Error Loading User List From File");
        }
    }


}

import java.util.Scanner;


public class Main {


    public static void main(String[] args) {


        // Create an instance of WestminsterShoppingManager to manage products and users
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Scanner to take user input
        Scanner input=new Scanner(System.in);
        boolean exit = false;

        System.out.println();

        // Display initial menu
        shoppingManager.displayMenu();


        // Load users and products data from files
        shoppingManager.loadFromFileUsers("userData.txt");
        shoppingManager.loadFromFileProducts("productData.txt");



        // Main loop to interact with the user
        while (!exit){


            // Prompt user for menu choice
            System.out.println("Choose a Option : ");
            String choice = input.nextLine();

            switch (choice){


                // Case 1: Add a new product
                case "1" :

                    String addProductType;
                    do {
                        System.out.println("Enter 1 for add an Electronic\nEnter 2 for add a Cloth: ");
                        addProductType = input.nextLine();
                        if (!addProductType.equals("1") && !addProductType.equals("2")) {
                            System.out.println("Invalid Category Choice.\n Please enter either '1' for Electronic or '2' for Clothe.");
                        }
                    } while (!addProductType.equals("1") && !addProductType.equals("2"));

                    System.out.println("Enter Product ID : ");
                    String productID = input.nextLine();

                    System.out.println("Enter Product Name : ");
                    String productName = input.nextLine();


                    int availableItems;
                    while (true) {
                        try {
                            System.out.println("Enter Product Available items : ");
                            availableItems = Integer.parseInt(input.nextLine());
                            break; // Break the loop if parsing is successful
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input type for available items.\n Please enter a valid number.");
                        }
                    }


                    double price;
                    while (true) {
                        try {
                            System.out.println("Enter Product Price : ");
                            price = Double.parseDouble(input.nextLine());
                            break; // Break the loop if parsing is successful
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for price. \n Please enter a valid price.");
                        }
                    }


                    if (addProductType.equals("1")){

                        System.out.println("Enter Brand Name : ");
                        String brandName = input.nextLine();

                        int warrantyPeriod;
                        while (true) {
                            try {
                                System.out.println("Enter Warranty Period");
                                warrantyPeriod = Integer.parseInt(input.nextLine());
                                break; // Break the loop if parsing is successful
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input for warranty period. Please enter a valid integer.");
                            }
                        }

                        Product product = new Electronics(productID,productName,availableItems,price,brandName,warrantyPeriod);
                        shoppingManager.addProduct(product);
                    }

                    if (addProductType.equals("2")){
                        System.out.println("Enter Size : ");
                        String size = input.nextLine();

                        System.out.println("Enter Color : ");
                        String color = input.nextLine();

                        Product product = new Clothing(productID,productName,availableItems,price,size,color);
                        shoppingManager.addProduct(product);
                    }

                    break;



                // Case 2: Remove a product by ID
                case "2" :

                    System.out.println("Enter Product ID to Remove Product : ");
                    String removeProductId = input.nextLine();
                    shoppingManager.removeProduct(removeProductId);

                    break;



                // Case 3: Print the list of products
                case "3" :
                    shoppingManager.printProductList();
                    break;


                // Case 4: Save products and users data to files
                case "4" :

                    shoppingManager.saveToFileProducts("productData.txt");
                    shoppingManager.saveToFileUsers("userData.txt");

                    break;


                // Case 5: User login or create a new account
                case "5" :

                    System.out.println("Enter your user name : ");
                    String username = input.nextLine();

                    System.out.println("Enter your user password : ");
                    String password = input.nextLine();

                    User exisitingUser = shoppingManager.checkUser(username);

                    if (exisitingUser!= null){
                        System.out.println("Welcome Back " + exisitingUser.getUsername());
                        MyGUI.createAndShowGUI(exisitingUser);

                    }

                    else {
                        System.out.println("New user Created New Account Successfully");
                        User newUser = new User(username , password);
                        shoppingManager.getUserList().add(newUser);
                        MyGUI.createAndShowGUI(newUser);

                    }

                    break;

                // Case 6: Exit the application
                case "6" :
                    System.out.println("Good Bye ! \n Have a nice day");
                    exit=true;
                    break;

                // Default: Invalid menu option
                default:
                    System.out.println("Invalid Menu Option");
                    break;




            }
        }

    }














}
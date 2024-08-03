import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


 //The MyGUI class represents the main graphical user interface for the Westminster Shopping Manager application.
public class MyGUI extends JFrame {

    private JLabel selectedProductDetails;


    JFrame cartFrame;

    private WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

    private   User currentUser ;
    private ShoppingCart shoppingCart ;

    Font font = new Font("Arial",Font.BOLD,20);
    Font fontDetails = new Font("Arial",Font.PLAIN,18);
    Font headerFont = new Font("Arial",Font.BOLD,18);



     //Constructor to initialize the GUI with a specific user.
     //user The user for whom the GUI is created.
    public MyGUI(User user){
        // Initialization
        this.currentUser=user;
        this.shoppingCart=user.getShoppingCart();

        // JFrame setup
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(600, 800));
        frame.setTitle("Westminster Shopping Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panels for layout
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(20,100));
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel innerPanelTop = new JPanel();
        JPanel innerPanelBottom = new JPanel();

        frame.setLayout(new BorderLayout());

        frame.add(topPanel,BorderLayout.NORTH);
        frame.add(centerPanel,BorderLayout.CENTER);
        frame.add(bottomPanel ,BorderLayout.SOUTH);

        centerPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new BorderLayout());
        innerPanelBottom.setLayout(new FlowLayout());

        innerPanelTop.setLayout(new BorderLayout());

        // JLabel for selected product details
        selectedProductDetails = new JLabel("Selected product Details");

        EmptyBorder paddingBorder = new EmptyBorder(3, 25, 10, 30);
        selectedProductDetails.setBorder(paddingBorder);
        selectedProductDetails.setFont(font);


        // Adding panels to the bottom panel
        bottomPanel.add(innerPanelTop , BorderLayout.NORTH);
        bottomPanel.add(innerPanelBottom,BorderLayout.SOUTH);

        // Styling for the top panel
        topPanel.setBackground(new Color(72,50,72));
        int topPaddingTopPanel = 20;
        topPanel.setBorder(BorderFactory.createEmptyBorder(topPaddingTopPanel, 0, 0, 0));

        innerPanelBottom.setBackground(new Color(72,50,72));


        // Buttons and dropdown for top panel
        JButton cartBtn = new JButton("Shopping Cart");
        JButton addToCartBtn = new JButton("Add to Shopping Cart");

        String [] menuItems = {"All","Electronics","Clothing"};
        JComboBox dropDownMenu = new JComboBox<>(menuItems);
        topPanel.add(dropDownMenu);


        topPanel.add(cartBtn);
        innerPanelBottom.add(addToCartBtn);


        // JTable setup for center panel
        JTable table = new JTable();
        table.setRowHeight(40);


        // Table header styling
        JTableHeader header = table.getTableHeader();
        header.setFont(headerFont);

        // Center-aligning table cell contents
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);



        // Displaying products in the table
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);
        shoppingManager.displayProductsTable(table);


        // Scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        int paddingSide = 20;
        int paddingTopBottom = 50;
        scrollPane.setBorder(BorderFactory.createEmptyBorder(paddingTopBottom, paddingSide,paddingTopBottom,paddingSide));
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);



        Dimension preferredSize = new Dimension(150, 250); // Set the preferred width and height
        JScrollPane textScrollPane = new JScrollPane();
        textScrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Text area for displaying selected product details
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setColumns(30);
        textArea.setFont(fontDetails);
        textArea.setBackground(Color.lightGray);
        textArea.setPreferredSize(preferredSize);
        textScrollPane.setViewportView(textArea);

        innerPanelTop.add(selectedProductDetails , BorderLayout.NORTH);

        innerPanelTop.add(textScrollPane ,BorderLayout.SOUTH);


        // TableRowSorter for the table
        TableRowSorter <TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);




        // Handling table selection changes
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow !=-1){
                        Object productID = table.getValueAt(selectedRow,0);
                        Product selectedProduct = shoppingManager.findProductById(productID.toString());
                        textArea.setText(selectedProduct.toString());
                    }
                }
            }
        });



        // Dropdown menu action listener
        dropDownMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedMenuItem = dropDownMenu.getSelectedItem().toString();
                RowFilter<TableModel,Object> rowFilter;

                if (selectedMenuItem.equals("All")){
                    rowFilter = RowFilter.regexFilter(".*");
                }

                else {
                    rowFilter = RowFilter.regexFilter(selectedMenuItem,2);
                }

                sorter.setRowFilter(rowFilter);
            }
        });



        // Button actions

        cartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showShoppingCart(shoppingManager,cartFrame,shoppingCart,currentUser);
            }

        });


        addToCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow !=-1){
                    Object productID = table.getValueAt(selectedRow , 0);
                    Product selectedProduct = shoppingManager.findProductById(productID.toString());
                    ShoppingCartItem cartItem = shoppingCart.findCartItemByProductId(productID.toString());

                    if (cartItem==null){
                        shoppingCart.addProductToCart(selectedProduct);
                        selectedProduct.decreaseAvailableItems();
                        JOptionPane.showMessageDialog(null, "Added to cart Successfully",
                                "Alert", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        if (selectedProduct.getAvailableItems()>0){
                            int currentQuantity = cartItem.getQuantity();
                            cartItem.setQuantity(currentQuantity +1);
                            selectedProduct.decreaseAvailableItems();
                            JOptionPane.showMessageDialog(null, "Added to cart Successfully",
                                    "Alert", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Cannot add more from this item no items available",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }

            }
        });

        frame.setVisible(true);
        frame.toFront();

    }




     // Method to create and show the GUI.
     public static void createAndShowGUI(User user) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyGUI(user);
            }
        });
    }



     // Method to display the shopping cart.
    private static void showShoppingCart(WestminsterShoppingManager shoppingManager , JFrame frame,ShoppingCart shoppingCart,User user) {

        // JButton for completing the purchase
        JButton purchaseButton = new JButton("Purchase");
        Font headerFont = new Font("Arial",Font.BOLD,18);


        // If the frame is null, create a new JFrame for the shopping cart
        if (frame == null) {
            frame = new JFrame("Shopping Cart");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            frame.setLayout(new BorderLayout());
            JPanel topPanel = new JPanel();
            JPanel bottomPanel = new JPanel();

            // Styling for the top panel
            topPanel.setLayout(new BorderLayout());
            frame.add(topPanel,BorderLayout.NORTH);
            frame.add(bottomPanel,BorderLayout.CENTER);

            EmptyBorder paddingBorder = new EmptyBorder(20, 25, 10, 30);
            topPanel.setBorder(paddingBorder);
            topPanel.setBackground(new Color(72,50,72));

            // JTable setup for displaying cart contents
            JTable table = new JTable();
            table.setRowHeight(30);
            JScrollPane scrollPane = new JScrollPane(table);



            table.setShowVerticalLines(true);
            shoppingCart.displayCartProductsTable(table);
            JTableHeader header = table.getTableHeader();
            header.setFont(headerFont);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);

            topPanel.add(scrollPane);

            // Layout and styling for the bottom panel
            bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
            bottomPanel.setBackground(Color.lightGray);

            // Labels for displaying various pricing details
            JLabel totalLabel = new JLabel("Total Price: $" + shoppingCart.calculateTotalPrice() );
            JLabel discountLabel = new JLabel("Three Items in Same Category Discount (20%) : $" + shoppingCart.calculateDiscountedPrice());
            JLabel userFirstPurchaseLabel = new JLabel("First Purchase Discount (10%) : $" +shoppingCart.calculateUserFirstPurchase(user));
            JLabel afterDiscountLabel = new JLabel("Final Total Price: $" + shoppingCart.calculateAfterDiscountPrice(user));


            // Font and alignment settings for the labels
            Font labelFont = new Font("Arial", Font.PLAIN, 20);
            totalLabel.setFont(headerFont);
            discountLabel.setFont(headerFont);
            afterDiscountLabel.setFont(headerFont);
            userFirstPurchaseLabel.setFont(headerFont);

            totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            discountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            afterDiscountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            userFirstPurchaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


            // Border settings for the labels
            Border shoppingCartTextBorder = new EmptyBorder(10,10,10,10);



            totalLabel.setBorder(shoppingCartTextBorder);
            discountLabel.setBorder(shoppingCartTextBorder);
            afterDiscountLabel.setBorder(shoppingCartTextBorder);
            userFirstPurchaseLabel.setBorder(shoppingCartTextBorder);


            bottomPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));


            // Add labels to the bottom panel if applicable
            if (shoppingCart.calculateTotalPrice()>0.0){
                bottomPanel.add(totalLabel);
            }

            if (shoppingCart.calculateDiscountedPrice()>0.0){
                bottomPanel.add(discountLabel);
            }

            if (shoppingCart.calculateUserFirstPurchase(user)>0.0){
                bottomPanel.add(userFirstPurchaseLabel);
            }

            if (shoppingCart.calculateAfterDiscountPrice(user)>0.0){
                bottomPanel.add(afterDiscountLabel);
            }
            purchaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);


            // Add the purchase button to the bottom panel
            bottomPanel.add(purchaseButton);


            // Make the frame visible

            frame.setVisible(true);
        }

        else {
            // If the frame already exists, make it visible
            frame.setVisible(true);
        }


        // ActionListener for the purchase button
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingCart.clearShoppingCart();
                user.setFirstPurchase(false);
                JOptionPane.showMessageDialog(null, "Purchase Completed",
                        "Alert", JOptionPane.INFORMATION_MESSAGE);


            }
        });

    }










}

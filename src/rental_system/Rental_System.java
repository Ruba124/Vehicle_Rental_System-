package rental_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Rental_System extends Application {

    private RentalSystem system;
    private Stage window;
    
    // Colors with css
    private final String COLOR_PRIMARY = "#007bff"; // Blue
    private final String COLOR_SUCCESS = "#28a745"; // Green
    private final String COLOR_DANGER  = "#dc3545"; // Red
    private final String COLOR_DARK    = "#343a40"; // Dark Grey

    @Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;
        this.system = new RentalSystem();
        seedData();

        window.setTitle("Drive & Go Rentals");
        
        // START HERE: Scene with 2 Big Buttons
        window.setScene(getStartupScene());
        window.show();
    }

    // =========================================================
    // SCENE 1: STARTUP (Select Role)
    // =========================================================
 
private Scene getStartupScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #E9ECEF;");
        
        ImageView logo = getImageView("logo.png", 400);
        
        Label lblWelcome = new Label("Welcome to Drive & Go");
        lblWelcome.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        // BIG BUTTON 1: CUSTOMER
        Button btnCustomer = new Button("Customer");
        btnCustomer.setPrefSize(200, 60);
        styleButton(btnCustomer, COLOR_SUCCESS); 
        btnCustomer.setFont(Font.font(18));
        
        // BIG BUTTON 2: ADMIN
        Button btnAdmin = new Button("Administrator");
        btnAdmin.setPrefSize(200, 60);
        styleButton(btnAdmin, COLOR_PRIMARY);
        btnAdmin.setFont(Font.font(18));

        // Actions
        btnCustomer.setOnAction(e -> window.setScene(getCustomerLoginScene()));
        btnAdmin.setOnAction(e -> window.setScene(getAdminLoginScene()));

        layout.getChildren().addAll(logo, lblWelcome, new Label("Please select your role:"), btnCustomer, btnAdmin);
        return new Scene(layout, 900, 600);
    }

        
    

        // Actions
     
   

    // =========================================================
    // SCENE 2A: ADMIN LOGIN (Split Screen)
    // =========================================================
    private Scene getAdminLoginScene() {
        HBox root = createSplitLoginLayout("Admin Login", "Enter Code", true);
        return new Scene(root, 900, 600);
    }

    // =========================================================
    // SCENE 2B: CUSTOMER LOGIN (Split Screen)
    // =========================================================
    private Scene getCustomerLoginScene() {
        HBox root = createSplitLoginLayout("Customer Login", "Enter Email", false);
        return new Scene(root, 900, 600);
    }
    
    // Helper to create the Split Screen Layout
    private HBox createSplitLoginLayout(String title, String prompt, boolean isAdmin) {
        // LEFT SIDE (Blue Brand)
        VBox left = new VBox(20); 
        left.setAlignment(Pos.CENTER);
        left.setStyle("-fx-background-color: " + COLOR_DARK + ";"); 
        left.setPrefWidth(300);
        left.getChildren().addAll(getImageView("logo.png", 150), new Label("Drive & Go"){{setTextFill(Color.WHITE); setFont(new Font(30));}});

        // RIGHT SIDE (Inputs)
        VBox right = new VBox(20); 
        right.setAlignment(Pos.CENTER); 
        right.setPadding(new Insets(40));
        
        // 1. Label for the field
        Label lblPrompt = new Label(isAdmin ? "Admin Code:" : "Email Address:");
        lblPrompt.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        // 2. The Input Field
        TextField input = isAdmin ? new PasswordField() : new TextField();
        input.setPromptText(prompt); 
        input.setPrefWidth(200); // Set preferred width instead of max
        
        // 3. Horizontal Box to hold Label + Field side-by-side
        HBox inputBox = new HBox(10); // 10px spacing between label and box
        inputBox.setAlignment(Pos.CENTER);
        inputBox.getChildren().addAll(lblPrompt, input);
        
        Button btnLogin = new Button("Login"); styleButton(btnLogin, COLOR_SUCCESS);
        Button btnBack = new Button("Back"); styleButton(btnBack, COLOR_DARK);
        Label lblError = new Label(); lblError.setTextFill(Color.RED);

        btnLogin.setOnAction(e -> {
            String text = input.getText();
            
            // --- VALIDATION CHECK ---
            if (text.isEmpty()) {
                lblError.setText("Error: Please write your " + (isAdmin ? "Code" : "Email") + "!");
                return; // Stop here
            }
            // ------------------------
             //  Email must contain @
            if (!text.contains("@")&& (!isAdmin)) {
             lblError.setText("Error: Please enter a valid email address!");
             return;
            }

            if(isAdmin) {
                if(text.equals("admin123")) window.setScene(getAdminDashboard(new Admin("A","Boss","admin","123")));
                else lblError.setText("Wrong Code!");
            } else {
                User u = system.loginUser(text);
                if(u != null && u instanceof Customer) window.setScene(getCustomerDashboard((Customer)u));
                else window.setScene(getSignUpScene(text)); // Go to Sign Up
            }
        });
        
        btnBack.setOnAction(e -> window.setScene(getStartupScene()));

        right.getChildren().addAll(new Label(title){{setFont(new Font(24));}}, inputBox, btnLogin, btnBack, lblError);
        
        HBox split = new HBox(left, right);
        HBox.setHgrow(left, Priority.ALWAYS); HBox.setHgrow(right, Priority.ALWAYS);
        return split;
    }
    private Scene getSignUpScene(String emailEntered) {
        VBox layout = new VBox(20); 
        layout.setAlignment(Pos.CENTER);
        
        Label lblTitle = new Label("Create New Account"); 
        lblTitle.setFont(new Font(24));
        
        // 1. Name Input with Label on the Left
        Label lblNamePrompt = new Label("Full Name:");
        lblNamePrompt.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        TextField txtName = new TextField(); 
        txtName.setPromptText("Enter your name"); 
        txtName.setPrefWidth(200);
        
        HBox nameBox = new HBox(10); // 10px spacing
        nameBox.setAlignment(Pos.CENTER);
        nameBox.getChildren().addAll(lblNamePrompt, txtName);

        // Avatar Selection
        HBox icons = new HBox(15); icons.setAlignment(Pos.CENTER);
        ToggleGroup group = new ToggleGroup();
        String[] files = {"icon1.png","icon2.png","icon3.jpg","icon4.jpg"};
        for(String f : files) {
            ToggleButton tb = new ToggleButton(); tb.setGraphic(getImageView(f, 60));
            tb.setUserData(f); tb.setToggleGroup(group);
            if(f.equals("icon1.png")) tb.setSelected(true);
            icons.getChildren().add(tb);
        }

        Button btn = new Button("Create Account"); 
        styleButton(btn, COLOR_PRIMARY);
        
        // Error Label
        Label lblError = new Label();
        lblError.setTextFill(Color.RED);

        btn.setOnAction(e -> {
            // --- VALIDATION CHECK ---
            if(txtName.getText().trim().isEmpty()) {
                lblError.setText("Error: Please write your name!");
                return; // Stop here
            }
            // Name must contain letters only (no numbers)
            if (!txtName.getText().matches("[a-zA-Z ]+")) {
            lblError.setText("Error: Name must contain letters only!");
            return;
            }  

            // ------------------------
            
            ToggleButton sel = (ToggleButton)group.getSelectedToggle();
            String img = (sel!=null) ? (String)sel.getUserData() : "icon1.png";
            
            Customer c = new Customer("C-"+system.get_user_count(), txtName.getText(), emailEntered, 500.0, img);
            system.addUser(c);
            window.setScene(getCustomerDashboard(c));
        });

        layout.getChildren().addAll(lblTitle, new Label("Email: "+emailEntered), nameBox, new Label("Choose Avatar:"), icons, btn, lblError);
        return new Scene(layout, 900, 600);
    }
    // =========================================================
    // SCENE 4: ADMIN DASHBOARD
    // =========================================================
    private Scene getAdminDashboard(Admin admin) {
        BorderPane layout = new BorderPane();
        
        // Sidebar
        VBox sidebar = createSidebar(admin, "Admin Panel", COLOR_DARK);
        Button btnFleet = createSidebarButton("Manage Fleet");
        Button btnAdd = createSidebarButton("Add Vehicle"); // <--- THIS OPENS THE SMART FORM
        Button btnOut = createSidebarButton("Log Out"); btnOut.setStyle("-fx-background-color: "+COLOR_DANGER+"; -fx-text-fill: white;");
        
        sidebar.getChildren().addAll(new Separator(), btnFleet, btnAdd, new Region(), btnOut);
        
        // Actions
        btnFleet.setOnAction(e -> layout.setCenter(createAdminFleetView(admin, layout)));
        btnAdd.setOnAction(e -> window.setScene(getAddVehicleScene(admin))); // Switch scene to Form
        btnOut.setOnAction(e -> window.setScene(getStartupScene()));

        layout.setLeft(sidebar);
        layout.setCenter(createAdminFleetView(admin, layout)); // Default View
        return new Scene(layout, 1000, 700);
    }

    // =========================================================
    // [FIXED] SCENE 5: SMART ADD VEHICLE FORM (Dynamic)
    // =========================================================
    private Scene getAddVehicleScene(Admin admin) {
        VBox layout = new VBox(15); layout.setAlignment(Pos.CENTER); layout.setPadding(new Insets(40));
        
        Label title = new Label("Add New Vehicle"); title.setFont(new Font(24));
        
        // 1. Type Selection
        ComboBox<String> comboType = new ComboBox<>();
        comboType.getItems().addAll("Car", "Van", "Bike");
        comboType.setPromptText("Select Type");
        
        // 2. Common Inputs
        TextField txtBrand = new TextField(); txtBrand.setPromptText("Brand"); txtBrand.setMaxWidth(300);
        TextField txtModel = new TextField(); txtModel.setPromptText("Model"); txtModel.setMaxWidth(300);
        TextField txtPrice = new TextField(); txtPrice.setPromptText("Price/Day"); txtPrice.setMaxWidth(300);
        
        // 3. Dynamic Inputs (Hidden container)
        VBox dynamicBox = new VBox(10); dynamicBox.setAlignment(Pos.CENTER);
        dynamicBox.setStyle("-fx-border-color: #ccc; -fx-padding: 10;");
        dynamicBox.setVisible(false);
        
        TextField txtSeats = new TextField(); txtSeats.setPromptText("Seats"); txtSeats.setMaxWidth(300);
        TextField txtFuel = new TextField(); txtFuel.setPromptText("Fuel"); txtFuel.setMaxWidth(300);
        TextField txtLoad = new TextField(); txtLoad.setPromptText("Load (kg)"); txtLoad.setMaxWidth(300);
        TextField txtGears = new TextField(); txtGears.setPromptText("Gears"); txtGears.setMaxWidth(300);

        // Logic to show/hide fields
        comboType.setOnAction(e -> {
            dynamicBox.getChildren().clear();
            dynamicBox.setVisible(true);
            String t = comboType.getValue();
            if(t.equals("Car")) dynamicBox.getChildren().addAll(txtSeats, txtFuel);
            else if(t.equals("Van")) dynamicBox.getChildren().add(txtLoad);
            else if(t.equals("Bike")) dynamicBox.getChildren().add(txtGears);
        });

        Button btnSave = new Button("Save"); styleButton(btnSave, COLOR_SUCCESS);
        Button btnCancel = new Button("Cancel"); styleButton(btnCancel, COLOR_DANGER);
        Label lblMsg = new Label(); lblMsg.setTextFill(Color.RED);

   btnSave.setOnAction(e -> {
            // CHECK 1: Did they forget to select a Type?
            // We check this BEFORE the try-catch block
            String type = comboType.getValue();
            if (type == null) {
                lblMsg.setText("Error: Please select a Vehicle Type (Car, Van, or Bike)!");
                return; // STOP HERE. Don't run the rest of the code.
            }

            // CHECK 2: Are the numbers valid?
            try {
                String brand = txtBrand.getText();
                String model = txtModel.getText();
                int vehicle_count = (system.getAllVehicles()).size();
                vehicle_count++;
                // If this fails, it jumps to catch(NumberFormatException)
                double p = Double.parseDouble(txtPrice.getText()); 
                
                String id = "V-" + vehicle_count % 1000;
                Vehicle v = null;

                if ("Car".equals(type)) {
                    int seats = Integer.parseInt(txtSeats.getText()); // specific check
                    String fuel = txtFuel.getText();
                    v = new Car(id, brand, model, p, seats, fuel);
                } 
                else if ("Van".equals(type)) {
                    double load = Double.parseDouble(txtLoad.getText()); // specific check
                    v = new Van(id, brand, model, p, load);
                } 
                else if ("Bike".equals(type)) {
                    int gears = Integer.parseInt(txtGears.getText()); // specific check
                    v = new Bike(id, brand, model, p, gears);
                }

                // If we get here, everything is perfect
                system.addVehicle(v);
                window.setScene(getAdminDashboard(admin)); 

            } catch (NumberFormatException ex) {
                // This block runs ONLY if they typed "abc" instead of a number
                lblMsg.setText("Error: Price, Seats, and Load must be valid numbers!");
            } catch (Exception ex) {
                // This block catches any other unexpected errors
                lblMsg.setText("Error: An unexpected error occurred.");
            }
        });
        
        btnCancel.setOnAction(e -> window.setScene(getAdminDashboard(admin)));

        layout.getChildren().addAll(title, comboType, txtBrand, txtModel, txtPrice, dynamicBox, btnSave, btnCancel, lblMsg);
        return new Scene(layout, 900, 600);
    }

    // =========================================================
    // SCENE 6: CUSTOMER DASHBOARD
    // =========================================================
    private Scene getCustomerDashboard(Customer cust) {
        BorderPane layout = new BorderPane();
        
        VBox sidebar = createSidebar(cust, "Hello, "+cust.getName(), COLOR_DARK);
        Label lblBal = new Label("Balance: $"+cust.getBalance()); lblBal.setTextFill(Color.LIGHTGREEN);
        
        Button btnRent = createSidebarButton("Rent Vehicle");
        Button btnFunds = createSidebarButton("Add Funds");
        Button btnHist = createSidebarButton("History");
        Button btnOut = createSidebarButton("Log Out"); btnOut.setStyle("-fx-background-color: "+COLOR_DANGER+"; -fx-text-fill: white;");

        sidebar.getChildren().addAll(lblBal, new Separator(), btnRent, btnFunds, btnHist, new Region(), btnOut);
        
        layout.setLeft(sidebar);
        layout.setCenter(createCustomerCarView(cust, layout)); // Default View

        btnRent.setOnAction(e -> layout.setCenter(createCustomerCarView(cust, layout)));
        btnFunds.setOnAction(e -> layout.setCenter(createAddFundsView(cust, lblBal)));
      btnHist.setOnAction(e -> {
    layout.setCenter(createHistoryView(cust)); 
});

        btnOut.setOnAction(e -> window.setScene(getStartupScene()));

        return new Scene(layout, 1000, 700);
    }

    // =========================================================
    // VIEWS: GRIDS FOR CARS
    // =========================================================
    // =========================================================
    // VIEW: HISTORY (Receipt Style)
    // =========================================================
    private ScrollPane createHistoryView(Customer cust) {
        VBox layout = new VBox(15); // Vertical list with spacing
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("My Rental History");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        layout.getChildren().add(title);

        if (cust.getHistory().isEmpty()) {
            Label empty = new Label("No rentals found.");
            empty.setTextFill(Color.GREY);
            layout.getChildren().add(empty);
        } else {
            // Loop through every Booking object
            for (Booking b : cust.getHistory()) {
                VBox card = createReceiptCard(b);
                layout.getChildren().add(card);
            }
        }

        ScrollPane sp = new ScrollPane(layout);
        sp.setFitToWidth(true);
        // Make the background look nice
        sp.setStyle("-fx-background: #f4f4f4; -fx-background-color: #f4f4f4;"); 
        return sp;
    }

    // Helper: Design a Single Receipt Card
    private VBox createReceiptCard(Booking b) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setMaxWidth(600); // Limit width like a real receipt
        // CSS for Shadow and White Background
        card.setStyle("-fx-background-color: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0); -fx-background-radius: 8;");

        // 1. Header (Booking ID + Date)
        HBox header = new HBox();
        Label lblId = new Label("Order #" + b.getBookingId());
        lblId.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblId.setTextFill(Color.DARKBLUE);
        
        Region spacer = new Region(); HBox.setHgrow(spacer, Priority.ALWAYS);
        
        // Format Date nicely
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
        Label lblDate = new Label(sdf.format(b.getBookingDate()));
        lblDate.setTextFill(Color.GREY);
        
        header.getChildren().addAll(lblId, spacer, lblDate);

        // 2. Divider
        Separator sep1 = new Separator();

        // 3. Vehicle Details
        Label lblVehicle = new Label(b.getVehicle().getBrand() + " " + b.getVehicle().getModel());
        lblVehicle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        Label lblDetail = new Label(b.getVehicle().getDetails());
        lblDetail.setTextFill(Color.DARKGREY);

        // 4. Payment Info (Backend Logic Proof)
        HBox payBox = new HBox(10);
        Label lblPayId = new Label("Payment ID: " + b.getPayment().getPaymentId());
        lblPayId.setStyle("-fx-font-size: 10px; -fx-text-fill: #888;");
        payBox.getChildren().add(lblPayId);

        // 5. Total Cost
        HBox costBox = new HBox();
        costBox.setAlignment(Pos.CENTER_RIGHT);
        Label lblTotal = new Label("Total Paid: $" + b.getPayment().getAmount());
        lblTotal.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        lblTotal.setTextFill(Color.GREEN);
        costBox.getChildren().add(lblTotal);

        card.getChildren().addAll(header, sep1, lblVehicle, lblDetail, new Separator(), payBox, costBox);
        return card;
    }
    private ScrollPane createCustomerCarView(Customer cust, BorderPane layout) {
        FlowPane grid = new FlowPane(); grid.setPadding(new Insets(20)); grid.setHgap(20); grid.setVgap(20);
        for(Vehicle v : system.getAvailableVehicles()) {
            VBox card = createCard(v);
            Button btn = new Button("Rent"); styleButton(btn, COLOR_SUCCESS);
            btn.setOnAction(e -> {
                TextInputDialog td = new TextInputDialog("1");
                td.setHeaderText("Rent "+v.getModel());
                td.setContentText("Number of days:");
               
             td.showAndWait().ifPresent(d -> {

    //  Empty input
    if (d.trim().isEmpty()) {
        new Alert(Alert.AlertType.ERROR,
            "Please enter a valid number").showAndWait();
        return;
    }

    try {
        int days = Integer.parseInt(d);

        //  No zero or negative numbers
        if (days <= 0) {
            new Alert(Alert.AlertType.ERROR,
                "Please enter a valid number").showAndWait();
            return;
        }

        String msg = system.processRental(cust, v, days);
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
        window.setScene(getCustomerDashboard(cust)); // Refresh

    } catch (NumberFormatException ex) {
        //  Letters entered
        new Alert(Alert.AlertType.ERROR,
            "Please enter a number").showAndWait();
    }
});

            });
            card.getChildren().add(btn); grid.getChildren().add(card);
        }
        return new ScrollPane(grid){{setFitToWidth(true);}};
    }

    private ScrollPane createAdminFleetView(Admin admin, BorderPane layout) {
        FlowPane grid = new FlowPane();
        grid.setPadding(new Insets(20)); 
        grid.setHgap(20);
        grid.setVgap(20);
        for(Vehicle v : system.getAllVehicles()) {
            VBox card = createCard(v);
            Button btn = new Button("Remove"); styleButton(btn, COLOR_DANGER);
            btn.setOnAction(e -> {
                system.removeVehicle(v);
                layout.setCenter(createAdminFleetView(admin, layout));
            });
            card.getChildren().add(btn); grid.getChildren().add(card);
        }
        return new ScrollPane(grid){{setFitToWidth(true);}};
    }
    
   // =========================================================
    // VIEW: ADD FUNDS (With Red/Green Feedback)
    // =========================================================
    private VBox createAddFundsView(Customer cust, Label balanceLabelToUpdate) {
        VBox view = new VBox(20); 
        view.setAlignment(Pos.CENTER);
        
        Label lblTitle = new Label("Add Funds to Wallet");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        TextField txtAmount = new TextField();
        txtAmount.setPromptText("Enter Amount (e.g. 100)");
        txtAmount.setMaxWidth(250);
        
        Button btnAdd = new Button("Confirm Transaction");
        styleButton(btnAdd, COLOR_SUCCESS); // Green button
        
        // This is the message label (Hidden initially)
        Label lblMessage = new Label();
        lblMessage.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        btnAdd.setOnAction(e -> {
            try {
                // 1. Try to read the number
                double amount = Double.parseDouble(txtAmount.getText());
                
                // 2. Validate it is positive
                if (amount > 0) {
                    cust.addFunds(amount);
                    
                    // SUCCESS: Update Balance & Show Green Message
                    balanceLabelToUpdate.setText("Balance: $" + cust.getBalance());
                    lblMessage.setText("Success! $" + amount + " added to your wallet.");
                    lblMessage.setTextFill(Color.GREEN);
                    
                    txtAmount.clear(); // Clear the box for next time
                } else {
                    // ERROR: Negative or Zero
                    lblMessage.setText("Error: Amount must be positive.");
                    lblMessage.setTextFill(Color.RED);
                }
            } catch (NumberFormatException ex) {
                // ERROR: They typed text or nonsense
                lblMessage.setText("Error: Invalid Input! Please enter a number.");
                lblMessage.setTextFill(Color.RED);
            }
        });
        
        view.getChildren().addAll(lblTitle, txtAmount, btnAdd, lblMessage);
        return view;
    }

    // =========================================================
    // HELPERS
    // =========================================================
    private VBox createSidebar(User u, String title, String color) {
        VBox s = new VBox(20); s.setPadding(new Insets(20)); s.setPrefWidth(250);
        s.setStyle("-fx-background-color: "+color+";"); s.setAlignment(Pos.TOP_CENTER);
        ImageView p = getImageView(u.getImagePath(), 100);
        Label l = new Label(title); l.setTextFill(Color.WHITE); l.setFont(new Font(18));
        s.getChildren().addAll(getImageView("logo.png", 80), p, l);
        return s;
    }
    
  // Base Card Design (Shared)
    private VBox createCard(Vehicle v) {
        VBox c = new VBox(10);
        c.setPadding(new Insets(15));
        c.setPrefWidth(200); // Card is 200px wide
        c.setStyle("-fx-background-color: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0); -fx-background-radius: 10;");

        Label l1 = new Label(v.getBrand() + " " + v.getModel());
        l1.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        l1.setWrapText(true);
        l1.setMaxWidth(170); // Wrap title if brand name is long

        Label l2 = new Label("$" + v.getPrice() + "/day");
        l2.setTextFill(Color.GREEN);
        
        Label l3 = new Label(v.getDetails());
        l3.setWrapText(true);     // Turn on wrapping
        l3.setMaxWidth(170);      // [FIX] Force wrap at 170px (200 width - 30 padding)
        l3.setMinHeight(Region.USE_PREF_SIZE); // Ensure it grows vertically

        c.getChildren().addAll(l1, l2, l3);
        return c;
    }

    private Button createSidebarButton(String t) {
        Button b = new Button(t); b.setMaxWidth(Double.MAX_VALUE);
        b.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: #555; -fx-border-width: 0 0 1 0;");
        b.setPadding(new Insets(15)); b.setAlignment(Pos.CENTER_LEFT);
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: white; -fx-border-color: #555; -fx-border-width: 0 0 1 0;"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: #555; -fx-border-width: 0 0 1 0;"));
        return b;
    }

    private void styleButton(Button b, String color) {
        b.setStyle("-fx-background-color: "+color+"; -fx-text-fill: white; -fx-background-radius: 5;");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: derive("+color+", -20%); -fx-text-fill: white; -fx-background-radius: 5;"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: "+color+"; -fx-text-fill: white; -fx-background-radius: 5;"));
    }

    private ImageView getImageView(String n, double w) {
        ImageView iv = new ImageView();
        try { iv.setImage(new Image(getClass().getResourceAsStream(n)));
        System.out.println(n + " Loaded from getClass().getResourceAsStream(n) ");}
        catch(Exception e) { try{ 
            iv.setImage(new Image("file:src/rental_system/"+n)); 
            System.out.println(n + " Loaded from file:src/rental_system/ ");
        }catch(Exception ex){
            System.out.println(n + "Failed to be loaded using getClass().getResourceAsStream(n) or  file:src/rental_system/ ");
        } }
        iv.setPreserveRatio(true); iv.setFitWidth(w);
        return iv;
    }

 private void seedData() {
        // --- EXISTING DATA ---
        Customer richUser = new Customer("C-1", "Sarah Connor", "sarah@gmail.com", 2000.0, "icon2.png");
        system.addUser(richUser);
        
        system.addVehicle(new Car("V-1", "Toyota", "Corolla", 60.0, 5, "Petrol"));
        system.addVehicle(new Car("V-2", "Tesla", "Model S", 200.0, 5, "Electric"));
        system.addVehicle(new Bike("V-3", "Yamaha", "R15", 25.0, 6));

        // --- NEW CUSTOMERS (4 New Users) ---
        // ID, Name, Email, Balance, Icon
        system.addUser(new Customer("C-2", "Mike Ross", "mike@gmail.com", 5000.0, "icon3.png"));
        system.addUser(new Customer("C-3", "Rachel Green", "rachel@gmail.com", 300.0, "icon1.png"));
        system.addUser(new Customer("C-4", "Bruce Wayne", "batman@gmail.com", 100000.0, "icon4.png"));
        system.addUser(new Customer("C-5", "Peter Parker", "sp  idey@gmail.com", 50.0, "icon2.png"));

        // --- NEW VEHICLES (5 New Cars/Vans/Bikes) ---
        // Cars: ID, Brand, Model, Price, Seats, Fuel
        system.addVehicle(new Car("V-4", "Honda", "Civic", 70.0, 5, "Petrol"));
        system.addVehicle(new Car("V-5", "Ford", "Mustang", 120.0, 2, "Petrol"));
        system.addVehicle(new Car("V-6", "Kia", "Sportage", 85.0, 7, "Diesel"));

        // Vans: ID, Brand, Model, Price, Load(kg)
        system.addVehicle(new Van("V-7", "Mercedes", "V-Class", 150.0, 2000));

        // Bikes: ID, Brand, Model, Price, Gears
        system.addVehicle(new Bike("V-8", "Harley", "Davidson", 90.0, 6));
    }

    public static void main(String[] args) { launch(args); }
}
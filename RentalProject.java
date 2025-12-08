import java.util.Scanner; // 2

public class RentalProject {
    public static void main(String[] args) { // 3
        int counter = 3;
        RentalSystem system = new RentalSystem(); // 4
        Scanner scanner = new Scanner(System.in); // 5
        
        // --- SEEDING DATA ---
        Admin theBoss = new Admin("ADM-01", "Dr. Supervisor", "admin", "admin123"); // 6
        system.addUser(theBoss); // 7
        
        Customer richUser = new Customer("C-1", "Sarah Connor", "sarah@gmail.com", 2000.0); // 8
        Customer poorUser = new Customer("C-2", "John Wick", "john@gmail.com", 50.0); // 9
        system.addUser(richUser); // 10
        system.addUser(poorUser); // 11

        Vehicle car1 = new Car("V-1", "Toyota", "Corolla", 60.0, 5, "Petrol"); // 12
        system.addVehicle(car1); // 13
        
        Vehicle bike1 = new Bike("V-2", "Yamaha", "R15", 20.0, 6); // 14
        system.addVehicle(bike1); // 15
        
        System.out.println(">>> SYSTEM READY. DATA LOADED."); // 16
        
        boolean appRunning = true; // 17
        
        while (appRunning) { // 18
            
            System.out.println("\n=== LOGIN PAGE ===");
            User currentUser = null; // 19
            
            while (currentUser == null) { // 20
                System.out.print("Enter Email (or 'exit'): ");
                String input = scanner.next(); // 21
                
                if (input.equalsIgnoreCase("exit")) { // 22
                    appRunning = false;
                    break; 
                }
                
                if (input.equals("admin")) { // 23
                    System.out.print("Enter Code: ");
                    String code = scanner.next();
                    if (code.equals("admin123")) {
                        currentUser = theBoss; // 24
                    } else {
                        System.out.println("Wrong Code.");
                    }
                } else {
                    // CUSTOMER LOGIN
                    User found = system.loginUser(input); // 25
                    
                    if (found != null) {
                        currentUser = found; // 26
                        System.out.println("Welcome back, " + currentUser.getName());
                    } else {
                        System.out.println("New user? Creating Guest account...");
                         System.out.println("Enter Your Name");
                         Scanner scanner1 = new Scanner(System.in); 
                         String name = scanner1.nextLine();
                        currentUser = new Customer("C-" + counter,name , input, 500.0); // 27
                        system.addUser(currentUser);// 28
                        counter++;
                    }
                }
            } // End of Login Loop
            
            if (!appRunning) break; // 29
            
            // ==========================================
            // STEP 3: THE SESSION LOOP (The Dashboard)
            // ==========================================
            boolean sessionActive = true;
            
            while (sessionActive) {
                System.out.println("\n--- DASHBOARD (" + currentUser.getName() + ") ---");
                currentUser.displayDashboard(); // Prints the menu
                System.out.println("[0] Log Out"); 
                
                System.out.print("Select Option: ");
                int choice = -1; // Default to an invalid number

                // --- 1. SAFE INPUT HANDLING (The Try-Catch) ---
                try {
                    choice = scanner.nextInt(); 
                } catch (Exception e) {
                    System.out.println(">> Error: Invalid Input! Please enter a number.");
                    scanner.nextLine(); // Clear the "garbage" text from memory
                    continue; // Restart the loop immediately
                }

                // --- 2. LOGOUT LOGIC (Universal) ---
                if (choice == 0) {
                    System.out.println("Logging out...");
                    sessionActive = false;
                    currentUser = null;
                }
                
                // --- 3. ADMIN LOGIC (With Range Check) ---
                else if (currentUser instanceof Admin) {
                    // RANGE CHECK: Admin has options 1, 2, 3
                    if (choice < 1 || choice > 3) {
                        System.out.println(">> Error: Please select a number between 1 and 3.");
                    } 
                    else {
                        // Logic is safe to run now
                        Admin admin = (Admin) currentUser;
                        if (choice == 1) { 
                            
                            admin.addNewVehicle(system, scanner);
                        }
                        else if (choice == 2) { 
                            System.out.print("Enter Vehicle ID to remove: ");
                            String remId = scanner.next();
                            Vehicle vToRemove = system.findVehicleById(remId);
                            if (vToRemove != null) admin.removeExistingVehicle(system, vToRemove);
                            else System.out.println("Vehicle not found.");
                        }
                        else if (choice == 3) { 
                            admin.viewAllBookings(system);
                        }
                    }
                }
                
                // --- 4. CUSTOMER LOGIC (With Range Check) ---
                else if (currentUser instanceof Customer) {
                    // RANGE CHECK: Customer has options 1, 2, 3, 4
                    if (choice < 1 || choice > 4) {
                        System.out.println(">> Error: Please select a number between 1 and 4.");
                    }
                    else {
                        Customer cust = (Customer) currentUser;
                        
                        if (choice == 1) {
                            
                            system.displayAvailableVehicles();
                            
                        }
                        else if (choice == 2) { // RENT VEHICLE
                            System.out.print("Enter Vehicle ID: ");
                            String vId = scanner.next();
                            Vehicle v = system.findVehicleById(vId);
                            
                            if (v != null) {
                                // NESTED TRY-CATCH for Days Input
                                try {
                                    System.out.print("Enter Days: ");
                                    int days = scanner.nextInt();
                                    
                                    if (days > 0) system.processRental(cust, v, days);
                                    else System.out.println(">> Error: Days must be positive.");
                                    
                                } catch (Exception e) {
                                    System.out.println(">> Error: Days must be a number!");
                                    scanner.nextLine(); // Clear buffer
                                }
                            } else {
                                System.out.println(">> Error: Vehicle ID not found.");
                            }
                        }
                        else if (choice == 3) { // MY HISTORY
                             // Logic to show history
                             system.displayAllBookings();
                             System.out.println("Your Wallet: $" + cust.getBalance());
                        }
                        else if (choice == 4) { // ADD FUNDS
                            // NESTED TRY-CATCH for Money Input
                            try {
                                System.out.print("Amount to Add: ");
                                double amt = scanner.nextDouble();
                                if (amt > 0) cust.addFunds(amt);
                                else System.out.println(">> Error: Amount must be positive.");
                            } catch (Exception e) {
                                System.out.println(">> Error: Invalid Amount!");
                                scanner.nextLine(); // Clear buffer
                            }
                        }
                    }
                }
            } }// End Session Loop












    }
}
 {
    
}

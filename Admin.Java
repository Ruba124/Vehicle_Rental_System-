import java.util.Scanner;

public class Admin extends User {

    private String adminCode;
    
    // --- ID GENERATOR ---
    // Static means this number is shared and remembers its value
    private static int vCounter = 3; 

    public Admin(String id, String name, String email, String code) {
        super(id, name, email);
        this.adminCode = code;
    }

    @Override
    public void displayDashboard() {
        System.out.println("=== ADMIN DASHBOARD ===");
        System.out.println("[1] Add Vehicle");
        System.out.println("[2] Remove Vehicle");
        System.out.println("[3] View All Bookings");
    }

    // --- THE NEW LOGIC STARTS HERE ---
    
    // Note: We need 'Scanner' passed in to read inputs
    public void addNewVehicle(RentalSystem system, Scanner scanner) {
        
        System.out.println("\n--- ADD NEW VEHICLE ---");
        System.out.println("Select Type: [1] Car  [2] Van  [3] Bike");
        
        int typeChoice = -1;
        
        // 1. SAFE TYPE SELECTION
        try {
            typeChoice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Invalid input! Operation cancelled.");
            scanner.nextLine(); // Clear buffer
            return; // Stop the function
        }
        
        // Consume the leftover "enter" key from nextInt()
        scanner.nextLine(); 

        try {
            // 2. ASK COMMON DETAILS (Brand, Model, Price)
            System.out.print("Enter Brand: ");
            String brand = scanner.nextLine();
            
            System.out.print("Enter Model: ");
            String model = scanner.nextLine();
            
            System.out.print("Enter Base Price per Day: ");
            double price = scanner.nextDouble();
            
            // Auto-Generate ID
            String newId = "V-" + vCounter;
            vCounter++; // Increment for next time (V-2 -> V-3)

            Vehicle newVehicle = null;

            // 3. ASK SPECIFIC DETAILS BASED ON TYPE
            if (typeChoice == 1) { 
                // --- CAR ---
                System.out.print("Enter Number of Seats: ");
                int seats = scanner.nextInt();
                scanner.nextLine(); // consume enter
                System.out.print("Enter Fuel Type (Petrol/Electric): ");
                String fuel = scanner.nextLine();
                
                newVehicle = new Car(newId, brand, model, price, seats, fuel);
                
            } else if (typeChoice == 2) { 
                // --- VAN ---
                System.out.print("Enter Load Capacity (kg): ");
                double load = scanner.nextDouble();
                
                newVehicle = new Van(newId, brand, model, price, load);
                
            } else if (typeChoice == 3) { 
                // --- BIKE ---
                System.out.print("Enter Gear Count: ");
                int gears = scanner.nextInt();
                
                newVehicle = new Bike(newId, brand, model, price, gears);
                
            } else {
                System.out.println("Error: Invalid Vehicle Type selected.");
                return;
            }

            // 4. FINAL STEP: ADD TO SYSTEM
            system.addVehicle(newVehicle);
            System.out.println("Success! " + brand + " " + model + " added with ID: " + newId);

        } catch (Exception e) {
            System.out.println(">> Error: Invalid Input! Please enter numbers for Price/Seats/Load.");
            scanner.nextLine(); // Clear buffer so next loop works
        }
    }

    public void removeExistingVehicle(RentalSystem system, Vehicle v) {
        system.removeVehicle(v);
    }

    public void viewAllBookings(RentalSystem system) {
        system.displayAllBookings();
    }
}
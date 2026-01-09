/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

public class Admin extends User {
    private String adminCode;

    public Admin(String id, String name, String email, String code) {
        // Admin gets default icon1.png
        super(id, name, email, "icon.png"); 
        this.adminCode = code;
    }

    public void addNewVehicle(RentalSystem system, Vehicle v) {
        system.addVehicle(v);
    }

    public boolean removeExistingVehicle(RentalSystem system, Vehicle v) {
        return system.removeVehicle(v); 
    }
}
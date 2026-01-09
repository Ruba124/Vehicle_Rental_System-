/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String imagePath;

    public User(String userId, String name, String email, String imagePath) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.imagePath = imagePath;
    }
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getImagePath() { return imagePath;}
}
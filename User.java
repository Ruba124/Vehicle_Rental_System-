// ABSTRACT PARENT: User
public abstract class User {
    protected String userId;
    protected String name;
    protected String email;

    public User(String userId, String name, String email) {
        // Logic: Initialize standard user data.
    }
    // --- NEW: REQUIRED FOR SEARCHING ---
    public String getEmail() {
        return this.email;
    }
    public String getName() {
        return this.name;
    }

    public abstract void displayDashboard(); // Polymorphism: Admin sees different menu than Customer
}
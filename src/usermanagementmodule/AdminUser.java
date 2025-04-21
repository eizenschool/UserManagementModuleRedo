/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usermanagementmodule;

/**
 *
 * @author clogg
 */
public class AdminUser extends User{
    
    public AdminUser(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. View Staff");
        System.out.println("2. Logout");
    }

    // Later: add methods like manageStaff(...) that link to AdminManager
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usermanagementmodule;

/**
 *
 * @author clogg
 */
public class AdminUser extends User {

    public AdminUser(String username, String password) {
        super(username, password);
    }

    public boolean canManageStaff() {
        return true;
    }

    public void showRole() {
        System.out.println("Logged in as Admin");
    }

    @Override
    public String toString() {
        return "Role: Admin, Username: " + username;
    }
}
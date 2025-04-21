/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usermanagementmodule;
/**
 *
 * @author clogg
 */
public class StaffUser extends User {
    private static int nextId = 1001;  // Auto-increment base ID
    private final int staffId;

    public StaffUser(String username, String password) {
        super(username, password);
        this.staffId = nextId++;
    }

    public int getStaffId() {
        return staffId;
    }

    public boolean canManageMembers() {
        return true;
    }

    public void showRole() {
        System.out.println("Logged in as Staff (ID: " + staffId + ")");
    }

    @Override
    public String toString() {
        return "ID: " + staffId + ", Username: " + username;
    }
}
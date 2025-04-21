/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package usermanagementmodule;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author clogg
 */
public class UserManagementModule {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        AdminUser admin = new AdminUser("admin", "admin123");
        ArrayList<StaffUser> staffList = new ArrayList<>();
        staffList.add(new StaffUser("staff1", "pass123"));

        MembershipManager membershipManager = new MembershipManager();

        boolean running = true;

        while (running) {
            System.out.println("\n=== Welcome to Retail System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Staff Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter admin username: ");
                    String adminUser = input.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPass = input.nextLine();

                    if (admin.getUsername().equals(adminUser) && admin.checkPassword(adminPass)) {
                        admin.showRole(); // Optional: "Logged in as Admin"
                        handleAdminMenu(input, staffList);
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                    break;

                case 2:
                    System.out.print("Enter staff username: ");
                    String staffUser = input.nextLine();
                    System.out.print("Enter staff password: ");
                    String staffPass = input.nextLine();

                    StaffUser loggedIn = null;
                    for (StaffUser staff : staffList) {
                        if (staff.getUsername().equals(staffUser) && staff.checkPassword(staffPass)) {
                            loggedIn = staff;
                            break;
                        }
                    }

                    if (loggedIn != null) {
                        loggedIn.showRole(); // Optional: shows ID
                        handleStaffMenu(input, loggedIn, membershipManager);
                    } else {
                        System.out.println("Invalid staff credentials.");
                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid input.");
            }
        }

        input.close();
    }

    // ---------- Admin Menu ----------
    public static void handleAdminMenu(Scanner input, ArrayList<StaffUser> staffList) {
        boolean adminActive = true;
        while (adminActive) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View Staff");
            System.out.println("2. Add Staff");
            System.out.println("3. Delete Staff");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    if (staffList.isEmpty()) {
                        System.out.println("No staff accounts found.");
                    } else {
                        for (StaffUser staff : staffList) {
                            System.out.println(staff); // uses overridden toString() with ID
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter new staff username: ");
                    String newUsername = input.nextLine();
                    System.out.print("Enter new staff password: ");
                    String newPassword = input.nextLine();

                    boolean exists = false;
                    for (User user : staffList) {
                        if (user.getUsername().equals(newUsername)) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Staff username already exists.");
                    } else {
                        staffList.add(new StaffUser(newUsername, newPassword));
                        System.out.println("Staff added successfully.");
                    }
                    break;

                case 3:
                    System.out.print("Enter staff username to delete: ");
                    String deleteUsername = input.nextLine();
                    boolean removed = false;
                    for (int i = 0; i < staffList.size(); i++) {
                        if (staffList.get(i).getUsername().equals(deleteUsername)) {
                            staffList.remove(i);
                            removed = true;
                            break;
                        }
                    }
                    System.out.println(removed ? "Staff deleted." : "Staff not found.");
                    break;

                case 4:
                    adminActive = false;
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ---------- Staff Menu ----------
    public static void handleStaffMenu(Scanner input, StaffUser staff, MembershipManager manager) {
        boolean staffActive = true;
        while (staffActive) {
            System.out.println("\n=== Staff Menu ===");
            System.out.println("1. Manage Members");
            System.out.println("2. Purchase (Coming Soon)");
            System.out.println("3. Inventory (Coming Soon)");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    handleMemberSubmenu(input, manager);
                    break;

                case 2:
                case 3:
                    System.out.println("Module coming soon.");
                    break;

                case 4:
                    staffActive = false;
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    // ---------- Member Menu ----------
    public static void handleMemberSubmenu(Scanner input, MembershipManager manager) {
        boolean active = true;
        while (active) {
            System.out.println("\n--- Manage Members ---");
            System.out.println("1. Add Member");
            System.out.println("2. View Members");
            System.out.println("3. Delete Member");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter phone number: ");
                    String phone = input.nextLine();
                    if (manager.findMemberByPhone(phone) != null) {
                        System.out.println("Member with this phone number already exists.");
                        break;
                    }

                    System.out.print("Enter name: ");
                    String name = input.nextLine();

                    String tier;
                    boolean validTier;
                    do {
                        System.out.print("Enter tier (Silver/Gold/Platinum): ");
                        tier = input.nextLine();
                        validTier = tier.equalsIgnoreCase("Silver") ||
                                    tier.equalsIgnoreCase("Gold") ||
                                    tier.equalsIgnoreCase("Platinum");
                        if (!validTier) {
                            System.out.println("Invalid tier.");
                        }
                    } while (!validTier);

                    boolean added = manager.addMember(name, phone, tier);
                    if(added){
                        System.out.println("Member added!");
                    }else{
                        System.out.println("Failed to add");
                    } break;
                case 2:
                    ArrayList<MemberUser> members = manager.getAllMembers();
                    if (members.isEmpty()) {
                        System.out.println("No members found.");
                    } else {
                        for (MemberUser member : members) {
                            System.out.println(member);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter phone number to delete: ");
                    String delPhone = input.nextLine();
                    boolean deleted = manager.deleteMember(delPhone);
                    if(deleted){
                        System.out.println("Member deleted.");
                    }else {
                        System.out.println("Member not found.");
                    }
                    break;

                case 4:
                    active = false;
                    break;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}


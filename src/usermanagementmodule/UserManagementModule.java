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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Admin account (fixed)
        AdminUser admin = new AdminUser("admin", "admin123");

        // Staff accounts
        ArrayList<StaffUser> staffList = new ArrayList<>();
        staffList.add(new StaffUser("staff1", "pass123"));

        // Member manager
        MembershipManager membershipManager = new MembershipManager();

        boolean running = true;

        while (running) {
            System.out.println("\n=== Welcome to Retail System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Staff Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    System.out.print("Enter admin username: ");
                    String adminUser = input.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPass = input.nextLine();

                    if (admin.getUsername().equals(adminUser) && admin.checkPassword(adminPass)) {
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
                    for (StaffUser s : staffList) {
                        if (s.getUsername().equals(staffUser) && s.checkPassword(staffPass)) {
                            loggedIn = s;
                            break;
                        }
                    }

                    if (loggedIn != null) {
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

    // Admin Menu Logic
    private static void handleAdminMenu(Scanner input, ArrayList<StaffUser> staffList) {
        boolean adminActive = true;
        while (adminActive) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View Staff");
            System.out.println("2. Logout");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    if (staffList.isEmpty()) {
                        System.out.println("No staff accounts found.");
                    } else {
                        for (StaffUser staff : staffList) {
                            System.out.println("Staff: " + staff.getUsername());
                        }
                    }
                    break;

                case 2:
                    adminActive = false;
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Staff Menu Logic
    private static void handleStaffMenu(Scanner input, StaffUser staff, MembershipManager manager) {
        boolean staffActive = true;
        while (staffActive) {
            System.out.println("\n=== Staff Menu ===");
            System.out.println("1. Add Member");
            System.out.println("2. View Members");
            System.out.println("3. Delete Member");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = input.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = input.nextLine();
                    String tier;
                    boolean added = false;
                    do {
                        System.out.print("Enter tier (Silver/Gold/Platinum): ");
                        tier = input.nextLine();
                        added = manager.addMember(name, phone, tier);
                        System.out.println(added ? "Member added." : "Invalid tier.");
                    } while (!added);
                    break;

                case 2:
                    ArrayList<MemberUser> members = manager.getAllMembers();
                    if (members.isEmpty()) {
                        System.out.println("No members.");
                    } else {
                        for (MemberUser m : members) {
                            System.out.println(m);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter phone to delete: ");
                    String delPhone = input.nextLine();
                    boolean deleted = manager.deleteMember(delPhone);
                    System.out.println(deleted ? "Member deleted." : "Member not found.");
                    break;

                case 4:
                    staffActive = false;
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
    
    


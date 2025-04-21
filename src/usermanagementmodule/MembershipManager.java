/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usermanagementmodule;
import java.util.ArrayList;
/**
 *
 * @author clogg
 */
public class MembershipManager {
    private ArrayList<MemberUser> memberList;

    public MembershipManager() {
        memberList = new ArrayList<>();
    }

    // Add member with full validation: tier + duplicate phone
    public boolean addMember(String name, String phoneNo, String tier) {
        if (!isValidTier(tier) || memberExists(phoneNo)) {
            return false;
        }
        
        //Normalize name and tier
        name = name.toUpperCase();  
        tier = tier.toUpperCase(); 
        memberList.add(new MemberUser(name, phoneNo, tier));
        return true;
    }

    // Helper method to check if phone number already exists
    private boolean memberExists(String phoneNo) {
        for (MemberUser m : memberList) {
            if (m.getPhoneNo().equals(phoneNo)) {
                return true;
            }
        }
        return false;
    }

    // Tier check logic
    private boolean isValidTier(String tier) {
        return tier.equalsIgnoreCase("Silver") ||
               tier.equalsIgnoreCase("Gold") ||
               tier.equalsIgnoreCase("Platinum");
    }

    public ArrayList<MemberUser> getAllMembers() {
        return memberList;
    }

    public boolean deleteMember(String phoneNo) {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getPhoneNo().equals(phoneNo)) {
                memberList.remove(i);
                return true;
            }
        }
        return false;
    }
}
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

    public boolean addMember(String name, String phoneNo, String tier) {
        if (!isValidTier(tier)) return false;

        tier = tier.toUpperCase(); // Standardize casing
        memberList.add(new MemberUser(name, phoneNo, tier));
        return true;
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

    public ArrayList<MemberUser> getAllMembers() {
        return memberList;
    }

    public MemberUser findMemberByPhone(String phoneNo) {
        for (MemberUser m : memberList) {
            if (m.getPhoneNo().equals(phoneNo)) return m;
        }
        return null;
    }

    public int getMemberCount() {
        return memberList.size();
    }

    private boolean isValidTier(String tier) {
        return tier.equalsIgnoreCase("Silver") ||
               tier.equalsIgnoreCase("Gold") ||
               tier.equalsIgnoreCase("Platinum");
    }
}


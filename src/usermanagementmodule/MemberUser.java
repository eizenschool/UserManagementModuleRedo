/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usermanagementmodule;

/**
 *
 * @author clogg
 */
public class MemberUser {
    private String name;
    private String phoneNo;
    private String membershipType;

    public MemberUser(String name, String phoneNo, String membershipType) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.membershipType = membershipType;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Phone: " + phoneNo + " | Tier: " + membershipType;
    }
}


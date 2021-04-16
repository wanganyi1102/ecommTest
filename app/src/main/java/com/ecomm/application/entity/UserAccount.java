package com.ecomm.application.entity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.LocalDate;

public class UserAccount {

    private String email;
    private String phoneNo;
    private String password;
    private String shippingAddress;
    private String cardNo;
    private LocalDate expireDate;
    private int CVC;
    FirebaseAuth mAuth;

    public UserAccount(String email, String phoneNo, String password){
        setEmail(email);
        setPhoneNo(phoneNo);
        setPassword(password);
    }

    public String getEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user.getEmail();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public int getCVC() {
        return CVC;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setCVC(int CVC) {
        this.CVC = CVC;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}

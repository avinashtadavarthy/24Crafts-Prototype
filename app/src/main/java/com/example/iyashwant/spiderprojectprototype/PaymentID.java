package com.example.iyashwant.spiderprojectprototype;

/**
 * Created by rakesh on 21/12/17.
 */

public class PaymentID {

    String razorpayPaymentID;
    int amount;

    public String getRazorpayPaymentID(){
        return razorpayPaymentID;
    }

    public void setRazorpayPaymentID(String razorpayPaymentID){
        this.razorpayPaymentID = razorpayPaymentID;
    }
    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

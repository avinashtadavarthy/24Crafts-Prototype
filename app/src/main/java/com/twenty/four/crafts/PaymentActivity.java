package com.twenty.four.crafts;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.twenty.four.crafts.ApiInterface.retrofit;


public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();
    public int amount = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Checkout.preload(getApplicationContext());

        startPayment();


    }


    public void startPayment(){
        final Activity activity = this;
        final Checkout checkout = new Checkout();
        String jsonStringResponse = null;
        try{
            JSONObject options = new JSONObject();

            options.put("name","merchant name");
            options.put("currency","INR");
            options.put("amount","100");
            checkout.open(activity,options);
            jsonStringResponse = options.toString();

        }catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }

        Log.d("payment",jsonStringResponse);
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {

        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
        ApiInterface service = retrofit.create(ApiInterface.class);
        PaymentID paymentID = new PaymentID();
        paymentID.setRazorpayPaymentID(razorpayPaymentID);
        paymentID.setAmount(amount);
        Call<PaymentID> call = service.insertData(paymentID.getRazorpayPaymentID(), paymentID.getAmount());
        call.enqueue(new Callback<PaymentID>() {
            @Override
            public void onResponse(Call<PaymentID> call, Response<PaymentID> response) {

            }

            @Override
            public void onFailure(Call<PaymentID> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, "Throwable"+t, Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }

    }


}


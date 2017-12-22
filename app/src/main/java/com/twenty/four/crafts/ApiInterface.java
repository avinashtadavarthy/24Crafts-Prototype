package com.twenty.four.crafts;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by rakesh on 21/12/17.
 */

public interface ApiInterface {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://24crafts.tk:3000/user/payments/dummy/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @FormUrlEncoded
    @POST("")
    Call<PaymentID> insertData(@Field("payment_id") String razorpayPaymentID, @Field("amount") int amount);

}

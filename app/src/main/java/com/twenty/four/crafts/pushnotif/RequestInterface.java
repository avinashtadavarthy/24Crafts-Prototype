package com.twenty.four.crafts.pushnotif;


import com.twenty.four.crafts.pushnotif.models.RequestBody;
import com.twenty.four.crafts.pushnotif.models.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("devices")
    Call<ResponseBody> registerDevice(@Body RequestBody body);
}

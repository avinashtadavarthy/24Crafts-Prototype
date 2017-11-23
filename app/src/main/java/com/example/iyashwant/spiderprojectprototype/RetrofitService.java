package com.example.iyashwant.spiderprojectprototype;

import com.example.iyashwant.spiderprojectprototype.Auditions.AuditionHelper;

import org.junit.runners.Parameterized;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by srikanth on 10/11/17.
 */

public interface RetrofitService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://24crafts.ml:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("/user/audition/view")
    Call<List<AuditionHelper>> fetchAuditions(@Header("authorization") String jwtToken);
    }
package com.example.iyashwant.spiderprojectprototype;

/**
 * Created by rajiv on 11/9/17.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by srikanth on 25/5/17.
 */

public interface Retrofitextract {

    @GET("/{craft_name}")//attached to base url
    Call<List<ContactsHelper>> getLongUrl(@Path("craft_name") String craft_name);//
    //Call<ShortToLongUrlHelper> a pojo object

}

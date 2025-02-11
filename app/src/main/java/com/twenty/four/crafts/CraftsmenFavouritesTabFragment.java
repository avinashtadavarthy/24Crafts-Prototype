package com.twenty.four.crafts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class CraftsmenFavouritesTabFragment extends android.support.v4.app.Fragment {

    View myView;
    ArrayList<String> arrayList1 = new ArrayList<>();



    ArrayList<String> imageURLs = new ArrayList<>();
    RecyclerView favs_grid;

    String[] mThumbIds = {
            "https://homepages.cae.wisc.edu/~ece533/images/airplane.png", "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", "https://homepages.cae.wisc.edu/~ece533/images/baboon.png",
            "https://homepages.cae.wisc.edu/~ece533/images/barbara.bmp", "https://homepages.cae.wisc.edu/~ece533/images/boat.png", "https://homepages.cae.wisc.edu/~ece533/images/boy.bmp",
            "https://homepages.cae.wisc.edu/~ece533/images/cat.png", "https://homepages.cae.wisc.edu/~ece533/images/fruits.png", "https://homepages.cae.wisc.edu/~ece533/images/frymire.png",
            "https://homepages.cae.wisc.edu/~ece533/images/girl.png", "https://homepages.cae.wisc.edu/~ece533/images/goldhill.png", "https://homepages.cae.wisc.edu/~ece533/images/lena.png",
            "https://homepages.cae.wisc.edu/~ece533/images/monarch.png", "https://homepages.cae.wisc.edu/~ece533/images/mountain.png", "https://homepages.cae.wisc.edu/~ece533/images/peppers.png",
            "https://homepages.cae.wisc.edu/~ece533/images/serrano.png", "https://source.unsplash.com/Xq1ntWruZQI/600x800", "https://source.unsplash.com/NYyCqdBOKwc/600x800",
            "https://source.unsplash.com/buF62ewDLcQ/600x800", "https://source.unsplash.com/THozNzxEP3g/600x800", "https://source.unsplash.com/USrZRcRS2Lw/600x800",
            "https://source.unsplash.com/PeFk7fzxTdk/600x800", "https://source.unsplash.com/LrMWHKqilUw/600x800", "https://source.unsplash.com/HN-5Z6AmxrM/600x800",
            "https://source.unsplash.com/CdVAUADdqEc/600x800", "https://source.unsplash.com/AWh9C-QjhE4/600x800"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_dashboard_favourites_tab_fragment, container, false);


        User.getInstance().navbarpos = 2;
        imageURLs = new ArrayList<>();


        Log.e("onCreateView","called");


        favs_grid = (RecyclerView) myView.findViewById(R.id.favs_grid);
        int numberOfColumns = 3;
        favs_grid.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), numberOfColumns));

        //ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(mThumbIds));

        ArrayList<String> arrayList = new ArrayList<>();

        //getfavourites();



        favs_grid.setNestedScrollingEnabled(true);

        favs_grid.addOnItemTouchListener(new RecyclerItemClickListener(this.getActivity().getApplicationContext(), favs_grid ,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.e("favsgridonClick",arrayList1.get(position));
                        Intent page = new Intent(getActivity().getApplicationContext(), ProfileView.class)
                                .putExtra("thisistogetback", "do nothing")
                                .putExtra("fromwhom", "do nothing")
                                .putExtra("mode","favorites")
                                .putExtra("userid",arrayList1.get(position))
                                .putExtra("viewingmyprofile", "false");
                        startActivity(page);
                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );


        return myView;
    }


    public void getfavourites()
    {

        imageURLs = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        String userdatamain = getSPData("userdatamain");

        try {
            JSONObject jsonObject = new JSONObject(userdatamain);
            JSONArray jsonArray = jsonObject.optJSONArray("favorites");


            //if(getSPData("").equals("favsImgURLs") || getSPData("favsImgURLs").equals(null))
            //getAllUsersData(arrayList);


            if (jsonArray.length() != 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        String temp = jsonArray.getString(i);
                        temp = temp.substring(0, temp.length());

                        Log.e("temp" + i, temp);

                        arrayList1.add(temp);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                JSONArray favoritesIDs = new JSONArray(arrayList1);
                storeSPData("favoritesIDs", favoritesIDs.toString());

                String concatID;

                if(arrayList1.size() != 1)
                    concatID = arrayList1.get(0) + ",";

                else
                    concatID = arrayList1.get(0);

                for (int i = 1; i < arrayList1.size(); i++) {
                    if (i == arrayList1.size() - 1)
                        concatID = concatID + arrayList1.get(i);

                    else
                        concatID = concatID + arrayList1.get(i) + ",";
                }

                Log.e("concatID", concatID);

                AndroidNetworking.post(User.getInstance().BASE_URL + "user/getDetails")
                        .setPriority(Priority.MEDIUM)
                        .addHeaders("authorization", getSPData("jwtToken"))
                        .addBodyParameter("idArray", concatID)
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {


                                Log.e("user/getDetails:log", response.toString());
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject current = response.getJSONObject(i);
                                        String profileImageURL = current.optString("profileImageURL");

                                        Log.e("imageURL:favs" + i, profileImageURL);
                                        if (profileImageURL.equals("") || profileImageURL.equals(null))
                                            imageURLs.add("homepages.cae.wisc.edu/~ece533/images/airplane.png");

                                        else
                                            imageURLs.add(profileImageURL);


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }


                                    FavsRecyclerAdapter adapter = new FavsRecyclerAdapter(getContext(), imageURLs);

                                    favs_grid.setAdapter(adapter);



                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }

            } catch(JSONException e){
                e.printStackTrace();
            }


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume","called");

        getfavourites();
    }



    //Shared Preferences
    private void storeSPData(String key, String data) {

        SharedPreferences mUserData = getContext().getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

    private String getSPData(String key) {

        SharedPreferences mUserData = getContext().getSharedPreferences("UserData", MODE_PRIVATE);
        String data = mUserData.getString(key, "");

        return data;

    }


}

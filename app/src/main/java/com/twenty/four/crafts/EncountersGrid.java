package com.twenty.four.crafts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.irozon.alertview.AlertActionStyle;
import com.irozon.alertview.AlertStyle;
import com.irozon.alertview.AlertTheme;
import com.irozon.alertview.AlertView;
import com.irozon.alertview.objects.AlertAction;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class EncountersGrid extends android.support.v4.app.Fragment {

    View myView;

    FavsRecyclerAdapter adapter;
    String chosenCraftEncounters = "";

    String[] whoNcrafts = {
            "Actor","Actress","Child Artist","Singer","Dancer",
            "Side Artist","Assistant Director","Lyric Writer / Lyricist",
            "Dialouge Writer","Script / Screenplay Writers", "Story Board Artist",
            "Choreographer","Director of Photography", "Still Photographer",
            "PRO", "Designer", "Production Manager",
            "Focus Puller", "Vehicle Driver", "Mic Department",
            "Music Director", "Make-up Man", "Hair Dresser",
            "Costumer", "Art Department", "Set Department",
            "Stuntman", "Editor", "Location Manager",
            "Production (Food)", "Dubbing Artist", "Sound Recording Engineer",
            "Sound Mixing Engineer", "Digital Intermediate", "VFX / CG",
            "SFX", "Pet Supplier / Pet Doctor / AWBI Certifications"};

    String[] whoNcrafts1 = {
            "Actor","Actress","Childartist","Singer","Dancer",
            "Sideartist","Assistantdirector","Lyricwriter",
            "Dialoguewriter","Scriptwriter", "Storyboardartist",
            "Choreographer","Directorofphotography", "Stillphotographer",
            "Pro", "Designer", "Productionmanager",
            "Focuspuller", "Vehicledriver", "Micdepartment",
            "Musicdirector", "Makeupman", "Hairdresser",
            "Costumer", "Artdepartment", "Setdepartment",
            "Stuntman", "Editor", "Locationmanager",
            "Productionfood", "Dubbingartist", "Soundrecordingengineer",
            "Soundmixingengineer", "Di", "VFX",
            "Sfx", "Petsupplier"};

    RecyclerView encounters_grid;
    private ProgressBar progressBar;

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

    ArrayList<String> encountersimage = new ArrayList<>();
    ArrayList<String> userids = new ArrayList<>();

    SharedPref sharedPref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.encounters_grid_fragment,container,false);
        getActivity().setTitle("Encounters");
        setHasOptionsMenu(true);

        sharedPref = new SharedPref(getActivity().getApplicationContext());

        progressBar = (ProgressBar) myView.findViewById(R.id.activity_main_progress_bar);

        encounters_grid = (RecyclerView) myView.findViewById(R.id.encounters_grid);
        int numberOfColumns = 3;
        encounters_grid.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), numberOfColumns));









        encounters_grid.addOnItemTouchListener(new RecyclerItemClickListener(this.getActivity().getApplicationContext(), encounters_grid ,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent page = new Intent(getActivity().getApplicationContext(), ProfileView.class)
                                .putExtra("thisistogetback", "do nothing")
                                .putExtra("fromwhom", "do nothing")
                                .putExtra("viewingmyprofile", "false")
                                .putExtra("mode","encountersgrid")
                                .putExtra("userid", userids.get(position));
                        startActivity(page);
                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );

        return myView;
    }

    //functions space
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_encounters_grid_off, menu);
    }


    @Override
    public void onResume() {
        super.onResume();

        if(!getSPData("chosenCraftEncounters").equalsIgnoreCase(""))
            populateAdapter(getSPData("chosenCraftEncounters"));

        else
            populateAdapter("Actor");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case  R.id.menu_grid_off:
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new EncountersMain()).commit();
                sharedPref.storeSPData(getActivity().getApplicationContext(), "encountersView", "GridOff");
                break;

            case R.id.menu_filter:

                final AlertView alert = new AlertView("CHOOSE CRAFT", "Please choose the craft whose profiles you would like to see", AlertStyle.DIALOG);
                alert.addAction(new AlertAction(whoNcrafts[0], AlertActionStyle.DEFAULT, action -> {
                    chosenCraftEncounters = whoNcrafts1[0];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[1], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[1];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[2], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[2];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[3], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[3];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[4], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[4];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[5], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[5];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[6], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[6];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[7], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[7];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[8], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[8];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[9], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[9];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[10], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[10];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[11], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[11];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[12], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[12];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[13], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[13];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[14], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[14];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[15], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[15];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[16], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[16];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[17], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[17];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[18], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[18];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[19], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[19];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[20], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[20];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[21], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[21];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[22], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[22];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[23], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[23];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);

                }));
                alert.addAction(new AlertAction(whoNcrafts[24], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[24];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[25], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[25];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[26], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[26];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[27], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[27];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[28], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[28];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[29], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[29];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[30], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[30];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[31], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[31];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[32], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[32];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[33], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[33];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[34], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[34];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[35], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[35];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));
                alert.addAction(new AlertAction(whoNcrafts[36], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[36];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    populateAdapter(chosenCraftEncounters);
                }));

                alert.setTheme(AlertTheme.LIGHT);
                alert.show((AppCompatActivity) getActivity());


                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateAdapter(String craftChosen) {

        progressBar.setVisibility(View.VISIBLE);

        Log.e("craft:Grid",craftChosen);
        encountersimage.clear();
        userids.clear();

        AndroidNetworking.get(User.getInstance().BASE_URL + "user/encounters/" + craftChosen)
                .addHeaders("authorization", sharedPref.getSPData(getActivity().getApplicationContext(), "jwtToken"))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.e("encounterResponse:Grid",response.toString());

                        for (int i = 0; i<response.length(); i++) {

                            try {

                                userids.add(response.getJSONObject(i).optString("_id"));

                                String imgurl = "null";

                                if(response.getJSONObject(i).optString("profileImageURL").equals("")) {
                                    //add default image
                                    imgurl = "homepages.cae.wisc.edu/~ece533/images/airplane.png";
                                } else {
                                    imgurl =  response.getJSONObject(i).optString("profileImageURL");
                                }

                                encountersimage.add(imgurl);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        progressBar.setVisibility(View.GONE);

                        adapter = new FavsRecyclerAdapter(getActivity().getApplicationContext(), encountersimage);

                            encounters_grid.setAdapter(adapter);
                            encounters_grid.setNestedScrollingEnabled(true);


                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                    }
                });
        //pull the data down
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

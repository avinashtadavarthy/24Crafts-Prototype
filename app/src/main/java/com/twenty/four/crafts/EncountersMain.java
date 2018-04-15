package com.twenty.four.crafts;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.irozon.alertview.AlertActionStyle;
import com.irozon.alertview.AlertStyle;
import com.irozon.alertview.AlertTheme;
import com.irozon.alertview.AlertView;
import com.irozon.alertview.objects.AlertAction;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class EncountersMain extends android.support.v4.app.Fragment {

    View myView;
    private ProgressBar progressBar;
    private CardStackView cardStackView;
    private TouristSpotCardAdapter adapter,adapter1;
    LinearLayout row;

    String chosenCraftEncounters = "";
    int lastindex;
    int right,left,pos;
    int undo;
    boolean targetEncounter;
    //int clickStar,clickCross,swipeRighttoLike,swipelefttoDislike,undo;

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


    SharedPreferences pref;
    SharedPreferences.Editor editor;

    SharedPref sharedPref;

    String lastSwipeDirection = "";
    ImageView forbiddenMark,undoButton,starInEncounters,closeEnvelope;


    ArrayList<String> encountersnamenage = new ArrayList<>();
    ArrayList<String> encounterscraft = new ArrayList<>();
    ArrayList<String> encountersimage = new ArrayList<>();
    ArrayList<String> userids = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.from(container.getContext()).inflate(R.layout.activity_encounters_main, container, false);

        getActivity().setTitle("Encounters");
        setHasOptionsMenu(true);
        row = myView.findViewById(R.id.rowOfIcons);






        sharedPref = new SharedPref(getActivity().getApplicationContext());

        User.getInstance().navbarpos = 0;

        forbiddenMark = myView.findViewById(R.id.forbiddenMark);
        undoButton = myView.findViewById(R.id.undoButton);
        starInEncounters = myView.findViewById(R.id.starInEncounters);
        closeEnvelope = myView.findViewById(R.id.closeEnvelope);


        pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();

        progressBar = (ProgressBar) myView.findViewById(R.id.activity_main_progress_bar);

        cardStackView = (CardStackView) myView.findViewById(R.id.activity_main_card_stack_view);

        //setup();
        cardStackView.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                Log.d("CardStackView", "onCardDragging");
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {

                getSharedPreferences();


                lastSwipeDirection = direction.toString();
                lastindex = cardStackView.getTopIndex();

                Log.e("lastSwipeDir",lastSwipeDirection);
                Log.e("lastindex",lastindex+"");

                if (direction.toString().equalsIgnoreCase("right")) {

                    if (right == 0) {
                       // AlertDialogSwipeRight();
                        editor.putInt("right", 1);
                        editor.commit();
                    }



                    onSwipeRight(cardStackView.getTopIndex()-1);
                }

                if (direction.toString().equalsIgnoreCase("left")) {

                    if (left == 0) {
                      //  AlertDialogSwipeLeft();
                        editor.putInt("left", 1);
                        editor.commit();
                    }

                }
                Log.e("CardStackView", "onCardSwiped: " + direction.toString());
                Log.e("CardStackView", "topIndex: " + cardStackView.getTopIndex());
                if (cardStackView.getTopIndex() == adapter.getCount() - 5) {
                    Log.d("CardStackView", "Paginate: " + cardStackView.getTopIndex());
                    paginate();
                }
            }

            @Override
            public void onCardReversed() {

                Log.e("onCardReverse","called");
                if(lastSwipeDirection.equalsIgnoreCase("right"))
                {
                    removeFromFavs(userids.get(lastindex-1));
                }

                Log.d("CardStackView", "onCardReversed");
            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin");
            }

            @Override
            public void onCardClicked(int index) {

                Intent page = new Intent(getActivity().getApplicationContext(), ProfileView.class)
                        .putExtra("thisistogetback", "do nothing")
                        .putExtra("fromwhom", "do nothing")
                        .putExtra("viewingmyprofile", "false")
                        .putExtra("mode","encountersmain")
                        .putExtra("userid", userids.get(index));
                startActivity(page);

            }
        });


        forbiddenMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeLeft();
            }
        });

        starInEncounters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeRight();
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reverse();
            }
        });

        closeEnvelope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences();

                if (undo == 0) {
                    AlertDialogUndo();
                    editor.putInt("undo", 1);
                    editor.commit();
                } else
                    reverse();
            }
        });



        //reload();

        // Tap Target View - Implementation for a Fragment - Haiz,,,,
            TapTargetSequence sequence = new TapTargetSequence(getActivity())
                    .targets(
                            // Specify the target radius (in dp)
                            TapTarget.forView(starInEncounters, "Add to Favorites", "Click on the Star Button or Swipe Right to add Profile to your Favorites!")
                                    // All options below are optional
                                    .outerCircleColor(R.color.Color_SeaGreen)
                                    .targetCircleColor(R.color.Color_YellowGreen)
                                    .titleTextSize(22)
                                    .titleTextColor(R.color.Color_White)
                                    .descriptionTextSize(20)
                                    .descriptionTextColor(R.color.Color_WhiteSmoke)
                                    .textTypeface(Typeface.SANS_SERIF)
                                    .dimColor(R.color.Color_Azure)
                                    .drawShadow(true)
                                    .cancelable(true)
                                    .tintTarget(false)
                                    .transparentTarget(false)
                                    .targetRadius(35)
                                    .id(1)
                    )
                    .targets(
                            TapTarget.forView(closeEnvelope, "Send a Message", "Click here to send a personal message")
                                    .outerCircleColor(R.color.Color_FireBrick)
                                    .targetCircleColor(R.color.Color_DarkOrange)
                                    .titleTextSize(22)
                                    .titleTextColor(R.color.Color_White)
                                    .descriptionTextSize(20)
                                    .descriptionTextColor(R.color.Color_WhiteSmoke)
                                    .textTypeface(Typeface.SANS_SERIF)
                                    .dimColor(R.color.Color_Azure)
                                    .drawShadow(true)
                                    .cancelable(true)
                                    .tintTarget(false)
                                    .transparentTarget(false)
                                    .targetRadius(35)
                                    .id(1)
                    )
                    .targets(
                            TapTarget.forView(undoButton, "Undo Action", "Made a wrong Swipe? We got you covered!")
                                    .outerCircleColor(R.color.Color_DarkOrange)
                                    .targetCircleColor(R.color.Color_FireBrick)
                                    .titleTextSize(22)
                                    .titleTextColor(R.color.Color_White)
                                    .descriptionTextSize(20)
                                    .descriptionTextColor(R.color.Color_White)
                                    .textTypeface(Typeface.SANS_SERIF)
                                    .dimColor(R.color.Color_Azure)
                                    .drawShadow(true)
                                    .cancelable(true)
                                    .tintTarget(false)
                                    .transparentTarget(false)
                                    .targetRadius(35)
                                    .id(1)
                    )
                    .targets(
                            TapTarget.forView(forbiddenMark, "Skip Profile", "Clicking on the X or Swiping Left will skip the Profile")
                                    .outerCircleColor(R.color.Color_DarkBlue)
                                    .targetCircleColor(R.color.Color_DarkOrange)
                                    .titleTextSize(22)
                                    .titleTextColor(R.color.Color_White)
                                    .descriptionTextSize(20)
                                    .descriptionTextColor(R.color.Color_WhiteSmoke)
                                    .textTypeface(Typeface.SANS_SERIF)
                                    .dimColor(R.color.Color_Azure)
                                    .drawShadow(true)
                                    .cancelable(true)
                                    .tintTarget(false)
                                    .transparentTarget(false)
                                    .targetRadius(35)
                                    .id(1)
                    )
                    .listener(new TapTargetSequence.Listener() {
                        @Override
                        public void onSequenceFinish() {

                        }

                        @Override
                        public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                        }

                        @Override
                        public void onSequenceCanceled(TapTarget lastTarget) {

                        }
                        // This listener will tell us when interesting(tm) events happen in regards
                        // to the sequence


                    });
            sequence.considerOuterCircleCanceled(true);
            sequence.continueOnCancel(true);
            getSharedPreferences();

            if (!targetEncounter){
                sequence.start();
                editor.putBoolean("targetEncounter",true);
                editor.commit();
                return myView;
            }
            else {
                return myView;
            }
    }

    private void removeFromFavs(String s) {

        Log.e("userIDundo",s);
        AndroidNetworking.initialize(getActivity().getApplicationContext());
        AndroidNetworking.get(User.getInstance().BASE_URL + "user/favs/remove/" + s)
                .setPriority(Priority.MEDIUM)
                .addHeaders("authorization",getSPData("jwtToken"))
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Removefromfavs",response);
                        Toast.makeText(getContext(), "Removed from favourites!", Toast.LENGTH_SHORT).show();
                        updateUserRequest();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void updateUserRequest() {


        AndroidNetworking.get(User.getInstance().BASE_URL + "user")
                .addHeaders("authorization",getSPData("jwtToken"))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        storeSPData("userdatamain",response.toString());
                        Log.e("userdatamain:profview",response.toString());
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void getSharedPreferences() {


        right = pref.getInt("right",0);
        left = pref.getInt("left",0);
        undo = pref.getInt("undo",0);
        targetEncounter = pref.getBoolean("targetEncounter",false);
    }



    private TouristSpot createTouristSpot() {
        return new TouristSpot("Yasaka Shrine", "Kyoto", "https://source.unsplash.com/Xq1ntWruZQI/600x800");
    }

    private List<TouristSpot> createTouristSpots() {
        List<TouristSpot> spots = new ArrayList<>();

        for(int i = 0; i < encountersnamenage.size(); i++) {

            Log.e("encounters data", encountersnamenage.get(i) + '\n' + encounterscraft.get(i) + '\n' + encountersimage.get(i));

            spots.add(new TouristSpot(encountersnamenage.get(i), encounterscraft.get(i), encountersimage.get(i)));
        }

        return spots;

    }

    private TouristSpotCardAdapter createTouristSpotCardAdapter(String craft) {
         adapter1 = null;
         if(getActivity()!= null) {
             adapter1 = new TouristSpotCardAdapter(getActivity().getApplicationContext());
             encountersnamenage.clear();
             encounterscraft.clear();
             encountersimage.clear();
             userids.clear();

             Log.e("craft:Main", craft);

             //pull the data down
             AndroidNetworking.get(User.getInstance().BASE_URL + "user/encounters/" + craft)
                     .addHeaders("authorization", sharedPref.getSPData(getActivity().getApplicationContext(), "jwtToken"))
                     .setPriority(Priority.MEDIUM)
                     .build()
                     .getAsJSONArray(new JSONArrayRequestListener() {
                         @Override
                         public void onResponse(JSONArray response) {
                             Log.e("encounterResponse:Main", response.toString());

                             for (int i = 0; i < response.length(); i++) {

                                 try {

                                     String dob = response.getJSONObject(i).optString("dob");
                                     int year = Integer.parseInt(dob.substring(0, 4));
                                     int month = Integer.parseInt(dob.substring(5, 7));
                                     int day = Integer.parseInt(dob.substring(8, 10));
                                     String Age = User.getInstance().getAge(year, month, day);
                                     String namenage = response.getJSONObject(i).optString("name") + ", " + Age;

                                     String craft = response.getJSONObject(i).optString("category");

                                     String imgurl = "null";

                                     if (response.getJSONObject(i).optString("profileImageURL").equals("")) {
                                         //add default image
                                         imgurl = "http://homepages.cae.wisc.edu/~ece533/images/airplane.png";
                                     } else {
                                         imgurl = "http://" + response.getJSONObject(i).optString("profileImageURL");
                                     }

                                     userids.add(response.getJSONObject(i).optString("_id"));

                                     encountersnamenage.add(namenage);
                                     encounterscraft.add(craft);
                                     encountersimage.add(imgurl);

                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 }
                             }


                             adapter1.addAll(createTouristSpots());

                         }

                         @Override
                         public void onError(ANError error) {
                             error.printStackTrace();
                         }
                     });
         }
        //pull the data down

        return adapter1;
    }

    @Override
    public void onResume() {
        super.onResume();


        reload();
    }

    private void setup() {


    }

    private void reload() {


        if(getSPData("chosenCraftEncounters").equalsIgnoreCase("") || getSPData("chosenCraftEncounters").equalsIgnoreCase(null))
            chosenCraftEncounters = "Actor";

        else
            chosenCraftEncounters = getSPData("chosenCraftEncounters");



        cardStackView.setVisibility(View.GONE);
        row.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter = createTouristSpotCardAdapter(chosenCraftEncounters);


                    cardStackView.setAdapter(adapter);
                    cardStackView.setVisibility(View.VISIBLE);
                    row.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

            }
        }, 1000);
    }

    private LinkedList<TouristSpot> extractRemainingTouristSpots() {
        LinkedList<TouristSpot> spots = new LinkedList<>();
        for (int i = cardStackView.getTopIndex(); i < adapter.getCount(); i++) {
            spots.add(adapter.getItem(i));
        }
        return spots;
    }

    private void addFirst() {
        LinkedList<TouristSpot> spots = extractRemainingTouristSpots();
        spots.addFirst(createTouristSpot());
        adapter.clear();
        adapter.addAll(spots);
        adapter.notifyDataSetChanged();
    }

    private void addLast() {
        LinkedList<TouristSpot> spots = extractRemainingTouristSpots();
        spots.addLast(createTouristSpot());
        adapter.clear();
        adapter.addAll(spots);
        adapter.notifyDataSetChanged();
    }

    private void removeFirst() {
        LinkedList<TouristSpot> spots = extractRemainingTouristSpots();
        if (spots.isEmpty()) {
            return;
        }

        spots.removeFirst();
        adapter.clear();
        adapter.addAll(spots);
        adapter.notifyDataSetChanged();
    }

    private void removeLast() {
        LinkedList<TouristSpot> spots = extractRemainingTouristSpots();
        if (spots.isEmpty()) {
            return;
        }

        spots.removeLast();
        adapter.clear();
        adapter.addAll(spots);
        adapter.notifyDataSetChanged();
    }

    private void paginate() {
        cardStackView.setPaginationReserved();
        adapter.addAll(createTouristSpots());
        adapter.notifyDataSetChanged();
    }

    public void swipeLeft() {
        List<TouristSpot> spots = extractRemainingTouristSpots();
        if (spots.isEmpty()) {
            return;
        }

        View target = cardStackView.getTopView();



        ValueAnimator rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", -10f));
        rotation.setDuration(200);
        ValueAnimator translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, -2000f));
        ValueAnimator translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f));
        translateX.setStartDelay(100);
        translateY.setStartDelay(100);
        translateX.setDuration(500);
        translateY.setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(rotation, translateX, translateY);
        cardStackView.swipe(SwipeDirection.Left, set);


    }


    public void onSwipeRight(int pos)
    {
        if (adapter.getCount() == 0) {
            return;
        }

        Log.e("pos:onSwipeRight",pos+"");
        Log.e("userid:onSwipeRight",userids.get(pos));

        AndroidNetworking.initialize(getActivity().getApplicationContext());


        AndroidNetworking.get(User.getInstance().BASE_URL + "user/favs/add/" + userids.get(pos))
                .setPriority(Priority.MEDIUM)
                .addHeaders("authorization", getSPData("jwtToken"))
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("encountersaddtofavs",response);
                        getUserDetails();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }

    private void getUserDetails() {

        AndroidNetworking.get(User.getInstance().BASE_URL + "user")
                .addHeaders("authorization",getSPData("jwtToken"))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        storeSPData("userdatamain",response.toString());
                        Log.e("userdatamain:encMain",response.toString());
                        reload();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }


    public void swipeRight() {
        //List<TouristSpot> spots = extractRemainingTouristSpots();
        if (adapter.getCount() == 0) {
            return;
        }


        View target = cardStackView.getTopView();




        ValueAnimator rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", 10f));
        rotation.setDuration(200);
        ValueAnimator translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, 2000f));
        ValueAnimator translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f));
        translateX.setStartDelay(100);
        translateY.setStartDelay(100);
        translateX.setDuration(500);
        translateY.setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(rotation, translateX, translateY);

        cardStackView.swipe(SwipeDirection.Right, set);

    }


    private void reverse() {

        Log.e("reverse","called");
        cardStackView.reverse();

    }


    public void AlertDialogUndo()
    {



        new LovelyStandardDialog(getActivity(),LovelyStandardDialog.ButtonLayout.VERTICAL)
                .setTopColorRes(R.color.indigo_500)
                .setButtonsColorRes(R.color.indigo_500)
                .setIcon(R.drawable.star75)
                .setTitle("Undo?")
                .setMessage("Are you sure you want to undo your last action?")
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        reverse();
                    }
                })
                .setNegativeButton("CANCEL", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                })
                .show();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_encounters, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case  R.id.menu_grid_on:
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new EncountersGrid()).commit();
                sharedPref.storeSPData(getActivity().getApplicationContext(), "encountersView", "GridOn");
                break;

            case R.id.menu_filter_grid:
                final AlertView alert = new AlertView("CHOOSE CRAFT", "Please choose the craft whose profiles you would like to see", AlertStyle.DIALOG);
                alert.addAction(new AlertAction(whoNcrafts[0], AlertActionStyle.DEFAULT, action -> {
                    chosenCraftEncounters = whoNcrafts1[0];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[1], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[1];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[2], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[2];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[3], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[3];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[4], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[4];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[5], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[5];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[6], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[6];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[7], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[7];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[8], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[8];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[9], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[9];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[10], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[10];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[11], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[11];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[12], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[12];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[13], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[13];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[14], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[14];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[15], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[15];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[16], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[16];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[17], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[17];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[18], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[18];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[19], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[19];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[20], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[20];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[21], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[21];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[22], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[22];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[23], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[23];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();

                }));
                alert.addAction(new AlertAction(whoNcrafts[24], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[24];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[25], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[25];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[26], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[26];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[27], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[27];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[28], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[28];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[29], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[29];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[30], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[30];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[31], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[31];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[32], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[32];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[33], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[33];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[34], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[34];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[35], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[35];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));
                alert.addAction(new AlertAction(whoNcrafts[36], AlertActionStyle.DEFAULT, action -> {

                    chosenCraftEncounters = whoNcrafts1[36];
                    storeSPData("chosenCraftEncounters",chosenCraftEncounters);
                    reload();
                }));

                alert.setTheme(AlertTheme.LIGHT);
                alert.show((AppCompatActivity) getActivity());


                //populateAdapter(chosenCraftEncounters);
                break;
        }

        return super.onOptionsItemSelected(item);
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

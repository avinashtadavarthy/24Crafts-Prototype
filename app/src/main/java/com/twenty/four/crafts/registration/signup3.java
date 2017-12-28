package com.twenty.four.crafts.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.twenty.four.crafts.CustomAdapterSpinner;
import com.twenty.four.crafts.R;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

public class signup3 extends AppCompatActivity {

    public static int APP_REQUEST_CODE = 99;


    Bundle bundle;
    String type, name, craft;
    LinearLayout questions_crafts, questions_clients;


    String[] actor_61string={"If yes, then what kind?",
            "Feature Films", "TV Shows", "Short Films", "College Culturals", "Anchor", "Hosts", "Other"};

    String[] actress_61string={"If yes, then what kind?",
            "Feature Films", "TV Shows", "Short Films", "College Culturals", "Anchor", "Hosts", "Other"};

    String[] side_61string={"If yes, then what kind?",
            "Feature Films", "TV Shows", "Short Films", "College Culturals", "Anchor", "Hosts", "Other"};

    String[] child_41string={"If yes, then what kind?",
            "Feature Films", "TV Shows", "Short Films", "College Culturals", "Anchor", "Hosts", "Other"};

    String[] singer_3string={"Select your singing style",
            "Choir / Orchestra", "Soloist", "Opera / Chinese Opera", "Gospel", "Traditional Music", "Carnatic", "Folk / Country", "Ghana", "Ghazal", "Rap", "World Music", "Jazz", "Blues", "Pop and Rock", "Others"};

    String[] dancer_3string={"Please Select Your Dancing Style"};

    String[] dancer_6string={"What are you interested in?",
            "Dancing", "Choreographing", "Both"};
    
    String[] sba_2string={"What do you design storyboards on?",
            "Pen & Paper", "Computer", "Both"};
    
    String[] sba_3string={"What are your story boards like?",
            "Static Pics", "Animated", "Both"};

    String[] choreo_3string={"Please Select Your Dancing Style"};
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        bundle = getIntent().getExtras();
        name = bundle.getString("name");
        craft = bundle.getString("craft");
        type = bundle.getString("type");

        questions_crafts = (LinearLayout) findViewById(R.id.questions_crafts);
        questions_clients = (LinearLayout) findViewById(R.id.questions_clients);

        if(type.equals("craftsman")) {
            questions_clients.setVisibility(View.GONE);
        } else if(type.equals("client")) {
            questions_crafts.setVisibility(View.GONE);
        }

        //craftsmen

        final LinearLayout actor = (LinearLayout) findViewById(R.id.actor);
        actor.setVisibility(View.GONE);
        RadioGroup actor_1radio = (RadioGroup) findViewById(R.id.actor_1radio);
        RadioButton actor_1radioyes = (RadioButton) findViewById(R.id.actor_1radioyes);
        final EditText actor_11 = (EditText) findViewById(R.id.actor_11);
        actor_11.setVisibility(View.GONE);
        RadioGroup actor_2radio = (RadioGroup) findViewById(R.id.actor_2radio);
        RadioButton actor_2radioyes = (RadioButton) findViewById(R.id.actor_2radioyes);
        final EditText actor_22 = (EditText) findViewById(R.id.actor_22);
        actor_22.setVisibility(View.GONE);
        RadioGroup actor_3radio = (RadioGroup) findViewById(R.id.actor_3radio);
        RadioButton actor_3radioyes = (RadioButton) findViewById(R.id.actor_3radioyes);
        RadioGroup actor_4radio = (RadioGroup) findViewById(R.id.actor_4radio);
        RadioButton actor_4radioyes = (RadioButton) findViewById(R.id.actor_4radioyes);
        final EditText actor_41 = (EditText) findViewById(R.id.actor_41);
        actor_41.setVisibility(View.GONE);
        RadioGroup actor_5radio = (RadioGroup) findViewById(R.id.actor_5radio);
        RadioButton actor_5radioyes = (RadioButton) findViewById(R.id.actor_5radioyes);
        RadioGroup actor_6radio = (RadioGroup) findViewById(R.id.actor_6radio);
        RadioButton actor_6radioyes = (RadioButton) findViewById(R.id.actor_6radioyes);
        final Spinner actor_61 = (Spinner) findViewById(R.id.actor_61);
        CustomAdapterSpinner actor_61adapter=new CustomAdapterSpinner(getApplicationContext(),actor_61string);
        actor_61.setAdapter(actor_61adapter);
        actor_61.setVisibility(View.GONE);
        final EditText actor_62 = (EditText) findViewById(R.id.actor_62);
        actor_62.setVisibility(View.GONE);


        final LinearLayout actress = (LinearLayout) findViewById(R.id.actress);
        actress.setVisibility(View.GONE);
        RadioGroup actress_1radio = (RadioGroup) findViewById(R.id.actress_1radio);
        RadioButton actress_1radioyes = (RadioButton) findViewById(R.id.actress_1radioyes);
        final EditText actress_11 = (EditText) findViewById(R.id.actress_11);
        actress_11.setVisibility(View.GONE);
        RadioGroup actress_2radio = (RadioGroup) findViewById(R.id.actress_2radio);
        RadioButton actress_2radioyes = (RadioButton) findViewById(R.id.actress_2radioyes);
        final EditText actress_22 = (EditText) findViewById(R.id.actress_22);
        actress_22.setVisibility(View.GONE);
        RadioGroup actress_3radio = (RadioGroup) findViewById(R.id.actress_3radio);
        RadioButton actress_3radioyes = (RadioButton) findViewById(R.id.actress_3radioyes);
        RadioGroup actress_4radio = (RadioGroup) findViewById(R.id.actress_4radio);
        RadioButton actress_4radioyes = (RadioButton) findViewById(R.id.actress_4radioyes);
        final EditText actress_41 = (EditText) findViewById(R.id.actress_41);
        actress_41.setVisibility(View.GONE);
        RadioGroup actress_5radio = (RadioGroup) findViewById(R.id.actress_5radio);
        RadioButton actress_5radioyes = (RadioButton) findViewById(R.id.actress_5radioyes);
        RadioGroup actress_6radio = (RadioGroup) findViewById(R.id.actress_6radio);
        RadioButton actress_6radioyes = (RadioButton) findViewById(R.id.actress_6radioyes);
        final Spinner actress_61 = (Spinner) findViewById(R.id.actress_61);
        CustomAdapterSpinner actress_61adapter=new CustomAdapterSpinner(getApplicationContext(),actress_61string);
        actress_61.setAdapter(actress_61adapter);
        actress_61.setVisibility(View.GONE);
        final EditText actress_62 = (EditText) findViewById(R.id.actress_62);
        actress_62.setVisibility(View.GONE);


        final LinearLayout side = (LinearLayout) findViewById(R.id.side);
        side.setVisibility(View.GONE);
        RadioGroup side_1radio = (RadioGroup) findViewById(R.id.side_1radio);
        RadioButton side_1radioyes = (RadioButton) findViewById(R.id.side_1radioyes);
        final EditText side_11 = (EditText) findViewById(R.id.side_11);
        side_11.setVisibility(View.GONE);
        RadioGroup side_2radio = (RadioGroup) findViewById(R.id.side_2radio);
        RadioButton side_2radioyes = (RadioButton) findViewById(R.id.side_2radioyes);
        final EditText side_22 = (EditText) findViewById(R.id.side_22);
        side_22.setVisibility(View.GONE);
        RadioGroup side_3radio = (RadioGroup) findViewById(R.id.side_3radio);
        RadioButton side_3radioyes = (RadioButton) findViewById(R.id.side_3radioyes);
        RadioGroup side_4radio = (RadioGroup) findViewById(R.id.side_4radio);
        RadioButton side_4radioyes = (RadioButton) findViewById(R.id.side_4radioyes);
        final EditText side_41 = (EditText) findViewById(R.id.side_41);
        side_41.setVisibility(View.GONE);
        RadioGroup side_5radio = (RadioGroup) findViewById(R.id.side_5radio);
        RadioButton side_5radioyes = (RadioButton) findViewById(R.id.side_5radioyes);
        RadioGroup side_6radio = (RadioGroup) findViewById(R.id.side_6radio);
        RadioButton side_6radioyes = (RadioButton) findViewById(R.id.side_6radioyes);
        final Spinner side_61 = (Spinner) findViewById(R.id.side_61);
        CustomAdapterSpinner side_61adapter=new CustomAdapterSpinner(getApplicationContext(),side_61string);
        side_61.setAdapter(side_61adapter);
        side_61.setVisibility(View.GONE);
        final EditText side_62 = (EditText) findViewById(R.id.side_62);
        side_62.setVisibility(View.GONE);


        final LinearLayout child = ( LinearLayout) findViewById(R.id.child);
        child.setVisibility(View.GONE);
        EditText child_11 = (EditText) findViewById(R.id.child_11);
        EditText child_12 = (EditText) findViewById(R.id.child_12);
        Spinner child_13 = (Spinner) findViewById(R.id.child_13);
        RadioGroup child_2radio = (RadioGroup) findViewById(R.id.child_2radio);
        RadioButton child_2radioyes = (RadioButton) findViewById(R.id.child_2radioyes);
        final EditText child_21 = (EditText) findViewById(R.id.child_21);
        child_21.setVisibility(View.GONE);
        RadioGroup child_3radio = (RadioGroup) findViewById(R.id.child_3radio);
        RadioButton child_3radioyes = (RadioButton) findViewById(R.id.child_3radioyes);
        final EditText child_31 = (EditText) findViewById(R.id.child_31);
        child_31.setVisibility(View.GONE);
        RadioGroup child_4radio = (RadioGroup) findViewById(R.id.child_4radio);
        RadioButton child_4radioyes = (RadioButton) findViewById(R.id.child_4radioyes);
        final Spinner child_41 = (Spinner) findViewById(R.id.child_41);
        CustomAdapterSpinner child_41adapter=new CustomAdapterSpinner(getApplicationContext(),child_41string);
        child_41.setAdapter(child_41adapter);
        child_41.setVisibility(View.GONE);
        final EditText child_42 = (EditText) findViewById(R.id.child_42);
        child_42.setVisibility(View.GONE);


        final LinearLayout singer = ( LinearLayout) findViewById(R.id.singer);
        singer.setVisibility(View.GONE);
        RadioGroup singer_1radio = (RadioGroup) findViewById(R.id.singer_1radio);
        RadioButton singer_1radioyes = (RadioButton) findViewById(R.id.singer_1radioyes);
        final EditText singer_11 = (EditText) findViewById(R.id.singer_11);
        singer_11.setVisibility(View.GONE);
        RadioGroup singer_2radio = (RadioGroup) findViewById(R.id.singer_2radio);
        RadioButton singer_2radioyes = (RadioButton) findViewById(R.id.singer_2radioyes);
        final EditText singer_21 = (EditText) findViewById(R.id.singer_21);
        singer_21.setVisibility(View.GONE);
        Spinner singer_3 = (Spinner) findViewById(R.id.singer_3);
        CustomAdapterSpinner singer_3adapter=new CustomAdapterSpinner(getApplicationContext(),singer_3string);
        singer_3.setAdapter(singer_3adapter);
        RadioGroup singer_4radio = (RadioGroup) findViewById(R.id.singer_4radio);
        RadioButton singer_4radioyes = (RadioButton) findViewById(R.id.singer_4radioyes);
        final EditText singer_41 = (EditText) findViewById(R.id.singer_41);
        singer_41.setVisibility(View.GONE);
        RadioGroup singer_5radio = (RadioGroup) findViewById(R.id.singer_5radio);
        RadioButton singer_5radioyes = (RadioButton) findViewById(R.id.singer_5radioyes);
        final EditText singer_51 = (EditText) findViewById(R.id.singer_51);
        singer_51.setVisibility(View.GONE);
        RadioGroup singer_6radio = (RadioGroup) findViewById(R.id.singer_6radio);
        RadioButton singer_6radioyes = (RadioButton) findViewById(R.id.singer_6radioyes);
        final EditText singer_61 = (EditText) findViewById(R.id.singer_61);
        singer_61.setVisibility(View.GONE);
        

        final LinearLayout dancer = ( LinearLayout) findViewById(R.id.dancer);
        dancer.setVisibility(View.GONE);
        RadioGroup dancer_1radio = (RadioGroup) findViewById(R.id.dancer_1radio);
        RadioButton dancer_1radioyes = (RadioButton) findViewById(R.id.dancer_1radioyes);
        final EditText dancer_11 = (EditText) findViewById(R.id.dancer_11);
        dancer_11.setVisibility(View.GONE);
        RadioGroup dancer_2radio = (RadioGroup) findViewById(R.id.dancer_2radio);
        RadioButton dancer_2radioyes = (RadioButton) findViewById(R.id.dancer_2radioyes);
        final EditText dancer_21 = (EditText) findViewById(R.id.dancer_21);
        dancer_21.setVisibility(View.GONE);
        Spinner dancer_3 = (Spinner) findViewById(R.id.dancer_3);
        CustomAdapterSpinner dancer_3adapter=new CustomAdapterSpinner(getApplicationContext(),dancer_3string);
        dancer_3.setAdapter(dancer_3adapter);
        RadioGroup dancer_4radio = (RadioGroup) findViewById(R.id.dancer_4radio);
        RadioButton dancer_4radioyes = (RadioButton) findViewById(R.id.dancer_4radioyes);
        final EditText dancer_41 = (EditText) findViewById(R.id.dancer_41);
        dancer_41.setVisibility(View.GONE);
        RadioGroup dancer_5radio = (RadioGroup) findViewById(R.id.dancer_5radio);
        RadioButton dancer_5radioyes = (RadioButton) findViewById(R.id.dancer_5radioyes);
        final Spinner dancer_51 = (Spinner) findViewById(R.id.dancer_51);
        dancer_51.setVisibility(View.GONE);
        final EditText dancer_52 = (EditText) findViewById(R.id.dancer_52);
        dancer_52.setVisibility(View.GONE);
        Spinner dancer_6 = (Spinner) findViewById(R.id.dancer_6);
        CustomAdapterSpinner dancer_6adapter=new CustomAdapterSpinner(getApplicationContext(),dancer_6string);
        dancer_6.setAdapter(dancer_6adapter);

        final LinearLayout assdir = ( LinearLayout) findViewById(R.id.assdir);
        assdir.setVisibility(View.GONE);
        RadioGroup assdir_1radio = (RadioGroup) findViewById(R.id.assdir_1radio);
        RadioButton assdir_1radioyes = (RadioButton) findViewById(R.id.assdir_1radioyes);
        final EditText assdir_11 = (EditText) findViewById(R.id.assdir_11);
        assdir_11.setVisibility(View.GONE);
        RadioGroup assdir_2radio = (RadioGroup) findViewById(R.id.assdir_2radio);
        RadioButton assdir_2radioyes = (RadioButton) findViewById(R.id.assdir_2radioyes);
        final EditText assdir_22 = (EditText) findViewById(R.id.assdir_22);
        assdir_22.setVisibility(View.GONE);
        RadioGroup assdir_3radio = (RadioGroup) findViewById(R.id.assdir_3radio);
        RadioButton assdir_3radioyes = (RadioButton) findViewById(R.id.assdir_3radioyes);
        RadioGroup assdir_4radio = (RadioGroup) findViewById(R.id.assdir_4radio);
        RadioButton assdir_4radioyes = (RadioButton) findViewById(R.id.assdir_4radioyes);
        RadioGroup assdir_5radio = (RadioGroup) findViewById(R.id.assdir_5radio);
        RadioButton assdir_5radioyes = (RadioButton) findViewById(R.id.assdir_5radioyes);
        RadioGroup assdir_6radio = (RadioGroup) findViewById(R.id.assdir_6radio);
        RadioButton assdir_6radioyes = (RadioButton) findViewById(R.id.assdir_6radioyes);
        

        final LinearLayout lyric = ( LinearLayout) findViewById(R.id.lyric);
        lyric.setVisibility(View.GONE);
        RadioGroup lyric_1radio = (RadioGroup) findViewById(R.id.lyric_1radio);
        RadioButton lyric_1radioyes = (RadioButton) findViewById(R.id.lyric_1radioyes);
        final EditText lyric_11 = (EditText) findViewById(R.id.lyric_11);
        lyric_11.setVisibility(View.GONE);
        final EditText lyric_12 = (EditText) findViewById(R.id.lyric_12);
        lyric_12.setVisibility(View.GONE);
        RadioGroup lyric_2radio = (RadioGroup) findViewById(R.id.lyric_2radio);
        RadioButton lyric_2radioyes = (RadioButton) findViewById(R.id.lyric_2radioyes);
        RadioGroup lyric_3radio = (RadioGroup) findViewById(R.id.lyric_3radio);
        RadioButton lyric_3radioyes = (RadioButton) findViewById(R.id.lyric_3radioyes);
        final EditText lyric_31 = (EditText) findViewById(R.id.lyric_31);
        lyric_31.setVisibility(View.GONE);
        

        final LinearLayout dwrite = ( LinearLayout) findViewById(R.id.dwrite);
        dwrite.setVisibility(View.GONE);
        RadioGroup dwrite_1radio = (RadioGroup) findViewById(R.id.dwrite_1radio);
        RadioButton dwrite_1radioyes = (RadioButton) findViewById(R.id.dwrite_1radioyes);
        final EditText dwrite_11 = (EditText) findViewById(R.id.dwrite_11);
        dwrite_11.setVisibility(View.GONE);
        EditText dwrite_2 = (EditText) findViewById(R.id.dwrite_2);
        RadioGroup dwrite_3radio = (RadioGroup) findViewById(R.id.dwrite_3radio);
        RadioButton dwrite_3radioyes = (RadioButton) findViewById(R.id.dwrite_3radioyes);
        RadioGroup dwrite_4radio = (RadioGroup) findViewById(R.id.dwrite_4radio);
        RadioButton dwrite_4radioyes = (RadioButton) findViewById(R.id.dwrite_4radioyes);
        final EditText dwrite_41 = (EditText) findViewById(R.id.dwrite_41);
        dwrite_41.setVisibility(View.GONE);
        

        final LinearLayout screenp = ( LinearLayout) findViewById(R.id.screenp);
        screenp.setVisibility(View.GONE);
        RadioGroup screenp_1radio = (RadioGroup) findViewById(R.id.screenp_1radio);
        RadioButton screenp_1radioyes = (RadioButton) findViewById(R.id.screenp_1radioyes);
        final EditText screenp_11 = (EditText) findViewById(R.id.screenp_11);
        screenp_11.setVisibility(View.GONE);
        EditText screenp_2 = (EditText) findViewById(R.id.screenp_2);
        RadioGroup screenp_3radio = (RadioGroup) findViewById(R.id.screenp_3radio);
        RadioButton screenp_3radioyes = (RadioButton) findViewById(R.id.screenp_3radioyes);
        RadioGroup screenp_4radio = (RadioGroup) findViewById(R.id.screenp_4radio);
        RadioButton screenp_4radioyes = (RadioButton) findViewById(R.id.screenp_4radioyes);
        final EditText screenp_41 = (EditText) findViewById(R.id.screenp_41);
        screenp_41.setVisibility(View.GONE);
        

        final LinearLayout sba = ( LinearLayout) findViewById(R.id.sba);
        sba.setVisibility(View.GONE);
        RadioGroup sba_1radio = (RadioGroup) findViewById(R.id.sba_1radio);
        RadioButton sba_1radioyes = (RadioButton) findViewById(R.id.sba_1radioyes);
        final EditText sba_11 = (EditText) findViewById(R.id.sba_11);
        sba_11.setVisibility(View.GONE);
        Spinner sba_2 = (Spinner) findViewById(R.id.sba_2);
        CustomAdapterSpinner sba_2adapter=new CustomAdapterSpinner(getApplicationContext(),sba_2string);
        sba_2.setAdapter(sba_2adapter);
        Spinner sba_3 = (Spinner) findViewById(R.id.sba_3);
        CustomAdapterSpinner sba_3adapter=new CustomAdapterSpinner(getApplicationContext(),sba_3string);
        sba_3.setAdapter(sba_3adapter);
        RadioGroup sba_4radio = (RadioGroup) findViewById(R.id.sba_4radio);
        RadioButton sba_4radioyes = (RadioButton) findViewById(R.id.sba_4radioyes);


        final LinearLayout choreo = ( LinearLayout) findViewById(R.id.choreo);
        choreo.setVisibility(View.GONE);
        Spinner choreo_1 = (Spinner) findViewById(R.id.choreo_1);
        RadioGroup choreo_2radio = (RadioGroup) findViewById(R.id.choreo_2radio);
        RadioButton choreo_2radioyes = (RadioButton) findViewById(R.id.choreo_2radioyes);
        final EditText choreo_21 = (EditText) findViewById(R.id.choreo_21);
        choreo_21.setVisibility(View.GONE);
        Spinner choreo_3 = (Spinner) findViewById(R.id.choreo_3);
        CustomAdapterSpinner choreo_3adapter=new CustomAdapterSpinner(getApplicationContext(),choreo_3string);
        choreo_3.setAdapter(choreo_3adapter);
        RadioGroup choreo_4radio = (RadioGroup) findViewById(R.id.choreo_4radio);
        RadioButton choreo_4radioyes = (RadioButton) findViewById(R.id.choreo_4radioyes);
        final EditText choreo_41 = (EditText) findViewById(R.id.choreo_41);
        choreo_41.setVisibility(View.GONE);
        RadioGroup choreo_5radio = (RadioGroup) findViewById(R.id.choreo_5radio);
        RadioButton choreo_5radioyes = (RadioButton) findViewById(R.id.choreo_5radioyes);
        final Spinner choreo_51 = (Spinner) findViewById(R.id.choreo_51);
        choreo_51.setVisibility(View.GONE);
        final EditText choreo_52 = (EditText) findViewById(R.id.choreo_52);
        choreo_52.setVisibility(View.GONE);
        EditText choreo_6 = (EditText) findViewById(R.id.choreo_6);



        final LinearLayout dop = ( LinearLayout) findViewById(R.id.dop);
        dop.setVisibility(View.GONE);
        RadioGroup dop_1radio = (RadioGroup) findViewById(R.id.dop_1radio);
        RadioButton dop_1radioyes = (RadioButton) findViewById(R.id.dop_1radioyes);
        final EditText dop_11 = (EditText) findViewById(R.id.dop_11);
        dop_11.setVisibility(View.GONE);
        EditText dop_2 = (EditText) findViewById(R.id.dop_2);
        EditText dop_3 = (EditText) findViewById(R.id.dop_3);
        EditText dop_4 = (EditText) findViewById(R.id.dop_4);
        RadioGroup dop_5radio = (RadioGroup) findViewById(R.id.dop_5radio);
        RadioButton dop_5radioyes = (RadioButton) findViewById(R.id.dop_5radioyes);




        final LinearLayout stp = ( LinearLayout) findViewById(R.id.stp);
        stp.setVisibility(View.GONE);
        RadioGroup stp_1radio = (RadioGroup) findViewById(R.id.stp_1radio);
        RadioButton stp_1radioyes = (RadioButton) findViewById(R.id.stp_1radioyes);
        final EditText stp_11 = (EditText) findViewById(R.id.stp_11);
        stp_11.setVisibility(View.GONE);
        EditText stp_2 = (EditText) findViewById(R.id.stp_2);
        RadioGroup stp_3radio = (RadioGroup) findViewById(R.id.stp_3radio);
        RadioButton stp_3radioyes = (RadioButton) findViewById(R.id.stp_3radioyes);




        final LinearLayout pro = ( LinearLayout) findViewById(R.id.pro);
        pro.setVisibility(View.GONE);
        RadioGroup pro_1radio = (RadioGroup) findViewById(R.id.pro_1radio);
        RadioButton pro_1radioyes = (RadioButton) findViewById(R.id.pro_1radioyes);
        final EditText pro_11 = (EditText) findViewById(R.id.pro_11);
        pro_11.setVisibility(View.GONE);
        EditText pro_2 = (EditText) findViewById(R.id.pro_2);
        RadioGroup pro_3radio = (RadioGroup) findViewById(R.id.pro_3radio);
        RadioButton pro_3radioyes = (RadioButton) findViewById(R.id.pro_3radioyes);




        final LinearLayout desi = ( LinearLayout) findViewById(R.id.desi);
        desi.setVisibility(View.GONE);
        RadioGroup desi_1radio = (RadioGroup) findViewById(R.id.desi_1radio);
        RadioButton desi_1radioyes = (RadioButton) findViewById(R.id.desi_1radioyes);
        final EditText desi_11 = (EditText) findViewById(R.id.desi_11);
        desi_11.setVisibility(View.GONE);
        EditText desi_2 = (EditText) findViewById(R.id.desi_2);
        EditText desi_3 = (EditText) findViewById(R.id.desi_3);
        RadioGroup desi_3radio = (RadioGroup) findViewById(R.id.desi_4radio);
        RadioButton desi_3radioyes = (RadioButton) findViewById(R.id.desi_4radioyes);




        final LinearLayout prodm = ( LinearLayout) findViewById(R.id.prodm);
        prodm.setVisibility(View.GONE);
        RadioGroup prodm_1radio = (RadioGroup) findViewById(R.id.prodm_1radio);
        RadioButton prodm_1radioyes = (RadioButton) findViewById(R.id.prodm_1radioyes);
        final EditText prodm_11 = (EditText) findViewById(R.id.prodm_11);
        prodm_11.setVisibility(View.GONE);
        EditText prodm_2 = (EditText) findViewById(R.id.prodm_2);
        RadioGroup prodm_3radio = (RadioGroup) findViewById(R.id.prodm_3radio);
        RadioButton prodm_3radioyes = (RadioButton) findViewById(R.id.prodm_3radioyes);




        final LinearLayout focus = ( LinearLayout) findViewById(R.id.focus);
        focus.setVisibility(View.GONE);
        RadioGroup focus_1radio = (RadioGroup) findViewById(R.id.focus_1radio);
        RadioButton focus_1radioyes = (RadioButton) findViewById(R.id.focus_1radioyes);
        final EditText focus_11 = (EditText) findViewById(R.id.focus_11);
        focus_11.setVisibility(View.GONE);
        EditText focus_2 = (EditText) findViewById(R.id.focus_2);
        RadioGroup focus_3radio = (RadioGroup) findViewById(R.id.focus_3radio);
        RadioButton focus_3radioyes = (RadioButton) findViewById(R.id.focus_3radioyes);




        final LinearLayout driver = ( LinearLayout) findViewById(R.id.driver);
        driver.setVisibility(View.GONE);
        RadioGroup driver_1radio = (RadioGroup) findViewById(R.id.driver_1radio);
        RadioButton driver_1radioyes = (RadioButton) findViewById(R.id.driver_1radioyes);
        final EditText driver_11 = (EditText) findViewById(R.id.driver_11);
        driver_11.setVisibility(View.GONE);
        EditText driver_2 = (EditText) findViewById(R.id.driver_2);
        RadioGroup driver_3radio = (RadioGroup) findViewById(R.id.driver_3radio);
        RadioButton driver_3radioyes = (RadioButton) findViewById(R.id.driver_3radioyes);
        


        final LinearLayout mic = ( LinearLayout) findViewById(R.id.mic);
        mic.setVisibility(View.GONE);
        RadioGroup mic_1radio = (RadioGroup) findViewById(R.id.mic_1radio);
        RadioButton mic_1radioyes = (RadioButton) findViewById(R.id.mic_1radioyes);
        final EditText mic_11 = (EditText) findViewById(R.id.mic_11);
        mic_11.setVisibility(View.GONE);
        EditText mic_2 = (EditText) findViewById(R.id.mic_2);
        RadioGroup mic_3radio = (RadioGroup) findViewById(R.id.mic_3radio);
        RadioButton mic_3radioyes = (RadioButton) findViewById(R.id.mic_3radioyes);



        final LinearLayout musicd = ( LinearLayout) findViewById(R.id.musicd);
        musicd.setVisibility(View.GONE);
        RadioGroup musicd_1radio = (RadioGroup) findViewById(R.id.musicd_1radio);
        RadioButton musicd_1radioyes = (RadioButton) findViewById(R.id.musicd_1radioyes);
        final EditText musicd_11 = (EditText) findViewById(R.id.musicd_11);
        musicd_11.setVisibility(View.GONE);
        EditText musicd_2 = (EditText) findViewById(R.id.musicd_2);
        RadioGroup musicd_3radio = (RadioGroup) findViewById(R.id.musicd_3radio);
        RadioButton musicd_3radioyes = (RadioButton) findViewById(R.id.musicd_3radioyes);
        final EditText musicd_31 = (EditText) findViewById(R.id.musicd_31);
        musicd_31.setVisibility(View.GONE);
        RadioGroup musicd_4radio = (RadioGroup) findViewById(R.id.musicd_4radio);
        RadioButton musicd_4radioyes = (RadioButton) findViewById(R.id.musicd_4radioyes);




        final LinearLayout makeup = ( LinearLayout) findViewById(R.id.makeup);
        makeup.setVisibility(View.GONE);
        RadioGroup makeup_1radio = (RadioGroup) findViewById(R.id.makeup_1radio);
        RadioButton makeup_1radioyes = (RadioButton) findViewById(R.id.makeup_1radioyes);
        final EditText makeup_11 = (EditText) findViewById(R.id.makeup_11);
        makeup_11.setVisibility(View.GONE);
        EditText makeup_2 = (EditText) findViewById(R.id.makeup_2);
        RadioGroup makeup_3radio = (RadioGroup) findViewById(R.id.makeup_3radio);
        RadioButton makeup_3radioyes = (RadioButton) findViewById(R.id.makeup_3radioyes);




        final LinearLayout hairdr = ( LinearLayout) findViewById(R.id.hairdr);
        hairdr.setVisibility(View.GONE);
        RadioGroup hairdr_1radio = (RadioGroup) findViewById(R.id.hairdr_1radio);
        RadioButton hairdr_1radioyes = (RadioButton) findViewById(R.id.hairdr_1radioyes);
        final EditText hairdr_11 = (EditText) findViewById(R.id.hairdr_11);
        hairdr_11.setVisibility(View.GONE);
        EditText hairdr_2 = (EditText) findViewById(R.id.hairdr_2);
        RadioGroup hairdr_3radio = (RadioGroup) findViewById(R.id.hairdr_3radio);
        RadioButton hairdr_3radioyes = (RadioButton) findViewById(R.id.hairdr_3radioyes);




        final LinearLayout costu = ( LinearLayout) findViewById(R.id.costu);
        costu.setVisibility(View.GONE);
        RadioGroup costu_1radio = (RadioGroup) findViewById(R.id.costu_1radio);
        RadioButton costu_1radioyes = (RadioButton) findViewById(R.id.costu_1radioyes);
        final EditText costu_11 = (EditText) findViewById(R.id.costu_11);
        costu_11.setVisibility(View.GONE);
        EditText costu_2 = (EditText) findViewById(R.id.costu_2);
        RadioGroup costu_3radio = (RadioGroup) findViewById(R.id.costu_3radio);
        RadioButton costu_3radioyes = (RadioButton) findViewById(R.id.costu_3radioyes);




        final LinearLayout artd = ( LinearLayout) findViewById(R.id.artd);
        artd.setVisibility(View.GONE);
        RadioGroup artd_1radio = (RadioGroup) findViewById(R.id.artd_1radio);
        RadioButton artd_1radioyes = (RadioButton) findViewById(R.id.artd_1radioyes);
        final EditText artd_11 = (EditText) findViewById(R.id.artd_11);
        artd_11.setVisibility(View.GONE);
        EditText artd_2 = (EditText) findViewById(R.id.artd_2);
        RadioGroup artd_3radio = (RadioGroup) findViewById(R.id.artd_3radio);
        RadioButton artd_3radioyes = (RadioButton) findViewById(R.id.artd_3radioyes);




        final LinearLayout setd = ( LinearLayout) findViewById(R.id.setd);
        setd.setVisibility(View.GONE);
        RadioGroup setd_1radio = (RadioGroup) findViewById(R.id.setd_1radio);
        RadioButton setd_1radioyes = (RadioButton) findViewById(R.id.setd_1radioyes);
        final EditText setd_11 = (EditText) findViewById(R.id.setd_11);
        setd_11.setVisibility(View.GONE);
        EditText setd_2 = (EditText) findViewById(R.id.setd_2);
        RadioGroup setd_3radio = (RadioGroup) findViewById(R.id.setd_3radio);
        RadioButton setd_3radioyes = (RadioButton) findViewById(R.id.setd_3radioyes);




        final LinearLayout stunt = ( LinearLayout) findViewById(R.id.stunt);
        stunt.setVisibility(View.GONE);
        RadioGroup stunt_1radio = (RadioGroup) findViewById(R.id.stunt_1radio);
        RadioButton stunt_1radioyes = (RadioButton) findViewById(R.id.stunt_1radioyes);
        final EditText stunt_11 = (EditText) findViewById(R.id.stunt_11);
        stunt_11.setVisibility(View.GONE);
        EditText stunt_2 = (EditText) findViewById(R.id.stunt_2);
        RadioGroup stunt_3radio = (RadioGroup) findViewById(R.id.stunt_3radio);
        RadioButton stunt_3radioyes = (RadioButton) findViewById(R.id.stunt_3radioyes);



        final LinearLayout editor = ( LinearLayout) findViewById(R.id.editor);
        editor.setVisibility(View.GONE);
        EditText editor_1 = (EditText) findViewById(R.id.editor_1);
        RadioGroup editor_2radio = (RadioGroup) findViewById(R.id.editor_2radio);
        RadioButton editor_2radioyes = (RadioButton) findViewById(R.id.editor_2radioyes);
        final EditText editor_21 = (EditText) findViewById(R.id.editor_21);
        editor_21.setVisibility(View.GONE);
        EditText editor_3 = (EditText) findViewById(R.id.editor_3);
        EditText editor_4 = (EditText) findViewById(R.id.editor_4);
        RadioGroup editor_5radio = (RadioGroup) findViewById(R.id.editor_5radio);
        RadioButton editor_5radioyes = (RadioButton) findViewById(R.id.editor_5radioyes);




        final LinearLayout locat = ( LinearLayout) findViewById(R.id.locat);
        locat.setVisibility(View.GONE);
        RadioGroup locat_1radio = (RadioGroup) findViewById(R.id.locat_1radio);
        RadioButton locat_1radioyes = (RadioButton) findViewById(R.id.locat_1radioyes);
        final EditText locat_11 = (EditText) findViewById(R.id.locat_11);
        locat_11.setVisibility(View.GONE);
        EditText locat_2 = (EditText) findViewById(R.id.locat_2);
        RadioGroup locat_3radio = (RadioGroup) findViewById(R.id.locat_3radio);
        RadioButton locat_3radioyes = (RadioButton) findViewById(R.id.locat_3radioyes);




        final LinearLayout foodp = ( LinearLayout) findViewById(R.id.foodp);
        foodp.setVisibility(View.GONE);
        RadioGroup foodp_1radio = (RadioGroup) findViewById(R.id.foodp_1radio);
        RadioButton foodp_1radioyes = (RadioButton) findViewById(R.id.foodp_1radioyes);
        final EditText foodp_11 = (EditText) findViewById(R.id.foodp_11);
        foodp_11.setVisibility(View.GONE);
        EditText foodp_2 = (EditText) findViewById(R.id.foodp_2);
        RadioGroup foodp_3radio = (RadioGroup) findViewById(R.id.foodp_3radio);
        RadioButton foodp_3radioyes = (RadioButton) findViewById(R.id.foodp_3radioyes);



        final LinearLayout dub = ( LinearLayout) findViewById(R.id.dub);
        dub.setVisibility(View.GONE);
        RadioGroup dub_1radio = (RadioGroup) findViewById(R.id.dub_1radio);
        RadioButton dub_1radioyes = (RadioButton) findViewById(R.id.dub_1radioyes);
        final EditText dub_11 = (EditText) findViewById(R.id.dub_11);
        dub_11.setVisibility(View.GONE);
        EditText dub_2 = (EditText) findViewById(R.id.dub_2);
        RadioGroup dub_3radio = (RadioGroup) findViewById(R.id.dub_3radio);
        RadioButton dub_3radioyes = (RadioButton) findViewById(R.id.dub_3radioyes);


        final LinearLayout srec = ( LinearLayout) findViewById(R.id.srec);
        srec.setVisibility(View.GONE);
        EditText srec_1 = (EditText) findViewById(R.id.srec_1);
        final EditText srec_11 = (EditText) findViewById(R.id.srec_11);
        RadioGroup srec_2radio = (RadioGroup) findViewById(R.id.srec_2radio);
        RadioButton srec_2radioyes = (RadioButton) findViewById(R.id.srec_2radioyes);
        final EditText srec_21 = (EditText) findViewById(R.id.srec_21);
        srec_21.setVisibility(View.GONE);
        EditText srec_3 = (EditText) findViewById(R.id.srec_3);
        RadioGroup srec_4radio = (RadioGroup) findViewById(R.id.srec_4radio);
        RadioButton srec_4radioyes = (RadioButton) findViewById(R.id.srec_4radioyes);



        final LinearLayout smix = ( LinearLayout) findViewById(R.id.smix);
        smix.setVisibility(View.GONE);
        EditText smix_1 = (EditText) findViewById(R.id.smix_1);
        final EditText smix_11 = (EditText) findViewById(R.id.smix_11);
        RadioGroup smix_2radio = (RadioGroup) findViewById(R.id.smix_2radio);
        RadioButton smix_2radioyes = (RadioButton) findViewById(R.id.smix_2radioyes);
        final EditText smix_21 = (EditText) findViewById(R.id.smix_21);
        smix_21.setVisibility(View.GONE);
        EditText smix_3 = (EditText) findViewById(R.id.smix_3);
        RadioGroup smix_4radio = (RadioGroup) findViewById(R.id.smix_4radio);
        RadioButton smix_4radioyes = (RadioButton) findViewById(R.id.smix_4radioyes);



        final LinearLayout digint = ( LinearLayout) findViewById(R.id.digint);
        digint.setVisibility(View.GONE);
        EditText digint_1 = (EditText) findViewById(R.id.digint_1);
        final EditText digint_11 = (EditText) findViewById(R.id.digint_11);
        EditText digint_2 = (EditText) findViewById(R.id.digint_2);
        EditText digint_3 = (EditText) findViewById(R.id.digint_3);
        RadioGroup digint_4radio = (RadioGroup) findViewById(R.id.digint_4radio);
        RadioButton digint_4radioyes = (RadioButton) findViewById(R.id.digint_4radioyes);
        final EditText digint_41 = (EditText) findViewById(R.id.digint_41);
        digint_41.setVisibility(View.GONE);
        EditText digint_5 = (EditText) findViewById(R.id.digint_5);
        RadioGroup digint_6radio = (RadioGroup) findViewById(R.id.digint_6radio);
        RadioButton digint_6radioyes = (RadioButton) findViewById(R.id.digint_6radioyes);



        final LinearLayout vfx = ( LinearLayout) findViewById(R.id.vfx);
        vfx.setVisibility(View.GONE);
        EditText vfx_1 = (EditText) findViewById(R.id.vfx_1);
        final EditText vfx_11 = (EditText) findViewById(R.id.vfx_11);
        EditText vfx_2 = (EditText) findViewById(R.id.vfx_2);
        EditText vfx_3 = (EditText) findViewById(R.id.vfx_3);
        RadioGroup vfx_4radio = (RadioGroup) findViewById(R.id.vfx_4radio);
        RadioButton vfx_4radioyes = (RadioButton) findViewById(R.id.vfx_4radioyes);
        final EditText vfx_41 = (EditText) findViewById(R.id.vfx_41);
        vfx_41.setVisibility(View.GONE);
        EditText vfx_5 = (EditText) findViewById(R.id.vfx_5);
        RadioGroup vfx_6radio = (RadioGroup) findViewById(R.id.vfx_6radio);
        RadioButton vfx_6radioyes = (RadioButton) findViewById(R.id.vfx_6radioyes);



        final LinearLayout sfx = ( LinearLayout) findViewById(R.id.sfx);
        sfx.setVisibility(View.GONE);
        EditText sfx_1 = (EditText) findViewById(R.id.sfx_1);
        final EditText sfx_11 = (EditText) findViewById(R.id.sfx_11);
        EditText sfx_2 = (EditText) findViewById(R.id.sfx_2);
        EditText sfx_3 = (EditText) findViewById(R.id.sfx_3);
        RadioGroup sfx_4radio = (RadioGroup) findViewById(R.id.sfx_4radio);
        RadioButton sfx_4radioyes = (RadioButton) findViewById(R.id.sfx_4radioyes);
        final EditText sfx_41 = (EditText) findViewById(R.id.sfx_41);
        sfx_41.setVisibility(View.GONE);
        EditText sfx_5 = (EditText) findViewById(R.id.sfx_5);
        RadioGroup sfx_6radio = (RadioGroup) findViewById(R.id.sfx_6radio);
        RadioButton sfx_6radioyes = (RadioButton) findViewById(R.id.sfx_6radioyes);



        final LinearLayout pets = ( LinearLayout) findViewById(R.id.pets);
        pets.setVisibility(View.GONE);
        EditText pets_1 = (EditText) findViewById(R.id.pets_1);
        EditText pets_2 = (EditText) findViewById(R.id.pets_2);
        RadioGroup pets_3radio = (RadioGroup) findViewById(R.id.pets_3radio);
        RadioButton pets_3radioyes = (RadioButton) findViewById(R.id.pets_3radioyes);



        //clients
        final LinearLayout castingagent = (LinearLayout) findViewById(R.id.casting_agent);
        castingagent.setVisibility(View.GONE);
        RadioGroup castingagent_1radio = (RadioGroup) findViewById(R.id.casting_agent_1radio);
        RadioButton castingagent_1radioyes = (RadioButton) findViewById(R.id.casting_agent_1radioyes);
        final EditText castingagent_11 = (EditText) findViewById(R.id.casting_agent_11);
        castingagent_11.setVisibility(View.GONE);



        final LinearLayout codirector = (LinearLayout) findViewById(R.id.codirector);
        codirector.setVisibility(View.GONE);
        RadioGroup codirector_1radio = (RadioGroup) findViewById(R.id.codirector_1radio);
        RadioButton codirector_1radioyes = (RadioButton) findViewById(R.id.codirector_1radioyes);
        final EditText codirector_11 = (EditText) findViewById(R.id.codirector_11);
        codirector_11.setVisibility(View.GONE);



        final LinearLayout coproducer = (LinearLayout) findViewById(R.id.coproducer);
        coproducer.setVisibility(View.GONE);
        EditText coproducer_2 = (EditText) findViewById(R.id.coproducer_2);
        RadioGroup coproducer_1radio = (RadioGroup) findViewById(R.id.coproducer_1radio);
        RadioButton coproducer_1radioyes = (RadioButton) findViewById(R.id.coproducer_1radioyes);
        final EditText coproducer_11 = (EditText) findViewById(R.id.coproducer_11);
        coproducer_11.setVisibility(View.GONE);




        final LinearLayout director = (LinearLayout) findViewById(R.id.director);
        director.setVisibility(View.GONE);
        RadioGroup director_1radio = (RadioGroup) findViewById(R.id.director_1radio);
        RadioButton director_1radioyes = (RadioButton) findViewById(R.id.director_1radioyes);
        final EditText director_11 = (EditText) findViewById(R.id.director_11);
        director_11.setVisibility(View.GONE);




        final LinearLayout asstdirector = (LinearLayout) findViewById(R.id.asstdirector);
        asstdirector.setVisibility(View.GONE);
        RadioGroup asstdirector_1radio = (RadioGroup) findViewById(R.id.asstdirector_1radio);
        RadioButton asstdirector_1radioyes = (RadioButton) findViewById(R.id.asstdirector_1radioyes);
        final EditText asstdirector_11 = (EditText) findViewById(R.id.asstdirector_11);
        asstdirector_11.setVisibility(View.GONE);




        final LinearLayout directoraudit = (LinearLayout) findViewById(R.id.directoraudit);
        directoraudit.setVisibility(View.GONE);
        RadioGroup directoraudit_1radio = (RadioGroup) findViewById(R.id.directoraudit_1radio);
        RadioButton directoraudit_1radioyes = (RadioButton) findViewById(R.id.directoraudit_1radioyes);
        final EditText directoraudit_11 = (EditText) findViewById(R.id.directoraudit_11);
        directoraudit_11.setVisibility(View.GONE);




        final LinearLayout execproducer = (LinearLayout) findViewById(R.id.execproducer);
        execproducer.setVisibility(View.GONE);
        EditText execproducer_2 = (EditText) findViewById(R.id.execproducer_2);
        RadioGroup execproducer_1radio = (RadioGroup) findViewById(R.id.execproducer_1radio);
        RadioButton execproducer_1radioyes = (RadioButton) findViewById(R.id.execproducer_1radioyes);
        final EditText execproducer_11 = (EditText) findViewById(R.id.execproducer_11);
        execproducer_11.setVisibility(View.GONE);




        final LinearLayout modelcoor = (LinearLayout) findViewById(R.id.modelcoor);
        modelcoor.setVisibility(View.GONE);
        RadioGroup modelcoor_1radio = (RadioGroup) findViewById(R.id.modelcoor_1radio);
        RadioButton moedlcoor_1radioyes = (RadioButton) findViewById(R.id.modelcoor_1radioyes);
        final EditText modelcoor_11 = (EditText) findViewById(R.id.modelcoor_11);
        modelcoor_11.setVisibility(View.GONE);



        final LinearLayout producer = (LinearLayout) findViewById(R.id.producer);
        producer.setVisibility(View.GONE);
        EditText producer_2 = (EditText) findViewById(R.id.producer_2);
        RadioGroup producer_1radio = (RadioGroup) findViewById(R.id.producer_1radio);
        RadioButton producer_1radioyes = (RadioButton) findViewById(R.id.producer_1radioyes);
        final EditText producer_11 = (EditText) findViewById(R.id.producer_11);
        producer_11.setVisibility(View.GONE);




        final LinearLayout prodhouseman = (LinearLayout) findViewById(R.id.prodhouseman);
        prodhouseman.setVisibility(View.GONE);
        EditText prodhouseman_2 = (EditText) findViewById(R.id.prodhouseman_2);
        RadioGroup prodhouseman_1radio = (RadioGroup) findViewById(R.id.prodhouseman_1radio);
        RadioButton prodhouseman_1radioyes = (RadioButton) findViewById(R.id.prodhouseman_1radioyes);
        final EditText prodhouseman_11 = (EditText) findViewById(R.id.prodhouseman_11);
        prodhouseman_11.setVisibility(View.GONE);



        switch(craft)
        {
            //craftsmen
            case "Actor": actor.setVisibility(View.VISIBLE); break;
            case "Actress": actress.setVisibility(View.VISIBLE); break;
            case "Child Artist": child.setVisibility(View.VISIBLE); break;
            case "Singer": singer.setVisibility(View.VISIBLE); break;
            case "Dancer": dancer.setVisibility(View.VISIBLE); break;
            case "Side Artists": side.setVisibility(View.VISIBLE); break;
            case "Assistant Director": assdir.setVisibility(View.VISIBLE); break;
            case "Lyric Writer / Lyricist": lyric.setVisibility(View.VISIBLE); break;
            case "Dialouge Writer": dwrite.setVisibility(View.VISIBLE); break;
            case "Script / Screenplay Writers": screenp.setVisibility(View.VISIBLE); break;
            case "Story Board Artist": sba.setVisibility(View.VISIBLE); break;
            case "Choreographer": choreo.setVisibility(View.VISIBLE); break;
            case "Director of Photography": dop.setVisibility(View.VISIBLE); break;
            case "Still Photographer": stp.setVisibility(View.VISIBLE); break;
            case "PRO": pro.setVisibility(View.VISIBLE); break; 
            case "Designer": desi.setVisibility(View.VISIBLE); break;
            case "Production Manager": prodm.setVisibility(View.VISIBLE); break;
            case "Focus Puller": focus.setVisibility(View.VISIBLE); break;
            case "Vehicle Driver": driver.setVisibility(View.VISIBLE); break;
            case "Mic Department": mic.setVisibility(View.VISIBLE); break;
            case "Music Director": musicd.setVisibility(View.VISIBLE); break;
            case "Makeup Man": makeup.setVisibility(View.VISIBLE); break;
            case "Hair Dresser": hairdr.setVisibility(View.VISIBLE); break;
            case "Costumer": costu.setVisibility(View.VISIBLE); break;
            case "Art Department": artd.setVisibility(View.VISIBLE); break;
            case "Set Department": setd.setVisibility(View.VISIBLE); break;
            case "Stuntman": stunt.setVisibility(View.VISIBLE); break;
            case "Editor": editor.setVisibility(View.VISIBLE); break;
            case "Location Manager": locat.setVisibility(View.VISIBLE); break;
            case "Production (Food)": foodp.setVisibility(View.VISIBLE); break;
            case "Dubbing Artists": dub.setVisibility(View.VISIBLE); break;
            case "Sound Recording Engineers": srec.setVisibility(View.VISIBLE); break;
            case "Sound Mixing Engineers": smix.setVisibility(View.VISIBLE); break;
            case "Digital Intermediate": digint.setVisibility(View.VISIBLE); break;
            case "VFX / CG": vfx.setVisibility(View.VISIBLE); break;
            case "SFX": sfx.setVisibility(View.VISIBLE); break;
            case "Pet Suppliers / Pet Doctors / AWBI Certifications": pets.setVisibility(View.VISIBLE); break;

            //clients
            case "Casting Agent": castingagent.setVisibility(View.VISIBLE); break;
            case "Co-Director": codirector.setVisibility(View.VISIBLE); break;
            case "Co-Producer": coproducer.setVisibility(View.VISIBLE); break;
            case "Director": director.setVisibility(View.VISIBLE); break;
            case "Director Assistant": asstdirector.setVisibility(View.VISIBLE); break;
            case "Director Audition": directoraudit.setVisibility(View.VISIBLE); break;
            case "Executive Producer": execproducer.setVisibility(View.VISIBLE); break;
            case "Model Coordinator": modelcoor.setVisibility(View.VISIBLE); break;
            case "Producer": producer.setVisibility(View.VISIBLE); break;
            case "Production House Manager": prodhouseman.setVisibility(View.VISIBLE); break;
        }


        //craftsmen
        
        //actor
        actor_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.actor_1radioyes)
                    actor_11.setVisibility(View.VISIBLE);
                else
                    actor_11.setVisibility(View.GONE);
            }
        });

        actor_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.actor_2radioyes)
                    actor_22.setVisibility(View.VISIBLE);
                else
                    actor_22.setVisibility(View.GONE);
            }
        });

        actor_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.actor_4radioyes)
                    actor_41.setVisibility(View.VISIBLE);
                else
                    actor_41.setVisibility(View.GONE);
            }
        });

        actor_6radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.actor_6radioyes){
                    actor_61.setVisibility(View.VISIBLE);
                    actor_62.setVisibility(View.VISIBLE);
                }
                else {
                    actor_61.setVisibility(View.GONE);
                    actor_62.setVisibility(View.GONE);
                }
            }
        });

        
        //actress
        actress_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.actress_1radioyes)
                    actress_11.setVisibility(View.VISIBLE);
                else
                    actress_11.setVisibility(View.GONE);
            }
        });

        actress_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.actress_2radioyes)
                    actress_22.setVisibility(View.VISIBLE);
                else
                    actress_22.setVisibility(View.GONE);
            }
        });

        actress_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.actress_4radioyes)
                    actress_41.setVisibility(View.VISIBLE);
                else
                    actress_41.setVisibility(View.GONE);
            }
        });

        actress_6radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.actress_6radioyes){
                    actress_61.setVisibility(View.VISIBLE);
                    actress_62.setVisibility(View.VISIBLE);
                }
                else {
                    actress_61.setVisibility(View.GONE);
                    actress_62.setVisibility(View.GONE);
                }
            }
        });
        
        
        //side
        side_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.side_1radioyes)
                    side_11.setVisibility(View.VISIBLE);
                else
                    side_11.setVisibility(View.GONE);
            }
        });

        side_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.side_2radioyes)
                    side_22.setVisibility(View.VISIBLE);
                else
                    side_22.setVisibility(View.GONE);
            }
        });

        side_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.side_4radioyes)
                    side_41.setVisibility(View.VISIBLE);
                else
                    side_41.setVisibility(View.GONE);
            }
        });

        side_6radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.side_6radioyes){
                    side_61.setVisibility(View.VISIBLE);
                    side_62.setVisibility(View.VISIBLE);
                }
                else {
                    side_61.setVisibility(View.GONE);
                    side_62.setVisibility(View.GONE);
                }
            }
        });
        
        //child
        child_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.child_2radioyes)
                    child_21.setVisibility(View.VISIBLE);
                else
                    child_21.setVisibility(View.GONE);
            }
        });

        child_3radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.child_3radioyes)
                    child_31.setVisibility(View.VISIBLE);
                else
                    child_31.setVisibility(View.GONE);
            }
        });

        child_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.child_4radioyes) {
                    child_41.setVisibility(View.VISIBLE);
                    child_42.setVisibility(View.VISIBLE);
                }
                else {
                    child_41.setVisibility(View.GONE);
                    child_42.setVisibility(View.GONE);
                }
            }
        });
        
        
        //singer
        singer_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.singer_1radioyes)
                    singer_11.setVisibility(View.VISIBLE);
                else
                    singer_11.setVisibility(View.GONE);
            }
        });

        singer_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.singer_2radioyes)
                    singer_21.setVisibility(View.VISIBLE);
                else
                    singer_21.setVisibility(View.GONE);
            }
        });

        singer_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.singer_4radioyes)
                    singer_41.setVisibility(View.VISIBLE);
                else
                    singer_41.setVisibility(View.GONE);
            }
        });

        singer_5radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.singer_5radioyes)
                    singer_51.setVisibility(View.VISIBLE);
                else
                    singer_51.setVisibility(View.GONE);
            }
        });

        singer_6radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.singer_6radioyes)
                    singer_61.setVisibility(View.VISIBLE);
                else
                    singer_61.setVisibility(View.GONE);
            }
        });
        
        
        //dancer
        dancer_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.dancer_1radioyes)
                    dancer_11.setVisibility(View.VISIBLE);
                else
                    dancer_11.setVisibility(View.GONE);
            }
        });

        dancer_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.dancer_2radioyes)
                    dancer_21.setVisibility(View.VISIBLE);
                else
                    dancer_21.setVisibility(View.GONE);
            }
        });

        dancer_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.dancer_4radioyes)
                    dancer_41.setVisibility(View.VISIBLE);
                else
                    dancer_41.setVisibility(View.GONE);
            }
        });

        dancer_5radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.dancer_5radioyes){
                    dancer_51.setVisibility(View.VISIBLE);
                    dancer_52.setVisibility(View.VISIBLE);
                }
                else{
                    dancer_51.setVisibility(View.GONE);
                    dancer_52.setVisibility(View.GONE);
                }
            }
        });
        
        
        //assdir
        assdir_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.assdir_1radioyes)
                    assdir_11.setVisibility(View.VISIBLE);
                else
                    assdir_11.setVisibility(View.GONE);
            }
        });

        assdir_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.assdir_2radioyes)
                    assdir_22.setVisibility(View.VISIBLE);
                else
                    assdir_22.setVisibility(View.GONE);
            }
        });
        
        
        //lyric
        lyric_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.lyric_1radioyes){
                    lyric_11.setVisibility(View.VISIBLE);
                    lyric_12.setVisibility(View.VISIBLE);
                }
                else{
                    lyric_11.setVisibility(View.GONE);
                    lyric_12.setVisibility(View.GONE);
                }

            }
        });

        lyric_3radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.lyric_3radioyes)
                    lyric_31.setVisibility(View.VISIBLE);
                else
                    lyric_31.setVisibility(View.GONE);
            }
        });
        
        
        //dwrite
        dwrite_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.dwrite_1radioyes)
                    dwrite_11.setVisibility(View.VISIBLE);
                else
                    dwrite_11.setVisibility(View.GONE);
            }
        });

        dwrite_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.dwrite_4radioyes)
                    dwrite_41.setVisibility(View.VISIBLE);
                else
                    dwrite_41.setVisibility(View.GONE);
            }
        });
        
        
        //screenp
        screenp_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.screenp_1radioyes)
                    screenp_11.setVisibility(View.VISIBLE);
                else
                    screenp_11.setVisibility(View.GONE);
            }
        });

        screenp_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.screenp_4radioyes)
                    screenp_41.setVisibility(View.VISIBLE);
                else
                    screenp_41.setVisibility(View.GONE);
            }
        });
        
        
        //sba
        sba_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.sba_1radioyes)
                    sba_11.setVisibility(View.VISIBLE);
                else
                    sba_11.setVisibility(View.GONE);
            }
        });
        
        //choreo
        choreo_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.choreo_2radioyes)
                    choreo_21.setVisibility(View.VISIBLE);
                else
                    choreo_21.setVisibility(View.GONE);
            }
        });

        choreo_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.choreo_4radioyes)
                    choreo_41.setVisibility(View.VISIBLE);
                else
                    choreo_41.setVisibility(View.GONE);
            }
        });

        choreo_5radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.choreo_5radioyes){
                    choreo_51.setVisibility(View.VISIBLE);
                    choreo_52.setVisibility(View.VISIBLE);
                }
                else {
                    choreo_51.setVisibility(View.GONE);
                    choreo_52.setVisibility(View.GONE);
                }
            }
        });


        //dop
        stp_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.stp_1radioyes)
                    stp_11.setVisibility(View.VISIBLE);
                else
                    stp_11.setVisibility(View.GONE);
            }
        });
        

        //stp
        stp_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.stp_1radioyes)
                    stp_11.setVisibility(View.VISIBLE);
                else
                    stp_11.setVisibility(View.GONE);
            }
        });


        //pro
        pro_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.pro_1radioyes)
                    pro_11.setVisibility(View.VISIBLE);
                else
                    pro_11.setVisibility(View.GONE);
            }
        });

        //desi
        desi_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.desi_1radioyes)
                    desi_11.setVisibility(View.VISIBLE);
                else
                    desi_11.setVisibility(View.GONE);
            }
        });

        //prodm
        prodm_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.prodm_1radioyes)
                    prodm_11.setVisibility(View.VISIBLE);
                else
                    prodm_11.setVisibility(View.GONE);
            }
        });

        //focus
        focus_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.focus_1radioyes)
                    focus_11.setVisibility(View.VISIBLE);
                else
                    focus_11.setVisibility(View.GONE);
            }
        });

        //driver
        driver_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.driver_1radioyes)
                    driver_11.setVisibility(View.VISIBLE);
                else
                    driver_11.setVisibility(View.GONE);
            }
        });

        //mic
        mic_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.mic_1radioyes)
                    mic_11.setVisibility(View.VISIBLE);
                else
                    mic_11.setVisibility(View.GONE);
            }
        });

        //musicd
        musicd_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.musicd_1radioyes)
                    musicd_11.setVisibility(View.VISIBLE);
                else
                    musicd_11.setVisibility(View.GONE);
            }
        });

        //makeup
        makeup_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.makeup_1radioyes)
                    makeup_11.setVisibility(View.VISIBLE);
                else
                    makeup_11.setVisibility(View.GONE);
            }
        });

        //hairdr
        hairdr_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.hairdr_1radioyes)
                    hairdr_11.setVisibility(View.VISIBLE);
                else
                    hairdr_11.setVisibility(View.GONE);
            }
        });

        //costu
        costu_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.costu_1radioyes)
                    costu_11.setVisibility(View.VISIBLE);
                else
                    costu_11.setVisibility(View.GONE);
            }
        });

        //artd
        artd_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.artd_1radioyes)
                    artd_11.setVisibility(View.VISIBLE);
                else
                    artd_11.setVisibility(View.GONE);
            }
        });

        //setd
        setd_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.setd_1radioyes)
                    setd_11.setVisibility(View.VISIBLE);
                else
                    setd_11.setVisibility(View.GONE);
            }
        });

        //stunt
        stunt_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.stunt_1radioyes)
                    stunt_11.setVisibility(View.VISIBLE);
                else
                    stunt_11.setVisibility(View.GONE);
            }
        });


        //editor
        editor_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.editor_2radioyes)
                    editor_21.setVisibility(View.VISIBLE);
                else
                    editor_21.setVisibility(View.GONE);
            }
        });

        //locat
        locat_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.locat_1radioyes)
                    locat_11.setVisibility(View.VISIBLE);
                else
                    locat_11.setVisibility(View.GONE);
            }
        });

        //foodp
        foodp_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.foodp_1radioyes)
                    foodp_11.setVisibility(View.VISIBLE);
                else
                    foodp_11.setVisibility(View.GONE);
            }
        });

        //dub
        dub_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.dub_1radioyes)
                    dub_11.setVisibility(View.VISIBLE);
                else
                    dub_11.setVisibility(View.GONE);
            }
        });

        //srec
        srec_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.srec_2radioyes)
                    srec_11.setVisibility(View.VISIBLE);
                else
                    srec_11.setVisibility(View.GONE);
            }
        });

        //smix
        smix_2radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.smix_2radioyes)
                    smix_11.setVisibility(View.VISIBLE);
                else
                    smix_11.setVisibility(View.GONE);
            }
        });

        //digint
        digint_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.digint_4radioyes)
                    digint_11.setVisibility(View.VISIBLE);
                else
                    digint_11.setVisibility(View.GONE);
            }
        });

        //vfx
        vfx_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.vfx_4radioyes)
                    vfx_11.setVisibility(View.VISIBLE);
                else
                    vfx_11.setVisibility(View.GONE);
            }
        });

        //sfx
        sfx_4radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.sfx_4radioyes)
                    sfx_11.setVisibility(View.VISIBLE);
                else
                    sfx_11.setVisibility(View.GONE);
            }
        });



        //clients

        //castingagent
        castingagent_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.casting_agent_1radioyes)
                    castingagent_11.setVisibility(View.VISIBLE);
                else
                    castingagent_11.setVisibility(View.GONE);
            }
        });


        //codirector
        codirector_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.codirector_1radioyes)
                    codirector_11.setVisibility(View.VISIBLE);
                else
                    codirector_11.setVisibility(View.GONE);
            }
        });


        //coproducer
        coproducer_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.coproducer_1radioyes)
                    coproducer_11.setVisibility(View.VISIBLE);
                else
                    coproducer_11.setVisibility(View.GONE);
            }
        });


        //director
        director_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.director_1radioyes)
                    director_11.setVisibility(View.VISIBLE);
                else
                    director_11.setVisibility(View.GONE);
            }
        });


        //director assistant
        asstdirector_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.asstdirector_1radioyes)
                    asstdirector_11.setVisibility(View.VISIBLE);
                else
                    asstdirector_11.setVisibility(View.GONE);
            }
        });


        //director audition
        directoraudit_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.directoraudit_1radioyes)
                    directoraudit_11.setVisibility(View.VISIBLE);
                else
                    directoraudit_11.setVisibility(View.GONE);
            }
        });


        //executive producer
        execproducer_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.execproducer_1radioyes){
                    execproducer_11.setVisibility(View.VISIBLE);
                }
                else{
                    execproducer_11.setVisibility(View.GONE);
                }

            }
        });


        //modelcoordinator
        modelcoor_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.modelcoor_1radioyes)
                    modelcoor_11.setVisibility(View.VISIBLE);
                else
                    modelcoor_11.setVisibility(View.GONE);
            }
        });


        //producer
        producer_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.producer_1radioyes)
                    producer_11.setVisibility(View.VISIBLE);
                else
                    producer_11.setVisibility(View.GONE);
            }
        });



        //prodhouseman
        prodhouseman_1radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.prodhouseman_1radioyes)
                    prodhouseman_11.setVisibility(View.VISIBLE);
                else
                    prodhouseman_11.setVisibility(View.GONE);
            }
        });










        Button button_3 = (Button)findViewById(R.id.button_3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initAccountKitSmsFlow();

                //Intent next = new Intent(getApplicationContext(),ProfileView.class);
                //startActivity(next);
            }
        });
    }

    //keyboard disappears when you click outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }









    public void initAccountKitSmsFlow() {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder;

        configurationBuilder = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                LoginType.PHONE,
                AccountKitActivity.ResponseType.TOKEN);

        // or .ResponseType.TOKEN
        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());

        startActivityForResult(intent, APP_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage= "";
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    getAccount();

                    if(type.equals("craftsman")){

                        Intent intent = new Intent(this, Verification.class)
                                .putExtra("fromhere", "PhoneVerified")
                                .putExtra("fromwhom", "Crafts");
                        startActivity(intent);

                    } else if(type.equals("client")){

                        Intent intent = new Intent(this, Verification.class)
                                .putExtra("fromhere", "PhoneVerified")
                                .putExtra("fromwhom", "Clients");
                        startActivity(intent);

                    }

                }
            }
            // Surface the result to your user in an appropriate way.
            Toast.makeText(
                    this,
                    toastMessage,
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * Gets current account from Facebook Account Kit which include user's phone number.
     */
    private void getAccount(){
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                // Get Account Kit ID
                String accountKitId = account.getId();

                // Get phone number
                PhoneNumber phoneNumber = account.getPhoneNumber();
                String phoneNumberString = phoneNumber.toString();

                // Surface the result to your user in an appropriate way.
                Toast.makeText(
                        signup3.this,
                        phoneNumberString,
                        Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onError(final AccountKitError error) {
                Log.e("AccountKit",error.toString());
                // Handle Error
            }
        });
    }

}

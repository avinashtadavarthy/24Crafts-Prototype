package com.example.iyashwant.spiderprojectprototype;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class signup3 extends AppCompatActivity {

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


        final Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");
        final String craft = bundle.getString("craft");

        switch(craft)
        {
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
        }

        
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


        

        Button button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(),ProfileView.class);
                startActivity(next);
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

}

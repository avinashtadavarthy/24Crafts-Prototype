package com.twenty.four.crafts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class DirectoryFragment extends android.support.v4.app.Fragment {

    View myView;
    AdView mAdView;


    GridView gv1;
    DirectoryGridAdapter adapter;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    ArrayList<IconsClass> class_obj=new ArrayList<>();
    String[] desc = {"Ad and Corporate Filmmakers","Advertising and Marketing","Agency","Art Directors","Audigraphers","Auditorium","Dance","Directors",
            "Cine Banners","Cine Designers","Cinema Laboratories","Cinematographers", "Classical Singers","Colour Lab","Costumers","Dialogue Writers",
            "Dubbing and Recording Studios","Dubbing Artist","Editing Studio-Editing Suite","Event Managers","Exhibitors Associations","Film Directors","Film Distributors","Film Editors",
            "Film Mediators","Film Producers","Film Writers","Graphic Designers","Hotels","Journalists","Journals(Websites)","Journals","Light Music Troupe",
            "Lyric Writers","Makeup Men & Hairdressers","Modelling and Portfolio","Music Directors","Music Incharge","Outdoor units",
            "Playback Singers","Press Photographers","Preview Theatres","PRO","Production Executive","Recording Studios","Script Writers",
            "Shooting House","Still Photographers","Studios","Stunt Masters","TV Serial Directors","TV Serial Producers","Unions and Associations",
            "Video Post Production Studios"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_directory,container,false);

        getActivity().setTitle("Directory");
        User.getInstance().navbarposclient = 0;


        User.getInstance().navbarpos = 0;

        mCustomPagerAdapter = new CustomPagerAdapter(getFragmentManager(), getActivity().getApplicationContext());

        gv1 = myView.findViewById(R.id.gv);
        mViewPager = (ViewPager) myView.findViewById(R.id.directoryViewPager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        pageSwitcher(3);
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mViewPager.getParent().requestDisallowInterceptTouchEvent(true);
            }
        });
       /* recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getActivity().getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.i("Hello",desc[position]);//returns fine
                        Intent I = new Intent(getActivity(),Contacts.class);
                        Log.i("hi",class_obj.get(position).getUrl_param());
                        I.putExtra("craft",class_obj.get(position).getUrl_param());

                        startActivity(I);
                        // do whatever
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );*/

      /*  GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);*/



        MobileAds.initialize(getActivity().getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = myView.findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device
         AdRequest adRequest = new AdRequest.Builder()
                .build();

        mAdView.loadAd(adRequest);



        //adapter = new DirectoryGridAdapter(getActivity(),class_obj);///////////////////////////////////////////////
        class_obj.add(new IconsClass(R.drawable.adcorp,"Ad and Corporate Filmmakers","adcorp"));
        class_obj.add(new IconsClass(R.drawable.advertisingandmarketing,"Advertisingandmarketing","advertising"));
        class_obj.add(new IconsClass(R.drawable.artdirector_white,"Art directors","artdirectors"));
        class_obj.add(new IconsClass(R.drawable.audiographers,"audiographers","audiographers"));
        class_obj.add(new IconsClass(R.drawable.auditorium,"auditorium","auditorium"));
        class_obj.add(new IconsClass(R.drawable.choreographers,"choreographers","choreographers"));
        class_obj.add(new IconsClass(R.drawable.cinedesigners,"cinedesigners","cinedesigners"));
        class_obj.add(new IconsClass(R.drawable.cinemabanners,"cinemabanners","cinebanners"));
        class_obj.add(new IconsClass(R.drawable.cinematographer,"cinematographer","cinematographers"));
        class_obj.add(new IconsClass(R.drawable.cinemlaboratories,"Cinema laboratories","cinemalaboratories"));
        class_obj.add(new IconsClass(R.drawable.classicalsingers,"Classical Singers","classicalsingers"));
        class_obj.add(new IconsClass(R.drawable.colorlab,"ColorLab","colorlab"));
        class_obj.add(new IconsClass(R.drawable.costumer,"costumer","costumers"));
        class_obj.add(new IconsClass(R.drawable.dialoguewriters,"dialoguewriters","dialoguewriters"));
        class_obj.add(new IconsClass(R.drawable.distributor,"distributor","distributors"));
        class_obj.add(new IconsClass(R.drawable.dubbingandrecordingstudio,"Dubbing And RecordingStudio","dubbingandrecordingstudios"));
        class_obj.add(new IconsClass(R.drawable.dubbingartist,"DubbingArtist","dubbingartist"));
        class_obj.add(new IconsClass(R.drawable.editingstudio,"EditingStudio","editingstudio"));
        class_obj.add(new IconsClass(R.drawable.editor,"editor","editors"));
        class_obj.add(new IconsClass(R.drawable.eventmanagers,"eventmanagers","eventmanagers"));
        class_obj.add(new IconsClass(R.drawable.exhibitorsassociation,"exhibitors Association","exhibitorassociation"));
        class_obj.add(new IconsClass(R.drawable.filmdirectors,"filmdirectors","filmdirectors"));
        class_obj.add(new IconsClass(R.drawable.filmwriters,"filmwriters","filmwriters"));
        class_obj.add(new IconsClass(R.drawable.graphicdesigners,"Graphic designers","graphicdesigners"));
        class_obj.add(new IconsClass(R.drawable.hotels,"hotels","hotels"));
        class_obj.add(new IconsClass(R.drawable.journalist,"journalist","journalists"));
        class_obj.add(new IconsClass(R.drawable.journals,"journals","journals"));
        class_obj.add(new IconsClass(R.drawable.journals_websites,"Journals and Websites","journalwebsites"));
        class_obj.add(new IconsClass(R.drawable.lightmusictroupe,"lightmusictroupe","lightmusictroupe"));
        class_obj.add(new IconsClass(R.drawable.lyricwriters,"lyricwriters","lyricwriters"));
        class_obj.add(new IconsClass(R.drawable.makeupmen,"makeupmen","makeupmenandhairdressers"));
        class_obj.add(new IconsClass(R.drawable.mediators,"mediators","mediators"));
        class_obj.add(new IconsClass(R.drawable.modellingandportfolio,"modellingandportfolio","modellingandportfolio"));
        class_obj.add(new IconsClass(R.drawable.musicdirector,"music director","musicdirectors"));
        class_obj.add(new IconsClass(R.drawable.musicincharge,"music in charge","musicincharge"));
        class_obj.add(new IconsClass(R.drawable.outdoorunits,"outdoor units","outdoorunits"));
        class_obj.add(new IconsClass(R.drawable.playbacksingers,"play back singers","playbacksingers"));
        class_obj.add(new IconsClass(R.drawable.pressphotographer,"press photographer","pressphotographers"));
        class_obj.add(new IconsClass(R.drawable.previewtheatre,"preview theatre","previewtheatres"));
        class_obj.add(new IconsClass(R.drawable.pro,"pro","pro"));
        class_obj.add(new IconsClass(R.drawable.producers,"producers","producers"));
        class_obj.add(new IconsClass(R.drawable.productionexecutive,"productionexecutive","productionexecutive"));
        class_obj.add(new IconsClass(R.drawable.recordingstudio,"recording studio","recordingstudios"));
        class_obj.add(new IconsClass(R.drawable.scriptwriters,"script writers","scriptwriters"));
        class_obj.add(new IconsClass(R.drawable.shootinghouse,"shooting house","shootinghouse"));
        class_obj.add(new IconsClass(R.drawable.stillphotographer,"still photographer","stillphotographers"));
        class_obj.add(new IconsClass(R.drawable.studios,"studios","studios"));
        class_obj.add(new IconsClass(R.drawable.stuntmaster,"stuntmaster","stuntmaster"));
        class_obj.add(new IconsClass(R.drawable.tvserialdirectors,"tv serial directors","tvserialdirectors"));
        class_obj.add(new IconsClass(R.drawable.tvserialproducers,"tv serial producers","tvserialproducers"));
        class_obj.add(new IconsClass(R.drawable.unionsandassicoations,"unions and assicoations","unionsandassociations"));
        class_obj.add(new IconsClass(R.drawable.videopostproduction,"video post production","videopostproduction"));

        gv1.setNumColumns(3);
        gv1.setAdapter(new DirectoryGridAdapter(getActivity().getApplicationContext(),class_obj));


        gv1.setNestedScrollingEnabled(false);
        mViewPager.setNestedScrollingEnabled(false);


        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(),layoutManager.getOrientation());
        /*recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);*/

        return myView;
    }


    Timer timer;
    int page = 0;
    final boolean keeprunning = true;

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
        // in
        // milliseconds
    }

    // this is an inner class...
    public class RemindTask extends TimerTask {

        @Override
        public void run() {


                // As the TimerTask run on a seprate thread from UI thread we have
                // to call runOnUiThread to do work on UI thread.

     if(getActivity()!=null) {
         getActivity().runOnUiThread(new Runnable() {
             public void run() {

                 if (page > 2) { // In my case the number of pages are 5
                     page = 0;
                     // Showing a toast for just testing purpose
                     //Toast.makeText(getActivity().getApplicationContext(), "Timer stoped",
                     //      Toast.LENGTH_LONG).show();
                 } else {
                     mViewPager.setCurrentItem(page++);
                 }
             }
         });
     }
     else cancel();


            }


    }



    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}

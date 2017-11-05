package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class DirectoryFragment extends android.support.v4.app.Fragment {

    View myView;

    RecyclerView recyclerView;
    DirectoryGridAdapter adapter;
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

        recyclerView = (RecyclerView) myView.findViewById(R.id.rv);

        recyclerView.addOnItemTouchListener(
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
        );

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);



        adapter = new DirectoryGridAdapter(getActivity(),class_obj);///////////////////////////////////////////////
        class_obj.add(new IconsClass(R.drawable.adcorp,"Ad and Corporate Filmmakers","adcorp"));
        class_obj.add(new IconsClass(R.drawable.advertisingandmarketing,"Advertisingandmarketing","advertising"));
        class_obj.add(new IconsClass(R.drawable.artdirector,"Art directors","artdirectors"));
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
        class_obj.add(new IconsClass(R.drawable.journalsandwebsites,"Journals and Websites","journalwebsites"));
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


        recyclerView.setAdapter(adapter);

        return myView;
    }
}

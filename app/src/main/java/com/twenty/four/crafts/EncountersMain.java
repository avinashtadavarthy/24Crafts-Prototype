package com.twenty.four.crafts;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EncountersMain extends android.support.v4.app.Fragment {

    View myView;
    private ProgressBar progressBar;
    private CardStackView cardStackView;
    private TouristSpotCardAdapter adapter;
    LinearLayout row;

    int clickStar,clickCross,swipeRighttoLike,swipelefttoDislike,undo;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    ImageView forbiddenMark,undoButton,starInEncounters,closeEnvelope;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.from(container.getContext()).inflate(R.layout.activity_encounters_main,container,false);

        getActivity().setTitle("Encounters");
        setHasOptionsMenu(true);
        row = myView.findViewById(R.id.rowOfIcons);

        User.getInstance().navbarpos = 0;

        forbiddenMark = myView.findViewById(R.id.forbiddenMark);
        undoButton = myView.findViewById(R.id.undoButton);
        starInEncounters = myView.findViewById(R.id.starInEncounters);
        closeEnvelope = myView.findViewById(R.id.closeEnvelope);




        pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
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


                if(direction.toString().equalsIgnoreCase("right"))
                {
                    if(swipeRighttoLike == 0)
                    {
                        AlertDialogSwipeRight();
                        editor.putInt("swipeRighttoLike",1);
                        editor.commit();
                    }
                }

                if(direction.toString().equalsIgnoreCase("left"))
                {

                    if(swipelefttoDislike == 0)
                    {
                        AlertDialogSwipeLeft();
                        editor.putInt("swipeLefttoDislike",1);
                        editor.commit();
                    }

                }
                Log.d("CardStackView", "onCardSwiped: " + direction.toString());
                Log.d("CardStackView", "topIndex: " + cardStackView.getTopIndex());
                if (cardStackView.getTopIndex() == adapter.getCount() - 5) {
                    Log.d("CardStackView", "Paginate: " + cardStackView.getTopIndex());
                    paginate();
                }
            }

            @Override
            public void onCardReversed() {
                Log.d("CardStackView", "onCardReversed");
            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin");
            }

            @Override
            public void onCardClicked(int index) {
                Log.d("CardStackView", "onCardClicked: " + index);
            }
        });




        forbiddenMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSharedPreferences();

                if(clickCross == 0)
                {
                    AlertDialogPressDislike();
                    editor.putInt("clickCross",1);
                    editor.commit();
                }

                else
                    swipeLeftClick();

            }
        });


        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences();

                if(undo == 0)
                {
                    AlertDialogUndo();
                    editor.putInt("undo",1);
                    editor.commit();
                }

                else
                    reverse();
            }
        });

        starInEncounters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSharedPreferences();

                if(clickStar == 0)
                {
                    AlertDialogPressLike();
                    editor.putInt("clickStar",1);
                    editor.commit();
                }

                else
                    swipeRightClick();

            }
        });
        reload();




        return myView;
    }

    private void getSharedPreferences() {
        clickStar = pref.getInt("clickStar",0);
        clickCross = pref.getInt("clickCross",0);
        swipeRighttoLike = pref.getInt("swipeRighttoLike",0);
        swipelefttoDislike = pref.getInt("swipeLefttoDislike",0);
        undo = pref.getInt("Undo",0);
    }


 /*   @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_activity_main_reload:
                reload();
                break;
            case R.id.menu_activity_main_add_first:
                addFirst();
                break;
            case R.id.menu_activity_main_add_last:
                addLast();
                break;
            case R.id.menu_activity_main_remove_first:
                removeFirst();
                break;
            case R.id.menu_activity_main_remove_last:
                removeLast();
                break;
            case R.id.menu_activity_main_swipe_left:
                swipeLeft();
                break;
            case R.id.menu_activity_main_swipe_right:
                swipeRight();
                break;
            case R.id.menu_activity_main_reverse:
                reverse();
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    private TouristSpot createTouristSpot() {
        return new TouristSpot("Yasaka Shrine", "Kyoto", "https://source.unsplash.com/Xq1ntWruZQI/600x800");
    }

    private List<TouristSpot> createTouristSpots() {
        List<TouristSpot> spots = new ArrayList<>();
        spots.add(new TouristSpot("Yasaka Shrine", "Kyoto", "https://source.unsplash.com/Xq1ntWruZQI/600x800"));
        spots.add(new TouristSpot("Fushimi Inari Shrine", "Kyoto", "https://source.unsplash.com/NYyCqdBOKwc/600x800"));
        spots.add(new TouristSpot("Bamboo Forest", "Kyoto", "https://source.unsplash.com/buF62ewDLcQ/600x800"));
        spots.add(new TouristSpot("Brooklyn Bridge", "New York", "https://source.unsplash.com/THozNzxEP3g/600x800"));
        spots.add(new TouristSpot("Empire State Building", "New York", "https://source.unsplash.com/USrZRcRS2Lw/600x800"));
        spots.add(new TouristSpot("The statue of Liberty", "New York", "https://source.unsplash.com/PeFk7fzxTdk/600x800"));
        spots.add(new TouristSpot("Louvre Museum", "Paris", "https://source.unsplash.com/LrMWHKqilUw/600x800"));
        spots.add(new TouristSpot("Eiffel Tower", "Paris", "https://source.unsplash.com/HN-5Z6AmxrM/600x800"));
        spots.add(new TouristSpot("Big Ben", "London", "https://source.unsplash.com/CdVAUADdqEc/600x800"));
        spots.add(new TouristSpot("Great Wall of China", "China", "https://source.unsplash.com/AWh9C-QjhE4/600x800"));
        return spots;
    }

    private TouristSpotCardAdapter createTouristSpotCardAdapter() {
        final TouristSpotCardAdapter adapter = new TouristSpotCardAdapter(getActivity().getApplicationContext());
        adapter.addAll(createTouristSpots());
        return adapter;
    }

    private void setup() {


    }

    private void reload() {
        cardStackView.setVisibility(View.GONE);
        row.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter = createTouristSpotCardAdapter();
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

    public void swipeRight() {
        List<TouristSpot> spots = extractRemainingTouristSpots();
        if (spots.isEmpty()) {
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

    public void swipeLeftClick()
    {
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

    public void swipeRightClick()
    {
        List<TouristSpot> spots = extractRemainingTouristSpots();
        if (spots.isEmpty()) {
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
        cardStackView.reverse();
    }



    public void AlertDialogSwipeLeft()
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Not Interested?")
                .setMessage("Are you sure you're not interested in this person")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        reverse();
                    }
                })
                .show();
    }



    public void AlertDialogSwipeRight()
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Like")
                .setMessage("Swiping Right will add <name> to favourites")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        reverse();
                    }
                })
                .show();
    }



    public void AlertDialogPressLike()
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Like")
                .setMessage("Clicking on the star icon will add <name> to favourites")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        swipeRightClick();


                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


    public void AlertDialogPressDislike()
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Not Interested?")
                .setMessage("Are you sure you're not interested in this person?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        swipeLeftClick();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }



    public void AlertDialogUndo()
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Undo?")
                .setMessage("Are you sure you want to Undo your last action?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        reverse();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }

}

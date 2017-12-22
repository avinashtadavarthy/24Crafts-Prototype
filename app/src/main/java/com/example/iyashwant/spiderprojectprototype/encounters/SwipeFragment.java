package com.example.iyashwant.spiderprojectprototype.encounters;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.style.IconMarginSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iyashwant.spiderprojectprototype.R;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirectionalView;
import com.mindorks.placeholderview.Utils;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class SwipeFragment extends android.support.v4.app.Fragment implements TinderCard.Callback {

    View myView;

    private SwipeDirectionalView mSwipeView;
    private Context mContext;
    private int mAnimationDuration = 300;
    private boolean isToUndo = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.from(container.getContext()).inflate(R.layout.activity_tinderswipemain,container,false);

        getActivity().setTitle("Encounters");

        mSwipeView = (SwipeDirectionalView) myView.findViewById(R.id.swipeView);
        mContext = getActivity().getApplicationContext();

        int bottomMargin = Utils.dpToPx(80);
        Point windowSize = com.example.iyashwant.spiderprojectprototype.encounters.Utils.getDisplaySize(getActivity().getWindowManager());
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setSwipeVerticalThreshold(Utils.dpToPx(50))
                .setSwipeHorizontalThreshold(Utils.dpToPx(50))
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setSwipeAnimTime(mAnimationDuration)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));


        Point cardViewHolderSize = new Point(windowSize.x, windowSize.y - bottomMargin);

        for (Profile profile : com.example.iyashwant.spiderprojectprototype.encounters.Utils.loadProfiles(getActivity().getApplicationContext())) {
            mSwipeView.addView(new TinderCard(mContext, profile, cardViewHolderSize, this));
        }


        myView.findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        myView.findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        myView.findViewById(R.id.undoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.undoLastSwipe();
            }
        });

        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                if (isToUndo) {
                    isToUndo = false;
                    mSwipeView.undoLastSwipe();
                }
            }
        });

        return myView;
    }

    @Override
    public void onSwipeUp() {
        Toast.makeText(getActivity(), "SAVED TO FAVOURITES!", Toast.LENGTH_SHORT).show();
        isToUndo = true;
    }
    
}

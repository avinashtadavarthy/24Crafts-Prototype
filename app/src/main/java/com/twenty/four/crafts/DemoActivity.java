/**
 * Copyright 2017 Harish Sridharan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twenty.four.crafts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;


public class DemoActivity extends android.support.v4.app.Fragment {

    public static final String EXTRA_TYPE = "type";

    private ShimmerRecyclerView shimmerRecycler;
    private CardAdapter mAdapter;
    View myView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final DemoConfiguration demoConfiguration = BaseUtils.getDemoConfiguration(BaseUtils.TYPE_LIST, getActivity().getApplicationContext());
        myView = inflater.from(container.getContext()).inflate(demoConfiguration.getLayoutResource(),container,false);

        RecyclerView.LayoutManager layoutManager;

        getActivity().setTitle("Newsfeed");
        /*setTheme(demoConfiguration.getStyleResource());*/
        layoutManager = demoConfiguration.getLayoutManager();
        /*setTitle(demoConfiguration.getTitleResource());*/

        shimmerRecycler = myView.findViewById(R.id.shimmer_recycler_view);

        if (demoConfiguration.getItemDecoration() != null) {
            shimmerRecycler.addItemDecoration(demoConfiguration.getItemDecoration());
        }

        mAdapter = new CardAdapter();
        mAdapter.setType(BaseUtils.TYPE_LIST);

        shimmerRecycler.setLayoutManager(layoutManager);
        shimmerRecycler.setAdapter(mAdapter);
        shimmerRecycler.showShimmerAdapter();

        shimmerRecycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadCards();
            }
        }, 3000);
        return myView;
    }



    private void loadCards() {
        int type = BaseUtils.TYPE_LIST;

        mAdapter.setCards(BaseUtils.getCards(getResources(), type));
        shimmerRecycler.hideShimmerAdapter();
    }


}

    /*public static final String EXTRA_TYPE = "type";

    private ShimmerRecyclerView shimmerRecycler;
    private CardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int type = getType();

        RecyclerView.LayoutManager layoutManager;

        final DemoConfiguration demoConfiguration = BaseUtils.getDemoConfiguration(BaseUtils.TYPE_LIST, this);
        setTheme(demoConfiguration.getStyleResource());
        setContentView(demoConfiguration.getLayoutResource());
        layoutManager = demoConfiguration.getLayoutManager();
        setTitle(demoConfiguration.getTitleResource());

        shimmerRecycler = findViewById(R.id.shimmer_recycler_view);

        if (demoConfiguration.getItemDecoration() != null) {
            shimmerRecycler.addItemDecoration(demoConfiguration.getItemDecoration());
        }

        mAdapter = new CardAdapter();
        mAdapter.setType(type);

        shimmerRecycler.setLayoutManager(layoutManager);
        shimmerRecycler.setAdapter(mAdapter);
        shimmerRecycler.showShimmerAdapter();

        shimmerRecycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadCards();
            }
        }, 3000);
    }

    private void loadCards() {
        int type = getType();

        mAdapter.setCards(BaseUtils.getCards(getResources(), type));
        shimmerRecycler.hideShimmerAdapter();
    }

    private int getType() {
        return getIntent().getIntExtra(EXTRA_TYPE, BaseUtils.TYPE_LIST);
    }*/


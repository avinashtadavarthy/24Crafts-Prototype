package com.example.iyashwant.spiderprojectprototype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.iyashwant.spiderprojectprototype.Auditions.AppliedAuditionsRecyclerAdapter;
import com.example.iyashwant.spiderprojectprototype.Auditions.AuditionHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Avinash Tadavarthy on 05-Nov-17.
 */

public class AppliedAuditionsFragment extends Fragment implements Callback<List<AuditionHelper>> {

    View myView;
    RecyclerView recyclerView;
    AppliedAuditionsRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.applied_auditions_tab,container,false);
        recyclerView = (RecyclerView) myView.findViewById(R.id.open_auditions_recyclerview);
        return myView;
    }

    void fetchAppliedAuditions(){
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<List<AuditionHelper>> call = retrofitService.fetchAuditions(getString(R.string.sample_auth_token));
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<List<AuditionHelper>> call, Response<List<AuditionHelper>> response) {
        List<AuditionHelper> auditionHelperList = response.body();
        adapter = new AppliedAuditionsRecyclerAdapter(getContext(),auditionHelperList);
        if(recyclerView!=null)
            recyclerView.swapAdapter(adapter, true);
        else
            recyclerView.setAdapter(adapter);
        recyclerView.notify();
    }

    @Override
    public void onFailure(Call<List<AuditionHelper>> call, Throwable t) {
        Toast.makeText(this.getContext(), "Failed to fetch auditions", Toast.LENGTH_SHORT).show();
    }
}

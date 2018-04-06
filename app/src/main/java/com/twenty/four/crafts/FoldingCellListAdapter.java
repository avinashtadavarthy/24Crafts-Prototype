package com.twenty.four.crafts;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
public class FoldingCellListAdapter extends ArrayAdapter<Item>  implements SwipeRefreshLayout.OnRefreshListener{

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    List<Item> items;
    int chooseTypeofAudition;


    Context context;
    Activity activity;


    public FoldingCellListAdapter(Context context, List<Item> objects, Activity activity,int chooseTypeofAudition) {
        super(context, 0, objects);
        this.context = context;
        this.items = objects;
        this.activity = activity;
        this.chooseTypeofAudition = chooseTypeofAudition;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // get item for selected view
        final Item item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder
            viewHolder.applicantsLayout = cell.findViewById(R.id.applicantsLayout);

            viewHolder.noofApplicants = cell.findViewById(R.id.content_noofapplicants);
            viewHolder.locationAudition = (TextView) cell.findViewById(R.id.locationAudition);
            viewHolder.dateAudition = (TextView) cell.findViewById(R.id.dateAudition);
            viewHolder.timeAudition = (TextView) cell.findViewById(R.id.timeAudition);
            viewHolder.projectName = (TextView) cell.findViewById(R.id.ProjectName);
            viewHolder.projectType = (TextView) cell.findViewById(R.id.ProjectType);
            viewHolder.projectDescription = (TextView) cell.findViewById(R.id.ProjectDescription);

            viewHolder.date = (TextView) cell.findViewById(R.id.publishDate);
            viewHolder.innerProjectName = cell.findViewById(R.id.innerProjectname);
            viewHolder.innerImageURL = cell.findViewById(R.id.head_image);
            viewHolder.innerSenderImageURL = cell.findViewById(R.id.content_avatar);
            viewHolder.innerName = cell.findViewById(R.id.content_name_view);
            viewHolder.innerPhoneNumber = cell.findViewById(R.id.content_phoneno_view);
            viewHolder.innerApplnFrom = cell.findViewById(R.id.content_from_date_1);
            viewHolder.innerApplnTo = cell.findViewById(R.id.content_to_date_1);
            viewHolder.innerAuditionDate = cell.findViewById(R.id.content_audition_date);
            viewHolder.innerAuditionTime = cell.findViewById(R.id.content_audition_time);
            viewHolder.innerAuditionLocation = cell.findViewById(R.id.content_audition_location);
            viewHolder.innerProjectDescription = cell.findViewById(R.id.content_project_desc);
            viewHolder.contentRequestBtn = (TextView) cell.findViewById(R.id.content_request_btn);
            viewHolder.lastdividerline = cell.findViewById(R.id.lastdivider);

            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        String isClient = "";
        String userdata =  getSPData("userdatamain");
        try {
            JSONObject object = new JSONObject(userdata);
            isClient = object.optString("isClient");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(isClient.equals("false"))
        {
            viewHolder.applicantsLayout.setVisibility(View.VISIBLE);
            viewHolder.lastdividerline.setVisibility(View.VISIBLE);
        }


        // bind data from selected element to view through view holder
        viewHolder.locationAudition.setText(item.getLocation());
        viewHolder.dateAudition.setText(item.getAuditionDate());
        viewHolder.timeAudition.setText(item.getAuditionTime());
        viewHolder.projectName.setText(item.getProjectName());
        viewHolder.projectType.setText(item.getProjectType());
        viewHolder.projectDescription.setText(item.getProjectDescription());
        viewHolder.innerProjectName.setText(item.getProjectName());
        viewHolder.noofApplicants.setText(item.getApplicantsSize() + "");

       /* //Glide to populate images for auditions and profilepics of uploader
        Glide.with(context).load("http://" + item.getInnerImageURL()).placeholder(R.drawable.avatar_placeholder).into(viewHolder.innerImageURL);
        Glide.with(context).load("http://" + item.getInnerSenderImageURL()).placeholder(R.drawable.avatar_placeholder).into(viewHolder.innerSenderImageURL);
*/
        viewHolder.innerName.setText(item.getInnerName());
        viewHolder.innerPhoneNumber.setText(item.getInnerPhoneNumber());
        viewHolder.innerApplnFrom.setText(item.getInnerApplnFrom());
        viewHolder.innerApplnTo.setText(item.getInnerApplnTo());
        viewHolder.innerAuditionDate.setText(item.getAuditionDate());
        viewHolder.innerAuditionTime.setText(item.getAuditionTime());
        viewHolder.innerAuditionLocation.setText(item.getInnerAuditionLocation());
        viewHolder.innerProjectDescription.setText(item.getInnerProjectDescription());

        if(chooseTypeofAudition == 1)
            viewHolder.contentRequestBtn.setVisibility(View.GONE);

        Log.e("sender image adapter",item.getInnerSenderImageURL());
        if(item.getInnerSenderImageURL().equals(null))
            viewHolder.innerSenderImageURL.setImageResource(R.drawable.male);

        else
            Glide.with(context).load("http://" + item.getInnerSenderImageURL()).into(viewHolder.innerSenderImageURL);


        viewHolder.contentRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //User.getInstance().BASE_URL + "user/audition/register/" + item.getId()

                if(chooseTypeofAudition == 0) {
                    StringRequest getRequest = new StringRequest(Request.Method.GET, User.getInstance().BASE_URL + "user/audition/register/" + item.getId(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.e("applied?", response);
                                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();

                                    if (response.toLowerCase().equals("registered for audition successfully and successfully updated features.")) {
                                        ArrayList<Item> items2 = new ArrayList<>();
                                        items2 = Item.getTestingList2(context, "CraftsmenOpenAuditions", activity);
                                        items2 = Item.getTestingList2(context, "CraftsmenAppliedAuditions", activity);
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("authorization", new SharedPref(context).getSPData(context, "jwtToken"));
                            return params;
                        }
                    };
                    MySingleton.getInstance(context).addToRequestQueue(getRequest);

                }






                else if(chooseTypeofAudition == 2)
                {
                    AndroidNetworking.initialize(context);


                    AndroidNetworking.get(User.getInstance().BASE_URL + "user/audition/unregister/" + item.getId())
                            .setPriority(Priority.MEDIUM)
                            .addHeaders("authorization",getSPData("jwtToken"))
                            .build()
                            .getAsString(new StringRequestListener() {
                                @Override
                                public void onResponse(String response) {

                                    Log.e("responseUnregister",response);
                                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                                    if (response.equals("Successfully unregistered!")) {
                                        ArrayList<Item> items2 = new ArrayList<>();
                                        items2 = Item.getTestingList2(context, "CraftsmenAppliedAuditions", activity);
                                        items2 = Item.getTestingList2(context, "CraftsmenOpenAuditions", activity);

                                    }
                                }

                                @Override
                                public void onError(ANError anError) {

                                }
                            });
                }
            }
        });




        if(isClient.equals("false"))
        {
            if(chooseTypeofAudition == 0)
                viewHolder.contentRequestBtn.setText("APPLY");

            else if(chooseTypeofAudition == 2)
                viewHolder.contentRequestBtn.setText("UNREGISTER");



        }
        else
        {
            viewHolder.contentRequestBtn.setText("EDIT/DELETE");
        }

        /*// set custom btn handler for list item from that item
        if (item.getRequestBtnClickListener() != null) {
            viewHolder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }
*/

        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }


    //Shared Preferences
    private void storeSPData(String key, String data) {

        SharedPreferences mUserData = context.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

    private String getSPData(String key) {

        SharedPreferences mUserData = context.getSharedPreferences("UserData", MODE_PRIVATE);
        String data = mUserData.getString(key, "");

        return data;

    }

    @Override
    public void onRefresh() {

    }


    // View lookup cache
    private static class ViewHolder {

        ImageView innerImageURL;
        ImageView innerSenderImageURL;
        TextView innerProjectName;
        TextView innerPhoneNumber;
        TextView innerName;
        TextView innerApplnFrom;
        TextView innerApplnTo;
        TextView innerAuditionDate;
        TextView innerAuditionTime;
        TextView innerAuditionLocation;
        TextView innerProjectDescription;

        ImageView lastdividerline;

        TextView noofApplicants;
        TextView locationAudition;
        TextView contentRequestBtn;
        TextView dateAudition;
        TextView timeAudition;
        TextView projectName;
        TextView projectType;
        TextView date;
        TextView projectDescription;
        RelativeLayout contentRequestBtnClient;
        TextView contentRequestBtnClientEdit;
        TextView contentRequestBtnClientDelete;


        LinearLayout applicantsLayout;
    }
}

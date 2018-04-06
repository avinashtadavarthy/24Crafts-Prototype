package com.twenty.four.crafts;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Simple POJO model for example
 */

public class Item {

    SharedPref sharedPref;


    private String id;
    private String location;
    private String auditionDate;
    private String auditionTime;
    private String projectName;
    private String projectType;
    private String projectDescription;


    private String innerImageURL;
    private String innerSenderImageURL;

    private String innerPhoneNumber;
    private String innerName;
    private String innerApplnFrom;
    private String innerApplnTo;
    private String innerAuditionLocation;
    private String innerProjectDescription;

    int applicantsSize;

    public Item() {

    }

    public Item(String id, String location, String auditionDate, String auditionTime, String projectName, String projectType, String description,
                String innerPhoneNumber,
                String innerName,String innerApplnFrom,String innerApplnTo,
                String innerAuditionLocation, String innerProjectDescription,
                String innerImageURL, String innerSenderImageURL,int applicantsSize) {


        this.id = id;
        this.location = location;
        this.auditionDate = auditionDate;
        this.auditionTime = auditionTime;
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectDescription = description;

     /*   this.innerImageURL = innerImageURL;
        this.innerSenderImageURL = innerSenderImageURL;*/

        this.innerApplnFrom = innerApplnFrom;
        this.innerApplnTo = innerApplnTo;
        this.innerPhoneNumber = innerPhoneNumber;
        this.innerName = innerName;
        this.innerAuditionLocation = innerAuditionLocation;
        this.innerProjectDescription = innerProjectDescription;
        this.applicantsSize = applicantsSize;
        this.innerSenderImageURL = innerSenderImageURL;
        this.innerImageURL = innerImageURL;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getInnerImageURL() {
        return innerImageURL;
    }

    public void setInnerImageURL(String innerImageURL) {
        this.innerImageURL = innerImageURL;
    }

    public String getInnerSenderImageURL() {
        return innerSenderImageURL;
    }

    public void setInnerSenderImageURL(String innerSenderImageURL) {
        this.innerSenderImageURL = innerSenderImageURL;
    }

    public String getInnerPhoneNumber() {
        return innerPhoneNumber;
    }

    public void setInnerPhoneNumber(String innerPhoneNumber) {
        this.innerPhoneNumber = innerPhoneNumber;
    }

    public int getApplicantsSize(){return applicantsSize;}
    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getInnerApplnFrom() {
        return innerApplnFrom;
    }

    public void setInnerApplnFrom(String innerApplnFrom) {
        this.innerApplnFrom = innerApplnFrom;
    }

    public String getInnerApplnTo() {
        return innerApplnTo;
    }

    public void setInnerApplnTo(String innerApplnTo) {
        this.innerApplnTo = innerApplnTo;
    }



    public String getInnerAuditionLocation() {
        return innerAuditionLocation;
    }

    public void setInnerAuditionLocation(String innerAuditionLocation) {
        this.innerAuditionLocation = innerAuditionLocation;
    }

    public String getInnerProjectDescription() {
        return innerProjectDescription;
    }

    public void setInnerProjectDescription(String innerProjectDescription) {
        this.innerProjectDescription = innerProjectDescription;
    }


    private View.OnClickListener requestBtnClickListener;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAuditionDate() {
        return auditionDate;
    }

    public void setAuditionDate(String auditionDate) {
        this.auditionDate = auditionDate;
    }

    public String getAuditionTime() {
        return auditionTime;
    }

    public void setAuditionTime(String auditionTime) {
        this.auditionTime = auditionTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() { return projectType;}

    public void setProjectType() { this.projectType = projectType; }


    public String getProjectDescription() { return projectDescription;}

    public void setProjectDescription(String projectDescription) { this.projectDescription = projectDescription; }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (requestsCount != item.requestsCount) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (pledgePrice != null ? !pledgePrice.equals(item.pledgePrice) : item.pledgePrice != null)
            return false;
        if (fromAddress != null ? !fromAddress.equals(item.fromAddress) : item.fromAddress != null)
            return false;
        if (toAddress != null ? !toAddress.equals(item.toAddress) : item.toAddress != null)
            return false;
        if (date != null ? !date.equals(item.date) : item.date != null) return false;
        return !(time != null ? !time.equals(item.time) : item.time != null);

    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (pledgePrice != null ? pledgePrice.hashCode() : 0);
        result = 31 * result + (fromAddress != null ? fromAddress.hashCode() : 0);
        result = 31 * result + (toAddress != null ? toAddress.hashCode() : 0);
        result = 31 * result + requestsCount;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
*/
    /**
     * @return List of elements prepared for tests
     */
    public static ArrayList<Item> getTestingList(final Context context, String fromwhere) {



        final ArrayList<Item> items = new ArrayList<>();


        switch(fromwhere) {
            case "CraftsmenOpenAuditions" :

                auditionRequest(context,User.getInstance().BASE_URL + "user/audition/viewAll",items,"viewAllAuditions");


                Log.e("yeah?", "code has been accessed");
                break;
                //Log.e("jwt", sharedPref.getSPData(context,"jwtToken"));

                /*JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                        User.getInstance().BASE_URL + "user/audition/viewAll", null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.e("auditionsresponse", response.toString());
                                try {

                                    for(int i=0; i<response.length(); i++) {
                                        JSONObject jsonObject = response.getJSONObject(i);

                                        String id = jsonObject.optString("_id"),
                                                location = jsonObject.optString("auditionLocation"),
                                                auditionDate = User.getInstance().getFormattedDate(jsonObject.optString("auditionDate")),
                                                auditionTime = jsonObject.optString("auditionTime"),
                                                projectName = jsonObject.optString("title"),
                                                projectType = jsonObject.optString("projectType"),
                                                description = jsonObject.optString("description"),
                                                innerPhoneNumber = jsonObject.optString("contactNo"),
                                                innerName = jsonObject.optString("senderName"),
                                                innerApplnFrom = User.getInstance().getFormattedDate(jsonObject.optString("applicationFromDate")),
                                                innerApplnTo = User.getInstance().getFormattedDate(jsonObject.optString("applicationToDate")),
                                                innerAuditionLocation = jsonObject.optString("auditionLocation"),
                                                innerProjectDescription = jsonObject.optString("description"),
                                                innerImageURL = jsonObject.optString("auditionImageURL"),
                                                innerSenderImageURL = jsonObject.optString("senderProfileImage");

                                        innerImageURL = "hello";
                                        innerSenderImageURL = "hey";

                                        items.add(new Item(id, location, auditionDate, auditionTime, projectName, projectType, description, innerPhoneNumber, innerName, innerApplnFrom, innerApplnTo, innerAuditionLocation, innerProjectDescription, innerImageURL, innerSenderImageURL));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){
                    @Override
                    public Map<String,String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("authorization", sharedPref.getSPData(context, "jwtToken"));
                        return params;
                    }
                };
                MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);*/









            case "CraftsmenAppliedAuditions":

                auditionRequest(context,User.getInstance().BASE_URL + "user/audition/viewMyAuditions",items,"viewAppliedAuditions");
                break;


            case "CraftsmenClosedAuditions":
                auditionRequest(context,User.getInstance().BASE_URL + "user/audition/closedAuditions",items,"viewClosedAuditions");
                break;
            default:

                //, "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"

                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Chennai","2 FEB 2018","5:00 PM","CHENNAI SILKS","Feature Film","This is a Feature Film!!!","9172635490","Velu Pandian","11/01/2018","03/03/18","Chennai","This is a Feature Film", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",2));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Mumbai","3 MAR 2018","4:00 PM","24 CRAFTS","App","This is a Mobile Application!!!","9182612345","Hariharan","29/01/2018","23/03/18","Mumbai","This is a Mobile Application", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",2));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Coimbatore","21 JAN 2018","10:30 AM","RUBIKS","Fun","This is a WCA Competition!!!","9876512354","Rakesh Vaideeswaran","01/01/2018","07/02/18","Coimbatore","This is a WCA Competition", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",2));

                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Chennai","2 FEB 2018","5:00 PM","CHENNAI SILKS","Feature Film","This is a Feature Film!!!","9172635490","Velu Pandian","11/01/2018","03/03/18","Chennai","This is a Feature Film", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",3));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Mumbai","3 MAR 2018","4:00 PM","24 CRAFTS","App","This is a Mobile Application!!!","9182612345","Hariharan","29/01/2018","23/03/18","Mumbai","This is a Mobile Application", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",4));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Coimbatore","21 JAN 2018","10:30 AM","RUBIKS","Fun","This is a WCA Competition!!!","9876512354","Rakesh Vaideeswaran","01/01/2018","07/02/18","Coimbatore","This is a WCA Competition", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",7));

                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Chennai","2 FEB 2018","5:00 PM","CHENNAI SILKS","Feature Film","This is a Feature Film!!!","9172635490","Velu Pandian","11/01/2018","03/03/18","Chennai","This is a Feature Film", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",7));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Mumbai","3 MAR 2018","4:00 PM","24 CRAFTS","App","This is a Mobile Application!!!","9182612345","Hariharan","29/01/2018","23/03/18","Mumbai","This is a Mobile Application", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",4));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Coimbatore","21 JAN 2018","10:30 AM","RUBIKS","Fun","This is a WCA Competition!!!","9876512354","Rakesh Vaideeswaran","01/01/2018","07/02/18","Coimbatore","This is a WCA Competition", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",3));
                break;
        }

        return items;

    }




    public static ArrayList<Item> getTestingList2(final Context context, String fromwhere, Activity activity) {



        final ArrayList<Item> items = new ArrayList<>();


        switch(fromwhere) {
            case "CraftsmenOpenAuditions" :

                auditionRequest2(context,User.getInstance().BASE_URL + "user/audition/viewAll",items,"viewAllAuditions",activity);


                Log.e("yeah?", "code has been accessed");
                break;
            //Log.e("jwt", sharedPref.getSPData(context,"jwtToken"));

                /*JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                        User.getInstance().BASE_URL + "user/audition/viewAll", null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.e("auditionsresponse", response.toString());
                                try {

                                    for(int i=0; i<response.length(); i++) {
                                        JSONObject jsonObject = response.getJSONObject(i);

                                        String id = jsonObject.optString("_id"),
                                                location = jsonObject.optString("auditionLocation"),
                                                auditionDate = User.getInstance().getFormattedDate(jsonObject.optString("auditionDate")),
                                                auditionTime = jsonObject.optString("auditionTime"),
                                                projectName = jsonObject.optString("title"),
                                                projectType = jsonObject.optString("projectType"),
                                                description = jsonObject.optString("description"),
                                                innerPhoneNumber = jsonObject.optString("contactNo"),
                                                innerName = jsonObject.optString("senderName"),
                                                innerApplnFrom = User.getInstance().getFormattedDate(jsonObject.optString("applicationFromDate")),
                                                innerApplnTo = User.getInstance().getFormattedDate(jsonObject.optString("applicationToDate")),
                                                innerAuditionLocation = jsonObject.optString("auditionLocation"),
                                                innerProjectDescription = jsonObject.optString("description"),
                                                innerImageURL = jsonObject.optString("auditionImageURL"),
                                                innerSenderImageURL = jsonObject.optString("senderProfileImage");

                                        innerImageURL = "hello";
                                        innerSenderImageURL = "hey";

                                        items.add(new Item(id, location, auditionDate, auditionTime, projectName, projectType, description, innerPhoneNumber, innerName, innerApplnFrom, innerApplnTo, innerAuditionLocation, innerProjectDescription, innerImageURL, innerSenderImageURL));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){
                    @Override
                    public Map<String,String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("authorization", sharedPref.getSPData(context, "jwtToken"));
                        return params;
                    }
                };
                MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);*/









            case "CraftsmenAppliedAuditions":

                auditionRequest2(context,User.getInstance().BASE_URL + "user/audition/viewMyAuditions",items,"viewAppliedAuditions",activity);
                break;

            case "CraftsmenClosedAuditions":
                auditionRequest2(context,User.getInstance().BASE_URL + "user/audition/closedAuditions",items,"viewClosedAuditions",activity);
                break;

            default:

                //, "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"

                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Chennai","2 FEB 2018","5:00 PM","CHENNAI SILKS","Feature Film","This is a Feature Film!!!","9172635490","Velu Pandian","11/01/2018","03/03/18","Chennai","This is a Feature Film", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",2));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Mumbai","3 MAR 2018","4:00 PM","24 CRAFTS","App","This is a Mobile Application!!!","9182612345","Hariharan","29/01/2018","23/03/18","Mumbai","This is a Mobile Application", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",3));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Coimbatore","21 JAN 2018","10:30 AM","RUBIKS","Fun","This is a WCA Competition!!!","9876512354","Rakesh Vaideeswaran","01/01/2018","07/02/18","Coimbatore","This is a WCA Competition", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",1));

                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Chennai","2 FEB 2018","5:00 PM","CHENNAI SILKS","Feature Film","This is a Feature Film!!!","9172635490","Velu Pandian","11/01/2018","03/03/18","Chennai","This is a Feature Film", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",6));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Mumbai","3 MAR 2018","4:00 PM","24 CRAFTS","App","This is a Mobile Application!!!","9182612345","Hariharan","29/01/2018","23/03/18","Mumbai","This is a Mobile Application", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",4));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Coimbatore","21 JAN 2018","10:30 AM","RUBIKS","Fun","This is a WCA Competition!!!","9876512354","Rakesh Vaideeswaran","01/01/2018","07/02/18","Coimbatore","This is a WCA Competition", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",8));

                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Chennai","2 FEB 2018","5:00 PM","CHENNAI SILKS","Feature Film","This is a Feature Film!!!","9172635490","Velu Pandian","11/01/2018","03/03/18","Chennai","This is a Feature Film", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",9));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Mumbai","3 MAR 2018","4:00 PM","24 CRAFTS","App","This is a Mobile Application!!!","9182612345","Hariharan","29/01/2018","23/03/18","Mumbai","This is a Mobile Application", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",2));
                items.add(new Item("12227dbbiw7736bd_3e","Vadapalani, Coimbatore","21 JAN 2018","10:30 AM","RUBIKS","Fun","This is a WCA Competition!!!","9876512354","Rakesh Vaideeswaran","01/01/2018","07/02/18","Coimbatore","This is a WCA Competition", "https://content.paulreiffer.com/wp-content/uploads/2015/06/bonsai-rock-lake-tahoe-trees-incline-village-nevada-california-city-water-sunset-clouds-landscape-professional-photographer-paul-reiffer-usa-discover.jpg", "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",3));
                break;
        }

        return items;

    }



    public static void auditionRequest(final Context context, String url, final ArrayList<Item> items, final String sharedprefKey)
    {

        AndroidNetworking.initialize(context);
        final SharedPref sharedPref = new SharedPref(context);

        AndroidNetworking.get(url)
                .addHeaders("authorization",sharedPref.getSPData(context,"jwtToken"))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {





                        sharedPref.storeSPData(context,sharedprefKey,response.toString());

                        Log.e("auditionsresponse", response.toString());
                        try {

                            JSONArray responseSharedPref = new JSONArray(sharedPref.getSPData(context,sharedprefKey));


                            for(int i=0; i<responseSharedPref.length(); i++) {
                                JSONObject jsonObject = responseSharedPref.getJSONObject(i);

                                String id = jsonObject.optString("_id"),
                                        location = jsonObject.optString("auditionLocation"),
                                        auditionDate = User.getInstance().getFormattedDate(jsonObject.optString("auditionDate")),
                                        auditionTime = jsonObject.optString("auditionTime"),
                                        projectName = jsonObject.optString("title"),
                                        projectType = jsonObject.optString("projectType"),
                                        description = jsonObject.optString("description"),
                                        innerPhoneNumber = jsonObject.optString("contactNo"),
                                        innerName = jsonObject.optString("senderName"),
                                        innerApplnFrom = User.getInstance().getFormattedDate(jsonObject.optString("applicationFromDate")),
                                        innerApplnTo = User.getInstance().getFormattedDate(jsonObject.optString("applicationToDate")),
                                        innerAuditionLocation = jsonObject.optString("auditionLocation"),
                                        innerProjectDescription = jsonObject.optString("description"),
                                        innerImageURL = jsonObject.optString("auditionImageURL"),
                                        innerSenderImageURL = jsonObject.optString("senderProfileImage");

                                JSONArray applicantsID = jsonObject.optJSONArray("applicantsId");
                                int applicantssize = applicantsID.length();


                                innerImageURL = "hello";


                                Log.e("sender image Item",innerSenderImageURL);



                                String auditionDateFinal = User.getInstance().getDate(auditionDate);

                                items.add(new Item(id, location, auditionDateFinal, auditionTime, projectName, projectType, description, innerPhoneNumber, innerName, innerApplnFrom, innerApplnTo, innerAuditionLocation, innerProjectDescription, innerImageURL, innerSenderImageURL,applicantssize));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }



    public static void auditionRequest2(final Context context, String url, final ArrayList<Item> items, final String sharedprefKey, final Activity activity)
    {



        AndroidNetworking.initialize(context);
        final SharedPref sharedPref = new SharedPref(context);

        AndroidNetworking.get(url)
                .addHeaders("authorization",sharedPref.getSPData(context,"jwtToken"))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {


                        sharedPref.storeSPData(context,sharedprefKey,response.toString());

                        Log.e("auditionsresponse", response.toString());
                        try {

                            JSONArray responseSharedPref = new JSONArray(sharedPref.getSPData(context,sharedprefKey));


                            for(int i=0; i<responseSharedPref.length(); i++) {
                                JSONObject jsonObject = responseSharedPref.getJSONObject(i);

                                String id = jsonObject.optString("_id"),
                                        location = jsonObject.optString("auditionLocation"),
                                        auditionDate = User.getInstance().getFormattedDate(jsonObject.optString("auditionDate")),
                                        auditionTime = jsonObject.optString("auditionTime"),
                                        projectName = jsonObject.optString("title"),
                                        projectType = jsonObject.optString("projectType"),
                                        description = jsonObject.optString("description"),
                                        innerPhoneNumber = jsonObject.optString("contactNo"),
                                        innerName = jsonObject.optString("senderName"),
                                        innerApplnFrom = User.getInstance().getFormattedDate(jsonObject.optString("applicationFromDate")),
                                        innerApplnTo = User.getInstance().getFormattedDate(jsonObject.optString("applicationToDate")),
                                        innerAuditionLocation = jsonObject.optString("auditionLocation"),
                                        innerProjectDescription = jsonObject.optString("description"),
                                        innerImageURL = jsonObject.optString("auditionImageURL"),
                                        innerSenderImageURL = jsonObject.optString("senderProfileImage");

                                innerImageURL = "hello";

                                Log.e("sender image",innerSenderImageURL);
                                JSONArray applicantsID = jsonObject.optJSONArray("applicantsId");
                                int applicantssize = applicantsID.length();

                                String auditionDateFinal = User.getInstance().getDate(auditionDate);

                                items.add(new Item(id, location, auditionDateFinal, auditionTime, projectName, projectType, description, innerPhoneNumber, innerName, innerApplnFrom, innerApplnTo, innerAuditionLocation, innerProjectDescription, innerImageURL, innerSenderImageURL,applicantssize));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        activity.recreate();


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

}

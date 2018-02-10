package com.twenty.four.crafts;

import android.view.View;

import java.util.ArrayList;

/**
 * Simple POJO model for example
 */
public class Item {

    private String location;
    private String auditionDate;
    private String auditionTime;
    private String projectName;
    private String projectType;
    private String projectDescription;
    private String date;


    private View.OnClickListener requestBtnClickListener;

    public Item() {
    }

    public Item(String location, String auditionDate, String auditionTime, String projectName, String projectType, String description, String date) {

        this.location = location;
        this.auditionDate = auditionDate;
        this.auditionTime = auditionTime;
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectDescription = description;
        this.date = date;

    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Chennai", "20/01/2017", "5 PM", "Chennai Silks","Feature Film", "dajdasda dajdasjdaksdasjkdhaskjdhaskdhakjdada","10/02/2018"));
        items.add(new Item("Chennai", "20/01/2017", "5 PM", "Chennai Silks","Feature Film", "dajdasda dajdasjdaksdasjkdhaskjdhaskdhakjdada","10/02/2018"));
        items.add(new Item("Chennai", "20/01/2017", "5 PM", "Chennai Silks","Feature Film", "dajdasda dajdasjdaksdasjkdhaskjdhaskdhakjdada","10/02/2018"));
        items.add(new Item("Chennai", "20/01/2017", "5 PM", "Chennai Silks","Feature Film", "dajdasda dajdasjdaksdasjkdhaskjdhaskdhakjdada","10/02/2018"));
        items.add(new Item("Chennai", "20/01/2017", "5 PM", "Chennai Silks","Feature Film", "dajdasda dajdasjdaksdasjkdhaskjdhaskdhakjdada","10/02/2018"));
        items.add(new Item("Chennai", "20/01/2017", "5 PM", "Chennai Silks","Feature Film", "dajdasda dajdasjdaksdasjkdhaskjdhaskdhakjdada","10/02/2018"));
        return items;

    }

}

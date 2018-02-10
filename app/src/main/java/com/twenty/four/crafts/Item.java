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


    private String innerImageURL;

    private String innerPhoneNumber;
    private String innerName;
    private String innerApplnFrom;
    private String innerApplnTo;
    private String innerAuditionLocation;
    private String innerProjectDescription;


    public Item() {
    }

    public Item(String location, String auditionDate, String auditionTime, String projectName, String projectType, String description,
                String innerPhoneNumber,
                String innerName,String innerApplnFrom,String innerApplnTo,
                String innerAuditionLocation, String innerProjectDescription) {


        this.location = location;
        this.auditionDate = auditionDate;
        this.auditionTime = auditionTime;
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectDescription = description;

        this.innerApplnFrom = innerApplnFrom;
        this.innerApplnTo = innerApplnTo;

        this.innerPhoneNumber = innerPhoneNumber;
        this.innerName = innerName;

        this.innerAuditionLocation = innerAuditionLocation;
        this.innerProjectDescription = innerProjectDescription;

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



    public String getInnerPhoneNumber() {
        return innerPhoneNumber;
    }

    public void setInnerPhoneNumber(String innerPhoneNumber) {
        this.innerPhoneNumber = innerPhoneNumber;
    }

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
    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Vadapalani, Chennai","2 FEB 2018","5:00 PM","CHENNAI SILKS","Feature Film","This is a Feature Film!!!","9172635490","Velu Pandian","11/01/2018","03/03/18","Chennai","This is a Feature Film"));
        items.add(new Item("Vadapalani, Mumbai","3 MAR 2018","4:00 PM","24 CRAFTS","App","This is a Mobile Application!!!","9182612345","Hariharan","29/01/2018","23/03/18","Mumbai","This is a Mobile Application"));
        items.add(new Item("Vadapalani, Coimbatore","21 JAN 2018","10:30 AM","RUBIKS","Fun","This is a WCA Competition!!!","9876512354","Rakesh Vaideeswaran","01/01/2018","07/02/18","Coimbatore","This is a WCA Competition"));

        items.add(new Item("Vadapalani, Chennai","2 FEB 2018","5:00 PM","CHENNAI SILKS","Feature Film","This is a Feature Film!!!","9172635490","Velu Pandian","11/01/2018","03/03/18","Chennai","This is a Feature Film"));
        items.add(new Item("Vadapalani, Mumbai","3 MAR 2018","4:00 PM","24 CRAFTS","App","This is a Mobile Application!!!","9182612345","Hariharan","29/01/2018","23/03/18","Mumbai","This is a Mobile Application"));
        items.add(new Item("Vadapalani, Coimbatore","21 JAN 2018","10:30 AM","RUBIKS","Fun","This is a WCA Competition!!!","9876512354","Rakesh Vaideeswaran","01/01/2018","07/02/18","Coimbatore","This is a WCA Competition"));


        items.add(new Item("Vadapalani, Chennai","2 FEB 2018","5:00 PM","CHENNAI SILKS","Feature Film","This is a Feature Film!!!","9172635490","Velu Pandian","11/01/2018","03/03/18","Chennai","This is a Feature Film"));
        items.add(new Item("Vadapalani, Mumbai","3 MAR 2018","4:00 PM","24 CRAFTS","App","This is a Mobile Application!!!","9182612345","Hariharan","29/01/2018","23/03/18","Mumbai","This is a Mobile Application"));
        items.add(new Item("Vadapalani, Coimbatore","21 JAN 2018","10:30 AM","RUBIKS","Fun","This is a WCA Competition!!!","9876512354","Rakesh Vaideeswaran","01/01/2018","07/02/18","Coimbatore","This is a WCA Competition"));
        return items;

    }

}

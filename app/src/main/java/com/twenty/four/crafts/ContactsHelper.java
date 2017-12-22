package com.twenty.four.crafts;

/**
 * Created by rajiv on 11/9/17.
 */

public class ContactsHelper {


    private String PHONE;

    private String NAME;

    private String _id;

    public ContactsHelper() {
        this.PHONE = "";
        this.NAME = "";
        this._id = "";
        this.ADDRESS = "";
        this.EMAIL = "";
        this.WEBSITE = "";
    }

    private String ADDRESS;

    private String EMAIL;

    private String WEBSITE;

    public String getPHONE ()
    {
        return PHONE;
    }

    public void setPHONE (String PHONE)
    {
        this.PHONE = PHONE;
    }

    public String getNAME ()
    {
        return NAME;
    }

    public void setNAME (String NAME)
    {
        this.NAME = NAME;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getADDRESS ()
    {
        return ADDRESS;
    }

    public void setADDRESS (String ADDRESS)
    {
        this.ADDRESS = ADDRESS;
    }

    public String getEMAIL ()
    {
        return EMAIL;
    }

    public void setEMAIL (String EMAIL)
    {
        this.EMAIL = EMAIL;
    }

    public String getWEBSITE ()
    {
        return WEBSITE;
    }

    public void setWEBSITE (String WEBSITE)
    {
        this.WEBSITE = WEBSITE;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [PHONE = "+PHONE+", NAME = "+NAME+", _id = "+_id+", ADDRESS = "+ADDRESS+", EMAIL = "+EMAIL+", WEBSITE = "+WEBSITE+"]";
    }
}

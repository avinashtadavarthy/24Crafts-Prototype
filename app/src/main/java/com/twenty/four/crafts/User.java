package com.twenty.four.crafts;

/**
 * Created by avinash on 27/12/17.
 */

public class User {
    private static User mInstance= null;

    //page 1 (mandatory to all) - StartingScreen
    public String isClient;

    //page 2 (mandatory for all) - signup
    public String
            firstname,
            lastname,
            useremail,
            password,
            usergender,
            dob,
            residingin,
            hometown,
            category;

    //page 3 (only for actor, actress, child actor) - signup2
    public String
            bodytype,
            haircolor,
            hairlength,
            eyecolor,
            complexion,
            facialhair,
            userheight,
            userweight,
            hipsize,
            chestsize,
            waistsize;

    //page 4 (different for all) - signup 3
    public String phonenumber;

    //page 5 - Verification
    public Boolean
            phone_verified = false,
            facebook_verified = false,
            google_verified = false,
            instagram_verified = false,
            twitter_verified = false;

    protected User(){}

    public static synchronized User getInstance(){
        if(null == mInstance){
            mInstance = new User();
        }
        return mInstance;
    }

}

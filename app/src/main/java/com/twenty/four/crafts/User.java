package com.twenty.four.crafts;

/**
 * Created by avinash on 27/12/17.
 */

public class User {
    private static User mInstance= null;

    //page 1 (mandatory for all)
    public String
            isClient,
            firstname,
            lastname,
            useremail,
            password,
            usergender,
            dob,
            residingin,
            hometown,
            category;

    //page 2 (only for actor, actress, child actor)
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

    protected User(){}

    public static synchronized User getInstance(){
        if(null == mInstance){
            mInstance = new User();
        }
        return mInstance;
    }

}

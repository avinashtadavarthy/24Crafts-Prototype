package com.twenty.four.crafts;

/**
 * Created by avinash on 27/12/17.
 */

public class User {

    private static User mInstance= null;

    //variables or functions
    public static String
            languagesspoken = "", languagesspokendirty = "";

    public int navbarpos = 0;
    public int navbarposclient = 1;
    public static int ct = 0;

    protected User(){}

    public static synchronized User getInstance(){
        if(null == mInstance){
            mInstance = new User();
        }
        return mInstance;
    }

    /*
            
                  Shared Preferences File Name: "UserData"
                                MODE_PRIVATE
                          "All of them are Strings"


       +================+==========================+===============+
       |      Page      |  Shared Preferences Tag  |  Compulsary?  |
       +================+==========================+===============+
       |       1        |       isClient           |               |
       |----------------|--------------------------|---------------|
       |                |       firstname          |               |
       |                |       lastname           |               |
       |                |        email             |               |
       |       2        |       password           |               |
       |                |         dob              |               |
       |                |        gender            |               |
       |                |       category           |               |
       |                |       residingin         |               |
       |                |       hometown           |               |
       |                |    languagesspoken       |               |
       |----------------|--------------------------|---------------|
       |                |       bodyType           |               |
       |                |       hairColor          |               |
       |                |       hairLength         |               |
       |                |       eyeColor           |               |
       |                |       skinTone           |               |
       |       3        |       facialHair         |               |
       |                |       height             |               |
       |                |       weight             |               |
       |                |       //hipsize          |               |
       |                |       chestSize          |               |
       |                |       waistSize          |               |
       |----------------|--------------------------|---------------|
       |       4        |       phonenumber        |               |
       |                |          name            |               |
       |----------------|--------------------------|---------------|
       |                |     phone_verified       |               |
       |                |    facebook_verified     |               |
       |                |     google_verified      |               |
       |      5         |    instagram_verified    |               |
       |                |     twitter_verified     |               |
       |                |      allTheUserData      |               |
       |                |                          |               |
       +================+==========================+===============+





         upon login -
         --old ones to be rewritten--
         useremail
         dob
         usergender
         name
         password (change to null on login)
         isClient
         //to be done - languagesSpoken

         --new ones to be made--
         referralcode
         photocount
         referralCode
         photoCount
         videoCount
         photosUploaded
         coinCount
         isSubscribed
         photoURLS
         experienceDetails
         previousExperience
         sportsPlayed
         danceStyles
         tags
         videoYoutubeURLS
         referralCodeUsed
         isClient
         appliedAuditions







          //page 1 (mandatory to all) = StartingScreen
    private Boolean isClient = false; "isClient"

    //page 2 (mandatory for all) = signup
    private String
            firstname,
            lastname,
            useremail,
            password,
            usergender,
            dob,
            residingin = "null",
            hometown = "null",
            category;

    //page 3 (only for actor, actress, child actor) = signup2
    private String
            bodytype = "null",
            haircolor = "null",
            hairlength = "null",
            eyecolor = "null",
            complexion = "null",
            facialhair = "null",
            userheight = "null",
            userweight = "null",
            hipsize = "null",
            chestsize = "null",
            waistsize = "null";

    //page 4 (different for all) = signup 3
    private String phonenumber;


    //page 5 = Verification
    private Boolean
            phone_verified = false,
            facebook_verified = false,
            google_verified = false,
            instagram_verified = false,
            twitter_verified = false;


          

  */
}

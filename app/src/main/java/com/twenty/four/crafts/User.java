package com.twenty.four.crafts;

import java.util.Calendar;

/**
 * Created by avinash on 27/12/17.
 */

public class User {

    private static User mInstance= null;

    //variables or functions
    public static String
            languagesspoken = "", languagesspokendirty = "";/**/

    public int navbarpos = 0;
    public int navbarposclient = 1;

    public int[] uploadedpics = new int[]{0,0,0};

    public String getAge(int year,int month,int day)
    {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year,month,day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if(today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
            age--;

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }


    public String getDate(String date)
    {
        String year = date.substring(0,4);
        String day = date.substring(8,10);
        String month = "";
        switch(date.substring(5,7))
        {
            case "01": month = "JAN";break;
            case "02": month = "FEB";break;
            case "03": month = "MAR";break;
            case "04": month = "APR";break;
            case "05": month = "MAY";break;
            case "06": month = "JUN";break;
            case "07": month = "JUL";break;
            case "08": month = "AUG";break;
            case "09": month = "SEP";break;
            case "10": month = "OCT";break;
            case "11": month = "NOV";break;
            case "12": month = "DEC";break;
            default:   month = "HELLO";

        }

        String dateFinal = day + " " + month +  " " + year;

        return dateFinal;
    }


    public String getFormattedDate(String date) {
        return date.substring(0,10);
    }



    public String getCategoryFromTag(String tag) {
        String category = "Category";

        switch(tag) {
            case "Actor" : category = "Actor"; break;
            case "Actress" : category = "Actress"; break;
            case "Childartist" : category = "Child Artist"; break;
            case "Singer" : category = "Singer"; break;
            case "Dancer" : category = "Dancer"; break;
            case "Sideartist" : category = "Side Artist"; break;
            case "Assistantdirector" : category = "Assistant Director"; break;
            case "Lyricwriter" : category = "Lyric Writer / Lyricist"; break;
            case "Dialoguewriter" : category = "Dialouge Writer"; break;
            case "Scriptwriter" : category = "Script / Screenplay Writers"; break;
            case "Storyboardartist" : category = "Story Board Artist"; break;
            case "Choreographer" : category = "Choreographer"; break;
            case "Directorofphotography" : category = "Director of Photography"; break;
            case "Stillphotographer" : category = "Still Photographer"; break;
            case "Pro" : category = "PRO"; break;
            case "Designer" : category = "Designer"; break;
            case "Productionmanager" : category = "Production Manager"; break;
            case "Focuspuller" : category = "Focus Puller"; break;
            case "Vehicledriver" : category = "Vehicle Driver"; break;
            case "Micdepartment" : category = "Mic Department"; break;
            case "Musicdirector" : category = "Music Director"; break;
            case "Makeupman" : category = "Make-up Man"; break;
            case "Hairdresser" : category = "Hair Dresser"; break;
            case "Costumer" : category = "Costumer"; break;
            case "Artdepartment" : category = "Art Department"; break;
            case "Setdepartment" : category = "Set Department"; break;
            case "Stuntman" : category = "Stuntman"; break;
            case "Editor" : category = "Editor"; break;
            case "Locationmanager" : category = "Location Manager"; break;
            case "Productionfood" : category = "Production (Food)"; break;
            case "Dubbingartist" : category = "Dubbing Artist"; break;
            case "Soundrecordingengineer" : category = "Sound Recording Engineer"; break;
            case "Soundmixingengineer" : category = "Sound Mixing Engineer"; break;
            case "Di" : category = "Digital Intermediate"; break;
            case "Vfx" : category = "VFX / CG"; break;
            case "Sfx" : category = "SFX"; break;
            case "Petsupplier" : category = "Pet Supplier / Pet Doctor / AWBI Certifications"; break;
            case "Castingagent" : category = "Casting Agent"; break;
            case "Codirector" : category = "Co-Director"; break;
            case "Coproducer" : category = "Co-Producer"; break;
            case "Director" : category = "Director"; break;
            case "Directorassistant" : category = "Director Assistant"; break;
            case "Executiveproducer" : category = "Executive Producer"; break;
            case "Modelcoordinator" : category = "Model Coordinator"; break;
            case "Producer" : category = "Producer"; break;
            case "Productionhousemanager" : category = "Production House Manager"; break;
        }

        return category;
    }


    public String getTagFromCategory(String category) {
        String tag = "Tag";

        switch(category) {
            case "Actor": tag = "Actor"; break;
            case "Actress": tag = "Actress"; break;
            case "Child Artist": tag = "Childartist"; break;
            case "Singer": tag = "Singer"; break;
            case "Dancer": tag = "Dancer"; break;
            case "Side Artist": tag = "Sideartist"; break;
            case "Assistant Director": tag = "Assistantdirector"; break;
            case "Lyric Writer / Lyricist": tag = "Lyricwriter"; break;
            case "Dialouge Writer": tag = "Dialoguewriter"; break;
            case "Script / Screenplay Writers": tag = "Scriptwriter"; break;
            case "Story Board Artist": tag = "Storyboardartist"; break;
            case "Choreographer": tag = "Choreographer"; break;
            case "Director of Photography": tag = "Directorofphotography"; break;
            case "Still Photographer": tag = "Stillphotographer"; break;
            case "PRO": tag = "Pro"; break;
            case "Designer": tag = "Designer"; break;
            case "Production Manager": tag = "Productionmanager"; break;
            case "Focus Puller": tag = "Focuspuller"; break;
            case "Vehicle Driver": tag = "Vehicledriver"; break;
            case "Mic Department": tag = "Micdepartment"; break;
            case "Music Director": tag = "Musicdirector"; break;
            case "Make-up Man": tag = "Makeupman"; break;
            case "Hair Dresser": tag = "Hairdresser"; break;
            case "Costumer": tag = "Costumer"; break;
            case "Art Department": tag = "Artdepartment"; break;
            case "Set Department": tag = "Setdepartment"; break;
            case "Stuntman": tag = "Stuntman"; break;
            case "Editor": tag = "Editor"; break;
            case "Location Manager": tag = "Locationmanager"; break;
            case "Production (Food)": tag = "Productionfood"; break;
            case "Dubbing Artist": tag = "Dubbingartist"; break;
            case "Sound Recording Engineer": tag = "Soundrecordingengineer"; break;
            case "Sound Mixing Engineer": tag = "Soundmixingengineer"; break;
            case "Digital Intermediate": tag = "Di"; break;
            case "VFX / CG": tag = "Vfx"; break;
            case "SFX": tag = "Sfx"; break;
            case "Pet Supplier / Pet Doctor / AWBI Certifications": tag = "Petsupplier"; break;
            case "Casting Agent": tag = "Castingagent"; break;
            case "Co-Director": tag = "Codirector"; break;
            case "Co-Producer": tag = "Coproducer"; break;
            case "Director": tag = "Director"; break;
            case "Director Assistant": tag = "Directorassistant"; break;
            case "Executive Producer": tag = "Executiveproducer"; break;
            case "Model Coordinator": tag = "Modelcoordinator"; break;
            case "Producer": tag = "Producer"; break;
            case "Production House Manager": tag = "Productionhousemanager"; break;
        }

        return tag;
    }

    ///url///
    public String BASE_URL = "http://24crafts.cf:3000/";
    /////////

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

       REGISTRATION ONLY - cleared out in signup and signup3
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
       |                |       hipsize            |               |
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

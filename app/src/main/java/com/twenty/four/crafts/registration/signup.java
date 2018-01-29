package com.twenty.four.crafts.registration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.squareup.picasso.Picasso;
import com.steelkiwi.cropiwa.image.CropIwaResultReceiver;
import com.twenty.four.crafts.CustomAdapterSpinner;
import com.twenty.four.crafts.LanguagesPopUp;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickClick;
import com.vansuita.pickimage.listeners.IPickResult;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.view.View.GONE;

public class signup extends AppCompatActivity implements IPickResult {

    private static final int LANGUAGES_SPOKEN = 25;
    private static final int REQUEST_IMAGE_LOAD = 5555;

    //TODO: Add languages spoken "multi select spinner"

    String[] restricteddomains = { "tagyourself.com", "whatpaas.com", "emeil.in", "azmeil.tk", "mailfa.tk", "inbax.tk", "emeil.ir",
            "einrot.com", "10minut.com.pl", "0-mail.com", "33mail.com", "maildrop.cc", "inboxalias.com", "spam4.me", "koszmail.pl ",
            "0815.ru", "0clickemail.com", "0wnd.net", "0wnd.org", "10minutemail.com", "20minutemail.com", "2prong.com", "30minutemail.com",
            "3d-painting.com", "4warding.com", "4warding.net", "4warding.org", "60minutemail.com", "675hosting.com", "675hosting.net",
            "675hosting.org", "6url.com", "75hosting.com", "75hosting.net", "75hosting.org", "7tags.com", "9ox.net", "a-bc.net",
            "afrobacon.com", "ajaxapp.net", "amilegit.com", "amiri.net", "amiriindustries.com", "anonbox.net", "anonymbox.com",
            "antichef.com", "antichef.net", "antispam.de", "baxomale.ht.cx", "beefmilk.com", "binkmail.com", "bio-muesli.net",
            "bobmail.info", "bodhi.lawlita.com", "bofthew.com", "brefmail.com", "broadbandninja.com", "bsnow.net", "bugmenot.com",
            "bumpymail.com", "casualdx.com", "centermail.com", "centermail.net", "chogmail.com", "choicemail1.com", "cool.fr.nf",
            "correo.blogos.net", "cosmorph.com", "courriel.fr.nf", "courrieltemporaire.com", "cubiclink.com", "curryworld.de", "cust.in",
            "dacoolest.com", "dandikmail.com", "dayrep.com", "deadaddress.com", "deadspam.com", "despam.it", "despammed.com", "devnullmail.com",
            "dfgh.net", "digitalsanctuary.com", "discardmail.com", "discardmail.de", "emailmiser.com", "disposableaddress.com", "disposeamail.com",
            "disposemail.com", "dispostable.com", "dm.w3internet.co.ukexample.com", "dodgeit.com", "dodgit.com", "dodgit.org", "donemail.ru",
            "dontreg.com", "dontsendmespam.de", "dump-email.info", "dumpandjunk.com", "dumpmail.de", "dumpyemail.com", "e4ward.com", "email60.com",
            "emaildienst.de", "emailias.com", "emailigo.de", "emailinfive.com", "emailmiser.com", "emailsensei.com", "emailtemporario.com.br",
            "emailto.de", "emailwarden.com", "emailx.at.hm", "emailxfer.com", "emz.net", "enterto.com", "ephemail.net", "etranquil.com", "etranquil.net",
            "etranquil.org", "explodemail.com", "fakeinbox.com", "fakeinformation.com", "fastacura.com", "fastchevy.com", "fastchrysler.com",
            "fastkawasaki.com", "fastmazda.com", "fastmitsubishi.com", "fastnissan.com", "fastsubaru.com", "fastsuzuki.com", "fasttoyota.com",
            "fastyamaha.com", "filzmail.com", "fizmail.com", "fr33mail.info", "frapmail.com", "front14.org", "fux0ringduh.com", "garliclife.com",
            "get1mail.com", "get2mail.fr", "grr.la", "getonemail.com", "getonemail.net", "ghosttexter.de", "girlsundertheinfluence.com", "gishpuppy.com",
            "gowikibooks.com", "gowikicampus.com", "gowikicars.com", "gowikifilms.com", "gowikigames.com", "gowikimusic.com", "gowikinetwork.com",
            "gowikitravel.com", "gowikitv.com", "great-host.in", "greensloth.com", "gsrv.co.uk", "guerillamail.biz", "guerillamail.com", "guerillamail.net",
            "guerillamail.org", "guerrillamail.biz", "guerrillamail.com", "guerrillamail.de", "guerrillamail.net", "guerrillamail.org",
            "guerrillamailblock.com", "h.mintemail.com", "h8s.org", "haltospam.com", "hatespam.org", "hidemail.de", "hochsitze.com", "hotpop.com",
            "hulapla.de", "ieatspam.eu", "ieatspam.info", "ihateyoualot.info", "iheartspam.org", "imails.info", "inboxclean.com", "inboxclean.org",
            "incognitomail.com", "incognitomail.net", "incognitomail.org", "insorg-mail.info", "ipoo.org", "irish2me.com", "iwi.net", "jetable.com",
            "jetable.fr.nf", "jetable.net", "jetable.org", "jnxjn.com", "junk1e.com", "kasmail.com", "kaspop.com", "keepmymail.com", "killmail.com",
            "killmail.net", "kir.ch.tc", "klassmaster.com", "klassmaster.net", "klzlk.com", "kulturbetrieb.info", "kurzepost.de", "letthemeatspam.com",
            "lhsdv.com", "lifebyfood.com", "link2mail.net", "litedrop.com", "lol.ovpn.to", "lookugly.com", "lopl.co.cc", "lortemail.dk", "lr78.com",
            "m4ilweb.info", "maboard.com", "mail-temporaire.fr", "mail.by", "mail.mezimages.net", "mail2rss.org", "mail333.com", "mail4trash.com",
            "mailbidon.com", "mailblocks.com", "mailcatch.com", "maileater.com", "mailexpire.com", "mailfreeonline.com", "mailin8r.com", "mailinater.com",
            "mailinator.com", "mailinator.net", "mailinator2.com", "mailincubator.com", "mailme.ir", "mailme.lv", "mailmetrash.com", "mailmoat.com",
            "mailnator.com", "mailnesia.com", "mailnull.com", "mailshell.com", "mailsiphon.com", "mailslite.com", "mailzilla.com", "mailzilla.org", "mbx.cc",
            "mega.zik.dj", "meinspamschutz.de", "meltmail.com", "messagebeamer.de", "mierdamail.com", "mintemail.com", "moburl.com", "moncourrier.fr.nf",
            "monemail.fr.nf", "monmail.fr.nf", "msa.minsmail.com", "mt2009.com", "mx0.wwwnew.eu", "mycleaninbox.net", "mypartyclip.de", "myphantomemail.com",
            "myspaceinc.com", "myspaceinc.net", "myspaceinc.org", "myspacepi", "10minut.com.pl", "mpedup.com", "myspamless.com", "mytrashmail.com",
            "neomailbox.com", "nepwk.com", "nervmich.net", "nervtmich.net", "netmails.com", "netmails.net", "netzidiot.de", "neverbox.com", "no-spam.ws",
            "nobulk.com", "noclickemail.com", "nogmailspam.info", "nomail.xl.cx", "nomail2me.com", "nomorespamemails.com", "nospam.ze.tc", "nospam4.us",
            "nospamfor.us", "nospamthanks.info", "notmailinator.com", "nowmymail.com", "nurfuerspam.de", "nwldx.com", "objectmail.com", "obobbo.com",
            "oneoffemail.com", "onewaymail.com", "online.ms", "oopi.org", "ordinaryamerican.net", "otherinbox.com", "ourklips.com", "outlawspam.com", "ovpn.to",
            "owlpic.com", "pancakemail.com", "pimpedupmyspace.com", "pjjkp.com", "politikerclub.de", "poofy.org", "pookmail.com", "privacy.net", "proxymail.eu",
            "prtnx.com", "punkass.com", "PutThisInYourSpamDatabase.com", "qq.com", "quickinbox.com", "rcpt.at", "recode.me", "recursor.net", "regbypass.com",
            "regbypass.comsafe-mail.net", "rejectmail.com", "rklips.com", "rmqkr.net", "rppkn.com", "rtrtr.com", "s0ny.net", "safe-mail.net", "safersignup.de",
            "safetymail.info", "safetypost.de", "sandelf.de", "saynotospams.com", "selfdestructingmail.com", "SendSpamHere.com", "sharklasers.com", "shiftmail.com",
            "shitmail.me", "shortmail.net", "sibmail.com", "skeefmail.com", "slaskpost.se", "slopsbox.com", "smellfear.com", "snakemail.com", "sneakemail.com",
            "sofimail.com", "sofort-mail.de", "sogetthis.com", "soodonims.com", "spam.la", "spam.su", "spamavert.com", "spambob.com", "spambob.net", "spambob.org",
            "spambog.com", "spambog.de", "spambog.ru", "spambox.info", "spambox.irishspringrealty.com", "spambox.us", "spamcannon.com", "spamcannon.net",
            "spamcero.com", "spamcon.org", "spamcorptastic.com", "spamcowboy.com", "spamcowboy.net", "spamcowboy.org", "spamday.com", "spamex.com", "spamfree24.com",
            "spamfree24.de", "spamfree24.eu", "spamfree24.info", "spamfree24.net", "spamfree24.org", "spamgourmet.com", "spamgourmet.net", "spamgourmet.org",
            "SpamHereLots.com", "SpamHerePlease.com", "spamhole.com", "spamify.com", "spaminator.de", "spamkill.info", "spaml.com", "spaml.de", "spammotel.com",
            "spamobox.com", "spamoff.de", "spamslicer.com", "spamspot.com", "spamthis.co.uk", "spamthisplease.com", "spamtrail.com", "speed.1s.fr", "supergreatmail.com",
            "supermailer.jp", "suremail.info", "teewars.org", "teleworm.com", "tempalias.com", "tempe-mail.com", "tempemail.biz", "tempemail.com", "TempEMail.net",
            "tempinbox.co.uk", "tempinbox.com", "tempmail.it", "tempmail2.com", "tempomail.fr", "temporarily.de", "temporarioemail.com.br", "temporaryemail.net",
            "temporaryforwarding.com", "temporaryinbox.com", "thanksnospam.info", "thankyou2010.com", "thisisnotmyrealemail.com", "throwawayemailaddress.com",
            "tilien.com", "tmailinator.com", "tradermail.info", "trash-amil.com", "trash-mail.at", "trash-mail.com", "trash-mail.de", "trash2009.com",
            "trashemail.de", "trashmail.at", "trashmail.com", "trashmail.de", "trashmail.me", "trashmail.net", "trashmail.org", "trashmail.ws", "trashmailer.com",
            "trashymail.com", "trashymail.net", "trillianpro.com", "turual.com", "twinmail.de", "tyldd.com", "uggsrock.com", "upliftnow.com", "uplipht.com",
            "venompen.com", "veryrealemail.com", "viditag.com", "viewcastmedia.com", "viewcastmedia.net", "viewcastmedia.org", "webm4il.info", "wegwerfadresse.de",
            "wegwerfemail.de", "wegwerfmail.de", "wegwerfmail.net", "wegwerfmail.org", "wetrainbayarea.com", "wetrainbayarea.org", "wh4f.org", "whyspam.me",
            "willselfdestruct.com", "winemaven.info", "wronghead.com", "wuzup.net", "wuzupmail.net", "www.e4ward.com", "www.gishpuppy.com", "www.mailinator.com",
            "wwwnew.eu", "xagloo.com", "xemaps.com", "xents.com", "xmaily.com", "xoxy.net", "yep.it", "yogamaven.com", "yopmail.com", "yopmail.fr", "yopmail.net",
            "ypmail.webarnak.fr.eu.org", "yuurok.com", "zehnminutenmail.de", "zippymail.info", "zoaxe.com", "zoemail.org" };

    String[] whoNcrafts = {"Who am I?",
            "Actor","Actress","Child Artist","Singer","Dancer",
            "Side Artists","Assistant Director","Lyric Writer / Lyricist",
            "Dialouge Writer","Script / Screenplay Writers", "Story Board Artist",
            "Choreographer","Director of Photography", "Still Photographer",
            "PRO", "Designer", "Production Manager",
            "Focus Puller", "Vehicle Driver", "Mic Department",
            "Music Director", "Makeup Man", "Hair Dresser",
            "Costumer", "Art Department", "Set Department",
            "Stuntman", "Editor", "Location Manager",
            "Production (Food)", "Dubbing Artists", "Sound Recording Engineers",
            "Sound Mixing Engineers", "Digital Intermediate", "VFX / CG",
            "SFX", "Pet Suppliers / Pet Doctors / AWBI Certifications"};


    String[] whoNclients = {"Who am I?",
            "Casting Agent","Co-Director","Co-Producer","Director","Director Assistant",
            "Director Audition","Executive Producer","Model Coordinator",
            "Producer","Production House Manager"};

    String[] genderString = { "Select Gender",
            "Male", "Female", "Other" };

    String name, selectedcraft = "null", selectedgender = "null";


    //integrating logins
    String type;
    String firstname;
    String lastname;
    String email;
    String gender;
    String imgurl;

    EditText first_name1, last_name1, email1,
            password1, confirm_password1;
    Spinner craft,genderspin;
    CircleImageView profile_image1, edit_profile_btn;
    TextView display_languages;
    TextView residing1;
    TextView hometown1;

    TextInputLayout input_firstname, input_lastname, input_email, input_password, input_confirmpassword;

    //datepicker
    TextView dob1;
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);

    //password checker
    ProgressBar progress;

    ProgressBar loadimageprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        input_firstname = (TextInputLayout) findViewById(R.id.input_firstname);
        input_lastname = (TextInputLayout) findViewById(R.id.input_lastname);
        input_email = (TextInputLayout) findViewById(R.id.input_email);
        input_password = (TextInputLayout) findViewById(R.id.input_password);
        input_confirmpassword = (TextInputLayout) findViewById(R.id.input_confirmpassword);



        //import image
        edit_profile_btn = (CircleImageView) findViewById(R.id.edit_profile_btn);
        edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: send the images up to the server on submit
                PickSetup setup = new PickSetup()
                        .setTitle("Choose Image From")
                        .setFlip(true)
                        .setMaxSize(500)
                        .setPickTypes(EPickType.GALLERY, EPickType.CAMERA)
                        .setIconGravity(Gravity.CENTER)
                        .setButtonOrientation(LinearLayoutCompat.VERTICAL)
                        .setSystemDialog(true);

                PickImageDialog.build(setup).show(signup.this);

            }
        });



        //receiving data
        Bundle bundle1 = getIntent().getExtras();
            type = bundle1.getString("type");
            firstname = bundle1.getString("firstname");
            lastname = bundle1.getString("lastname");
            email = bundle1.getString("email");
            gender = bundle1.getString("gender");
            imgurl = bundle1.getString("imgurl");


        first_name1 = (EditText) findViewById(R.id.first_name);
        last_name1 = (EditText) findViewById(R.id.last_name);
        email1 = (EditText) findViewById(R.id.email);
        password1 = (EditText) findViewById(R.id.password);
        confirm_password1 = (EditText) findViewById(R.id.confirm_password);
        profile_image1 = (CircleImageView) findViewById(R.id.profile_image);
        dob1 = (TextView) findViewById(R.id.dob);


        /*calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        date = day + "/" + month + "/" + year;*/

        password1.setTransformationMethod(new PasswordTransformationMethod());
        confirm_password1.setTransformationMethod(new PasswordTransformationMethod());


        confirm_password1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(confirm_password1.getText().toString().equals("")) input_confirmpassword.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!password1.getText().toString().equals("") && password1.getText().toString().equals(confirm_password1.getText().toString()))
                    input_confirmpassword.setError(null);
                else input_confirmpassword.setError("Passwords don't match");

                if(confirm_password1.getText().toString().equals("")) input_confirmpassword.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!password1.getText().toString().equals("") && password1.getText().toString().equals(confirm_password1.getText().toString())) {
                    input_confirmpassword.setError(null);
                } else {
                    input_confirmpassword.setError("Passwords don't match");
                }

                if(confirm_password1.getText().toString().equals("")) input_confirmpassword.setError(null);

            }
        });


        genderspin = (Spinner) findViewById(R.id.gender);
        CustomAdapterSpinner genderAdapter=new CustomAdapterSpinner(getApplicationContext(),genderString);
        genderspin.setAdapter(genderAdapter);

        genderspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView gender_floatingtext = (TextView) findViewById(R.id.gender_floatingtext);

                switch(genderString[position])
                {
                    case "Male": selectedgender = "Male";
                        gender_floatingtext.setText("Gender");break;

                    case "Female": selectedgender = "Female";
                        gender_floatingtext.setText("Gender");break;
                    case "Other": selectedgender = "Other";
                        gender_floatingtext.setText("Gender");break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        craft = (Spinner) findViewById(R.id.spinner);
        if(type.equals("craftsman")) {

            CustomAdapterSpinner craftAdapter;

            craftAdapter = new CustomAdapterSpinner(getApplicationContext(), whoNcrafts);
            craft.setAdapter(craftAdapter);


            craft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch(whoNcrafts[position])
                    {
                        case "Actor": selectedcraft = "Actor"; break;
                        case "Actress": selectedcraft = "Actress"; break;
                        case "Child Artist": selectedcraft = "Child Artist"; break;
                        case "Singer": selectedcraft = "Singer"; break;
                        case "Dancer": selectedcraft = "Dancer"; break;
                        case "Side Artists": selectedcraft = "Side Artists"; break;
                        case "Assistant Director": selectedcraft = "Assistant Director"; break;
                        case "Lyric Writer / Lyricist": selectedcraft = "Lyric Writer / Lyricist"; break;
                        case "Dialouge Writer": selectedcraft = "Dialouge Writer"; break;
                        case "Script / Screenplay Writers": selectedcraft = "Script / Screenplay Writers"; break;
                        case "Story Board Artist": selectedcraft = "Story Board Artist"; break;
                        case "Choreographer": selectedcraft = "Choreographer"; break;
                        case "Director of Photography": selectedcraft = "Director of Photography"; break;
                        case "Still Photographer": selectedcraft = "Still Photographer"; break;
                        case "PRO": selectedcraft = "PRO"; break;
                        case "Designer": selectedcraft = "Designer"; break;
                        case "Production Manager": selectedcraft = "Production Manager"; break;
                        case  "Focus Puller": selectedcraft =  "Focus Puller"; break;
                        case "Vehicle Driver": selectedcraft = "Vehicle Driver"; break;
                        case "Mic Department": selectedcraft = "Mic Department"; break;
                        case "Music Director": selectedcraft = "Music Director"; break;
                        case "Makeup Man": selectedcraft = "Makeup Man"; break;
                        case "Hair Dresser": selectedcraft = "Hair Dresser"; break;
                        case "Costumer": selectedcraft = "Costumer"; break;
                        case "Art Department": selectedcraft = "Art Department"; break;
                        case "Set Department": selectedcraft = "Set Department"; break;
                        case "Stuntman": selectedcraft = "Stuntman"; break;
                        case "Editor": selectedcraft = "Editor"; break;
                        case "Location Manager": selectedcraft = "Location Manager"; break;
                        case "Production (Food)": selectedcraft = "Production (Food)"; break;
                        case  "Dubbing Artists": selectedcraft =  "Dubbing Artists"; break;
                        case "Sound Recording Engineers": selectedcraft = "Sound Recording Engineers"; break;
                        case "Sound Mixing Engineers": selectedcraft = "Sound Mixing Engineers"; break;
                        case "Digital Intermediate": selectedcraft = "Digital Intermediate"; break;
                        case "VFX / CG": selectedcraft = "VFX / CG"; break;
                        case "SFX": selectedcraft = "SFX"; break;
                        case "Pet Suppliers / Pet Doctors / AWBI Certifications": selectedcraft = "Pet Suppliers / Pet Doctors / AWBI Certifications"; break;

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } else if(type.equals("client")) {

            CustomAdapterSpinner craftAdapter=new CustomAdapterSpinner(getApplicationContext(),whoNclients);
            craft.setAdapter(craftAdapter);
            craft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch(whoNclients[position])
                    {
                        case "Casting Agent": selectedcraft = "Casting Agent"; break;
                        case "Co-Director": selectedcraft = "Co-Director"; break;
                        case "Co-Producer": selectedcraft = "Co-Producer"; break;
                        case "Director": selectedcraft = "Director"; break;
                        case "Director Assistant": selectedcraft = "Director Assistant"; break;
                        case "Director Audition": selectedcraft = "Director Audition"; break;
                        case "Executive Producer": selectedcraft = "Executive Producer"; break;
                        case "Model Coordinator": selectedcraft = "Model Coordinator"; break;
                        case "Producer": selectedcraft = "Producer"; break;
                        case "Production House Manager": selectedcraft = "Production House Manager"; break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        }




        if(!firstname.equals("null")) {

            first_name1.setText(firstname);
            last_name1.setText(lastname);
            email1.setText(email);

            if(!imgurl.equals("null")) Picasso.with(getApplicationContext()).load(imgurl).into(profile_image1);
            else profile_image1.setImageResource(R.drawable.com_facebook_profile_picture_blank_square);

            if(gender.equals("male")) genderspin.setSelection(1);
            else if(gender.equals("female")) genderspin.setSelection(2);

        }





        display_languages = (TextView) findViewById(R.id.display_languages);

        display_languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),LanguagesPopUp.class).putExtra("languagesspoken", User.getInstance().languagesspokendirty);
                startActivityForResult(i,LANGUAGES_SPOKEN);
            }
        });


        residing1 = (TextView) findViewById(R.id.residing);
        hometown1 = (TextView) findViewById(R.id.hometown);


        residing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(signup.this);
                    startActivityForResult(intent, 1000);

                } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                }


            }
        });



        hometown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(signup.this);
                    startActivityForResult(intent, 2000);

                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });


        Button next = (Button)findViewById(R.id.button11);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("craftsman")) {

                    if(selectedcraft.equals("Actor") || selectedcraft.equals("Actress") || selectedcraft.equals("Child Artist") || selectedcraft.equals("Dancer") || selectedcraft.equals("Side Artists")){

                        storeSPData("firstname", first_name1.getText().toString());
                        storeSPData("lastname", last_name1.getText().toString());
                        storeSPData("email", email1.getText().toString());
                        storeSPData("password", password1.getText().toString());
                        storeSPData("dob", dob1.getText().toString());
                        storeSPData("gender", selectedgender);
                        storeSPData("category", selectedcraft);

                        name=first_name1.getText().toString();
                        Intent goToNextActivity = new Intent(getApplicationContext(), signup2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("craft",selectedcraft);
                        bundle.putString("type",type);
                        goToNextActivity.putExtras(bundle);
                        startActivity(goToNextActivity);
                    } else if(selectedcraft.equals("null"))
                    {
                        Toast.makeText(getApplicationContext(),"Please select appropriate Portfolio to continue", Toast.LENGTH_LONG).show();
                    } else if(selectedgender.equals("null")) {
                        Toast.makeText(getApplicationContext(),"Please select appropriate gender to continue", Toast.LENGTH_LONG).show();
                    } else if(selectedcraft.equals("null") && selectedgender.equals("null")) {
                        Toast.makeText(getApplicationContext(), "Select all mandatory fields to continue", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        storeSPData("firstname", first_name1.getText().toString());
                        storeSPData("lastname", last_name1.getText().toString());
                        storeSPData("email", email1.getText().toString());
                        storeSPData("password", password1.getText().toString());
                        storeSPData("dob", dob1.getText().toString());
                        storeSPData("gender", selectedgender);
                        storeSPData("category", selectedcraft);

                        name=first_name1.getText().toString();
                        Intent goToNextActivity = new Intent(getApplicationContext(), signup3.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("craft",selectedcraft);
                        bundle.putString("type",type);
                        goToNextActivity.putExtras(bundle);
                        startActivity(goToNextActivity);
                    }



                } else if(type.equals("client")) {

                    if(selectedcraft.equals("null")) {
                        Toast.makeText(getApplicationContext(),"Please select appropriate Portfolio to continue",Toast.LENGTH_LONG).show();
                    } else if(selectedgender.equals("null")) {
                        Toast.makeText(getApplicationContext(),"Please select appropriate gender to continue",Toast.LENGTH_LONG).show();
                    } else if(selectedcraft.equals("null") && selectedgender.equals("null")) {
                        Toast.makeText(getApplicationContext(), "Select all mandatory fields to continue", Toast.LENGTH_SHORT).show();
                    } else {


                        storeSPData("firstname", first_name1.getText().toString());
                        storeSPData("lastname", last_name1.getText().toString());
                        storeSPData("email", email1.getText().toString());
                        storeSPData("password", password1.getText().toString());
                        storeSPData("dob", dob1.getText().toString().trim());
                        storeSPData("gender", selectedgender);
                        storeSPData("category", selectedcraft);

                        name = first_name1.getText().toString();
                        Intent goToNextActivity = new Intent(getApplicationContext(), signup3.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("craft", selectedcraft);
                        bundle.putString("type",type);
                        goToNextActivity.putExtras(bundle);
                        startActivity(goToNextActivity);
                    }

                }

            }
        });


        loadimageprogress = (ProgressBar) findViewById(R.id.loadimageprogress);
        loadimageprogress.setVisibility(View.GONE);

    }



    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {

            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());

            //If you want the Bitmap.
            //profile_image1.setImageBitmap(r.getBitmap());

            Intent intent = new Intent(this, EditPictureActivity.class);
            intent.putExtra("RawImageUri", r.getUri().toString());
            //createImageFromBitmap(r.getBitmap(), "RawImage");
            loadimageprogress.setVisibility(View.VISIBLE);
            startActivityForResult(intent, REQUEST_IMAGE_LOAD);

            //Image path
            //r.getPath();

        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {

                TextView residing_floatingtext = (TextView) findViewById(R.id.residing_floatingtext);
                residing_floatingtext.setText("Residing In");

                Place place = PlaceAutocomplete.getPlace(this, data);
                residing1.setText(place.getName());
                storeSPData("residingIn", place.getName().toString());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == 2000) {
            if (resultCode == RESULT_OK) {

                TextView hometown_floatingtext = (TextView) findViewById(R.id.hometown_floatingtext);
                hometown_floatingtext.setText("Native (Hometown)");

                Place place = PlaceAutocomplete.getPlace(this, data);
                hometown1.setText(place.getName());
                storeSPData("homeTown", place.getName().toString());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }


        if(requestCode == REQUEST_IMAGE_LOAD) {
            if(resultCode == Activity.RESULT_OK) {

                loadimageprogress.setVisibility(View.GONE);
                String final_path = data.getStringExtra("saved_path");
                profile_image1.setImageURI(Uri.parse(final_path));

            } else {
                loadimageprogress.setVisibility(View.GONE);
            }
        }


        if(requestCode == LANGUAGES_SPOKEN) {
            if(resultCode == Activity.RESULT_OK) {

                TextView langs_floatingtext = (TextView) findViewById(R.id.langs_floatingtext);
                langs_floatingtext.setText("Languages Spoken");

                String languagesspoken = data.getStringExtra("languagesspoken");
                if(languagesspoken.equals("")) {
                    display_languages.setText("Select Languages Spoken");
                    langs_floatingtext.setText("");
                }
                else display_languages.setText(languagesspoken);

            }
        }
    }




    //keyboard disappears when you click outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }

    }


    //datepicker
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        TextView dob_floatingtext = (TextView) findViewById(R.id.dob_floatingtext);
        dob_floatingtext.setText("Date of Birth");
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dob1.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }








    //Shared Preferences
    private void storeSPData(String key, String data) {

        SharedPreferences mUserData = this.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mUserEditor = mUserData.edit();
        mUserEditor.putString(key, data);
        mUserEditor.commit();

    }

    private String getSPData(String key) {

        SharedPreferences mUserData = this.getSharedPreferences("UserData", MODE_PRIVATE);
        String data = mUserData.getString(key, "");

        return data;

    }

}

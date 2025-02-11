package com.twenty.four.crafts.registration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.squareup.picasso.Picasso;
import com.steelkiwi.cropiwa.image.CropIwaResultReceiver;
import com.twenty.four.crafts.CustomAdapterSpinner;
import com.twenty.four.crafts.LanguagesPopUp;
import com.twenty.four.crafts.Main2Activity;
import com.twenty.four.crafts.Main3Activity;
import com.twenty.four.crafts.MySingleton;
import com.twenty.four.crafts.R;
import com.twenty.four.crafts.User;
import com.twenty.four.crafts.app_startup.Login;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickClick;
import com.vansuita.pickimage.listeners.IPickResult;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.view.View.GONE;

public class signup extends AppCompatActivity implements IPickResult {

    private static final int LANGUAGES_SPOKEN = 25;
    private static final int REQUEST_IMAGE_LOAD = 5555;
    private static final int CHOOSE_CATEGORY = 12093;

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

    String name, selectedcraft = "null";


    //integrating logins
    String type;
    String firstname;
    String lastname;
    String email;
    String gender;
    String imgurl;

    EditText first_name1, last_name1, email1, password1, confirm_password1, gender1, dob1, residingin1, nativeplace1, languagesspoken1, whoami1, haspreviousexp1, whatpreviousexp1;
    CircleImageView profile_image1, edit_profile_btn;
    TextInputLayout input_firstname, input_lastname, input_email, input_password, input_confirmpassword, input_gender, input_dob, input_residingin, input_nativeplace, input_languagesspoken, input_whoami, input_haspreviousexp, input_whatpreviousexp;

    //for verification sake
    public int vfirst_name = 0, vlast_name = 0, vemail = 0, vpassword = 0, vconfirm_password = 0, vgender = 0, vdob = 0, vresidingin = 0, vnativeplace = 0, vlanguagesspoken = 0, vwhoami = 0, vhaspreviousexp = 0, vwhatpreviousexp = 0;

    //datepicker
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
        input_gender = (TextInputLayout) findViewById(R.id.input_gender);
        input_dob = (TextInputLayout) findViewById(R.id.input_dob);
        input_residingin = (TextInputLayout) findViewById(R.id.input_residingin);
        input_nativeplace = (TextInputLayout) findViewById(R.id.input_nativeplace);
        input_languagesspoken = (TextInputLayout) findViewById(R.id.input_languagesspoken);
        input_whoami = (TextInputLayout) findViewById(R.id.input_whoami);
        input_haspreviousexp = (TextInputLayout) findViewById(R.id.input_haspreviousexp);
        input_whatpreviousexp = (TextInputLayout) findViewById(R.id.input_whatpreviousexp); input_whatpreviousexp.setVisibility(View.GONE);


        profile_image1 = (CircleImageView) findViewById(R.id.profile_image);


        first_name1 = (EditText) findViewById(R.id.first_name);
        last_name1 = (EditText) findViewById(R.id.last_name);
        email1 = (EditText) findViewById(R.id.email);
        password1 = (EditText) findViewById(R.id.password);
        confirm_password1 = (EditText) findViewById(R.id.confirm_password);
        gender1 = (EditText) findViewById(R.id.gender); gender1.setShowSoftInputOnFocus(false); gender1.setInputType(InputType.TYPE_NULL);
        dob1 = (EditText) findViewById(R.id.dob); dob1.setShowSoftInputOnFocus(false); dob1.setInputType(InputType.TYPE_NULL);
        residingin1 = (EditText) findViewById(R.id.residingin); residingin1.setShowSoftInputOnFocus(false); residingin1.setInputType(InputType.TYPE_NULL);
        nativeplace1 = (EditText) findViewById(R.id.nativeplace); nativeplace1.setShowSoftInputOnFocus(false); nativeplace1.setInputType(InputType.TYPE_NULL);
        languagesspoken1 = (EditText) findViewById(R.id.languagesspoken); languagesspoken1.setShowSoftInputOnFocus(false); languagesspoken1.setInputType(InputType.TYPE_NULL);
        whoami1 = (EditText) findViewById(R.id.whoami); whoami1.setShowSoftInputOnFocus(false); whoami1.setInputType(InputType.TYPE_NULL);
        haspreviousexp1 = (EditText) findViewById(R.id.haspreviousexp); haspreviousexp1.setShowSoftInputOnFocus(false); haspreviousexp1.setInputType(InputType.TYPE_NULL);
        whatpreviousexp1 = (EditText) findViewById(R.id.whatpreviousexp); whatpreviousexp1.setShowSoftInputOnFocus(false); whatpreviousexp1.setInputType(InputType.TYPE_NULL);


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


        first_name1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vfirst_name != 0) {
                    vfirst_name = 0;
                    input_firstname.setError(null);
                    input_firstname.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vfirst_name != 0) {
                    vfirst_name = 0;
                    input_firstname.setError(null);
                    input_firstname.setErrorEnabled(false);
                }
            }
        });

        last_name1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vlast_name != 0) {
                    vlast_name = 0;
                    input_lastname.setError(null);
                    input_lastname.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vlast_name != 0) {
                    vlast_name = 0;
                    input_lastname.setError(null);
                    input_lastname.setErrorEnabled(false);
                }
            }
        });

        email1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vemail != 0) {
                    vemail = 0;
                    input_email.setError(null);
                    input_email.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vemail != 0) {
                    vemail = 0;
                    input_email.setError(null);
                    input_email.setErrorEnabled(false);
                }


                if(isEmailValid(s.toString())) {
                    if(isDomainValid(s.toString())) {
                        input_email.setError(null);
                        input_email.setErrorEnabled(false);
                    } else {
                        input_email.setErrorEnabled(true);
                        input_email.setError("Domain is invalid");
                    }
                } else {
                    input_email.setErrorEnabled(true);
                    input_email.setError("Invalid email format");
                }


            }
        });


        password1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vpassword != 0) {
                    vpassword = 0;
                    input_password.setError(null);
                    input_password.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vpassword != 0) {
                    vpassword = 0;
                    input_password.setError(null);
                    input_password.setErrorEnabled(false);
                }
            }
        });

        password1.setTransformationMethod(new PasswordTransformationMethod());
        confirm_password1.setTransformationMethod(new PasswordTransformationMethod());


        confirm_password1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(confirm_password1.getText().toString().equals("")) {
                    input_confirmpassword.setError(null);
                    input_confirmpassword.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!password1.getText().toString().equals("") && password1.getText().toString().equals(confirm_password1.getText().toString())) {
                    input_confirmpassword.setError(null);
                    input_confirmpassword.setErrorEnabled(false);
                }
                else {
                    input_confirmpassword.setErrorEnabled(true);
                    input_confirmpassword.setError("Passwords don't match");
                }

                if(confirm_password1.getText().toString().equals("")) {
                    input_confirmpassword.setError(null);
                    input_confirmpassword.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!password1.getText().toString().equals("") && password1.getText().toString().equals(confirm_password1.getText().toString())) {
                    input_confirmpassword.setError(null);
                    input_confirmpassword.setErrorEnabled(false);
                }
                else {
                    input_confirmpassword.setErrorEnabled(true);
                    input_confirmpassword.setError("Passwords don't match");
                }

                if(confirm_password1.getText().toString().equals("")) {
                    input_confirmpassword.setError(null);
                    input_confirmpassword.setErrorEnabled(false);
                }
            }
        });



        gender1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    final CharSequence[] items = { "Male", "Female", "Other" };

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup.this,R.style.AlertDialogSignup));
                    alertDialogBuilder.setTitle("Choose Gender");
                    int position;
                    if (gender1.getText().toString().equals("Male")) {
                        position = 0;
                    } else if (gender1.getText().toString().equals("Female")) {
                        position = 1;
                    } else if (gender1.getText().toString().equals("Other")) {
                        position = 2;
                    }
                    else {
                        position = -1;
                    }
                    alertDialogBuilder.setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ListView lw = ((AlertDialog) dialog).getListView();
                                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                    String selectedgend = checkedItem.toString();
                                    gender1.setText(selectedgend);
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });

        gender1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final CharSequence[] items = { "Male", "Female", "Other" };

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup.this,R.style.AlertDialogSignup));
                alertDialogBuilder.setTitle("Choose Gender");
                int position;
                if (gender1.getText().toString().equals("Male")){
                    position = 0;
                } else if (gender1.getText().toString().equals("Female")){
                    position = 1;
                } else if (gender1.getText().toString().equals("Other")){
                    position = 2;
                }
                else {
                    position = -1;
                }
                alertDialogBuilder
                        .setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                String selectedgend = checkedItem.toString();
                                gender1.setText(selectedgend);
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        gender1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vgender != 0) {
                    vgender = 0;
                    input_gender.setError(null);
                    input_gender.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vgender != 0) {
                    vgender = 0;
                    input_gender.setError(null);
                    input_gender.setErrorEnabled(false);
                }
            }
        });



        dob1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) showDialog(999);
            }
        });

        dob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });

        dob1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vdob != 0) {
                    vdob = 0;
                    input_dob.setError(null);
                    input_dob.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vdob != 0) {
                    vdob = 0;
                    input_dob.setError(null);
                    input_dob.setErrorEnabled(false);
                }
            }
        });


        residingin1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    try {
                        Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(signup.this);
                        startActivityForResult(intent, 1000);

                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        residingin1.setOnClickListener(new View.OnClickListener() {
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



        residingin1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vresidingin != 0) {
                    vresidingin = 0;
                    input_residingin.setError(null);
                    input_residingin.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vresidingin != 0) {
                    vresidingin = 0;
                    input_residingin.setError(null);
                    input_residingin.setErrorEnabled(false);
                }
            }
        });


        nativeplace1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    try {
                        Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(signup.this);
                        startActivityForResult(intent, 2000);

                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        nativeplace1.setOnClickListener(new View.OnClickListener() {
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

        nativeplace1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vnativeplace != 0) {
                    vnativeplace = 0;
                    input_nativeplace.setError(null);
                    input_nativeplace.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vnativeplace != 0) {
                    vnativeplace = 0;
                    input_nativeplace.setError(null);
                    input_nativeplace.setErrorEnabled(false);
                }
            }
        });

        languagesspoken1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Intent i = new Intent(getApplicationContext(),LanguagesPopUp.class).putExtra("languagesspoken", User.getInstance().languagesspokendirty);
                    startActivityForResult(i,LANGUAGES_SPOKEN);
                }
            }
        });


        languagesspoken1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LanguagesPopUp.class).putExtra("languagesspoken", User.getInstance().languagesspokendirty);
                startActivityForResult(i, LANGUAGES_SPOKEN);
            }
        });

        languagesspoken1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vlanguagesspoken != 0) {
                    vlanguagesspoken = 0;
                    input_languagesspoken.setError(null);
                    input_languagesspoken.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vlanguagesspoken != 0) {
                    vlanguagesspoken = 0;
                    input_languagesspoken.setError(null);
                    input_languagesspoken.setErrorEnabled(false);
                }
            }
        });


        whoami1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Intent intnt = new Intent(getApplicationContext(), ChooseCraftOrClient.class).putExtra("category", type);
                    startActivityForResult(intnt, CHOOSE_CATEGORY);
                }
            }
        });

        whoami1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(getApplicationContext(), ChooseCraftOrClient.class).putExtra("category", type);
                startActivityForResult(intnt, CHOOSE_CATEGORY);
            }
        });

        whoami1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vwhoami != 0) {
                    vwhoami = 0;
                    input_whoami.setError(null);
                    input_whoami.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vwhoami != 0) {
                    vwhoami = 0;
                    input_whoami.setError(null);
                    input_whoami.setErrorEnabled(false);
                }
            }
        });



        haspreviousexp1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    final CharSequence[] items = { "Yes, I do", "No, I don't" };

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup.this,R.style.AlertDialogSignup));
                    alertDialogBuilder.setTitle("Do you have previous experience?");
                    int position;
                    if (haspreviousexp1.getText().toString().equals("Yes, I do")) {
                        position = 0;
                    } else if (haspreviousexp1.getText().toString().equals("No, I don't")) {
                        position = 1;
                    } else {
                        position = -1;
                    }

                    alertDialogBuilder.setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ListView lw = ((AlertDialog) dialog).getListView();
                            Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                            String selectedexp = checkedItem.toString();
                            haspreviousexp1.setText(selectedexp);
                            dialog.dismiss();
                        }
                    });

                    alertDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if(haspreviousexp1.getText().toString().equals("Yes, I do")) {
                                input_whatpreviousexp.setVisibility(View.VISIBLE);
                                whatpreviousexp1.setVisibility(View.VISIBLE);
                            } else if(haspreviousexp1.getText().toString().equals("No, I don't")) {
                                input_whatpreviousexp.setVisibility(View.GONE);
                                whatpreviousexp1.setVisibility(View.GONE);
                            }
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });

        haspreviousexp1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final CharSequence[] items = { "Yes, I do", "No, I don't" };

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup.this,R.style.AlertDialogSignup));
                alertDialogBuilder.setTitle("Do you have previous experience?");
                int position;
                if (haspreviousexp1.getText().toString().equals("Yes, I do")) {
                    position = 0;
                } else if (haspreviousexp1.getText().toString().equals("No, I don't")) {
                    position = 1;
                } else {
                    position = -1;
                }

                alertDialogBuilder.setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListView lw = ((AlertDialog) dialog).getListView();
                        Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                        String selectedexp = checkedItem.toString();
                        haspreviousexp1.setText(selectedexp);
                        dialog.dismiss();
                    }
                });

                alertDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(haspreviousexp1.getText().toString().equals("Yes, I do")) {
                            input_whatpreviousexp.setVisibility(View.VISIBLE);
                            whatpreviousexp1.setVisibility(View.VISIBLE);
                        } else if(haspreviousexp1.getText().toString().equals("No, I don't")) {
                            input_whatpreviousexp.setVisibility(View.GONE);
                            whatpreviousexp1.setVisibility(View.GONE);
                        }
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        haspreviousexp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vhaspreviousexp != 0) {
                    vhaspreviousexp = 0;
                    input_haspreviousexp.setError(null);
                    input_haspreviousexp.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vhaspreviousexp != 0) {
                    vhaspreviousexp = 0;
                    input_haspreviousexp.setError(null);
                    input_haspreviousexp.setErrorEnabled(false);
                }
            }
        });




        ///////
        whatpreviousexp1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    final CharSequence[] items = { "Feature Films", "Tv Shows", "Short Films", "College Culturals", "Web Series", "YouTube Channel", "Other" };

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup.this, R.style.AlertDialogSignup));
                    alertDialogBuilder.setTitle("What have you had an experience in?");
                    int position;
                    if(whatpreviousexp1.getText().toString().equals("Feature Films")){
                        position = 0;
                    }
                    else if(whatpreviousexp1.getText().toString().equals("Tv Shows")){
                        position = 1;
                    }
                    else if(whatpreviousexp1.getText().toString().equals("Short Films")){
                        position = 2;
                    }
                    else if(whatpreviousexp1.getText().toString().equals("College Culturals")){
                        position = 3;
                    }
                    else if(whatpreviousexp1.getText().toString().equals("Web Series")){
                        position = 4;
                    }
                    else if(whatpreviousexp1.getText().toString().equals("YouTube Channel")){
                        position = 5;
                    }
                    else if(whatpreviousexp1.getText().toString().equals("Other")){
                        position = 6;
                    }
                    else {
                        position = -1;
                    }
                    alertDialogBuilder.setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ListView lw = ((AlertDialog) dialog).getListView();
                            Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                            String selectedexp = checkedItem.toString();
                            whatpreviousexp1.setText(selectedexp);
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });

        whatpreviousexp1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final CharSequence[] items = { "Feature Films", "Tv Shows", "Short Films", "College Culturals", "Web Series", "YouTube Channel", "Other" };

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(signup.this,R.style.AlertDialogSignup));
                alertDialogBuilder.setTitle("What have you had an experience in?");
                int position;
                if(whatpreviousexp1.getText().toString().equals("Feature Films")){
                    position = 0;
                }
                else if(whatpreviousexp1.getText().toString().equals("Tv Shows")){
                    position = 1;
                }
                else if(whatpreviousexp1.getText().toString().equals("Short Films")){
                    position = 2;
                }
                else if(whatpreviousexp1.getText().toString().equals("College Culturals")){
                    position = 3;
                }
                else if(whatpreviousexp1.getText().toString().equals("Web Series")){
                    position = 4;
                }
                else if(whatpreviousexp1.getText().toString().equals("YouTube Channel")){
                    position = 5;
                }
                else if(whatpreviousexp1.getText().toString().equals("Other")){
                    position = 6;
                }
                else {
                    position = -1;
                }
                alertDialogBuilder.setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListView lw = ((AlertDialog) dialog).getListView();
                        Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                        String selectedexp = checkedItem.toString();
                        whatpreviousexp1.setText(selectedexp);
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        whatpreviousexp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(vwhatpreviousexp != 0) {
                    vwhatpreviousexp = 0;
                    input_whatpreviousexp.setError(null);
                    input_whatpreviousexp.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(vwhatpreviousexp != 0) {
                    vwhatpreviousexp = 0;
                    input_whatpreviousexp.setError(null);
                    input_whatpreviousexp.setErrorEnabled(false);
                }
            }
        });












        if(!firstname.equals("null")) {

            first_name1.setText(firstname);
            last_name1.setText(lastname);
            email1.setText(email);

            if(!imgurl.equals("null")) Picasso.with(getApplicationContext()).load(imgurl).into(profile_image1);
            else profile_image1.setImageResource(R.drawable.com_facebook_profile_picture_blank_square);

            if(gender.equals("male")) gender1.setText("Male");
            else if(gender.equals("female")) gender1.setText("Female");
        }


        Button next = (Button) findViewById(R.id.button11);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check for empty fields
                if(first_name1.getText().toString().equals("")) {
                    input_firstname.setErrorEnabled(true);
                    input_firstname.setError("Enter your first name.");
                    vfirst_name = 1;
                } else {
                    input_firstname.setError(null);
                    input_firstname.setErrorEnabled(false);
                }

                if(last_name1.getText().toString().equals("")) {
                    input_lastname.setErrorEnabled(true);
                    input_lastname.setError("Enter your last name.");
                    vlast_name = 1;
                } else {
                    input_lastname.setError(null);
                    input_lastname.setErrorEnabled(false);
                }

                if(email1.getText().toString().equals("")) {
                    input_email.setErrorEnabled(true);
                    input_email.setError("Enter your email.");
                    vemail = 1;
                } else {
                    input_email.setError(null);
                    input_email.setErrorEnabled(false);
                }

                if(password1.getText().toString().equals("")) {
                    input_password.setErrorEnabled(true);
                    input_password.setError("Enter your password.");
                    vpassword = 1;
                } else {
                    input_password.setError(null);
                    input_password.setErrorEnabled(false);
                }

                if(confirm_password1.getText().toString().equals("")) {
                    input_confirmpassword.setErrorEnabled(true);
                    input_confirmpassword.setError("Confirm the password.");
                    vconfirm_password = 1;
                } else {
                    input_confirmpassword.setError(null);
                    input_confirmpassword.setErrorEnabled(false);
                }

                if(gender1.getText().toString().equals("")) {
                    input_gender.setErrorEnabled(true);
                    input_gender.setError("Select your Gender");
                    vgender = 1;
                } else {
                    input_gender.setError(null);
                    input_gender.setErrorEnabled(false);
                }

                if(dob1.getText().toString().equals("")) {
                    input_dob.setErrorEnabled(true);
                    input_dob.setError("Select your Date of Birth");
                    vdob = 1;
                } else {
                    input_dob.setError(null);
                    input_dob.setErrorEnabled(false);
                }

                if(residingin1.getText().toString().equals("")) {
                    input_residingin.setErrorEnabled(true);
                    input_residingin.setError("Select your Residing In");
                    vresidingin = 1;
                } else {
                    input_residingin.setError(null);
                    input_residingin.setErrorEnabled(false);
                }

                if(nativeplace1.getText().toString().equals("")) {
                    input_nativeplace.setErrorEnabled(true);
                    input_nativeplace.setError("Select your Native Place");
                    vnativeplace = 1;
                } else {
                    input_nativeplace.setError(null);
                    input_nativeplace.setErrorEnabled(false);
                }

                if(languagesspoken1.getText().toString().equals("")) {
                    input_languagesspoken.setErrorEnabled(true);
                    input_languagesspoken.setError("Select your Languages Spoken");
                    vlanguagesspoken = 1;
                } else {
                    input_languagesspoken.setError(null);
                    input_languagesspoken.setErrorEnabled(false);
                }

                if(whoami1.getText().toString().equals("")) {
                    input_whoami.setErrorEnabled(true);
                    input_whoami.setError("Select your Category");
                    vwhoami = 1;
                } else {
                    input_whoami.setError(null);
                    input_whoami.setErrorEnabled(false);
                }

                if(haspreviousexp1.getText().toString().equals("")) {
                    input_haspreviousexp.setErrorEnabled(true);
                    input_haspreviousexp.setError("Mention your Experience");
                    vhaspreviousexp = 1;
                } else {
                    input_haspreviousexp.setError(null);
                    input_haspreviousexp.setErrorEnabled(false);
                }

                if(whatpreviousexp1.getVisibility() == View.VISIBLE){
                    if(whatpreviousexp1.getText().toString().equals("")) {
                        input_whatpreviousexp.setErrorEnabled(true);
                        input_whatpreviousexp.setError("Enter details of Experience");
                        vwhatpreviousexp = 1;
                    } else {
                        input_whatpreviousexp.setError(null);
                        input_whatpreviousexp.setErrorEnabled(false);
                    }
                }
                //check for empty fields


                if(!first_name1.getText().toString().equals("") && !last_name1.getText().toString().equals("") && !email1.getText().toString().equals("") && !password1.getText().toString().equals("") && !confirm_password1.getText().toString().equals("") && !gender1.getText().toString().equals("") && !dob1.getText().toString().equals("") && !residingin1.getText().toString().equals("") && !nativeplace1.getText().toString().equals("") && !languagesspoken1.getText().toString().equals("") && !whoami1.getText().toString().equals("") && !haspreviousexp1.getText().toString().equals(""))
                {
                    final ProgressBar pb = new ProgressBar(signup.this);
                    pb.setIndeterminate(true);
                    pb.setVisibility(pb.VISIBLE);

                     if(isEmailValid(email1.getText().toString())) {

                         if(isDomainValid(email1.getText().toString())) {



                             StringRequest stringRequest = new StringRequest(Request.Method.POST, User.getInstance().BASE_URL + "login" , new Response.Listener<String>() {
                                 @Override
                                 public void onResponse(String response) {

                                     try {

                                         JSONObject jsonObject = new JSONObject(response);

                                         if(jsonObject.optString("message").equals("Incorrect password.") || !jsonObject.optString("token").equals("")) {

                                             pb.setVisibility(pb.GONE);

                                             AlertDialog alert = new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(signup.this, R.style.AlertDialog))
                                                     .setMessage("This email has already been registered.")
                                                     .setCancelable(false)
                                                     .setPositiveButton("Redirect to login", new DialogInterface.OnClickListener() {
                                                         public void onClick(DialogInterface dialog, int which) {
                                                             Intent i = new Intent(signup.this, Login.class);
                                                             startActivity(i);
                                                         }
                                                     })
                                                     .setNegativeButton("Change email", new DialogInterface.OnClickListener() {
                                                         public void onClick(DialogInterface dialog, int which) {
                                                             dialog.dismiss();
                                                         }
                                                     })
                                                     .show();
                                             alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                                         } else {

                                             pb.setVisibility(pb.GONE);



                                             if(confirm_password1.getText().toString().equals(password1.getText().toString())) {

                                                 if (type.equals("craftsman")) {

                                                     if (selectedcraft.equals("Actor") || selectedcraft.equals("Actress") || selectedcraft.equals("Child Artist") || selectedcraft.equals("Dancer") || selectedcraft.equals("Side Artists")) {

                                                         storeSPData("firstname", first_name1.getText().toString());
                                                         storeSPData("lastname", last_name1.getText().toString());
                                                         storeSPData("email", email1.getText().toString());
                                                         storeSPData("password", password1.getText().toString());
                                                         storeSPData("dob", dob1.getText().toString());
                                                         storeSPData("gender", gender1.getText().toString());
                                                         if(haspreviousexp1.getText().toString().equals("Yes, I do")) {
                                                             storeSPData("hasPrevExp", "true");
                                                             storeSPData("prevExp", whatpreviousexp1.getText().toString());
                                                         }
                                                         else {
                                                             storeSPData("hasPrevExp", "false");
                                                             storeSPData("prevExp", "Other");
                                                         }

                                                         //for auto-login
                                                         storeSPData("uname", email1.getText().toString());
                                                         storeSPData("pword", password1.getText().toString());

                                                         name = first_name1.getText().toString();
                                                         Intent goToNextActivity = new Intent(getApplicationContext(), signup2.class);
                                                         Bundle bundle = new Bundle();
                                                         bundle.putString("name", name);
                                                         bundle.putString("craft", selectedcraft);
                                                         bundle.putString("type", type);
                                                         goToNextActivity.putExtras(bundle);
                                                         startActivity(goToNextActivity);

                                                     } else {
                                                         storeSPData("firstname", first_name1.getText().toString());
                                                         storeSPData("lastname", last_name1.getText().toString());
                                                         storeSPData("email", email1.getText().toString());
                                                         storeSPData("password", password1.getText().toString());
                                                         storeSPData("dob", dob1.getText().toString());
                                                         storeSPData("gender", gender1.getText().toString());
                                                         if(haspreviousexp1.getText().toString().equals("Yes, I do")) {
                                                             storeSPData("hasPrevExp", "true");
                                                             storeSPData("prevExp", whatpreviousexp1.getText().toString());
                                                         }
                                                         else {
                                                             storeSPData("hasPrevExp", "false");
                                                             storeSPData("prevExp", "Other");
                                                         }

                                                         //for auto-login
                                                         storeSPData("uname", email1.getText().toString());
                                                         storeSPData("pword", password1.getText().toString());


                                                         name = first_name1.getText().toString();
                                                         Intent goToNextActivity = new Intent(getApplicationContext(), signup3.class);
                                                         Bundle bundle = new Bundle();
                                                         bundle.putString("name", name);
                                                         bundle.putString("craft", selectedcraft);
                                                         bundle.putString("type", type);
                                                         goToNextActivity.putExtras(bundle);
                                                         startActivity(goToNextActivity);
                                                     }


                                                 } else if (type.equals("client")) {

                                                     storeSPData("firstname", first_name1.getText().toString());
                                                     storeSPData("lastname", last_name1.getText().toString());
                                                     storeSPData("email", email1.getText().toString());
                                                     storeSPData("password", password1.getText().toString());
                                                     storeSPData("dob", dob1.getText().toString());
                                                     storeSPData("gender", gender1.getText().toString());
                                                     if(haspreviousexp1.getText().toString().equals("Yes, I do")) {
                                                         storeSPData("hasPrevExp", "true");
                                                         storeSPData("prevExp", whatpreviousexp1.getText().toString());
                                                     }
                                                     else {
                                                         storeSPData("hasPrevExp", "false");
                                                         storeSPData("prevExp", "Other");
                                                     }

                                                     //for auto-login
                                                     storeSPData("uname", email1.getText().toString());
                                                     storeSPData("pword", password1.getText().toString());

                                                     name = first_name1.getText().toString();
                                                     Intent goToNextActivity = new Intent(getApplicationContext(), signup3.class);
                                                     Bundle bundle = new Bundle();
                                                     bundle.putString("name", name);
                                                     bundle.putString("craft", selectedcraft);
                                                     bundle.putString("type", type);
                                                     goToNextActivity.putExtras(bundle);
                                                     startActivity(goToNextActivity);

                                                 }

                                             }

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
                                 protected Map<String, String> getParams() throws AuthFailureError {

                                     Map<String, String> params = new HashMap<String, String>();

                                     params.put("username", email1.getText().toString());
                                     params.put("password", password1.getText().toString());

                                     return params;
                                 }
                             };

                             MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);




                         } else {
                             pb.setVisibility(pb.GONE);
                             input_email.setErrorEnabled(true);
                             input_email.setError("Domain is invalid");
                         }

                     } else {
                         pb.setVisibility(pb.GONE);
                         input_email.setErrorEnabled(true);
                         input_email.setError("Invalid email format");
                     }

                }

                //

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

                residingin1.setText("Residing In");

                Place place = PlaceAutocomplete.getPlace(this, data);
                residingin1.setText(place.getName());
                storeSPData("residingIn", place.getName().toString());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == 2000) {
            if (resultCode == RESULT_OK) {

                nativeplace1.setText("Native (Hometown)");

                Place place = PlaceAutocomplete.getPlace(this, data);
                nativeplace1.setText(place.getName());
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


        if(requestCode == CHOOSE_CATEGORY) {
            if(resultCode == Activity.RESULT_OK) {

                whoami1.setText("Who am I?");

                String chosencategory = data.getStringExtra("selectedcategory");
                if(chosencategory.equals("")) {
                    whoami1.setText("Who am I?");
                    whoami1.setText("");
                } else {

                    switch(chosencategory) {
                        //craftsmen
                        case "Actor": selectedcraft = "Actor"; break;
                        case "Actress": selectedcraft = "Actress"; break;
                        case "Childartist": selectedcraft = "Child Artist"; break;
                        case "Singer": selectedcraft = "Singer"; break;
                        case "Dancer": selectedcraft = "Dancer"; break;
                        case "Sideartist": selectedcraft = "Side Artist"; break;
                        case "Assistantdirector": selectedcraft = "Assistant Director"; break;
                        case "Lyricwriter": selectedcraft = "Lyric Writer / Lyricist"; break;
                        case "Dialoguewriter": selectedcraft = "Dialouge Writer"; break;
                        case "Scriptwriter": selectedcraft = "Script / Screenplay Writers"; break;
                        case "Storyboardartist": selectedcraft = "Story Board Artist"; break;
                        case "Choreographer": selectedcraft = "Choreographer"; break;
                        case "Directorofphotography": selectedcraft = "Director of Photography"; break;
                        case "Stillphotographer": selectedcraft = "Still Photographer"; break;
                        case "Pro": selectedcraft = "PRO"; break;
                        case "Designer": selectedcraft = "Designer"; break;
                        case "Productionmanager": selectedcraft = "Production Manager"; break;
                        case "Focuspuller": selectedcraft = "Focus Puller"; break;
                        case "Vehicledriver": selectedcraft = "Vehicle Driver"; break;
                        case "Micdepartment": selectedcraft = "Mic Department"; break;
                        case "Musicdirector": selectedcraft = "Music Director"; break;
                        case "Makeupman": selectedcraft = "Make-up Man"; break;
                        case "Hairdresser": selectedcraft = "Hair Dresser"; break;
                        case "Costumer": selectedcraft = "Costumer"; break;
                        case "Artdepartment": selectedcraft = "Art Department"; break;
                        case "Setdepartment": selectedcraft = "Set Department"; break;
                        case "Stuntman": selectedcraft = "Stuntman"; break;
                        case "Editor": selectedcraft = "Editor"; break;
                        case "Locationmanager": selectedcraft = "Location Manager"; break;
                        case "Productionfood": selectedcraft = "Production (Food)"; break;
                        case "Dubbingartist": selectedcraft = "Dubbing Artist"; break;
                        case "Soundrecordingengineer": selectedcraft = "Sound Recording Engineer"; break;
                        case "Soundmixingengineer": selectedcraft = "Sound Mixing Engineer"; break;
                        case "Di": selectedcraft = "Digital Intermediate"; break;
                        case "Vfx": selectedcraft = "VFX / CG"; break;
                        case "Sfx": selectedcraft = "SFX"; break;
                        case "Petsupplier": selectedcraft = "Pet Supplier / Pet Doctor / AWBI Certifications"; break;

                        //client
                        case "Castingagent": selectedcraft = "Casting Agent"; break;
                        case "Codirector": selectedcraft = "Co-Director"; break;
                        case "Coproducer": selectedcraft = "Co-Producer"; break;
                        case "Director": selectedcraft = "Director"; break;
                        case "Directorassistant": selectedcraft = "Director Assistant"; break;
                        case "Executiveproducer": selectedcraft = "Executive Producer"; break;
                        case "Modelcoordinator": selectedcraft = "Model Coordinator"; break;
                        case "Producer": selectedcraft = "Producer"; break;
                        case "Productionhousemanager": selectedcraft = "Production House Manager"; break;
                    }

                    whoami1.setText(selectedcraft);
                }

            }
        }


        if(requestCode == LANGUAGES_SPOKEN) {
            if(resultCode == Activity.RESULT_OK) {

                languagesspoken1.setText("Languages Spoken");

                String languagesspoken = data.getStringExtra("languagesspoken");
                if(languagesspoken.equals("")) {
                    languagesspoken1.setText("Select Languages Spoken");
                    languagesspoken1.setText("");
                }

                else languagesspoken1.setText(languagesspoken);

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
    @Override
    protected Dialog onCreateDialog(int id) {
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
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dob1.setText(new StringBuilder().append(month).append("/").append(day).append("/").append(year));
    }






    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean isDomainValid(String email) {
        boolean valid = true;
        String domain = email.substring(email.indexOf("@") + 1);

        for(int i=0; i<restricteddomains.length ; i++) {
            if(restricteddomains[i].equalsIgnoreCase(domain)) {
                valid = false;
                break;
            }
        }
        return valid;
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

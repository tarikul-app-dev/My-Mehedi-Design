package traintimingapp.planet.it.limited.mymehedidesign.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;
import traintimingapp.planet.it.limited.mymehedidesign.R;
import traintimingapp.planet.it.limited.mymehedidesign.adapter.CategoryAdapter;
import traintimingapp.planet.it.limited.mymehedidesign.utill.FontCustomization;
import traintimingapp.planet.it.limited.mymehedidesign.utill.LanguageUtility;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import static traintimingapp.planet.it.limited.mymehedidesign.utill.SaveLocalStorage.getValueFromSharedPreferences;
import static traintimingapp.planet.it.limited.mymehedidesign.utill.SaveLocalStorage.setValueToSharedPreferences;

public class MainActivity extends AppCompatActivity {
   Button btnAllDesign,btnMoreApps,btnRateMyApp;
    TextView txvMainPage;
    AlertDialog b;
    LanguageUtility languageUtility;
    Toolbar toolbar;
    FontCustomization fontCustomization;
   // String checkLan = "";
   InterstitialAd interstitial;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);

        initViews();
    }
    public void initViews(){
        btnAllDesign = (Button)findViewById(R.id.btn_all_design);
        btnMoreApps = (Button)findViewById(R.id.btn_more_apps);
        btnRateMyApp = (Button)findViewById(R.id.btn_rate);
        txvMainPage = (TextView) findViewById(R.id.txv_toolbar_head);

        languageUtility = new LanguageUtility(MainActivity.this);
      //  checkLan = getValueFromSharedPreferences("language",MainActivity.this);
        fontCustomization = new FontCustomization(MainActivity.this);
        btnAllDesign.setTypeface(fontCustomization.getTexgyreHerosBold());
        btnMoreApps.setTypeface(fontCustomization.getTexgyreHerosBold());
        btnRateMyApp.setTypeface(fontCustomization.getTexgyreHerosBold());
        txvMainPage.setTypeface(fontCustomization.getTexgyreHerosBold());

        AppRate.with(this)
                .setInstallDays(0) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10
                .setRemindInterval(2) // default 1
                .setShowLaterButton(true) // default true
                .setDebug(false) // default false
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .monitor();

        if(languageUtility.getSelectedLanguage() == ""){
            showChangeLangDialog();
        }


        btnAllDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AllListActivity.class);
                startActivity(intent);
            }
        });

        btnMoreApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Uri marketUri = Uri.parse("market://details?id=" + "com.tarikul.android.apps.my.myphotoframe");
                startActivity(new Intent(Intent.ACTION_VIEW, marketUri));
            }
        });

        btnRateMyApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a dialog if meets conditions
                AppRate.with(MainActivity.this).showRateDialog(MainActivity.this);
            }
        });

        toUseAdmobAdd();
    }

    public void toUseAdmobAdd(){
        interstitial = new InterstitialAd(MainActivity.this);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.intersitial_id));

        //Locate the Banner Ad in activity_main.xml
        AdView adView = (AdView) this.findViewById(R.id.banner_AdView);

        // Request for Ads
        AdRequest adRequest = new AdRequest.Builder().build();

        // Add a test device to show Test Ads

        requestNewInterstitial();

        // Load ads into Banner Ads
        adView.loadAd(adRequest);

        // Load ads into Interstitial Ads
        interstitial.loadAd(adRequest);

        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }


        });

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                //Test in Emulator by this code
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("B86BC9402A69B031A516BC57F7D3063F")
                //when ready skip this 2 line
                .build();

        interstitial.loadAd(adRequest);
    }
    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    //menu option



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Sudip: 20170212
        switch (item.getItemId()) {

            case R.id.menu_language:
                showChangeLangDialog();
                break;


            default:
                return super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }
    public void showChangeLangDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.language_dialog, null);
        dialogBuilder.setView(dialogView);

        final Spinner spinner1 = (Spinner) dialogView.findViewById(R.id.spinner1);

        dialogBuilder.setTitle(getResources().getString(R.string.lang_dialog_title));
        dialogBuilder.setMessage(getResources().getString(R.string.lang_dialog_message));
        dialogBuilder.setPositiveButton(R.string.alert_dialog_change_text, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                int langpos = spinner1.getSelectedItemPosition();
                switch (langpos) {
                    case 1: //bengali

                        languageUtility.selectLanguage("bn");
                        setValueToSharedPreferences("language","bn",MainActivity.this);
                        restartApp();

                        return;
                    case 2: //english

                        languageUtility.selectLanguage("en");
                        setValueToSharedPreferences("language","en",MainActivity.this);
                        restartApp();

                        return;

                    default: //By default set to english

                        languageUtility.selectLanguage("en");
                        setValueToSharedPreferences("language","en",MainActivity.this);
                        return;
                }
            }
        });
        dialogBuilder.setNegativeButton(R.string.alert_dialog_cancel_message, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        b = dialogBuilder.create();
        b.show();
    }

    public  void restartApp(){
        Intent i = getBaseContext().getPackageManager().
                getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }


}

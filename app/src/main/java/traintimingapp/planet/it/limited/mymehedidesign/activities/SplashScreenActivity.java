package traintimingapp.planet.it.limited.mymehedidesign.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import traintimingapp.planet.it.limited.mymehedidesign.R;
import traintimingapp.planet.it.limited.mymehedidesign.utill.LanguageUtility;

import static traintimingapp.planet.it.limited.mymehedidesign.utill.SaveLocalStorage.getValueFromSharedPreferences;

public class SplashScreenActivity extends AppCompatActivity {
    LanguageUtility languageUtility;
    String checkLan = "";
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        animation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.splash_blink);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(700);
        final ImageView splash = (ImageView) findViewById(R.id.img_splash);
        splash.startAnimation(animation);

        languageUtility = new LanguageUtility(SplashScreenActivity.this);
        checkLan = getValueFromSharedPreferences("language",SplashScreenActivity.this);
        if(checkLan!=null){
            if(checkLan.equals("en")){
                languageUtility.selectLanguage("en");

            }else {
                languageUtility.selectLanguage("bn");

            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                ActivityCompat.finishAffinity(SplashScreenActivity.this);
            }
        }, 2000);


    }
}

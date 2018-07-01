package traintimingapp.planet.it.limited.mymehedidesign.utill;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.view.ContextThemeWrapper;

import java.util.Locale;

public class LanguageUtility {
    Locale locale;
    ContextThemeWrapper ctxThemeWrapper;

    static String selectedLang = "";
    String sharedPreName = "abc";

    public LanguageUtility(ContextThemeWrapper c){
        ctxThemeWrapper = c;
    }
    public void selectLanguage(String langval){
        PreferenceManager.getDefaultSharedPreferences(ctxThemeWrapper.getApplicationContext()).edit().putString("LANG", "en").commit();

        Configuration config = ctxThemeWrapper.getBaseContext().getResources().getConfiguration();
        locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        // ctxThemeWrapper.getBaseContext().getResources().updateConfiguration(config, ctxThemeWrapper.getBaseContext().getResources().getDisplayMetrics());
        ctxThemeWrapper.getBaseContext().getResources().updateConfiguration(config, null);

        selectedLang = langval;
        SharedPreferences user = ctxThemeWrapper.getSharedPreferences(sharedPreName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();

        edit.putString("langSelection", selectedLang);
        edit.commit();
    }
    public String getSelectedLanguage(){
        SharedPreferences sharing =ctxThemeWrapper.getSharedPreferences(sharedPreName, Context.MODE_PRIVATE);
        String prefLang = sharing.getString("langSelection", "");
        return prefLang;

    }
    public void setDefaultLanguage(String langval) {
        selectedLang = getSelectedLanguage();
        if(selectedLang == "") {

            selectLanguage(langval);
        }
    }
} 

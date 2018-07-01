package traintimingapp.planet.it.limited.mymehedidesign.utill;

import android.content.Context;
import android.graphics.Typeface;

public class FontCustomization {
    public Typeface TexgyreHerosRegular;
    public Typeface TexgyreHerosBold;
    Context context;

    public FontCustomization(Context mContext){
        this.context = mContext;
        this.TexgyreHerosRegular = Typeface.createFromAsset(context.getAssets(),"rscaveman.ttf") ;
        this.TexgyreHerosBold = Typeface.createFromAsset(context.getAssets(),"rscavema.ttf") ;

    }
    public Typeface getTexgyreHerosRegular(){
        return TexgyreHerosRegular;
    }
    public Typeface getTexgyreHerosBold(){
        return TexgyreHerosBold;
    }
} 

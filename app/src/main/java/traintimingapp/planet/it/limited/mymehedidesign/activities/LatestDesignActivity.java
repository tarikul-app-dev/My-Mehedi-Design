package traintimingapp.planet.it.limited.mymehedidesign.activities;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import traintimingapp.planet.it.limited.mymehedidesign.R;
import traintimingapp.planet.it.limited.mymehedidesign.adapter.ImageAdapter;
import traintimingapp.planet.it.limited.mymehedidesign.utill.FontCustomization;

import static traintimingapp.planet.it.limited.mymehedidesign.utill.SaveLocalStorage.saveToSharedPreferences;

public class LatestDesignActivity extends AppCompatActivity {
    private GridView gdviewDesign=null;
    static Bitmap[] mThumbIds;
    private ArrayList<File> fl = new ArrayList<File>() ;
    ImageAdapter imageAdapter = null;
    Toolbar toolbar;
    FontCustomization fontCustomization;
    TextView txvToolbarHead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_design);
        toolbar = (Toolbar)findViewById(R.id.toolbar_latest_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        initViews();
    }
    public void initViews(){
        fontCustomization = new FontCustomization(LatestDesignActivity.this);
        txvToolbarHead =(TextView)findViewById(R.id.txv_toolbar_head);
        txvToolbarHead.setTypeface(fontCustomization.getTexgyreHerosBold());

        gdviewDesign = (GridView)findViewById(R.id.grid_latest_design);

        mThumbIds = new Bitmap[8];
        try
        {
            //these images are stored in the root of "assets"
            mThumbIds[0] =getBitmapFromAsset("latest01.jpg");
            mThumbIds[1] =getBitmapFromAsset("latest02.jpg");
            mThumbIds[2] =getBitmapFromAsset("latest03.jpg");
            mThumbIds[3] =getBitmapFromAsset("latest04.jpg");
            mThumbIds[4] =getBitmapFromAsset("latest05.jpg");
            mThumbIds[5] =getBitmapFromAsset("latest06.jpg");
            mThumbIds[6] =getBitmapFromAsset("latest07.jpg");
            mThumbIds[7] =getBitmapFromAsset("latest08.jpg");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        gdviewDesign.setAdapter(new ImageAdapter(LatestDesignActivity.this,mThumbIds));
        saveToSharedPreferences("from","latestdesign",LatestDesignActivity.this);

    }
    private Bitmap getBitmapFromAsset(String strName) throws IOException {
        AssetManager assetManager = this.getAssets();
        InputStream in = null;
        in = assetManager.open("latest/" + strName);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        in.close();

        return bitmap;

    }
}

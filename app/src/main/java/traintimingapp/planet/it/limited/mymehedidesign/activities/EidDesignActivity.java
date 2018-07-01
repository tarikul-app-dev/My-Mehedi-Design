package traintimingapp.planet.it.limited.mymehedidesign.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
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

public class EidDesignActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_eid_design);
        toolbar = (Toolbar)findViewById(R.id.toolbar_eid_activity);
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
        fontCustomization = new FontCustomization(EidDesignActivity.this);
        gdviewDesign = (GridView)findViewById(R.id.grid_eid_design);
        txvToolbarHead =(TextView)findViewById(R.id.txv_toolbar_head);
        txvToolbarHead.setTypeface(fontCustomization.getTexgyreHerosBold());

        mThumbIds = new Bitmap[8];
        try
        {
            //these images are stored in the root of "assets"
            mThumbIds[0] =getBitmapFromAsset("eid01.jpg");
            mThumbIds[1] =getBitmapFromAsset("eid02.jpg");
            mThumbIds[2] =getBitmapFromAsset("eid03.jpeg");
            mThumbIds[3] =getBitmapFromAsset("eid04.jpg");
            mThumbIds[4] =getBitmapFromAsset("eid05.jpg");
            mThumbIds[5] =getBitmapFromAsset("eid06.jpg");
            mThumbIds[6] =getBitmapFromAsset("eid07.jpg");
            mThumbIds[7] =getBitmapFromAsset("eid08.jpg");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        gdviewDesign.setAdapter(new ImageAdapter(EidDesignActivity.this,mThumbIds));
        saveToSharedPreferences("from","eiddesign",EidDesignActivity.this);

    }
    private Bitmap getBitmapFromAsset(String strName) throws IOException {
        AssetManager assetManager = this.getAssets();
        InputStream in = null;
        in = assetManager.open("eid/" + strName);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        in.close();

        return bitmap;

    }
}

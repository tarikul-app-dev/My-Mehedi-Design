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

public class LegDesignActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_leg_design);
        toolbar = (Toolbar)findViewById(R.id.toolbar_leg_activity);
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
        fontCustomization = new FontCustomization(LegDesignActivity.this);
        txvToolbarHead =(TextView)findViewById(R.id.txv_toolbar_head);
        txvToolbarHead.setTypeface(fontCustomization.getTexgyreHerosBold());

        gdviewDesign = (GridView)findViewById(R.id.grid_leg_design);

        mThumbIds = new Bitmap[6];
        try
        {
            //these images are stored in the root of "assets"
            mThumbIds[0] =getBitmapFromAsset("leg01.jpg");
            mThumbIds[1] =getBitmapFromAsset("leg02.jpg");
            mThumbIds[2] =getBitmapFromAsset("leg03.jpg");
            mThumbIds[3] =getBitmapFromAsset("leg04.jpg");
            mThumbIds[4] =getBitmapFromAsset("leg05.jpg");
            mThumbIds[5] =getBitmapFromAsset("leg06.jpg");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        gdviewDesign.setAdapter(new ImageAdapter(LegDesignActivity.this,mThumbIds));
        saveToSharedPreferences("from","legdesign",LegDesignActivity.this);

    }
    private Bitmap getBitmapFromAsset(String strName) throws IOException {
        AssetManager assetManager = this.getAssets();
        InputStream in = null;
        in = assetManager.open("leg/" + strName);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        in.close();

        return bitmap;

    }
}

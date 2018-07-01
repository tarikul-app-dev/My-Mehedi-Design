package traintimingapp.planet.it.limited.mymehedidesign.activities;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import traintimingapp.planet.it.limited.mymehedidesign.R;
import traintimingapp.planet.it.limited.mymehedidesign.adapter.ImageAdapter;
import traintimingapp.planet.it.limited.mymehedidesign.utill.FontCustomization;

import static traintimingapp.planet.it.limited.mymehedidesign.utill.SaveLocalStorage.saveToSharedPreferences;

public class WeedingDesignActivity extends AppCompatActivity {
    private GridView gdviewDesign=null;
    static Bitmap[] mThumbIds;
    private ArrayList<File> designList = new ArrayList<File>() ;
    ImageAdapter imageAdapter = null;
    String[] Designs = {"weed01.jpg","weed02.jpg","weed03.jpg"};
    Toolbar toolbar;
    FontCustomization fontCustomization;
    TextView txvToolbarHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeding_design);
        toolbar = (Toolbar)findViewById(R.id.toolbar_weeding_activity);
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
        fontCustomization = new FontCustomization(WeedingDesignActivity.this);
        txvToolbarHead =(TextView)findViewById(R.id.txv_toolbar_head);
        txvToolbarHead.setTypeface(fontCustomization.getTexgyreHerosBold());

        gdviewDesign = (GridView)findViewById(R.id.grid_weed_design);

        mThumbIds = new Bitmap[7];
        try
        {
            //these images are stored in the root of "assets"
            mThumbIds[0] =getBitmapFromAsset("weed01.jpg");
            mThumbIds[1] =getBitmapFromAsset("weed02.jpg");
            mThumbIds[2] =getBitmapFromAsset("weed03.jpg");

            mThumbIds[3] =getBitmapFromAsset("weed04.jpg");
            mThumbIds[4] =getBitmapFromAsset("weed05.jpg");
            mThumbIds[5] =getBitmapFromAsset("weed06.jpg");
            mThumbIds[6] =getBitmapFromAsset("weed07.jpg");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        gdviewDesign.setAdapter(new ImageAdapter(WeedingDesignActivity.this,mThumbIds));
        saveToSharedPreferences("from","weeddesign",WeedingDesignActivity.this);

    }
    private Bitmap getBitmapFromAsset(String strName) throws IOException {
        AssetManager assetManager = this.getAssets();
        InputStream in = null;
        in = assetManager.open("weeding/" + strName);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        in.close();

        return bitmap;

    }
}

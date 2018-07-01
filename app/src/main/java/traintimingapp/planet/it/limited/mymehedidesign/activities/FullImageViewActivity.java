package traintimingapp.planet.it.limited.mymehedidesign.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import traintimingapp.planet.it.limited.mymehedidesign.R;
import traintimingapp.planet.it.limited.mymehedidesign.adapter.ImageAdapter;
import traintimingapp.planet.it.limited.mymehedidesign.utill.TouchImageView;

import static traintimingapp.planet.it.limited.mymehedidesign.utill.SaveLocalStorage.getValueFromSharedPreferences;

public class FullImageViewActivity extends AppCompatActivity {
    TouchImageView imgvFull;
    String fromActivity = "";
    Button btnSavePic,btnSharePic;
    ImageAdapter imageAdapter;
    String imageFilePath = " ";
    final private int REQUEST_CODE_WRITE_STORAGE = 1;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);
        initViews();
    }

    public void initViews(){
        btnSavePic = (Button)findViewById(R.id.btn_save_pic);
        btnSharePic = (Button)findViewById(R.id.btn_share_pic);

        animation = AnimationUtils.loadAnimation(FullImageViewActivity.this, R.anim.splash_blink);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(700);
       // final ImageView splash = (ImageView) findViewById(R.id.img_splash);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        final int position = i.getExtras().getInt("id");
        imgvFull = (TouchImageView) findViewById(R.id.full_image_view);
        imgvFull.startAnimation(animation);

        fromActivity = getValueFromSharedPreferences("from",FullImageViewActivity.this);

        if(fromActivity.equals("eiddesign")){
            imageAdapter = new ImageAdapter(FullImageViewActivity.this,EidDesignActivity.mThumbIds);
            imgvFull.setImageBitmap(imageAdapter.mImageArray[position]);
        }else if (fromActivity.equals("weeddesign")){
            imageAdapter = new ImageAdapter(FullImageViewActivity.this,WeedingDesignActivity.mThumbIds);
            imgvFull.setImageBitmap(imageAdapter.mImageArray[position]);
//            Animation animation1 = AnimationUtils.loadAnimation(FullImageViewActivity.this,
//                    R.anim.clockwise);
//            imgvFull.startAnimation(animation1);

        }else if (fromActivity.equals("handdesign")){
            imageAdapter = new ImageAdapter(FullImageViewActivity.this,HandDesignActivity.mThumbIds);
            imgvFull.setImageBitmap(imageAdapter.mImageArray[position]);

        }else if (fromActivity.equals("legdesign")){
            imageAdapter = new ImageAdapter(FullImageViewActivity.this,LegDesignActivity.mThumbIds);
            imgvFull.setImageBitmap(imageAdapter.mImageArray[position]);
        }else if (fromActivity.equals("latestdesign")){
            imageAdapter = new ImageAdapter(FullImageViewActivity.this,LatestDesignActivity.mThumbIds);
            imgvFull.setImageBitmap(imageAdapter.mImageArray[position]);


        }

        btnSavePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(FullImageViewActivity.this,
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_STORAGE);

                } else{
                    saveFrameImageToSDCard(imageAdapter.mImageArray[position]);
                }

            }
        });

        btnSharePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(FullImageViewActivity.this,
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_STORAGE);

                } else{
                    saveFrameImageToSDCard(imageAdapter.mImageArray[position]);
                }

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(getImageFilePath()))); // Set image uri
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "Share image to.."));
            }
        });

    }

    public void saveFrameImageToSDCard(Bitmap saveBitmap){
        String frameImage = "mehedi_design";
        //String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File sdCardDir = Environment.getExternalStorageDirectory();
        // the name of the file to export with

        File saveFile = new File(sdCardDir, frameImage);

        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = n + frameImage + ".png";

        // File file = new File("/sdcard/" + frameImage + ".png");
        //File myDir = new File(sdCardDir + "mehedidesign");
        saveFile.mkdirs();

        File file = new File(saveFile, fname);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream ostream = new FileOutputStream(file);
            saveBitmap.compress(Bitmap.CompressFormat.PNG, 10, ostream);
            ostream.close();
            Toast.makeText(FullImageViewActivity.this,"Picture Save Successfully",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

       imageFilePath = file.getAbsolutePath();
        setImageFilePath(imageFilePath);


    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }



}

package traintimingapp.planet.it.limited.mymehedidesign.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import traintimingapp.planet.it.limited.mymehedidesign.R;
import traintimingapp.planet.it.limited.mymehedidesign.activities.FullImageViewActivity;

/**
 * Created by Empty on 11/5/2017.
 */

public class ImageAdapter extends BaseAdapter {
    Context mContext;
    public Bitmap[] mImageArray ;
    //ArrayList<String> mListPath;

    public ImageAdapter(Context context, Bitmap[] imgArray){
        this.mContext=context;
        mImageArray = imgArray;


    }

    @Override
    public int getCount() {
        return mImageArray.length;
    }

    @Override
    public Object getItem(int position) {
        return mImageArray[position];
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        gridView =new View(mContext);

        gridView = inflater.inflate(R.layout.gridview_members, null);
        //final Bitmap path = mImageArray[position];
        ImageView designImage = (ImageView)gridView.findViewById(R.id.design_item);
        designImage.setImageBitmap(mImageArray[position]);
        designImage.setScaleType(ImageView.ScaleType.FIT_XY);
        designImage.setLayoutParams(new GridView.LayoutParams(300, 300));
        designImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, FullImageViewActivity.class);
                // passing array index
                i.putExtra("id", position);
                mContext.startActivity(i);
            }
        });
//        // Step1 : create the  RotateAnimation object
//        RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
//        // Step 2:  Set the Animation properties
//        anim.setInterpolator(new LinearInterpolator());
//        anim.setRepeatCount(Animation.INFINITE);
//        anim.setDuration(700);
//
//        // Step 3: Start animating the image
//        designImage.startAnimation(anim);
//
//        // Later. if you want to  stop the animation
//        // image.setAnimation(null);
        Animation animation1 = AnimationUtils.loadAnimation(mContext,
                R.anim.clockwise);
        designImage.startAnimation(animation1);
        return gridView;
    }

}

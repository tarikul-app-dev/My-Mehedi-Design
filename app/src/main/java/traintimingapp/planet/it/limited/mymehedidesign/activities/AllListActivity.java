package traintimingapp.planet.it.limited.mymehedidesign.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import traintimingapp.planet.it.limited.mymehedidesign.R;
import traintimingapp.planet.it.limited.mymehedidesign.adapter.CategoryAdapter;
import traintimingapp.planet.it.limited.mymehedidesign.utill.FontCustomization;

public class AllListActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private String []headline = new String[5];
    ListView lvCategory;
    CategoryAdapter categoryAdapter;
    Toolbar toolbar;
    FontCustomization fontCustomization;
    TextView txvToolbarHead;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar_all_list_activity);
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
        fontCustomization = new FontCustomization(AllListActivity.this);
        txvToolbarHead =(TextView)findViewById(R.id.txv_toolbar_head);
        lvCategory = (ListView)findViewById(R.id.lv_category);
        txvToolbarHead.setTypeface(fontCustomization.getTexgyreHerosBold());

        headline[0] = getString(R.string.eid_design);
        headline[1] = getString(R.string.weeding_design);
        headline[2] = getString(R.string.hand_design);
        headline[3] = getString(R.string.leg_design);
        headline[4] = getString(R.string.latest_design);

        categoryAdapter = new CategoryAdapter(AllListActivity.this,headline);
        lvCategory.setAdapter(categoryAdapter);


        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TODO Do whatever you want with the list data
                if(position==0){
                    Intent intent = new Intent(AllListActivity.this,EidDesignActivity.class);
                    startActivity(intent);
                } else if (position == 1){
                    Intent intent = new Intent(AllListActivity.this,WeedingDesignActivity.class);
                    startActivity(intent);
                }else if (position == 2){
                    Intent intent = new Intent(AllListActivity.this,HandDesignActivity.class);
                    startActivity(intent);
                }else if (position == 3){
                    Intent intent = new Intent(AllListActivity.this,LegDesignActivity.class);
                    startActivity(intent);
                }else if (position == 4){
                    Intent intent = new Intent(AllListActivity.this,LatestDesignActivity.class);
                    startActivity(intent);
                }


            }
        });

        rewardAds();
    }
    private void rewardAds() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.reward_id),//use this id for testing
                new AdRequest.Builder().build());

    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(this, "Download to Earn" + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();

        // Reward the user.
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

}

package com.borysenko.advertiserecycler;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 25/05/19
 * Time: 21:47
 */
public class MobileAdsHelper implements RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;
    private Context mContext;

    MobileAdsHelper(Context context){
        mContext = context;
    }

    void initMobileAds() {
        MobileAds.initialize(mContext, "ca-app-pub-3940256099942544~3347511713");

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(mContext);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }

    void showVideo() {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }

    void videoAdResume() {
        mRewardedVideoAd.resume(mContext);
    }

    void videoAdPause() {
        mRewardedVideoAd.pause(mContext);
    }

    void videoAdDestroy() {
        mRewardedVideoAd.pause(mContext);
    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(mContext, "onRewarded! currency: " + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(mContext, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(mContext, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}

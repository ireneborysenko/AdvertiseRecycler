package com.borysenko.advertiserecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.borysenko.advertiserecycler.model.AdMessage;
import com.borysenko.advertiserecycler.model.CompanionMessage;
import com.borysenko.advertiserecycler.model.MessageType;
import com.borysenko.advertiserecycler.model.UserMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    private MobileAdsHelper mobileAdsHelper;

    @BindView(R.id.main_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_title);

        ButterKnife.bind(this);

        mobileAdsHelper = new MobileAdsHelper(this);
        mobileAdsHelper.initMobileAds();

        initMainRecyclerView(fillMessageList());
    }

    private List<MessageType> fillMessageList() {
        List<MessageType> userMessageList = new ArrayList<>();
        userMessageList.add(new UserMessage("Message from User", "12:00"));
        userMessageList.add(new UserMessage("Second message from User", "12:40"));
        userMessageList.add(new AdMessage("Ad Title", "Press button to watch video"));
        userMessageList.add(new CompanionMessage("Message from Companion", "12:55"));
        userMessageList.add(new UserMessage("Third message from User", "13:02"));
        userMessageList.add(new AdMessage("Another Ad Title", "Press Button"));
        userMessageList.add(new CompanionMessage("Second message from Companion", "13:15"));
        userMessageList.add(new AdMessage("Advertisement", "Want to watch video"));
        return userMessageList;
    }

    private void initMainRecyclerView(List<MessageType> userMessageList) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final MainRecyclerAdapter mAdapter = new MainRecyclerAdapter(userMessageList, this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MainRecyclerAdapter.ClickListener() {
            @Override
            public void onItemClick(String message) {
                showToast(message);
            }

            @Override
            public void onAdButtonClick() {
                mobileAdsHelper.showVideo();
            }
        });
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onResume() {
        mobileAdsHelper.videoAdResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mobileAdsHelper.videoAdPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mobileAdsHelper.videoAdDestroy();
        super.onDestroy();
    }
}

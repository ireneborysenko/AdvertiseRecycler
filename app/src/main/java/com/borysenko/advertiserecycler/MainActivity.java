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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_title);

        ButterKnife.bind(this);

        List<MessageType> userMessageList = new ArrayList<>();
        userMessageList.add(new UserMessage("message hhhh gffffh hgg2", "date"));
        userMessageList.add(new UserMessage("message 1", "date1"));
        userMessageList.add(new AdMessage("title1", "text111"));
        userMessageList.add(new CompanionMessage("message comp", "yyyy"));
        userMessageList.add(new UserMessage("message 3", "date2"));
        userMessageList.add(new AdMessage("title222", "2222text111"));
        userMessageList.add(new CompanionMessage("message comp", "yyyy"));

        initMainRecyclerView(userMessageList);
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
                showToast("Some text");
            }
        });
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }
}

package com.borysenko.advertiserecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.borysenko.advertiserecycler.model.AdMessage;
import com.borysenko.advertiserecycler.model.CompanionMessage;
import com.borysenko.advertiserecycler.model.MessageType;
import com.borysenko.advertiserecycler.model.UserMessage;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.borysenko.advertiserecycler.model.MessageType.*;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 22/05/19
 * Time: 18:41
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static ClickListener clickListener;
    private static List<MessageType> mMessages;
    private Context mContext;

    static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.user_icon) ImageView mUserIcon;
        @BindView(R.id.user_date) TextView mUserDate;
        @BindView(R.id.user_message) TextView mUserMessage;

        UserViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(((UserMessage) mMessages.get(getAdapterPosition()))
                    .getUserMessage());
        }
    }

    static class CompanionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.companion_icon) ImageView mCompanionIcon;
        @BindView(R.id.companion_date) TextView mCompanionDate;
        @BindView(R.id.companion_message) TextView mCompanionMessage;

        CompanionViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(((CompanionMessage) mMessages.get(getAdapterPosition()))
                    .getCompanionMessage());
        }
    }

    static class AdViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.ad_title) TextView mAdTitle;
        @BindView(R.id.ad_text) TextView mAdText;
        @BindView(R.id.ad_button) TextView mAdButton;

        AdViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {

        }
    }

    MainRecyclerAdapter(List<MessageType> messages, Context context) {
        mMessages = messages;
        mContext = context;
    }

    void setOnItemClickListener(ClickListener clickListener) {
        MainRecyclerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(String companionMessage);
        void onAdButtonClick();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getItemViewType();
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case USER_MESSAGE_TYPE:
                itemView = inflater.inflate(R.layout.item_user_message, parent, false);
                return new UserViewHolder(itemView);
            case COMPANION_MESSAGE_TYPE:
                itemView = inflater.inflate(R.layout.item_companion_message, parent, false);
                return new CompanionViewHolder(itemView);
            case AD_MESSAGE_TYPE:
                itemView = inflater.inflate(R.layout.item_ad_message, parent, false);
                return new AdViewHolder(itemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case USER_MESSAGE_TYPE:
                UserViewHolder userViewHolder = (UserViewHolder) viewHolder;
                MessageType userMessage = mMessages.get(i);
                userViewHolder.mUserMessage.setText(((UserMessage) userMessage).getUserMessage());
                userViewHolder.mUserDate.setText(((UserMessage) userMessage).getUserDate());

                RequestOptions userOptions = new RequestOptions()
                        .skipMemoryCache(false);

                Glide.with(mContext)
                        .load(R.drawable.ic_user)
                        .apply(userOptions)
                        .into(userViewHolder.mUserIcon);
                break;

            case COMPANION_MESSAGE_TYPE:
                CompanionViewHolder companionViewHolder = (CompanionViewHolder) viewHolder;
                MessageType companionMessage = mMessages.get(i);
                companionViewHolder.mCompanionMessage.setText(((CompanionMessage) companionMessage)
                        .getCompanionMessage());
                companionViewHolder.mCompanionDate.setText(((CompanionMessage) companionMessage)
                        .getCompanionDate());
                RequestOptions options = new RequestOptions()
                        .skipMemoryCache(false);

                Glide.with(mContext)
                        .load(R.drawable.ic_companion)
                        .apply(options)
                        .into(companionViewHolder.mCompanionIcon);
                break;

            case AD_MESSAGE_TYPE:
                AdViewHolder adViewHolder = (AdViewHolder) viewHolder;
                MessageType adMessage = mMessages.get(i);
                adViewHolder.mAdTitle.setText(((AdMessage) adMessage).getAdTitle());
                adViewHolder.mAdText.setText(((AdMessage) adMessage).getAdText());
                adViewHolder.mAdButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickListener.onAdButtonClick();
                    }
                });
                break;
        }
    }
}

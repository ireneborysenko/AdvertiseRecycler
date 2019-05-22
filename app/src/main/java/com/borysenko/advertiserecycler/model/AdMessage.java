package com.borysenko.advertiserecycler.model;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 22/05/19
 * Time: 18:56
 */
public class AdMessage implements MessageType {

    private String adTitle;
    private String adText;

    public AdMessage(String adTitle, String adText) {
        this.adTitle = adTitle;
        this.adText = adText;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public String getAdText() {
        return adText;
    }

    @Override
    public int getItemViewType() {
        return MessageType.AD_MESSAGE_TYPE;
    }
}

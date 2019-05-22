package com.borysenko.advertiserecycler.model;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 22/05/19
 * Time: 18:56
 */
public class CompanionMessage implements MessageType {

    private String companionMessage;
    private String companionDate;

    public CompanionMessage(String companionMessage, String companionDate) {
        this.companionMessage = companionMessage;
        this.companionDate = companionDate;
    }

    public String getCompanionMessage() {
        return companionMessage;
    }

    public String getCompanionDate() {
        return companionDate;
    }

    @Override
    public int getItemViewType() {
        return MessageType.COMPANION_MESSAGE_TYPE;
    }
}

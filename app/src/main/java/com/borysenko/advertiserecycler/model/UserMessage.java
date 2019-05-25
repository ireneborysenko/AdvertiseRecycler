package com.borysenko.advertiserecycler.model;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 22/05/19
 * Time: 18:55
 */
public class UserMessage implements MessageType {

    private String userMessage;
    private String userDate;

    public UserMessage(String userMessage, String userDate) {
        this.userMessage = userMessage;
        this.userDate = userDate;
    }

    @Override
    public int getItemViewType() {
        return MessageType.USER_MESSAGE_TYPE;
    }

    @Override
    public String getMessage() {
        return userMessage;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDate() {
        return userDate;
    }
}

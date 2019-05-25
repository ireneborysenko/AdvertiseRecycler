package com.borysenko.advertiserecycler.model;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 22/05/19
 * Time: 19:10
 */
public interface MessageType {
    int USER_MESSAGE_TYPE =   0;
    int COMPANION_MESSAGE_TYPE = 1;
    int AD_MESSAGE_TYPE = 2;

    int getItemViewType();

    String getMessage();

    String getTitle();

    String getDate();
}

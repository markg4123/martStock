package com.example.martstock;

import java.util.Date;

public class ChatMessage {
    String messageSender, messageReciever, messageText,date, messageID, adId;


    public ChatMessage(){

    }

    public ChatMessage(String messageSender, String messageReciever,String messageText, String date, String messageID, String adId) {
        this.messageSender = messageSender;
        this.messageReciever = messageReciever;
        this.messageText = messageText;
        this.date = date;
        this.messageID = messageID;
        this.adId = adId;

    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageReciever() {
        return messageReciever;
    }

    public void setMessageReciever(String messageReciever) {
        this.messageReciever = messageReciever;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }
}
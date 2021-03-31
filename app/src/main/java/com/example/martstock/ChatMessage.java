package com.example.martstock;

import java.util.Date;

public class ChatMessage {
    String messageSender, messageReciever, messageText;

    public ChatMessage(){

    }

    public ChatMessage(String messageSender, String messageReciever,String messageText) {
        this.messageSender = messageSender;
        this.messageReciever = messageReciever;
        this.messageText = messageText;

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
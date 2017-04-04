package com.smtp.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple_SMTP_Server
 * Created by JOYMANGUL Jensen Selwyn on 03/04/2017.
 */
public class Mail {
    private String sender;
    private List<String> receiver;

    public Mail(String mail) {
        this.setReceiver(new ArrayList<>());
        this.setSender("");
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    public void addReceiver(String receiver)
    {
        this.receiver.add(receiver);
    }

}

package com.smtp.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple_SMTP_Server
 * Created by JOYMANGUL Jensen Selwyn on 04/04/2017.
 */
public class Transaction {
    private String sender;
    private List<String> receivers;
    private String mailContent;

    public Transaction() {
        this.setSender("");
        this.setReceivers(new ArrayList<>());
        this.setMailContent("");
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public void addReceiver(String reseive) {
        this.getReceivers().add(reseive);
    }
}

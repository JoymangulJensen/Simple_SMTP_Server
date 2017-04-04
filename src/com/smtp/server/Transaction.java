package com.smtp.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple_SMTP_Server
 * Created by JOYMANGUL Jensen Selwyn on 04/04/2017.
 */
public class Transaction {
    private String sender;
    private List<String> recipient;
    private String mailContent;

    public Transaction() {
        this.setSender("");
        this.setRecipient(new ArrayList<>());
        this.setMailContent("");
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipient() {
        return recipient;
    }

    public void setRecipient(List<String> recipient) {
        this.recipient = recipient;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public void addRecipient(String recipient) {
        this.getRecipient().add(recipient);
    }

    public boolean isSenderValid() {
        return !this.getSender().equals("");
    }

    public boolean isRecipientValid() {
        return !this.getRecipient().isEmpty();
    }
}

package com.smtp.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
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
        this.reset();
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

    public Boolean saveMail() {
        Calendar now = Calendar.getInstance();
        for(String recipient : this.getRecipient())
        {
            String filename = MailboxUtils.MAILBOX_DIRECTORY + recipient + "/" + String.valueOf(now.getTimeInMillis()) + MailboxUtils.MAIL_EXTENSION;
            Path path = Paths.get(filename);
            try {
                BufferedWriter writer = Files.newBufferedWriter(path);
                writer.write(this.getMailContent());
                writer.close();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public void reset(){
        this.setSender("");
        this.setRecipient(new ArrayList<>());
        this.setMailContent("");
    }
}

package com.smtp.server;

import java.util.List;

/**
 * Created by p1508754 on 03/04/2017.
 */
public class Mail {
    private String sender;
    private List<String> receiver;

    public String getSender() {
        return sender;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public Mail(String mail) {
    }

    public void build(String mail) {

    }
}

package com.smtp.server;

import java.util.HashSet;

/**
 * Simple_SMTP_Server
 * Created by JOYMANGUL Jensen Selwyn on 03/04/2017.
 */
public enum Command {

    OK("250 OK"),
    EHLO("EHLO"),
    MAIL("MAIL"),
    RCPT("RCPT"),
    RST("RST"),
    DATA("DATA"),
    QUIT("QUIT"),
    ERROR("550"),
    DEFAULT("");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static HashSet<String> getEnums() {

        HashSet<String> values = new HashSet<>();
        for (Command c : Command.values()) {
            values.add(c.name());
        }
        return values;
    }

    public String getValue() {
        return value;
    }
}

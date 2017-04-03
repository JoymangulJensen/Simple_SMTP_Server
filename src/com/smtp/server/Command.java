package com.smtp.server;

import java.util.HashSet;

/**
 * Created by Gaetan on 05/03/2017.
 * List of commands
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

    public String getValue() {
        return value;
    }

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
}

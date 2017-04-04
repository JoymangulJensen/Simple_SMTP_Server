package com.smtp.server;

import java.io.File;

/**
 * Simple_SMTP_Server
 * Created by JOYMANGUL Jensen Selwyn on 03/04/2017.
 */
public class MailboxUtils {
    public final static String MAILBOX_DIRECTORY = new StringBuilder()
            .append(System.getProperty("user.home"))
            .append("/IPC/mailboxes/").toString();


    public static Boolean receiverExists(String receiver) {
        File[] directories = new File(MAILBOX_DIRECTORY).listFiles(File::isDirectory);
        for (File f : directories) {
            if (f.getName().equals(receiver)) {
                return true;
            }
        }
        return false;
    }
    // TODO: 03/04/2017 All the utilities related to a mailbox for example if the receiver mailbox exists
}

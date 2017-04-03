package com.smtp.server;

/**
 * Created by seljo on 4/3/2017.
 */
public class ResponseGenerator {
    private static final String SERVER_DOMAIN = "polytech.ipc";

    public static Message serverReady()
    {
        StringBuilder stringBuilder = new StringBuilder("220 ")
                .append(SERVER_DOMAIN)
                .append(" Simple Mail Transfer Protocol");
        return new Message(stringBuilder.toString());
    }

    public static Message getHelloReply()
    {
        return new Message(Command.OK, SERVER_DOMAIN);
    }

    public static Message getMailReply(Message msgReceive)
    {
        System.out.println(msgReceive.getMail());
        return new Message(Command.OK);
    }
}

package com.smtp.server;

/**
 * Simple_SMTP_Server
 * Created by JOYMANGUL Jensen Selwyn on 4/3/2017.
 */
public class ResponseGenerator {
    private static final String SERVER_DOMAIN = "polytech.ipc";

    public static Message serverReady() {
        StringBuilder stringBuilder = new StringBuilder("220 ")
                .append(SERVER_DOMAIN)
                .append(" Simple Mail Transfer Protocol");
        return new Message(stringBuilder.toString());
    }

    public static Message getHelloReply() {
        return new Message(Command.OK, SERVER_DOMAIN);
    }

    public static Message getMailReply(Message msgReceive, Transaction transaction) {
        String sender = msgReceive.getMail();
        if (UserUtils.userExists(sender)) {
            transaction.setSender(sender);
            return new Message(Command.OK);
        } else {
            return new Message(Command.ERROR, "No such user here");
        }
    }

    public static Message getRecipientReply(Message msgReceive, Transaction transaction) {
        String recipient = msgReceive.getMail();
        if(MailboxUtils.receiverExists(recipient))
        {
            transaction.addRecipient(recipient);
            return new Message(Command.OK);
        }else {
            return new Message(Command.ERROR, "No such user here");
        }
    }

    public static Message getDataReply(Transaction transaction){
        if(transaction.isSenderValid() && transaction.isRecipientValid())
            return new Message(Command.GETMAIL, "Start Mail input end with <CR><LF>.<CR><LF>");
        return new Message(Command.ERROR);
    }

    public static Message getMailContentReply(Message msgReceive, Transaction transaction) {
        transaction.setMailContent(msgReceive.toString());
        return new Message(Command.OK);
    }
}

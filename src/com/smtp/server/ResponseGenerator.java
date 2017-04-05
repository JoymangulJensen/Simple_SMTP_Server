package com.smtp.server;

/**
 * Simple_SMTP_Server
 * Created by JOYMANGUL Jensen Selwyn on 4/3/2017.
 */
public class ResponseGenerator {
    private static final String SERVER_DOMAIN = "polytech.ipc";

    private Transaction transaction;

    public ResponseGenerator() {
        transaction = new Transaction();
    }

    public Message serverReady() {
        StringBuilder stringBuilder = new StringBuilder("220 ")
                .append(SERVER_DOMAIN)
                .append(" Simple Mail Transfer Protocol");
        return new Message(stringBuilder.toString());
    }

    public Message getHelloReply() {
        System.out.println("heelo");
        return new Message(Command.OK, SERVER_DOMAIN);
    }

    public Message getMailReply(Message msgReceive) {
        String sender = msgReceive.getMail();
        if (UserUtils.userExists(sender)) {
            transaction.setSender(sender);
            return new Message(Command.OK);
        } else {
            return new Message(Command.ERROR, "No such user here");
        }
    }

    public Message getRecipientReply(Message msgReceive) {
        String recipient = msgReceive.getMail();
        if(MailboxUtils.receiverExists(recipient))
        {
            transaction.addRecipient(recipient);
            return new Message(Command.OK);
        }else {
            return new Message(Command.ERROR, "No such user here");
        }
    }

    public Message getDataReply(){
        if(transaction.isSenderValid() && transaction.isRecipientValid())
            return new Message(Command.GETMAIL, "Start Mail input end with <CR><LF>.<CR><LF>");
        return new Message(Command.ERROR);
    }

    public Message getMailContentReply(Message msgReceive) {
        transaction.setMailContent(msgReceive.toString());
        if(transaction.saveMail())
            return new Message(Command.OK);
        return new Message(Command.ERROR);
    }

    public Message getRsetReply() {
        transaction.reset();
        return new Message(Command.OK);
    }

    public Message getQuitReply() {
        return new Message(Command.QUITCODE, "Service closing transmission channel");
    }
}

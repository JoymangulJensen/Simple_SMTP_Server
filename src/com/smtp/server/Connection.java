package com.smtp.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple_SMTP_Server
 * Created by JOYMANGUL Jensen Selwyn on 03/04/2017.
 */
public class Connection implements Runnable {
    private Socket clientSocket;
    private InputStream is;
    private OutputStream os;
    private boolean stop = false;
    private List<Message> messagesSent = new ArrayList<>();
    private Transaction transaction;
    private ResponseGenerator responseGenerator;

    Connection(Socket socket) throws IOException {
        clientSocket = socket;
        is = clientSocket.getInputStream();
        os = clientSocket.getOutputStream();
    }

    /**
     * Process the receptions of commands
     *
     * @return boolean indicating if the program should stop or not
     */
    private void process() {
        Message message;
        switch ((message = receive()).getCommand()) {
            case OK:
                break;
            case EHLO:
                this.send(responseGenerator.getHelloReply());
                break;
            case MAIL:
                this.send(responseGenerator.getMailReply(message));
                break;
            case RCPT:
                this.send(responseGenerator.getRecipientReply(message));
                break;
            case RST:
                this.send(responseGenerator.getRsetReply());
                this.transaction = new Transaction();
                break;
            case DATA:
                Message reply = responseGenerator.getDataReply();
                this.send(reply);
                if(reply.getCommand() == Command.OK)
                {
                    Message mail = receive(); // This temporary for testing with putty for client
                    mail = receive();
                    this.send(responseGenerator.getMailContentReply(mail));
                }
                break;
            case QUIT:
                this.send(responseGenerator.getQuitReply());
                this.stop("QUIT");
                break;
            case ERROR:
                break;
            case DEFAULT:
                break;
            default:
                // Command not known
                send(new Message(Command.ERROR, "Invalid command"));
        }
    }

    @Override
    public void run() {
        init();
        while (!stop) {
            process();
        }
    }

    private void init() {
        responseGenerator = new ResponseGenerator();
        send(responseGenerator.serverReady());
    }

    /**
     * Send a message over TCP
     *
     * @param message Message to sent
     */
    private void send(Message message) {
        try {
            os.write(message.getBytes());
            messagesSent.add(message);
            System.out.println("Message Sent : " + message);
        } catch (SocketException se) {
            this.stop("Socket Error");
        } catch (IOException e) {
            System.out.println("Could not write " + message);
            e.printStackTrace();
        }
    }

    /**
     * Receive a message over TCP
     *
     * @return the message received
     */
    private Message receive() {
        byte b[] = new byte[Message.BUFFER_MAX_SIZE];
        try {
            if (is.read(b) != -1) {
                Message message = new Message(b);
                System.out.println("Message received : " + message);
                return message;
            }
        } catch (SocketException se) {
            this.stop("Socket Error");
        } catch (IOException e) {
            System.out.printf("Could not read from input stream");
            e.printStackTrace();
        }
        return new Message(Command.DEFAULT);
    }

    /**
     * End
     */
    private void stop(String statut) {
        if (!this.stop) {
            this.stop = true;
            try {
                is.close();
                os.close();
                this.clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(statut + " - Connexion finished");
        }
    }
}

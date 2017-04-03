package com.smtp.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by p1508754 on 03/04/2017.
 */
public class UserUtils {
    private static final String USERS_FILE_PATH = new StringBuilder()
            .append(System.getProperty("user.home"))
            .append("/IPC/users.txt").toString();

    public static Boolean userExists(String user) {
        try {
            File file = new File(USERS_FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] userDetails = scanner.nextLine().split(" ");
                if(userDetails[0].equals(user))
                    return true;
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
            return false;
        }
        return false;
    }
}

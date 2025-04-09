package com.insurance.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    public static String getPropertyString(String fileName) {
        Properties prop = new Properties();
        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
                return null;
            }

            prop.load(input);

            String host = prop.getProperty("host");
            String db = prop.getProperty("dbname");
            String user = prop.getProperty("username");
            String pass = prop.getProperty("password");
            String port = prop.getProperty("port");

            return "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + pass;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}


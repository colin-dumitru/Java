package edu.jspbeanservlet.service.authentication;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
public class AuthenticationService {
    public static final String USER_INFO_FILE = "userInfo.bin";

    private static final AuthenticationService instance = new AuthenticationService();

    private Map<String, UserInformation> userInformationMap = new HashMap<>();

    public static AuthenticationService instance() {
        return instance;
    }

    public void init() throws IOException, ClassNotFoundException {
        try (InputStream inputStream = new FileInputStream(USER_INFO_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            Object savedObject = objectInputStream.readObject();
            if (savedObject instanceof Map) {
                userInformationMap = (Map<String, UserInformation>) savedObject;
            }
        }
    }

    public void destroy() throws IOException {
        try (OutputStream outputStream = new FileOutputStream(USER_INFO_FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(userInformationMap);
        }
    }

    public synchronized UserInformation authenticateUser(String userName, String password) {
        UserInformation userInfo = userInformationMap.get(userName);
        if (userInfo == null) {
            return null;
        }
        if (!userInfo.getPassword().equals(password)) {
            return null;
        }
        return userInfo;
    }

    public synchronized UserInformation registerUser(String userName, String password) {
        if (userInformationMap.containsKey(userName)) {
            return null;
        }
        UserInformation userInformation = new UserInformation(userName, password);
        userInformationMap.put(userName, userInformation);
        return userInformation;
    }

    public synchronized UserInformation getUserInfo(String userName) {
        return userInformationMap.get(userName);
    }

}

package edu.jspbeanservlet.service.authentication;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 10/16/12
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserInformation {
    private String userName;
    private String password;

    public UserInformation() {
    }

    public UserInformation(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

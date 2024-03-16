package model;

import java.io.Serializable;

public class AdminUser implements Serializable {
    private String username;
    private int securitycode;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(int securitycode) {
        this.securitycode = securitycode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
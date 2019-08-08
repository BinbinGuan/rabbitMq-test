package com.example.transienttest;

import java.io.Serializable;

/**
 * @author: GuanBin
 * @date: Created in 下午2:31 2019/8/8
 */
public class User implements Serializable {

    private static final long serialVersionUID = 6653887738980830004L;
    private static String username;
    private transient String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

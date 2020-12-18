package com.lxh.bookmark;

public class User {
    long id;
    String account;
    String password;

    public User(long id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

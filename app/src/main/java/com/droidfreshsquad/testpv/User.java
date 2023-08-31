package com.droidfreshsquad.testpv;

public class User {
    private String email;
    private String sdt;

    public User(String email, String sdt) {
        this.email = email;
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public String getSdt() {
        return sdt;
    }
}

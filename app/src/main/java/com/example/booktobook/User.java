package com.example.booktobook;


import java.util.List;

public class User {
    private String id;
    private String password;
    private int point;
    private int uploaded_book_count;
    private List<Alert> alert;

    public User() {
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
        this.point = 0;
        this.uploaded_book_count = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getUploaded_book_count() {
        return uploaded_book_count;
    }

    public void setUploaded_book_count(int uploaded_book_count) {
        this.uploaded_book_count = uploaded_book_count;
    }

    public List<Alert> getAlert() {
        return alert;
    }

    public void setAlert(List<Alert> alert) {
        this.alert = alert;
    }
}
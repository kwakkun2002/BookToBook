package com.example.booktobook;

class AlertData{
    public int year;
    public int month;
    public int day;
    public String isBorrowed;
    public String title;
    public String name;
    public String place;

    public AlertData(String isBorrowed,int year,int month,int day,
                 String title,String name,String place){
        this.isBorrowed = isBorrowed;
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.name = name;
        this.place = place;
    }

}

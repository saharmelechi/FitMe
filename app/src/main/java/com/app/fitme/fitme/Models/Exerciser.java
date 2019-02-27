package com.app.fitme.fitme.Models;

import android.net.Uri;

import com.app.fitme.fitme.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Exerciser implements Serializable {
    String name;
    String exeImage;
    String subject;
    String content;
    long date;

    public Exerciser(){

    }

    public Exerciser(String UserName) {
        date = Calendar.getInstance().getTimeInMillis();
        this.name = UserName;
        this.subject = "Put subject here";
        this.content = "Hard work pays";
        this.exeImage =  Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +R.drawable.avatar2).toString();
    }


    public Exerciser(String name, String exeImage, String subject, String content, long date) {
        this.name = name;
        this.exeImage = exeImage;
        this.subject = subject;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getExeImage() {
        return exeImage;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public long getDate() { return this.date; }


    public String formatDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(new Date(this.getDate()));

    }
}

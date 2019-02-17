package com.app.fitme.fitme.Models;

import android.text.method.DateTimeKeyListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Exerciser implements Serializable {
    String name;
    String avatar;
    String subject;
    String content;
    long date;

    public Exerciser(String name, String avatar, String subject, String content, long date) {
        this.name = name;
        this.avatar = avatar;
        this.subject = subject;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
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

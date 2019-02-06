package com.app.fitme.fitme.Models;

import java.io.Serializable;

public class Mail implements Serializable {
    String name;
    int avatar;
    String subject;
    String content;
    String date;

    public Mail(String name, int avatar, String subject, String content, String date) {
        this.name = name;
        this.avatar = avatar;
        this.subject = subject;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}

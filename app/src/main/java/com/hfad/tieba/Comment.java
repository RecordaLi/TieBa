package com.hfad.tieba;

import java.util.Date;
import java.util.UUID;

/**
 * Created by lgy on 2017/10/21.
 */

public class Comment {
    private UUID mUUID;
    private String user;
    private Date mDate;
    private String comment;

    public Comment(String s){
        comment=s;
    }

    public Comment(){

    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

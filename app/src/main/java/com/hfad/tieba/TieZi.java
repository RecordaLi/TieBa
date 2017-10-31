package com.hfad.tieba;

import java.util.Date;
import java.util.UUID;

/**
 * Created by lgy on 2017/10/16.
 */

public class TieZi {
    private UUID mId;
    private String mTitle;
    private  String mContent;
    private Date mDate;
    private Commentlist mCommentlist;

    public Commentlist getCommentlist() {
        return mCommentlist;
    }

    public void setCommentlist(Commentlist commentlist) {
        mCommentlist = commentlist;
    }

    public TieZi(){
       this(UUID.randomUUID());
    }

    public TieZi(UUID id){
        mId = id;
        mDate = new Date();
        mCommentlist = new Commentlist(id);

        for(int i=0;i<5;i++){
            Comment comment = new Comment();
            comment.setComment("haha");
            mCommentlist.add(comment);
        }
    }
    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }


}

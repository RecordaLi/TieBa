package com.hfad.tieba;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lgy on 2017/10/24.
 */

public class Commentlist {
    private UUID mUUID;
    private List<Comment> mCommentList;

    public UUID getUUID() {
        return mUUID;

    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public Commentlist(UUID uuid){
        mUUID = uuid;
        mCommentList = new ArrayList<>();

    }


    public List<Comment> getCommentList() {
        return mCommentList;
    }

    public void setCommentList(List<Comment> commentList) {
        mCommentList = commentList;
    }

    public void add(Comment comment){
        mCommentList.add(comment);
    }




}

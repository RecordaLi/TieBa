package com.hfad.tieba;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lgy on 2017/10/24.
 */

public class CommentLab {

    private static CommentLab sCommentLab;

    private List<Commentlist> mList;

    public static CommentLab get(Context context){
        if(sCommentLab==null){
            sCommentLab = new CommentLab(context);
        }
        return sCommentLab;
    }

    public CommentLab(Context context){
        mList = new ArrayList<>();
    }

    public List<Commentlist> getList(){
        return mList;
    }

    public Commentlist getComments(UUID uuid){
        for (Commentlist commentlist : mList){
            if(commentlist.getUUID().equals(uuid)){
                return commentlist;
            }
        }
        return null;
    }

    public void add(Commentlist list){
        mList.add(list);
    }

}

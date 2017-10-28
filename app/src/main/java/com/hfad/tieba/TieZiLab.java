package com.hfad.tieba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.hfad.tieba.TieziDbSchema.TieziTable;

/**
 * Created by lgy on 2017/10/16.
 */

public class TieZiLab {
    private static TieZiLab mTieZiLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TieZiLab get(Context context){
        if(mTieZiLab==null){
            mTieZiLab = new TieZiLab(context);
        }
        return mTieZiLab;
    }

    private TieZiLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new TieziBaseHelper(mContext).getWritableDatabase();

//        for(int i=0;i<100;i++){
//            TieZi tieZi = new TieZi();
//            tieZi.setTitle("TieZi# "+i);
//            tieZi.setContent("哈哈哈");
//            mTieZis.add(tieZi);
//        }

    }

    public List<TieZi> getTieZis(){
        List<TieZi> tieZis = new ArrayList<>();

        TieziCursorWrapper cursor = queryTiezis(null,null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                tieZis.add(cursor.getTieZi());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return tieZis;

    }

    public TieZi getTieZi(UUID uuid){
        TieziCursorWrapper cursor = queryTiezis(TieziTable.Cols.UUID + " = ?",
                new String[]{uuid.toString()});

        try{
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTieZi();
        }finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(TieZi tieZi){
        ContentValues values = new ContentValues();
        values.put(TieziTable.Cols.UUID,tieZi.getId().toString());
        values.put(TieziTable.Cols.TITLE,tieZi.getTitle());
        values.put(TieziTable.Cols.CONTENT,tieZi.getContent());
        return values;
    }

    public void addTieZi(TieZi tieZi){
        ContentValues values = getContentValues(tieZi);
        mDatabase.insert(TieziTable.NAME,null,values);
    }

   public void updateTiezi(TieZi tieZi){
        String uuidString = tieZi.getId().toString();
        ContentValues values = getContentValues(tieZi);

        mDatabase.update(TieziTable.NAME,values,TieziTable.Cols.UUID + " = ?",new String[]{uuidString});
    }

    private  TieziCursorWrapper queryTiezis(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                TieziTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new TieziCursorWrapper(cursor);
    }
}

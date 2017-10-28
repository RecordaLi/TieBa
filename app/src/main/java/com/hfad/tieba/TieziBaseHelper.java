package com.hfad.tieba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hfad.tieba.TieziDbSchema.TieziTable;

/**
 * Created by lgy on 2017/10/22.
 */

public class TieziBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME="tieziBase.db";

    public TieziBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TieziTable.NAME + "(" +
        "_id integer primary key autoincrement, " +
        TieziTable.Cols.UUID + ", "+
        TieziTable.Cols.TITLE +", "+
        TieziTable.Cols.CONTENT +
        ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

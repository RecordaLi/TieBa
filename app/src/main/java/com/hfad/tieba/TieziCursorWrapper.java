package com.hfad.tieba;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.hfad.tieba.TieziDbSchema.TieziTable;

import java.util.UUID;

/**
 * Created by lgy on 2017/10/22.
 */

public class TieziCursorWrapper extends CursorWrapper {
    public TieziCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public TieZi getTieZi(){
        String uuidString = getString(getColumnIndex(TieziTable.Cols.UUID));
        String title = getString(getColumnIndex(TieziTable.Cols.TITLE));
        String content = getString(getColumnIndex(TieziTable.Cols.CONTENT));

        TieZi tieZi = new TieZi(UUID.fromString(uuidString));
        tieZi.setTitle(title);
        tieZi.setContent(content);

        return tieZi;
    }
}

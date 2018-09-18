package com.example.fangxy.latteec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }


}

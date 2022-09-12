package com.rhmn.gameplus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqlLite extends SQLiteOpenHelper {

    private String Table_name_consols = "consols";

    public SqlLite(Context context) {
        super(context, "sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE " + Table_name_consols + "(" +
                "id INTEGER PRIMARY KEY , " +
                "name TXT , " +
                "genre TXT )" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void Insert( String name,  String genre) {

        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("genre", genre);
        database.insert(Table_name_consols, null, contentValues);

    }

    public ArrayList<Consols> getDataConsols() {

        ArrayList<Consols> consolsArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Table_name_consols, null);
        if (c.moveToFirst()) {
            do {
                // Passing values
                int id = c.getInt(0);
                String name = c.getString(1);
                String genre = c.getString(2);
                Consols consols = new Consols(id, name, genre);
                consolsArrayList.add(consols);

                // Do something Here with values
            } while (c.moveToNext());
        }
        c.close();

        return consolsArrayList;

    }

    public boolean getByName(String name, String genre) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select name AND genre from "+ Table_name_consols
                + " where name = ? AND genre = ?", new String[]{name, genre});

        if (c.moveToFirst()) {
            return true;


        }
        c.close();

        return false;
    }

}

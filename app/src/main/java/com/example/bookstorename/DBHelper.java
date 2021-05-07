package com.example.bookstorename;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    Context context;


    public DBHelper(Context context) {
        super(context, "Bookdata.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Booksdetails(name TEXT primary key, author TEXT, price TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Booksdetails");
    }

    public Boolean insertbookdata(String name, String author, String price)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("author", author);
        contentValues.put("price", price);
        long result = DB.insert("Booksdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updatebookdata(String name, String author, String price)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("author", author);
        contentValues.put("price", price);
        Cursor cursor = DB.rawQuery("Select * from Booksdetails  where name = ?", new String[] {name});
        if(cursor.getCount() > 0) {
            long result = DB.update("Booksdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deletebookdata(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Booksdetails  where name = ?", new String[] {name});
        if(cursor.getCount() > 0) {
            long result = DB.delete("Booksdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getbooks()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Booksdetails", null);
        return cursor;
    }

}

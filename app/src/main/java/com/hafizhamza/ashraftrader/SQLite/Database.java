package com.hafizhamza.ashraftrader.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static String TableName="Endcut";
    public Database(Context context) {
        super(context,TableName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
sqLiteDatabase.execSQL("create table "+TableName+"(ID integer primary key unique,Date varchar,CustomerName varchar(50),SellerName varchar(50),Category varchar(50),Weight double,Rate Double,Amount Double,Paid Double,Credit Double,Balance Double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TableName);
        onCreate(sqLiteDatabase);
    }//Insert Data Method
    public void DataEnter(String date,String customername,String sellername,String category,Double weight,Double rate,Double amount,Double paid,Double credit,Double balance)
    {
        SQLiteDatabase dtabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //Table Column
        contentValues.put("Date",date);
        contentValues.put("CustomerName",customername);
        contentValues.put("SellerName",sellername);
        contentValues.put("Category",category);
        contentValues.put("Weight",weight);
        contentValues.put("Rate",rate);
        contentValues.put("Amount",amount);
        contentValues.put("Paid",paid);
        contentValues.put("Credit",credit);
        contentValues.put("Balance",balance);
        //Table Name
        dtabase.insert(TableName,null,contentValues);
    }


    //Update Data Method
    public void UpdateProduct(int id,String date,String customername,String sellername,String category,Double weight,Double rate,Double amount)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Date",date);
        contentValues.put("CustomerName",customername);
        contentValues.put("SellerName",sellername);
        contentValues.put("Category",category);
        contentValues.put("Weight",weight);
        contentValues.put("Rate",rate);
        contentValues.put("Amount",amount);

        database.update("products",contentValues,"id=?",new String[]{Integer.toString(id)});
    }

    //Delete Data Method
    public void Delete(int id)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(TableName,"id=?",new String[]{Integer.toString(id)});
    }

    //Select Data and Show in text But Now Here is Error
    public String[] getAppCategoryDetail() {

        final String TABLE_NAME = "products";

        String selectQuery = "SELECT  * FROM " + TableName;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String data[]      = null;

        if (cursor.moveToFirst()) {
            do {
                // get the data into array, or class variable
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public Cursor fetch(int id) {
        final String TABLE_NAME = "products";

        String selectQuery = "SELECT  * FROM " + TableName ;
        SQLiteDatabase db  = this.getReadableDatabase();

        Cursor cursor      = db.rawQuery(selectQuery, null);
        //Cursor cursor = this.database.query("products", new String[]{Integer.toString(id), "name", "category","prize"}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}

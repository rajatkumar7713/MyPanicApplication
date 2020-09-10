package com.example.mypanicapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import static com.example.mypanicapplication.ContactDetails._name;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    private static final int Database_Version = 2;
    private static final String Database_Name = "contacts.db";
    private static final String Tag = "DataBaseHelper";
    private static final String Table_Name = "emergency_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "name";
    public static final String COL3 = "number";
      String number, name ;
      int id;



    public DatabaseHelper2( Context context, String name, SQLiteDatabase.CursorFactory factory, int database_Version ) {
        super(context, Database_Name, null, 2);

    }

    String query = "CREATE TABLE emergency_table ( ID integer PRIMARY KEY AUTOINCREMENT , name text NOT NULL , number text NOT NULL  );";

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL("DROP TABLE  If EXISTS emergency_table " + Table_Name);
        onCreate(db);
    }

    public boolean saveDetails( ContactDetails contactDetails ) {

        SQLiteDatabase db = getWritableDatabase();
        Cursor res =   db.rawQuery( "SELECT * FROM emergency_table WHERE NAME = ? ", new String[]{ _name } );
        if(res.getCount() ==  0){
            ContentValues values = new ContentValues();
            values.put(COL2, ContactDetails.get_name());
            values.put(COL3, ContactDetails.get_number());
                db.insert(Table_Name, null, values);
                return true;
        }

            return false;



    }

    public void deleteName( String name ) {
        SQLiteDatabase db = getWritableDatabase();
        String qry = "DELETE FROM " + Table_Name + " WHERE " + COL2 + "='" + name + "'";
        db.execSQL(qry);

    }
    public ArrayList<People> getAllDatas() {
        ArrayList<People> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_Name;
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
             id = cursor.getInt(0);
             name = cursor.getString(1);
             number = cursor.getString(2);
            People people = new People(id, name ,number);

            arrayList.add(people);

        }
        this.number=number;
        return arrayList;

    }
    public String[] getphone() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_Name;
        Cursor cursor = db.rawQuery(selectQuery, null);
        String numbersss[] = new String[10];
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                number = cursor.getString(2);
                numbersss[i] = number;
                i++;
            } while (cursor.moveToNext());
            for (int j = 0; j < numbersss.length; j++) {

            }

        }
        return numbersss;
    }
    public Boolean checkNumberDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM  emergency_table";
        Cursor cursor = db.rawQuery(selectQuery, null);
        Boolean flag=false;
        while (cursor.moveToNext()) {
            flag=true;
        }
        return  flag;
    }
}





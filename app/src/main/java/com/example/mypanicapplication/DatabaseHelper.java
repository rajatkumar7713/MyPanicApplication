package com.example.mypanicapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;

import java.util.ArrayList;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int Database_Version = 1;
    private static final String Database_Name = "panic.db";
    private static final String Tag = "DataBaseHelper";
    private static final String Table_Name = "people_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "number";
    private static final String COL4 = "email";
    private static final String COL5 = "address";


    public DatabaseHelper( Context context, String name, SQLiteDatabase.CursorFactory factory,int database_Version ) {
        super( context, Database_Name, null, 1);

    }

    String query = "CREATE TABLE people_table ( ID integer PRIMARY KEY AUTOINCREMENT , name text NOT NULL , number text NOT NULL , email text NOT NULL , address text NOT NULL );";

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( query );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "DROP TABLE  If EXISTS peoples_table " + Table_Name );
        onCreate( db );
      //  System.out.println( "onUpgrade()" );
    }

    public void addDetails( UserDetails userDetails ) {
      //  System.out.println( " name====>>>" + PersonContacts.get_name() );
       // System.out.println( " phone number ====>>>" + PersonContacts.get_number() );

        ContentValues values = new ContentValues();
        values.put( COL2, UserDetails.get_name() );
      //  System.out.println( values );
        values.put( COL3, UserDetails.get_number() );
     //   System.out.println( "==!" +values );
        values.put( COL4, UserDetails.get_email() );
        values.put( COL5, UserDetails.get_address() );
     //   System.out.println( "==========$$==" + values );
        SQLiteDatabase db = getWritableDatabase();
      //  System.out.println(db);
     //   System.out.println("it worked");
        db.insert( Table_Name, null, values );
        db.close();

    }

    public void deleteName( String name ) {
        SQLiteDatabase db = getWritableDatabase();
     //   System.out.println( "Deleted" );
     //   System.out.println( name );
        String qry = "DELETE FROM " + Table_Name + " WHERE " + COL2 + "='" + name + "'";
//      System.out.println(qry);

        db.execSQL( qry );
    }


//    public ArrayList<UserDetails> getAllDatas() {
//        ArrayList<UserDetails> arrayList = new ArrayList<>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        String selectQuery = "SELECT  * FROM " + Table_Name;
//        Cursor cursor = db.rawQuery( selectQuery, null );
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt( 0 );
//            String name = cursor.getString( 1 );
//            String number = cursor.getString( 2 );
//            String email = cursor.getString( 3 );
//            String address = cursor.getString( 4 );
//            UserDetails userDetails = new UserDetails( id,name,number,email,address);
//            arrayList.add( userDetails );
//
//        }
//
//
//        return arrayList;

//        //  db.close();
//       //  return ;
//


    }




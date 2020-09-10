package com.example.mypanicapplication;


public class ContactDetails {
    public int id;
    public static String _name;
    public static String _number;


    public ContactDetails( String name, String number ) {
        this._name = name;
        this._number = number;


    }


    public int getId() {
        return id;
    }

    public static String get_name() {
        return _name;
    }

    public static String get_number() {
        return _number;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public void set_name( String _name ) {
        this._name = _name;
    }

    public void set_number( String _number1 ) {
        this._number = _number;
    }


}
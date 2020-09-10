package com.example.mypanicapplication;

public class UserDetails {
    public int id;
    public static String _name;
    public static String _number;
    public static  String _email;
    public static String  _address;

    public UserDetails( int id, String name, String number ) {
    }
    public UserDetails(String name, String number,String email,String address){
        this._name=name;
        this._number=number;
        this._email=email;
        this._address=address;
    }


    public  int getId() {
        return id;
    }

    public static String get_name() {
        return _name;
    }

    public static String get_number() {
        return _number;
    }

    public static String get_email() {
        return _email;
    }

    public static String get_address() {
        return _address;
    }

    public  void setId( int id ) {
        this.id = id;
    }

    public  void set_name( String _name ) {
        this._name = _name;
    }

    public  void set_number( String _number ) {
        this._number = _number;
    }

    public  void set_email( String _email ) {
        this._email = _email;
    }

    public  void set_address( String _address ) {
        this._address = _address;
    }
}

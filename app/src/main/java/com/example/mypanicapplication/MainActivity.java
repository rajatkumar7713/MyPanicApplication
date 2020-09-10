package com.example.mypanicapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    Button btnmove, btnStore, btnmv, bckbtn;
    EditText uName;
    EditText uNumber;
    EditText uEmail;
    EditText uAddress;
    DatabaseHelper dbhelper;
    ArrayList<UserDetails> arrayList;

    SharedPreferences sp;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("Register",MODE_PRIVATE);
        if(sp.getBoolean("Register",false)){
            goTocontactsList();

        }


        uName = (EditText) findViewById(R.id.userName);
        uNumber = (EditText) findViewById(R.id.userNumber);
        uEmail = (EditText) findViewById(R.id.userEmail);
        uAddress = (EditText) findViewById(R.id.userAddress);
        btnStore = (Button) findViewById(R.id.btnSave1);
        dbhelper = new DatabaseHelper(this, null, null, 1);
     //   bckbtn = findViewById(R.id.bckToMain);
      //  bckbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick( View view ) {
//                moveToMainScreen();
//            }
//        });
        arrayList = new ArrayList<>();
     //   btnmove=(Button)findViewById(R.id.btnToList);
//        btnmove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick( View view ) {
//                recordingpage();
//            }
//        });
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                saveOnClicked();
                sp.edit().putBoolean("Register",true).apply();
            }
        });
//        SharedPreferences prefs= getSharedPreferences("prefs",0);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putBoolean("hasRegistered", true);
//        editor.commit();
//        SharedPreferences settings = getSharedPreferences ("prefs", 0);
//        boolean hasRegistered = settings.getBoolean("hasRegistered", false);
//        if(hasRegistered){
//            Intent i = new Intent(MainActivity.this, contactsList.class);
//            startActivity(i);
//
//        }


    }

    public void saveOnClicked( ) {
        UserDetails userDetails = new UserDetails(uName.getText().toString(), uNumber.getText().toString(), uEmail.getText().toString(), uAddress.getText().toString());
       // System.out.println("worksifsdjkgb");
        dbhelper.addDetails(userDetails);
     //   System.out.println(userDetails);
     //   System.out.println("workifdsg");
        if( TextUtils.isEmpty(uName.getText()))  {
            Toast.makeText(MainActivity.this, "ENTER Username",
                    Toast.LENGTH_LONG).show();

        }

        else if( TextUtils.isEmpty(uNumber.getText())){
            Toast.makeText(MainActivity.this, "ENTER Number",
                    Toast.LENGTH_LONG).show();

        }
        else if( TextUtils.isEmpty(uEmail.getText())){
            Toast.makeText(MainActivity.this, "ENTER Email",
                    Toast.LENGTH_LONG).show();
        }
        else if( TextUtils.isEmpty(uAddress.getText())){
            Toast.makeText(MainActivity.this, "ENTER Address",
                    Toast.LENGTH_LONG).show();
        }
        else{
            Intent i = new Intent(MainActivity.this, contactsList.class);
            startActivity(i);
         //   System.out.println("long ago");
        }
    }


//    public void moveTocontactsList() {
//        System.out.println("Checking for emptiness");
//        if( TextUtils.isEmpty(uName.getText()))  {
//                Toast.makeText(MainActivity.this, "ENTER Username",
//                        Toast.LENGTH_LONG).show();
//
//        }
//
//        else if( TextUtils.isEmpty(uNumber.getText())){
//                Toast.makeText(MainActivity.this, "ENTER Number",
//                        Toast.LENGTH_LONG).show();
//
//        }
//        else if( TextUtils.isEmpty(uEmail.getText())){
//                Toast.makeText(MainActivity.this, "ENTER Email",
//                        Toast.LENGTH_LONG).show();
//        }
//        else if( TextUtils.isEmpty(uAddress.getText())){
//                Toast.makeText(MainActivity.this, "ENTER Address",
//                        Toast.LENGTH_LONG).show();
//        }
//        else{
//            Intent i = new Intent(MainActivity.this, contactsList.class);
//            startActivity(i);
//            System.out.println("long ago");
//        }
//    }
//
    public void recordingpage(){
        Intent i = new Intent(MainActivity.this, contactsList.class);
        startActivity(i);

    }


//    public void moveToMainScreen() {
//        Intent intent = new Intent(MainActivity.this, locationTracker.class);
//        startActivity(intent);
//    }
    public void  goTocontactsList(){
        Intent i = new Intent(this,contactsList.class);
        startActivity(i);
    }
}


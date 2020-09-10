package com.example.mypanicapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class contactsList extends AppCompatActivity {
     EditText nameOne,numOne;
     private Button btnStore2,btnmove2;
     DatabaseHelper2 dbhelper;
     ListView li;
    MyAdapter myAdapter;
    ArrayList<People> arrayList;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        nameOne=(EditText) findViewById(R.id.nameOne);
        numOne=(EditText)findViewById( R.id.numOne );
        dbhelper = new DatabaseHelper2(this,null,null,1 );
        btnStore2=(Button)findViewById(R.id.btnSave2);
        btnStore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                saveContacts();

            }
        });

        btnmove2=findViewById(R.id.backId);
        btnmove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                moveToMainActivity();
            }
        });
        li=(ListView)findViewById(R.id.listView);
        arrayList=dbhelper.getAllDatas();
        myAdapter=new MyAdapter(this,arrayList);
        printDatabases();


    }
    public void saveContacts(){
        if( TextUtils.isEmpty(nameOne.getText()))  {
            Toast.makeText(contactsList.this, "Enter Name",
                    Toast.LENGTH_LONG).show();

        }
        else if(TextUtils.isEmpty(numOne.getText())){ Toast.makeText(contactsList.this, "Enter Number ",
                Toast.LENGTH_LONG).show();
        }
        else {
            ContactDetails contactDetails = new ContactDetails(nameOne.getText().toString(), numOne.getText().toString());
            System.out.println(" 3. working");
            dbhelper.saveDetails(contactDetails);
            if (false){
            Toast.makeText(contactsList.this, "Enter Another Name",
                    Toast.LENGTH_LONG).show();}
        }
        printDatabases();

    }


    public void moveToMainActivity(){
        Intent intent =new Intent(contactsList.this,locationTracker.class);
        startActivity(intent);
    }
    public void printDatabases(){
        arrayList=dbhelper.getAllDatas();
        System.out.println("1" +arrayList);
        myAdapter=new MyAdapter(this,arrayList);
        System.out.println( "2" +myAdapter);
        li.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        if(!dbhelper.checkNumberDB()){
            Toast.makeText(contactsList.this, "Enter atleast one contact number Details",
                    Toast.LENGTH_LONG).show();
        }

    }
}

package com.example.mypanicapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;
import java.io.IOException;
import java.util.Random;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class locationTracker  extends AppCompatActivity {
    Button start, playbtn,stopbtn;
    Button settingbtn;
    Button  btnstop;
    double lat;
    double lon;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    Random random;
    DatabaseHelper2 databaseHelper2;
    String sphon[];
    String RandomAudioFileName = "Voice Recordings";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer;
    Chronometer mychronometer;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_tracker);

        ActivityCompat.requestPermissions(locationTracker.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        MyReciever mReceiver = new MyReciever (this);
        registerReceiver(mReceiver, filter);

        databaseHelper2=new DatabaseHelper2(this,null,null,1);
         sphon =databaseHelper2.getphone();
        playbtn=(Button)findViewById(R.id.btnplay);
        mychronometer = (Chronometer) findViewById(R.id.chronometer);
        btnstop = (Button) findViewById(R.id.stpBtn);

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                playRecording();
            }
        });
        playbtn.setVisibility(View.GONE);

        stopbtn=(Button)findViewById(R.id.btnstp);
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                stopPlay();

            }
        });
        stopbtn.setVisibility(View.GONE);
        settingbtn = (Button) findViewById(R.id.settingbtn);
        start = (Button) findViewById(R.id.startAction);
        start.setVisibility(View.VISIBLE);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick( View view ) {

                if(databaseHelper2.checkNumberDB()){
                    mychronometer.setBase(SystemClock.elapsedRealtime());
                    mychronometer.start();
                    startRecording();
                    for(int i=0;i<sphon.length;i++){
                        System.out.println("Rajat1" +sphon[i]);
                        if(sphon[i]!=null) {
                            System.out.println("Rajat==" +sphon[i]);
                            sendMsgs(sphon[i]);
                        }
                    }
                    start.setVisibility(View.GONE);
                    btnstop.setVisibility(View.VISIBLE);
                    if(start==start){
                        playbtn.setVisibility(View.GONE);
                    }



                }else{
                    Intent intent=new Intent(locationTracker.this,contactsList.class);
                    startActivity(intent);
            }
            }


        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                moveToMainActivity();
            }
        });


        btnstop.setEnabled(false);

        random = new Random();

        btnstop.setOnClickListener(new View.OnClickListener() {
            public void onClick( View view ) {
                stopRecording();
                start.setVisibility(View.VISIBLE);
                playbtn.setVisibility(View.VISIBLE);
            }
        });



    }


    public void sendMsgs(String num) {
        GPStracker g = new GPStracker(getApplicationContext());
        Location l = g.getLocation();

        System.out.println(l);
        if (l != null) {
            lat = l.getLatitude();
            lon = l.getLongitude();


        }

        DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(null, null, null, 2);


        String toPhoneNumber = num;
        System.out.println(toPhoneNumber);
        String smsMessage = " I need help " + " http://maps.google.com/maps?saddr= " + lat + "," + lon;
        System.out.println(smsMessage);
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhoneNumber, null, smsMessage.toString(), null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Sending SMS failed.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void startRecording() {
        if (checkPermission()) {

            AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CreateRandomAudioFileName(5) + "AudioRecording.mp4";

            MediaRecorderReady();

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            btnstop.setEnabled(true);

        } else {

            requestPermission();
        }


    }

    public void stopRecording() {
        mediaRecorder.stop();
        mychronometer.stop();

        btnstop.setEnabled(false);


        playbtn.setVisibility(View.VISIBLE);
        Toast.makeText(locationTracker.this, "Recording Completed", Toast.LENGTH_LONG).show();

    }


    public void playRecording() {
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(AudioSavePathInDevice);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
        playbtn.setVisibility(View.GONE);
        stopbtn.setVisibility(View.VISIBLE);
        stopbtn.setEnabled(true);
    }

    public void stopPlay() {

        btnstop.setEnabled(false);
        playbtn.setEnabled(true);
        if (mediaPlayer != null) {

            mediaPlayer.stop();
            mediaPlayer.release();

            MediaRecorderReady();
            if(stopbtn==stopbtn){
                stopbtn.setVisibility(View.GONE);
                playbtn.setVisibility(View.VISIBLE);
                start.setVisibility(View.VISIBLE);
            }



        }


    }

    public void MediaRecorderReady() {

        mediaRecorder = new MediaRecorder();

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        mediaRecorder.setOutputFile(AudioSavePathInDevice);

    }

    public String CreateRandomAudioFileName( int string ) {

        StringBuilder stringBuilder = new StringBuilder(string);

        int i = 0;
        while (i < string) {

            stringBuilder.append(RandomAudioFileName.charAt(random.nextInt(RandomAudioFileName.length())));

            i++;
        }
        return stringBuilder.toString();

    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(locationTracker.this, new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);

    }

    @Override
    public void onRequestPermissionsResult( int requestCode, String permissions[], int[] grantResults ) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {

                    boolean StoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {

                        Toast.makeText(locationTracker.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(locationTracker.this, "Permission Denied", Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    public boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    public void moveToMainActivity() {
        Intent intent = new Intent(locationTracker.this, contactsList.class);
        startActivity(intent);
    }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.location_tracker, menu);
    return true;
}

}

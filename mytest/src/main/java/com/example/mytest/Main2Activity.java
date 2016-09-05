package com.example.mytest;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private Button mButton ;
    private Button mButton1 ;
    private Button mButton2 ;
    private MyService.MyIBinder mBinder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mButton =(Button)findViewById(R.id.ma2_bt) ;
        mButton1=(Button)findViewById(R.id.ma2_bt1) ;
        mButton2=(Button)findViewById(R.id.ma2_bt2) ;
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent("com.example.mytest.SERVER_SERVICE"));
            }
        });
        final ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mBinder =(MyService.MyIBinder) service ;
                Toast.makeText(Main2Activity.this, mBinder.getString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent() ;
                intent.setAction("com.example.mytest.MYSERVICE") ;
                bindService(intent,conn, Service.BIND_AUTO_CREATE) ;

            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent() ;
                setResult(0,intent);
                finish();
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            }
        });
      }
}

package com.example.mytest;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    private TextView mTextView ;
    private Button mButton1 ;
    private Button mButton2 ;
    private Intent mIntent1 ;
    private Intent mIntent2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.ma_tv);
        mButton1 =(Button)findViewById(R.id.ma_bt1) ;
        mButton2 =(Button)findViewById(R.id.ma_bt2) ;
        mIntent1= new Intent(this,Main2Activity.class) ;
        mIntent1.putExtra("1","one") ;
        mIntent2 = new Intent(this,Main2Activity.class) ;
        mIntent2.putExtra("2","two") ;
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(mIntent1,0);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);

            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(mIntent2,1);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0&&resultCode==0){
            mTextView.setText(data.getStringExtra("1"));
        }else if(requestCode==1&&resultCode==0){
            mTextView.setText(data.getStringExtra("2"));
        }else{
            Toast.makeText(MainActivity.this, "not find value", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 双击返回退出程序
     */
    private static boolean isexit = false ;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            isexit = false ;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            if(!isexit){
                isexit = true ;
                Toast.makeText(MainActivity.this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(0,2000) ;
            }else{
                exitApp();
            }
            return false ;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exitApp(){
        Context context = this.getApplicationContext() ;
        ActivityCollector.finishAll();
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE) ;
        manager.killBackgroundProcesses(context.getPackageName());
        System.exit(0);
    }
}

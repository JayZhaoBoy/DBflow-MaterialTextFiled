package com.example.dbflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbflow.Utils.MD5Utils;
import com.example.dbflow.db.User;
import com.example.dbflow.db.User_Table;
import com.raizlabs.android.dbflow.sql.language.Select;

public class Main2Activity extends AppCompatActivity {
    private EditText mEt_Username;
    private EditText mEt_Password;
    private Button mBt_enter;
    private String TAG = "Main2Activity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mEt_Username = (EditText) findViewById(R.id.mian2_et_username);
        mEt_Password = (EditText) findViewById(R.id.main2_et_password);
        mBt_enter = (Button) findViewById(R.id.main2_bt_enter);
        mBt_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEt_Username.getText().toString();
                String password = mEt_Password.getText().toString();
                try {
                    String MD5password = MD5Utils.encode(password+username);
                    User user = new Select().from(User.class)
                            .where(User_Table.name.eq(username))
                            .querySingle();
                    Log.d(TAG, "onClick: "+user.name);
                    Log.d(TAG, "onClick: "+user.password);
                    Log.d(TAG, "onClick: "+MD5password);
                    if(user!=null && MD5password.equals(user.password)){
                        Toast.makeText(Main2Activity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(Main2Activity.this, "请输入正确的账号和密码", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}

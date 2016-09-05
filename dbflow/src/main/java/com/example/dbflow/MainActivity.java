package com.example.dbflow;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbflow.Utils.MD5Utils;
import com.example.dbflow.db.People;
import com.example.dbflow.db.User;
import com.example.dbflow.db.User_Table;
import com.github.florent37.materialtextfield.MaterialTextField;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.w3c.dom.Text;

import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private EditText mEt_username;
    private EditText mEt_password;
    private TextView mTv_sign;
    private TextView mTv_sign1;
    private Button mBt_regist;
    private MaterialTextField mMt;
    private boolean mPwIslogical = false;
    private boolean mUnIslogical = false;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMt = (MaterialTextField) findViewById(R.id.main_mt_username);
        mTv_sign = (TextView) findViewById(R.id.main_tv);
        mTv_sign1 = (TextView) findViewById(R.id.main_tv1);
        mBt_regist = (Button) findViewById(R.id.main_bt_regist);
        mEt_username = (EditText) findViewById(R.id.mian_et_username);
        mEt_password = (EditText) findViewById(R.id.main_et_password);
        mBt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPwIslogical && mUnIslogical && mCount == 0) {
                    String username = mEt_username.getText().toString();
                    String password = mEt_password.getText().toString();
                    try {
                        //加严处理 确保 密码独一性和安全性
                        String MD5password = MD5Utils.encode(password+username) ;
                        SQLite.insert(User.class)
                                .columns(User_Table.name, User_Table.password)
                                .values(username, MD5password)
                                .execute();
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "please input right username and password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        mEt_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = mEt_password.getText().toString();
                Pattern pattern = Pattern.compile("^.{6,}$", Pattern.CASE_INSENSITIVE);
                mPwIslogical = pattern.matcher(password).matches();
                if (!mPwIslogical) {
                    mTv_sign1.setVisibility(View.VISIBLE);
                    mTv_sign1.setTextColor(Color.RED);
                    mTv_sign1.setText("(密码不得小于6位)");
                } else {
                    mTv_sign1.setVisibility(View.GONE);
                }

            }
        });
        mEt_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String username = mEt_username.getText().toString();
                //正则限定输入格式
                Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{4,15}$", Pattern.CASE_INSENSITIVE);
                mUnIslogical = pattern.matcher(username).matches();
                mCount = (int) SQLite.select().from(User.class).where(User_Table.name.eq(username)).count();
                //动态显示格式提醒
                if (!mUnIslogical) {
                    mTv_sign.setVisibility(View.VISIBLE);
                    mTv_sign.setTextColor(Color.RED);
                    mTv_sign.setText("必须字母开头，允许5-16字节，允许字母数字下划线)");
                } else if (mCount > 0) {
                    mTv_sign.setVisibility(View.VISIBLE);
                    mTv_sign.setTextColor(Color.RED);
                    mTv_sign.setText("该账号已经存在");
                } else {
                    mTv_sign.setVisibility(View.GONE);
                }
            }
        });

    }

}

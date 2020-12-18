package com.lxh.bookmark;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    //注册按钮
    Button btnRegister;

    //账号输入框
    EditText etAccount;

    //密码输入框
    EditText etPassword;

    //重复密码输入框
    EditText etRePassword;

    //从本地获取已存储的用户（账号密码）列表
    final List<User> userList = SharedPreferencesUtil.getListData("userList", User.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //注册按钮
        btnRegister = findViewById(R.id.btn_register);

        //账号输入框
        etAccount = findViewById(R.id.et_account);

        //密码输入框
        etPassword = findViewById(R.id.et_password);

        //重复密码输入框
        etRePassword = findViewById(R.id.et_re_password);

        //注册按钮
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputAccount = etAccount.getText().toString();
                String inputPassword = etPassword.getText().toString();
                String inputRePassword = etRePassword.getText().toString();

                //检查两个输入的密码是否一致
                if (!inputPassword.equals(inputRePassword)) {
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                } else { //两次密码一致

                    //是否可以注册
                    boolean canRegister = true;

                    //遍历账户列表检查账号是否存在
                    for (User user : userList) {
                        if (user.account.equals(inputAccount)) {
                            Toast.makeText(RegisterActivity.this, "账号已存在", Toast.LENGTH_SHORT).show();
                            canRegister = false;
                        }
                    }

                    if (canRegister) {//可以注册
                        //添加当前账户进账户列表
                        userList.add(new User(System.currentTimeMillis(), inputAccount, inputPassword));
                        //通过SharedPreferences将账户列表存入
                        SharedPreferencesUtil.putListData("userList", userList);
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        //关闭当前界面 返回登陆界面去
                        finish();
                    }
                }

            }
        });

    }
}
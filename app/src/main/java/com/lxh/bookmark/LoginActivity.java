package com.lxh.bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    //登录按钮
    Button btnLogin;

    //去注册按钮
    Button btnRegister;

    //重置密码按钮
    Button btnResetPassword;

    //账号输入框
    EditText etAccount;

    //密码输入框
    EditText etPassword;

    //从本地获取已存储的用户（账号密码）列表
    List<User> userList = SharedPreferencesUtil.getListData("userList", User.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //登录按钮
        btnLogin = findViewById(R.id.btn_login);

        //注册按钮
        btnRegister = findViewById(R.id.btn_register);

        //重置密码按钮
        btnResetPassword = findViewById(R.id.btn_reset_password);

        //账号输入框
        etAccount = findViewById(R.id.et_account);

        //密码输入框
        etPassword = findViewById(R.id.et_password);



        //登录按钮点击事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //输入的账号
                String inputAccount = etAccount.getText().toString();

                //输入的密码
                String inputPassword = etPassword.getText().toString();

                //能否登录
                boolean canLogin = true;

                //遍历本地账户列表
                for (User user : userList) {
                    if (user.account.equals(inputAccount)) {//本地缓存列表中有这个账户
                        if (user.password.equals(inputPassword)) {//输入密码正确
                            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            //跳转至主界面
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            canLogin = false;
                            Toast.makeText(LoginActivity.this, "密码输入错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                if (!canLogin) {
                    //未找到输入的账户
                    Toast.makeText(LoginActivity.this, "请检查账号", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //去注册按钮点击事件
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至注册界面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //重置密码按钮点击事件
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至修改密码界面
                Intent intent = new Intent(LoginActivity.this, PasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        userList = SharedPreferencesUtil.getListData("userList", User.class);
    }
}
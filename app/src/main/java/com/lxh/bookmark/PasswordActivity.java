package com.lxh.bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class PasswordActivity extends AppCompatActivity {

    //确认按钮
    Button btnConfirm;

    //账号输入框
    EditText etAccount;

    //旧密码输入框
    EditText etOldPassword;

    //新密码输入框
    EditText etNewPassword;

    //从本地获取已存储的用户（账号密码）列表
    final List<User> userList = SharedPreferencesUtil.getListData("userList", User.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //确认按钮
        btnConfirm = findViewById(R.id.btn_confirm);

        //账号输入框
        etAccount = findViewById(R.id.et_account);

        //密码输入框
        etNewPassword = findViewById(R.id.et_new_password);

        //重复密码输入框
        etOldPassword = findViewById(R.id.et_old_password);

        //确认按钮点击事件
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputAccount = etAccount.getText().toString();
                String inputOldPassword = etOldPassword.getText().toString();
                String inputNewPassword = etNewPassword.getText().toString();

                //能否修改
                boolean canEdit = false;

                //遍历用户列表
                for (User user : userList) {
                    if (user.account.equals(inputAccount)) {
                        //有此账号
                        if (inputOldPassword.equals(user.password)) {
                            //原密码输入正确
                            canEdit = true;
                            int index = userList.indexOf(user);
                            //新密码设置入列表
                            userList.set(index, new User(user.id, user.account, inputNewPassword));
                            SharedPreferencesUtil.putListData("userList", userList);
                            Toast.makeText(PasswordActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }

                //没找到这个账号，不能修改
                if (!canEdit)
                    Toast.makeText(PasswordActivity.this, "请检查账户或密码", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
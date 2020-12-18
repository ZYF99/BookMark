package com.lxh.bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    //内容输入框
    EditText etContent;

    //完成按钮
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        etContent = findViewById(R.id.et_content);
        btnFinish = findViewById(R.id.btn_finish);
        //完成按钮点击事件
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过SharedPreferences从本地读取备忘录列表
                List<BookMark> list = SharedPreferencesUtil.getListData("bookMarkList", BookMark.class);
                //添加一条备忘录到列表
                list.add(new BookMark(System.currentTimeMillis(), etContent.getText().toString(), System.currentTimeMillis()));
                //将添加后的列表通过SharedPreferences重新存入本地
                SharedPreferencesUtil.putListData("bookMarkList", list);
                //关闭当前Activity界面
                finish();
            }
        });
    }
}
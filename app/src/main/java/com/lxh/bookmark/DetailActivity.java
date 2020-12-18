package com.lxh.bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    EditText etContent;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        etContent = findViewById(R.id.et_content);
        btnFinish = findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BookMark> list = SharedPreferencesUtil.getListData("bookMarkList", BookMark.class);
                list.add(new BookMark(System.currentTimeMillis(), etContent.getText().toString(), System.currentTimeMillis()));
                SharedPreferencesUtil.putListData("bookMarkList", list);
                finish();
            }
        });
    }
}
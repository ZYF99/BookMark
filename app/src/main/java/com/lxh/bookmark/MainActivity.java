package com.lxh.bookmark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //记事列表
    RecyclerView recyclerView;

    //添加按钮
    Button btnAdd;

    //记事本列表适配器
    BookMarkRecyclerAdapter recyclerAdapter;

    //记事本列表
    List<BookMark> bookMarks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_mark_book);
        btnAdd = findViewById(R.id.btn_add);

        bookMarks = SharedPreferencesUtil.getListData("bookMarkList", BookMark.class);

        //创建列表适配器
        recyclerAdapter = new BookMarkRecyclerAdapter(MainActivity.this, bookMarks);

        //初始化它的layoutManager 从上至下线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //设置列表适配器
        recyclerView.setAdapter(recyclerAdapter);

        //添加点击事件
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerAdapter != null) {
            recyclerAdapter.list = SharedPreferencesUtil.getListData("bookMarkList", BookMark.class);
            recyclerAdapter.notifyDataSetChanged();
        }

    }
}
package com.lxh.bookmark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookMarkRecyclerAdapter extends RecyclerView.Adapter<BookMarkRecyclerAdapter.BookMarkViewHolder> {

    //上下文 可以是Activity、Fragment
    Context context;

    //备忘录列表
    public List<BookMark> list;

    //构造方法
    public BookMarkRecyclerAdapter(Context context, List<BookMark> list) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public BookMarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book_mark, parent, false);
        return new BookMarkViewHolder(view);
    }

    //每个Item绑定数据的方法
    @Override
    public void onBindViewHolder(@NonNull BookMarkViewHolder holder, int position) {
        holder.tvContent.setText(list.get(position).content);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String timeStr = simpleDateFormat.format(new Date(list.get(position).getDate()));
        holder.tvTime.setText(timeStr);
    }

    //获得列表中item的总数
    @Override
    public int getItemCount() {
        return list.size();
    }

    static class BookMarkViewHolder extends RecyclerView.ViewHolder {

        //内容的TextView
        TextView tvContent;

        //时间的TextView;
        TextView tvTime;

        public BookMarkViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}

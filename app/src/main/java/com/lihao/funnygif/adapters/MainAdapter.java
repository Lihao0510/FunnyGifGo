package com.lihao.funnygif.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lihao.funnygif.R;
import com.lihao.funnygif.modle.GifBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private List<GifBean> mList;
    private Context mContext;

    public MainAdapter(Context mContext, List<GifBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void addDatas(List<GifBean> addList){
        mList.addAll(addList);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.gif_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mList.get(position).getTitle());
        Glide.with(mContext).load(mList.get(position).getGifUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.main_textview);
            imageView = (ImageView) itemView.findViewById(R.id.main_imageview);
        }
    }
}

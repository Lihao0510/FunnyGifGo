package com.lihao.funnygif.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lihao.funnygif.R;
import com.lihao.funnygif.modle.GifBean;
import com.lihao.funnygif.views.GifDetailActivity;
import com.lihao.funnygif.views.MainActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private List<GifBean> mList;
    private Context mContext;
    private int mWidth;
    private WindowManager wm;

    public MainAdapter(Context mContext, List<GifBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth() * 5 / 12;
        Log.d("Lihao", mWidth + "");
        wm = null;
        //Toast.makeText(mContext,mWidth + "" ,Toast.LENGTH_SHORT).show();
    }

    public void addDatas(List<GifBean> addList) {
        mList.addAll(addList);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.gif_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final int location = position;
        holder.textView.setText(mList.get(position).getTitle());
        Glide.with(mContext).load(mList.get(position).getGifUrl()).asGif().override(mWidth, (mList.get(position).getHeight() * mWidth) / mList.get(position).getWidth()).diskCacheStrategy(DiskCacheStrategy.NONE).fitCenter().into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GifDetailActivity.class);
                intent.putExtra("url", mList.get(location).getGifUrl());
                intent.putExtra("name", mList.get(location).getTitle());
                intent.putExtra("width", mList.get(location).getWidth());
                intent.putExtra("height", mList.get(location).getHeight());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.main_textview);
            imageView = (ImageView) itemView.findViewById(R.id.main_imageview);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.main_linearlayout);
        }
    }
}

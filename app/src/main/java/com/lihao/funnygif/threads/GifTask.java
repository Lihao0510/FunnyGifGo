package com.lihao.funnygif.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.lihao.funnygif.adapters.MainAdapter;
import com.lihao.funnygif.modle.GifBean;
import com.lihao.funnygif.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class GifTask extends AsyncTask<String, Void, List<GifBean>> {
    private RecyclerView mRecyclerView;
    private Context mContext;

    public GifTask(RecyclerView recyclerView, Context context) {
        mRecyclerView = recyclerView;
        mContext = context;
    }

    @Override
    protected List<GifBean> doInBackground(String... params) {
        String requestUrl = Constants.BASE_URL + Constants.SEPARATOR + params[0];
        List<GifBean> mList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(requestUrl).get();
            Elements gifLinks = document.getElementsByAttributeValue("class", "img");
            for (Element element : gifLinks) {
                Elements subLink = element.getElementsByTag("img");
                for (Element subElement : subLink) {
                    String gifName = subElement.attr("alt");
                    gifName = gifName.replace("的gif动态图片",".");
                    String gifUrl = subElement.attr("data-original");
                    Log.d("Lihao", gifName);
                    Log.d("Lihao", gifUrl);
                    mList.add(new GifBean(gifName, gifUrl));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mList;
    }

    @Override
    protected void onPostExecute(List<GifBean> gifBeen) {
        MainAdapter mAdapter = new MainAdapter(mContext, gifBeen);
        StaggeredGridLayoutManager mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}

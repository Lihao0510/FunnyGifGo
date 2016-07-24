package com.lihao.funnygif.modle;

/**
 * Created by Administrator on 2016/7/24.
 */
public class GifBean {

    public String title;
    public String gifUrl;

    public GifBean(String title, String gifUrl) {
        this.title = title;
        this.gifUrl = gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public String getTitle() {
        return title;
    }
}

package com.lihao.funnygif.modle;

/**
 * Created by Administrator on 2016/7/24.
 */
public class GifBean {

    public String title;
    public String gifUrl;
    public int width;
    public int height;

    public GifBean(String title, String gifUrl, int width, int height) {
        this.title = title;
        this.gifUrl = gifUrl;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public String getGifUrl() {
        return gifUrl;
    }

    public String getTitle() {
        return title;
    }
}

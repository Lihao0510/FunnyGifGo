package com.lihao.funnygif.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ShareUtil {

    public static void share(Context context, File content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/gif");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享Gif链接给好友");
        Uri uri = Uri.fromFile(content);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享Gif链接给好友"));
    }
}

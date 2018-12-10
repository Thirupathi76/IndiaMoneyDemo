package com.demo.indiamoneydemo.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Santosh Avasarala on 16-04-2017.
 */

public class AppUtil {

    public static String getVersionNumber(Context context) {
        try {
            return "Version: " + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getVideoId(String ytUrl) {

        /* String vId = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()) {
            vId = matcher.group(1);
        }
        return vId;*/

        String videoId = "";
        if (ytUrl != null && ytUrl.trim().length() > 0 && ytUrl.startsWith("http")) {

            String expression = "^.*((youtu.be"+ "/)" + "|(v/)|(/u/w/)|(embed/)|(watch\\?))\\??v?=?([^#&\\?]*).*"; // var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
            Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(ytUrl);
            if (matcher.matches()) {
                String groupIndex1 = matcher.group(7);
                if(groupIndex1!=null && groupIndex1.length()==11)
                    videoId = groupIndex1;
                Log.e("video ID", videoId+" , "+ytUrl);
            }

        }
        return videoId;
    }

    public static void loadNewsImage(Context context, String imageUrl, int placeholder, ImageView imageView) {
        if (imageUrl != null) {
            imageUrl.replaceAll(" ", "%20");
//            Picasso.with(context).load(AppApi.BASE_NEWS_URL + imageUrl).placeholder(R.mipmap.naa_varthalu_logo_2).error(R.mipmap.naa_varthalu_logo_2).into(imageView);
        } else
            imageView.setImageResource(placeholder);
    }

    public static void loadVideoImage(Context context, String imageUrl, ImageView imageView) {
//        Picasso.with(context).load(imageUrl).placeholder(R.mipmap.naa_varthalu_logo_2).error(R.mipmap.naa_varthalu_logo_2).into(imageView);
    }

    /*public static String getDisplayTime(String date) {
        try {
            SimpleDateFormat serverDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a, MMM dd, yyyy");
            return sdf.format(serverDate.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }*/

    public static String getDisplayTime(String date) {
        try {
            SimpleDateFormat serverDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
            return sdf.format(serverDate.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getCreatedDate(String date) {
        try {
            SimpleDateFormat serverDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
            return sdf.format(serverDate.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void setHtmlText(String text, TextView textView) {
        if (text != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.setText(Html.fromHtml(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT).toString(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                textView.setText(Html.fromHtml(Html.fromHtml(text).toString()));
            }
        }
    }

    public static String getHtmlText(String text) {
        if (text != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT).toString(), Html.FROM_HTML_MODE_COMPACT).toString();
            } else {
                return Html.fromHtml(Html.fromHtml(text).toString()).toString();
            }
        }
        return "";
    }


}

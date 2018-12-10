package com.demo.indiamoneydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.indiamoneydemo.NewsActivity;
import com.demo.indiamoneydemo.utils.AppUtil;
import com.demo.indiamoneydemo.MainActivity;
import com.demo.indiamoneydemo.bean.NewsItem;
import com.demo.indiamoneydemo.R;

import java.util.ArrayList;
import java.util.List;

public class TopNewsAdapter extends PagerAdapter {

    private Context mContext;
    private List<NewsItem> mTopNewsItems;
    private List<NewsItem> mNewsItems;

    public TopNewsAdapter(Context mContext, List<NewsItem> topNewsItems) {
        this.mContext = mContext;
        this.mTopNewsItems = topNewsItems;
    }

    @Override
    public int getCount() {
        return mTopNewsItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.top_news_item, container, false);

        NewsItem item = mTopNewsItems.get(position);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageview_top_news_item);
        imageView.setImageResource(item.getNewsImage());
//        ImageView videoIcon = (ImageView) itemView.findViewById(R.id.imageview_news_video_icon);

        /*if (!TextUtils.isEmpty(item.getNewsImage())) {
//            videoIcon.setVisibility(View.VISIBLE);
            String url = "http://img.youtube.com/vi/%s/hqdefault.jpg";
            String videoId = AppUtil.getVideoId(item.getNewsImage());
            if (videoId != null)
                AppUtil.loadVideoImage(mContext, String.format(url, videoId), imageView);
        } else {
//            videoIcon.setVisibility(View.GONE);
//            AppUtil.loadNewsImage(mContext, item.getNewsImage(), R.mipmap.naa_varthalu_logo_2, imageView);
        }*/

        TextView textView = (TextView) itemView.findViewById(R.id.textview_top_news_title);
        AppUtil.setHtmlText(item.getNewsTitle(), textView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Top News Adapter", "mTopNewsViewPager onItemClick " + position);

                Intent intent = new Intent(mContext, NewsActivity.class);
                intent.putExtra("newsPosition", position);
                intent.putParcelableArrayListExtra("newsItems", (ArrayList) mNewsItems);
                mContext.startActivity(intent);
            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}

package com.demo.indiamoneydemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.indiamoneydemo.R;
import com.demo.indiamoneydemo.utils.TextView_AvenirLTStd_Roman;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
//    private Integer[] images = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.news_item_list, null);
        ImageView imageView = view.findViewById(R.id.news_image);
        TextView text_title = view.findViewById(R.id.news_title);
        TextView text_desc = view.findViewById(R.id.news_desc);

        imageView.setImageResource(R.drawable.ic_launcher_background);
        text_title.setText("dsfaf");
        text_desc.setText("dsfa lksdg;lsakj dfskgfsd;l skrejt;gr dgkjdf;lkjg dgjdff");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position == 0){
                    Toast.makeText(context, "Slide 1 Clicked", Toast.LENGTH_SHORT).show();
                } else if(position == 1){
                    Toast.makeText(context, "Slide 2 Clicked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Slide 3 Clicked", Toast.LENGTH_SHORT).show();
                }

            }
        });

        VerticalViewPager vp = (VerticalViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        VerticalViewPager vp = (VerticalViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}

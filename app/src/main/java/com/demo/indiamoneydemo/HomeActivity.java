package com.demo.indiamoneydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.demo.indiamoneydemo.adapter.TopNewsAdapter;
import com.demo.indiamoneydemo.bean.NewsItem;
import com.demo.indiamoneydemo.utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    String[] newsTitle = {"sdf", "sdfa", "asdfsd", "SDFasdf"};
    String[] newsDesc = {"fsdhfasldf adfsj", "jkhslgdfjh dfgsjkdl", "lkjfgsdf fdgkljs", "kl;jdfgj fdkjgl"};
    int[] newsImages = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    private ViewPager introPager;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private DrawerLayout drawer;
    private LinearLayout clinic_doc_layout, chat_doctor_layout, order_medicine_layout, book_test_layout, reminder_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        introPager = findViewById(R.id.home_banner_pager);
        sliderDotspanel = findViewById(R.id.home_banner_pager_indicator);

        clinic_doc_layout = findViewById(R.id.news_layout);
        chat_doctor_layout = findViewById(R.id.chat_doctor_layout);
        chat_doctor_layout.setOnClickListener(this);
        order_medicine_layout = findViewById(R.id.looking_for_layout);
        reminder_layout = findViewById(R.id.reminder_layout);
        order_medicine_layout.setOnClickListener(this);

        book_test_layout = findViewById(R.id.personal_doc_layout);
        book_test_layout.setOnClickListener(this);
        clinic_doc_layout.setOnClickListener(this);
        reminder_layout.setOnClickListener(this);

        Utilities.setPreference(HomeActivity.this, Utilities.USER_NAME, "User");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


// Custom hamburger icon
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        toggle.setHomeAsUpIndicator(R.drawable.ic_right_arrow);


        List<NewsItem> topNewsItems = new ArrayList<>();
        for (int i = 0; i < newsDesc.length; i++) {
            NewsItem item = new NewsItem();
            item.setNewsImage(newsImages[i]);
            item.setNewsTitle(newsTitle[i]);
            item.setNewsDescription(newsDesc[i]);
            topNewsItems.add(item);
        }

        TopNewsAdapter viewPagerAdapter = new TopNewsAdapter(HomeActivity.this, topNewsItems);
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());

        introPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        setUpSlider();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 4000, 5000);
    }

    private void setUpSlider() {


        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 8, 8, 8);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.nonactive_dot));

        introPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.nonactive_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_layout:
                startActivity(new Intent(HomeActivity.this, NewsActivity.class));
                break;

            case R.id.chat_doctor_layout:
                startActivity(new Intent(HomeActivity.this, Chat_Room.class));
                break;

            case R.id.looking_for_layout:
                startActivity(new Intent(HomeActivity.this, LookingForActivity.class));
                break;

            case R.id.personal_doc_layout:
//                startActivity(new Intent(HomeActivity.this, PersonalDocuments.class));
                break;
            case R.id.reminder_layout:
                startActivity(new Intent(HomeActivity.this, RemindersActivity.class));
                break;
        }
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            /*if (HomeActivity.this == null)
                return;*/
            runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    if (introPager.getCurrentItem() == 0) {
                        introPager.setCurrentItem(1);
                    } else if (introPager.getCurrentItem() == 1) {
                        introPager.setCurrentItem(2);
                    } else if (introPager.getCurrentItem() == 2) {
                        introPager.setCurrentItem(3);
                    } else {
                        introPager.setCurrentItem(0);
                    }

                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

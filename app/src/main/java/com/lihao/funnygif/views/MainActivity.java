package com.lihao.funnygif.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lihao.funnygif.MyApplication;
import com.lihao.funnygif.R;
import com.lihao.funnygif.utils.Constants;
import com.lihao.funnygif.utils.FileUtil;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private FrameLayout fl_content;
    private SwipeRefreshLayout sr;
    private Toolbar mToolbar;
    private String contentTag;
    private TextView showCache;
    private com.rey.material.widget.Button clearCache;
    private long firstTime = 0L;
    private LinearLayout module1;
    private LinearLayout module2;
    private LinearLayout module3;
    private LinearLayout module4;
    private MenuFragment mMenuFragment;
    private String cacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        loadDatas();
        refreshCache();
    }

    private void refreshCache() {
        try {
            cacheSize = FileUtil.getCacheSize(MyApplication.supFile);
            showCache.setText("缓存大小:" + cacheSize);
            Log.d("Lihao", cacheSize);
        } catch (Exception e) {
            cacheSize = "0.0 Mb";
            showCache.setText("缓存大小:" + cacheSize);
        }
    }

    private void loadDatas() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(Constants.BAOXIAO), "Baoxiao").commit();
        contentTag = "Baoxiao";
    }

    private void initViews() {
        // TODO Auto-generated method stub
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        sr = (SwipeRefreshLayout) findViewById(R.id.sr);
        module1 = (LinearLayout) findViewById(R.id.module1);
        showCache = (TextView) findViewById(R.id.cache_text);
        clearCache = (com.rey.material.widget.Button) findViewById(R.id.cache_clean);
        module2 = (LinearLayout) findViewById(R.id.module2);
        module3 = (LinearLayout) findViewById(R.id.module3);
        module4 = (LinearLayout) findViewById(R.id.module4);
        setOnclickListener();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("搞笑GIF");
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
        sr = (SwipeRefreshLayout) findViewById(R.id.sr);
        sr.setColorSchemeResources(R.color.colorToolbar, R.color.colorPrimary, R.color.colorAccent);
        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFragment();
                sr.setRefreshing(false);
            }
        });
        sr.setEnabled(true);
        //mDrawerLayout.openDrawer(Gravity.LEFT);
        //mDrawerLayout.closeDrawer(Gravity.LEFT);
        clearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Lihao", "正在清除缓存");
                FileUtil.cleanInternalCache(MainActivity.this);
                refreshCache();
            }
        });
    }

    private void setOnclickListener() {
        module1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(Constants.BAOXIAO), "Baoxiao").commit();
                contentTag = "Baoxiao";
                MainFragment.page = 1;
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mToolbar.setTitle("搞笑GIF");
                refreshCache();
            }
        });
        module2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(Constants.DIAOBAO), "Diaobao").commit();
                contentTag = "Diaobao";
                MainFragment.page = 1;
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mToolbar.setTitle("吓你一跳");
                refreshCache();
            }
        });
        module3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(Constants.BEIJU), "Beiju").commit();
                contentTag = "Beiju";
                MainFragment.page = 1;
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mToolbar.setTitle("让你作死");
                refreshCache();
            }
        });
        module4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(Constants.ZHANGZHISHI), "Zhangzishi").commit();
                contentTag = "Zhangzishi";
                MainFragment.page = 1;
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mToolbar.setTitle("涨姿势");
                refreshCache();
            }
        });
    }

    private void refreshFragment() {
        if (contentTag.equals("Baoxiao")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(Constants.BAOXIAO), "Baoxiao").commit();
        } else if (contentTag.equals("Diaobao")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(Constants.DIAOBAO), "Diaobao").commit();
        } else if (contentTag.equals("Beiju")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(Constants.BEIJU), "Beiju").commit();
        } else if (contentTag.equals("Zhangzishi")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(Constants.ZHANGZHISHI), "Zhangzishi").commit();
        }
        refreshCache();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                //Toast.makeText(this, "再按一次退出程序!", Toast.LENGTH_SHORT).show();
                Snackbar snackbar = Snackbar.make(fl_content, "                        再按一次退出程序!", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorToolbar));
                snackbar.show();
                firstTime = secondTime;
            } else {
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        switch (itemID) {
            case R.id.action_lihao:
                refreshCache();
                //Toast.makeText(this, "作者:李昊", Toast.LENGTH_SHORT).show();
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setTitle("作者:李昊");
                builder.setMessage(
                        "QQ:591353643\n" +
                                "邮箱:lihao19910510@sina.com\n" +
                                "本App所使用的所有GIF来源于互联网\n" +
                                "若被告知需停止共享与使用\n" +
                                "本人会及时删除页面与整个项目");
                builder.setPositiveButton("确定", null);
                builder.show();
                break;
            case R.id.action_settings:
                refreshCache();
                //Toast.makeText(this, "暂无设置", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

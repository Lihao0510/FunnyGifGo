package com.lihao.funnygif.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.lihao.funnygif.R;
import com.lihao.funnygif.adapters.MainAdapter;
import com.lihao.funnygif.modle.GifBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private FrameLayout fl_content;
    private SwipeRefreshLayout sr;
    private Toolbar mToolbar;
    private String contentTag;
    private long firstTime = 0L;
    private MenuFragment mMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        loadDatas();
    }

    private void loadDatas() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(), "Gif").commit();
        contentTag = "Gif";
    }

    private void initViews() {
        // TODO Auto-generated method stub
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        sr = (SwipeRefreshLayout) findViewById(R.id.sr);
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
    }

    private void refreshFragment() {
        if (contentTag.equals("Gif")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainFragment(), "Gif").commit();
        }
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
                Toast.makeText(this, "作者:李昊", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "暂无设置", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

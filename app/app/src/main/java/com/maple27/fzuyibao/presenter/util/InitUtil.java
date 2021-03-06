package com.maple27.fzuyibao.presenter.util;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.maple27.fzuyibao.R;
import com.maple27.fzuyibao.presenter.adapter.MainAdapter;
import com.maple27.fzuyibao.view.fragment.HomeFragment;
import com.maple27.fzuyibao.view.fragment.MessageFragment;
import com.maple27.fzuyibao.view.fragment.PersonalFragment;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maple27 on 2017/11/1.
 */


public class InitUtil {

    private static final String[] TAB_NAME = {"主页","消息","个人"};
    private static final int[] TAB_ICON_1 = {R.drawable.home1,R.drawable.message1,R.drawable.personal1};
    private static final int[] TAB_ICON_2 = {R.drawable.home2,R.drawable.message2,R.drawable.personal2};

    //初始化主Activity
    public static void initMainActivity(ViewPager viewPager, final TabLayout tabLayout,
                                        MainAdapter adapter, final List<Fragment> list){
        ////初始化主页视图
        HomeFragment fragment1 = new HomeFragment();
        MessageFragment fragment2 = new MessageFragment();
        PersonalFragment fragment3 = new PersonalFragment();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        for(int i=0;i<list.size();i++){
            tabLayout.getTabAt(i).setText(TAB_NAME[i]);
            tabLayout.getTabAt(i).setIcon(TAB_ICON_1[i]);
        }

        //监听处理
        tabLayout.getTabAt(0).setIcon(TAB_ICON_2[0]);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(TAB_ICON_2[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(TAB_ICON_1[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //初始化HomeFragment
    public static void initHomeFragment(final Context context, final SmartRefreshLayout refresh, FloatingSearchView searchView,
                                        Banner banner, ImageView l_m, ImageView library, ImageView market, ImageView seek){

        //Refresh控件
        WaveSwipeHeader waveSwipeHeader = new WaveSwipeHeader(context);
        refresh.setRefreshHeader(waveSwipeHeader);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                //刷新请求
            }
        });

        //SearchBar控件
        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                Toast.makeText(context, currentQuery, Toast.LENGTH_SHORT).show();
                //搜索请求
            }
        });

        //Banner控件
        banner.setImageLoader(new GlideImageLoader());
        List<String> list = new ArrayList<>();
        //轮播图测试
        list.add("https://interface.fty-web.com/public/avatars/0315022121510315700.jpg");
        list.add("https://interface.fty-web.com/public/avatars/ajmd.jpg");
        banner.setImages(list);
        banner.start();
        //跳转控件
        //class A extends View.OnClickListener

    }



}

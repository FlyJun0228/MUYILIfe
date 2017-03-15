package com.example.administrator.muyilife;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.administrator.muyilife.Fragment.First;
import com.example.administrator.muyilife.Fragment.Fourth;
import com.example.administrator.muyilife.Fragment.Second;
import com.example.administrator.muyilife.Fragment.Third;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {First.class,Second.class,Third.class,Fourth.class};

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.housee,R.drawable.starr,R.drawable.ringg,
            R.drawable.vipp};
    private int mImageView[] = {R.drawable.btn_sure,R.drawable.btn_sure2,R.drawable.btn_sure3,
            R.drawable.btn_sure4};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "木易精品", "最新动态", "我的vip"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //TabHost tabHost = (TabHost)findViewById(R.id.tabhost);//获取tabhost组件
        /*tabHost.setup();
        //动态载入xml
        LayoutInflater.from(this).inflate(R.layout.activity_first,tabHost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.activity_second,tabHost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.activity_third,tabHost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.activity_fourth,tabHost.getTabContentView());
        //创建tab页
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("标签页1").setContent(R.id.first));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("标签页2").setContent(R.id.second));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("标签页3").setContent(R.id.third));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("标签页4").setContent(R.id.fourth));*/
    }
    public void initView(){
         layoutInflater = LayoutInflater.from(this);//实例化布局对象
        //实例化TanHost对象，得到Tabhost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        //得到fragment的个数
        int count = fragmentArray.length;

        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){

                @Override
                public void onTabChanged(String tabId){

                    if (mTabHost.getCurrentTabTag()==tabId){
                    for (int l = 0;l<4;l++){
                            ((TextView)mTabHost.getTabWidget().getChildTabViewAt(l).findViewById(R.id.text)).setTextColor(getResources().getColor(R.color.colorBlack));
                        ((ImageView)mTabHost.getTabWidget().getChildTabViewAt(l).findViewById(R.id.image)).setImageResource(mImageView[l]);
                    }
                        ((TextView)mTabHost.getCurrentTabView().findViewById(R.id.text)).setTextColor(getResources().getColor(R.color.colorSelect));

                    }
                }
            });
            ((TextView)mTabHost.getCurrentTabView().findViewById(R.id.text)).setTextColor(getResources().getColor(R.color.colorSelect));
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(mTextviewArray[index]);

        return view;
    }
}


package com.wan91.simo.lib.user;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wan91.simo.lib.bean.FunctionBean;
import com.wan91.simo.lib.listener.OnMultiClickListener;
import com.wan91.simo.lib.utils.LayoutUtils;

import java.util.ArrayList;

public class UserPopActivity extends Activity {

    private View view_null;
    private View viewById;
    private ImageView hread;
    private RecyclerView recyclerView;
    public ArrayList<FunctionBean> list = new ArrayList<FunctionBean>();
    private TextView wan91_funtion_tv_name;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏(主要功能就是去除页面弹出时顶部黑色条)
        super.onCreate(savedInstanceState);
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        setContentView(LayoutUtils.getLayout(this, "wan91t_main_layout"));
        recyclerView = (RecyclerView) findViewById(LayoutUtils.getIdByName(this, "id", "recycler_view"));
        view_null = findViewById(LayoutUtils.getIdByName(this, "id", "view_wan91_function_null"));
        wan91_funtion_tv_name = findViewById(LayoutUtils.getIdByName(this, "id", "wan91_funtion_tv_name"));
        this.viewById = findViewById(LayoutUtils.getIdByName(this, "id", "lay_con"));
        hread = findViewById(LayoutUtils.getIdByName(this, "id", "wan91_funtion_hread_img"));
//        hread.isCircle(true);
//        String head_img = PersonalCenterModel.getInstance().channelAndGame.getHead_img();
//        if (!TextUtils.isEmpty(head_img)) {
//            BitmapUtils bitmapUtils = new BitmapUtils(this);
//            bitmapUtils.display(hread, head_img);
//        } else {
//            if (PersonalCenterModel.getInstance().channelAndGame.getSex() == 0) {
//                hread.setImageDrawable(getResources().getDrawable(LayoutUtils.getDrawable(MCHFunctionPopActivity.this, "wan91_nav_pic_touxiang")));
//            } else {
//                hread.setImageDrawable(getResources().getDrawable(LayoutUtils.getDrawable(MCHFunctionPopActivity.this, "wan91_nav_pic_touxiang_women")));
//            }
//        }
        view_null.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
                finish();
            }
        });
        initData();
//        wan91_funtion_tv_name.setText(PersonalCenterModel.getInstance().channelAndGame.getAccount());
        ItemAdapter adapter = new ItemAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        list.clear();
        FunctionBean wode = new FunctionBean();
        wode.icon = "wan91_tab_icon_wo_n";
        wode.name = "我的";
        list.add(wode);

        FunctionBean libao = new FunctionBean();
        libao.icon = "wan91_tab_icon_wo_n";
        libao.name = "礼包";
        list.add(libao);

//        if (Constant.wan91_BACKGROUND_VERSION < Constant.VERSION_920) {
//            FunctionBean zhekou = new FunctionBean();
//            zhekou.icon = "wan91_tab_icon_wo_n";
//            zhekou.name = "折扣";
//            list.add(zhekou);
//        }

        FunctionBean zhangdan = new FunctionBean();
        zhangdan.icon = "wan91_tab_icon_wo_n";
        zhangdan.name = "游戏账单";
        list.add(zhangdan);

        FunctionBean kefu = new FunctionBean();
        kefu.icon = "wan91_tab_icon_wo_n";
        kefu.name = "客服";
        list.add(kefu);

//        if (SwitchManager.getInstance().changeAccount() && !ServiceManager.getInstance().isBaiDuYunOS) {
//            FunctionBean qiehuan = new FunctionBean();
//            qiehuan.icon = "wan91_tab_icon_zhuxiao_n";
//            qiehuan.name = "切换账号";
//            list.add(qiehuan);
//        }
    }

}

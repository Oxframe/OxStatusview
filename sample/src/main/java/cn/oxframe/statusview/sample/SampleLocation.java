package cn.oxframe.statusview.sample;

import android.app.Application;

import cn.oxframe.statusview.OxStatusManager;

/**
 * cn.oxframe.statusview.sample
 * Created by WangChangYun on 2019/12/25 11:30
 * slight negligence may lead to great disaster~
 */
public class SampleLocation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OxStatusManager.onCreateStatusView(R.layout.status_error,R.layout.status_blank,R.layout.status_loading);
    }
}

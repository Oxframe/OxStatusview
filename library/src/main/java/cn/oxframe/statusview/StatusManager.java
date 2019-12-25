package cn.oxframe.statusview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


public class StatusManager {

    private StatusView mSstatusView;

    public static int LAYOUT_ERROR_ID;
    public static int LAYOUT_BLANK_ID;
    public static int LAYOUT_LOADING_ID;

    public static void initStatusView(int layoutErrorResID, int layoutBlankResID, int layoutLoadingResID) {
        LAYOUT_ERROR_ID = layoutErrorResID;
        LAYOUT_BLANK_ID = layoutBlankResID;
        LAYOUT_LOADING_ID = layoutLoadingResID;
    }

    public static StatusManager onCreate(View view) {
        return new StatusManager(view);
    }

    private StatusManager(View iView) {
        Context context = iView.getContext();
        ViewGroup iParentView = (ViewGroup) iView.getParent();

        // 将原控件从父控件中删除
        iParentView.removeView(iView);
        // 创建一个新的状态控件，控件使用属性是原控件的属性
        StatusView iStatusView = new StatusView(context);
        int index = iParentView.indexOfChild(iView);
        ViewGroup.LayoutParams iParams = iView.getLayoutParams();
        iParentView.addView(iStatusView, index, iParams);
        // 将原控件作为内容页面添加到新的状态控件页面中

        iStatusView.setContentView(iView);
        iStatusView.setErrorView(LAYOUT_ERROR_ID);
        iStatusView.setBlankView(LAYOUT_BLANK_ID);
        iStatusView.setLoadingView(LAYOUT_LOADING_ID);

        mSstatusView = iStatusView;
        //初始状态:loading进去
        mSstatusView.showLoading();
    }


    public void showError() {
        mSstatusView.showError();
    }

    public void showBlank() {
        mSstatusView.showBlank();
    }

    public void showContent() {
        mSstatusView.showContent();
    }

    public void showLoading() {
        mSstatusView.showLoading();
    }


}

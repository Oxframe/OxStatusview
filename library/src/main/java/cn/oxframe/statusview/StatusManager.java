package cn.oxframe.statusview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

class StatusManager {

    private StatusView mSstatusView;

    public static int LAYOUT_ERROR_ID;
    public static int LAYOUT_BLANK_ID;
    public static int LAYOUT_LOADING_ID;

    public static void initStatusView(int layoutErrorResID, int layoutBlankResID, int layoutLoadingResID) {
        LAYOUT_ERROR_ID = layoutErrorResID;
        LAYOUT_BLANK_ID = layoutBlankResID;
        LAYOUT_LOADING_ID = layoutLoadingResID;
    }

    public static StatusManager onCreate(View iView, OxStatusListener iListener) {
        StatusManager manager = new StatusManager();

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

        return manager.initListener(iStatusView, iListener);
    }

    private StatusManager initListener(StatusView iStatusView, final OxStatusListener iListener) {
        View mErrorView = iStatusView.setErrorView(LAYOUT_ERROR_ID);
        View mErrorBtn = mErrorView.findViewById(R.id.status_error_button);
        if (null != mErrorBtn) {
            mErrorBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iListener.onErrorClick(v);
                }
            });
        }

        View mBlankView = iStatusView.setBlankView(LAYOUT_BLANK_ID);
        View mBlankBtn = mBlankView.findViewById(R.id.status_blank_button);
        if (null != mBlankBtn) {
            mBlankBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iListener.onBlankClick(v);
                }
            });
        }

        iStatusView.setLoadingView(LAYOUT_LOADING_ID);

        mSstatusView = iStatusView;
        return this;
    }

    public View iStatusView() {
        return mSstatusView;
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

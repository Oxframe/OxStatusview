package cn.oxframe.statusview;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * cn.oxframe.statusview
 * Created by WangChangYun on 2019/12/25 10:30
 * slight negligence may lead to great disaster~
 */
public class StatusView extends FrameLayout {

    private View mBlankView; // 空数据
    private View mErrorView; // 各种错误，重新请求
    private View mContentView;// 请求完成，数据展示
    private View mLoadingView;// 正在请求...
    private LayoutInflater mInflater;

    private static final String TAG = StatusView.class.getSimpleName();

    public StatusView(Context context) {
        this(context, null);
    }

    public StatusView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
    }

    private boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 重试
     */
    public void showError() {
        if (isMainThread()) {
            showView(mErrorView);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showView(mErrorView);
                }
            });
        }
    }

    /**
     * 空页面
     */
    public void showBlank() {
        if (isMainThread()) {
            showView(mBlankView);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showView(mBlankView);
                }
            });
        }
    }

    /**
     * 内容页面
     */
    public void showContent() {
        if (isMainThread()) {
            showView(mContentView);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showView(mContentView);
                }
            });
        }
    }

    /**
     * 加载页面
     */
    public void showLoading() {
        if (isMainThread()) {
            showView(mLoadingView);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showView(mLoadingView);
                }
            });
        }
    }

    /**
     * 展示页面
     */
    private void showView(View view) {
        if (null == view) return;
        if (view == mErrorView) {// 重试页面
            mErrorView.setVisibility(VISIBLE);
            if (mBlankView != null) mBlankView.setVisibility(GONE);
            if (mContentView != null) mContentView.setVisibility(GONE);
            if (mLoadingView != null) mLoadingView.setVisibility(GONE);
        } else if (view == mBlankView) {// 空白页面
            mBlankView.setVisibility(VISIBLE);
            if (mErrorView != null) mErrorView.setVisibility(GONE);
            if (mContentView != null) mContentView.setVisibility(GONE);
            if (mLoadingView != null) mLoadingView.setVisibility(GONE);
        } else if (view == mContentView) {// 内容页面
            mContentView.setVisibility(VISIBLE);
            if (mErrorView != null) mErrorView.setVisibility(GONE);
            if (mBlankView != null) mBlankView.setVisibility(GONE);
            if (mLoadingView != null) mLoadingView.setVisibility(GONE);
        } else if (view == mLoadingView) {// 加载页面
            mLoadingView.setVisibility(VISIBLE);
            if (mErrorView != null) mErrorView.setVisibility(GONE);
            if (mBlankView != null) mBlankView.setVisibility(GONE);
            if (mContentView != null) mContentView.setVisibility(GONE);
        }
    }

    public View setErrorView(int layoutId) {
        return setErrorView(mInflater.inflate(layoutId, this, false));
    }

    public View setErrorView(View view) {
        View retryView = mErrorView;
        if (retryView != null) {
            Log.w(TAG, "you have already set a retry view and would be instead of this new one.");
        }
        removeView(retryView);
        addView(view);
        mErrorView = view;
        return mErrorView;
    }

    public View setBlankView(int layoutId) {
        return setBlankView(mInflater.inflate(layoutId, this, false));
    }

    public View setBlankView(View view) {
        View emptyView = mBlankView;
        if (emptyView != null) {
            Log.w(TAG, "you have already set a empty view and would be instead of this new one.");
        }
        removeView(emptyView);
        addView(view);
        mBlankView = view;
        return mBlankView;
    }

    public View setContentView(View view) {
        View contentView = mContentView;
        if (contentView != null) {
            Log.w(TAG, "you have already set a retry view and would be instead of this new one.");
        }
        removeView(contentView);
        addView(view);
        mContentView = view;
        return mContentView;
    }

    public View setLoadingView(int layoutId) {
        return setLoadingView(mInflater.inflate(layoutId, this, false));
    }

    public View setLoadingView(View view) {
        View loadingView = mLoadingView;
        if (loadingView != null) {
            Log.w(TAG, "you have already set a loading view and would be instead of this new one.");
        }
        removeView(loadingView);
        addView(view);
        mLoadingView = view;
        return mLoadingView;
    }

    public View getErrorView() {
        return mErrorView;
    }

    public View getBlankView() {
        return mBlankView;
    }

    public View getContentView() {
        return mContentView;
    }

    public View getLoadingView() {
        return mLoadingView;
    }

}

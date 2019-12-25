package cn.oxframe.statusview;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


public class StatusManager {

    public static final int NO_LAYOUT_ID = 0;

    public static int LAYOUT_ERROR_ID; //= R.layout.layout_status_error
    public static int LAYOUT_BLANK_ID; //= R.layout.layout_status_blank
    public static int LAYOUT_LOADING_ID; //= R.layout.layout_status_loading

    public StatusView mPageLayout;

    public static void initInApp(int layoutIdOfError, int layoutIdOfBlank, int layoutIdOfLoading) {
        if (layoutIdOfError > 0) LAYOUT_ERROR_ID = layoutIdOfError;
        if (layoutIdOfBlank > 0) LAYOUT_BLANK_ID = layoutIdOfBlank;
        if (layoutIdOfLoading > 0) LAYOUT_LOADING_ID = layoutIdOfLoading;
    }

    public void showError() {
        mPageLayout.showError();
    }

    public void showBlank() {
        mPageLayout.showBlank();
    }

    public void showContent() {
        mPageLayout.showContent();
    }

    public void showLoading() {
        mPageLayout.showLoading();
    }

    public StatusListener DEFAULT_LISTENER = new StatusListener() {
        @Override
        public void onError(View errorView) {

        }
    };

    private StatusManager(Object activityOrView, boolean showLoadingFirstIn, StatusListener listener) {
        if (null == listener) listener = DEFAULT_LISTENER;
        ViewGroup contentParent = null;
        Context context;
        if (activityOrView instanceof Activity) {
            Activity activity = (Activity) activityOrView;
            context = activity;
            contentParent = activity.findViewById(android.R.id.content);
        } else if (activityOrView instanceof Fragment) {
            Fragment fragment = (Fragment) activityOrView;
            context = fragment.getActivity();
            contentParent = (ViewGroup) (fragment.getView().getParent());
            if (contentParent == null) {
                throw new IllegalArgumentException("the fragment must already has a parent ,please do not invoke this in oncreateView,you should use this method in onActivityCreated() or onstart");
            }
        } else if (activityOrView instanceof View) {
            View view = (View) activityOrView;
            contentParent = (ViewGroup) (view.getParent());
            if (contentParent == null) {
                throw new IllegalArgumentException("the view must already has a parent ");
            }
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("the container's type must be Fragment or Activity or a view ");
        }

        int childCount = contentParent.getChildCount();
        int index = 0;
        View oldContent;
        if (activityOrView instanceof View) {
            oldContent = (View) activityOrView;
            for (int i = 0; i < childCount; i++) {
                if (contentParent.getChildAt(i) == oldContent) {
                    index = i;
                    break;
                }
            }
        } else {
            oldContent = contentParent.getChildAt(0);
        }
        contentParent.removeView(oldContent);
        StatusView pageLayout = new StatusView(context);
        ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
        contentParent.addView(pageLayout, index, lp);

        pageLayout.setContentView(oldContent);
        // setup loading,retry,empty layout
        setupAgainLayout(listener, pageLayout);
        setupBlankLayout(listener, pageLayout);
        setupLoadingLayout(listener, pageLayout);

        final StatusListener finalListener = listener;
        View iErrorView = pageLayout.getErrorView();
//        TextView iErrorBtn = iErrorView.findViewById(R.id.error_button);
//        iErrorBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finalListener.onError(v);
//            }
//        });

        View iBlankView = pageLayout.getBlankView();
//        TextView iBlankBtn = iBlankView.findViewById(R.id.blank_button);
//        iBlankBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finalListener.onBlankViewClicked(v);
//            }
//        });
        mPageLayout = pageLayout;
        //初始状态:loading进去
        if (showLoadingFirstIn) {
            mPageLayout.showLoading();
        } else {
            mPageLayout.showContent();
        }
    }

    private void setupAgainLayout(StatusListener iListener, StatusView iPageLayout) {
        if (iListener.isSetAgainLayout()) {
            int layoutId = iListener.generateErrorLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                iPageLayout.setErrorView(layoutId);
            } else {
                iPageLayout.setErrorView(iListener.generateErrorLayout());
            }
        } else {
            if (LAYOUT_ERROR_ID != NO_LAYOUT_ID) iPageLayout.setErrorView(LAYOUT_ERROR_ID);
        }
    }

    private void setupBlankLayout(StatusListener iListener, StatusView iPageLayout) {
        if (iListener.isSetBlankLayout()) {
            int layoutId = iListener.generateBlankLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                iPageLayout.setBlankView(layoutId);
            } else {
                iPageLayout.setBlankView(iListener.generateBlankLayout());
            }
        } else {
            if (LAYOUT_BLANK_ID != NO_LAYOUT_ID) iPageLayout.setBlankView(LAYOUT_BLANK_ID);
        }
    }

    private void setupLoadingLayout(StatusListener iListener, StatusView iPageLayout) {
        if (iListener.isSetLoadingLayout()) {
            int layoutId = iListener.generateLoadingLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                iPageLayout.setLoadingView(layoutId);
            } else {
                iPageLayout.setLoadingView(iListener.generateLoadingLayout());
            }
        } else {
            if (LAYOUT_LOADING_ID != NO_LAYOUT_ID) iPageLayout.setLoadingView(LAYOUT_LOADING_ID);
        }
    }

    public static StatusManager generate(Object activityOrView, boolean showLoadingFirstIn, StatusListener listener) {
        return new StatusManager(activityOrView, showLoadingFirstIn, listener);
    }


}

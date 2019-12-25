package cn.oxframe.statusview;

import android.view.View;

public class OxStatusManager {

    private StatusManager mStatusManager;

    public static void onCreateStatusView(int layoutErrorResID, int layoutBlankResID, int layoutLoadingResID) {
        StatusManager.initStatusView(layoutErrorResID, layoutBlankResID, layoutLoadingResID);
    }

    public static OxStatusManager init(View view) {
        StatusManager iManager = StatusManager.onCreate(view);
        OxStatusManager iStatusManager = new OxStatusManager();
        iStatusManager.mStatusManager = iManager;
        return iStatusManager;
    }

    public void showLoading() {
        if (mStatusManager != null) {
            mStatusManager.showLoading();
        }
    }

    public void showContent() {
        if (mStatusManager != null) {
            mStatusManager.showContent();
        }
    }

    public void showError() {
        if (mStatusManager != null) {
            mStatusManager.showError();
        }
    }

    public void showBlank() {
        if (mStatusManager != null) {
            mStatusManager.showBlank();
        }
    }

}

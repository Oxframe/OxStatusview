package cn.oxframe.statusview;

import android.view.View;
import android.widget.TextView;

public class OxStatusManager {

    private StatusManager mStatusManager;

    public static void onCreateStatusView(int layoutErrorResID, int layoutBlankResID, int layoutLoadingResID) {
        StatusManager.initStatusView(layoutErrorResID, layoutBlankResID, layoutLoadingResID);
    }

    public static OxStatusManager init(View view, OxStatusListener listener) {
        StatusManager iManager = StatusManager.onCreate(view, listener);
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

    public void showError(String message, String button) {
        View view = mStatusManager.iStatusView();
        TextView textView = view.findViewById(R.id.status_error_message);
        if (null != textView) textView.setText(message);
        View btnView = view.findViewById(R.id.status_error_button);
        if (btnView instanceof TextView) {
            ((TextView) btnView).setText(button);
        }
        mStatusManager.showError();
    }

    public void showBlank(String message, String button) {
        View view = mStatusManager.iStatusView();
        TextView textView = view.findViewById(R.id.status_blank_message);
        if (null != textView) textView.setText(message);
        View btnView = view.findViewById(R.id.status_blank_button);
        if (btnView instanceof TextView) {
            ((TextView) btnView).setText(button);
        }
        mStatusManager.showBlank();
    }

}

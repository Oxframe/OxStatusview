package cn.oxframe.statusview;

import android.view.View;

public abstract class StatusListener {

    public abstract void onError(View errorView);

    public void onBlankViewClicked(View blankView) {
    }

    public View generateErrorLayout() {
        return null;
    }

    public int generateErrorLayoutId() {
        return StatusManager.NO_LAYOUT_ID;
    }

    public boolean isSetAgainLayout() {
        return (generateErrorLayoutId() != StatusManager.NO_LAYOUT_ID || generateErrorLayout() != null);
    }

    public View generateBlankLayout() {
        return null;
    }

    public int generateBlankLayoutId() {
        return StatusManager.NO_LAYOUT_ID;
    }

    public boolean isSetBlankLayout() {
        return (generateBlankLayoutId() != StatusManager.NO_LAYOUT_ID || generateBlankLayout() != null);
    }


    public View generateLoadingLayout() {
        return null;
    }

    public int generateLoadingLayoutId() {
        return StatusManager.NO_LAYOUT_ID;
    }

    public boolean isSetLoadingLayout() {
        return (generateLoadingLayoutId() != StatusManager.NO_LAYOUT_ID || generateLoadingLayout() != null);
    }

}

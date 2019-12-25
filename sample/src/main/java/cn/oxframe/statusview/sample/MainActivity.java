package cn.oxframe.statusview.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cn.oxframe.statusview.StatusView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusView statusView = new StatusView(getApplicationContext());
        statusView.setBlankView(R.layout.status_blank);
        statusView.showBlank();
    }
}

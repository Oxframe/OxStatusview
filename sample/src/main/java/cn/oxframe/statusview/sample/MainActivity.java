package cn.oxframe.statusview.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.oxframe.statusview.OxStatusListener;
import cn.oxframe.statusview.OxStatusManager;

public class MainActivity extends AppCompatActivity {

    OxStatusManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = OxStatusManager.init(findViewById(R.id.status_box), new OxStatusListener() {
            @Override
            public void onErrorClick(View view) {
                Toast.makeText(getApplicationContext(), "错误页面，点也没有用", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBlankClick(View view) {
                doRequest(true);
            }
        });

        manager.showLoading();

        findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRequest(false);
            }
        });
        findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.showError("当前页面加载出错", "点击按钮重新加载");
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    manager.showError();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void doRequest(final boolean show) {
        manager.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    if (show) {
                        manager.showContent();
                    } else {
                        manager.showBlank();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

package com.charlie.jobfinder;

import android.app.Activity;
import android.os.Bundle;
import com.charlie.jobfinder.R;
import com.umeng.analytics.MobclickAgent;

/**
 * Created
 * Author:Charlie Wei[]
 * Email:charlie_net@163.com
 * Date:2015/10/17
 */
public class AboutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("About");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("About");
        MobclickAgent.onPause(this);
    }
}
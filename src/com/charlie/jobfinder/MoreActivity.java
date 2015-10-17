package com.charlie.jobfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.charlie.jobfinder.R;
import com.umeng.analytics.MobclickAgent;

/**
 * Created
 * Author:Charlie Wei[]
 * Email:charlie_net@163.com
 * Date:2015/10/17
 */
public class MoreActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("More");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("Main");
        MobclickAgent.onPause(this);
    }

    public void btnJumpAbout(View view) {
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }
}
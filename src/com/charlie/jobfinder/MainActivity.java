package com.charlie.jobfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import com.umeng.analytics.MobclickAgent;
import utils.MyLog;

import java.util.HashMap;

public class MainActivity extends Activity implements PlatformActionListener {


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // ShareSDK必须要进行初始化的操作
        ShareSDK.initSDK(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        MobclickAgent.onPageStart("Main");

        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();

        MobclickAgent.onPageEnd("Main");
        MobclickAgent.onPause(this);
    }

    public void showShare(View v){
         show_Share();
    }


    private void show_Share() {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本                  -- 测试专用小尾巴。");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImageUrl("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本                  -- 测试专用小尾巴。");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 设置是否显示内容编辑框(是否允许用户编辑)
        oks.setSilent(false);

        // 设置分享接口回调
        oks.setCallback(this);

        // 设置只使用一种平台进行分享的操作
        oks.setPlatform(SinaWeibo.NAME);

        // 启动分享GUI
        oks.show(this);
    }

    // ---------------------------------------------------
    // 分享回调接口方法

    /**
     * 执行成功
     *  i 这个参数，使用Platform.Action_XXX来判断
     *
     * @param platform
     * @param i：   这个整数代表当前回调的操作，是分享还是获取用户信息
     * @param hashMap
     */
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        // TODO 处理成功的情况
        String pName = platform.getName();


        if (i == Platform.ACTION_SHARE) {
            Toast.makeText(this, " 分享成功 到" + pName, Toast.LENGTH_LONG).show();
        } else if (i == Platform.ACTION_USER_INFOR) {

            // 获取当前授权用户的数据
            PlatformDb db = platform.getDb();
            String userName = db.getUserName();
            String userId = db.getUserId();
            String userIcon = db.getUserIcon();
            MyLog.d("Share", "userName----" + userName);
            MyLog.d("Share", "userId-----" + userId);
            MyLog.d("Share", "userIcon----" + userIcon);
        }else if(i == Platform.ACTION_AUTHORIZING){
            // 获取用户ID方式的第三方登录

            PlatformDb db = platform.getDb();
            // 用户Id，作为服务器中的用户
            db.getUserId();

        }
    }

    /**
     * 分享失败
     * @param platform
     * @param i
     * @param throwable
     */
    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    /**
     * 取消分享
     * @param platform
     * @param i
     */
    @Override
    public void onCancel(Platform platform, int i) {

    }

    /**
     * 获取用户资料
     * @param view
     */
    public void btnGetUserInfo(View view) {

        // 1 获取指定的平台，通过字符串获取
        Platform platform = ShareSDK.getPlatform(this,SinaWeibo.NAME);

        // 2 设置当获取用户信息完成时的回调接口
        platform.setPlatformActionListener(this);

        // 获取指定平台下指定帐号的信息
        // Account为null时，代表当前授权的用户信息
        platform.showUser(null);
    }

    /**
     *  获取ID方式的第三方登录，服务器没有用户系统
     * @param view
     */
    public void btnAuth(View view) {
        //
        Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);

        platform.setPlatformActionListener(this);

        // 进行用户的授权，授权时，需要获取用户ID
        platform.authorize();
    }

    public void brnJumpMore(View view) {
        Intent intent = new Intent(this,MoreActivity.class);
        startActivity(intent);

    }
}
